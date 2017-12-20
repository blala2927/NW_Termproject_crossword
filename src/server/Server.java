package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mysql.jdbc.PreparedStatement;
import info.*;

public class Server {
	public final static int PORT = 9000;
	private static DB db;
	private static HashMap<String, User> userList = new HashMap<String, User>();
	private static HashMap<PrintWriter, ObjectOutputStream> writers = new HashMap<PrintWriter, ObjectOutputStream>(); 
	private static List<Room> roomList = new ArrayList<Room>();
	private static AtomicInteger atomicInteger = new AtomicInteger();

	public static void main(String[] args) throws Exception {
		Server server = new Server();
		ServerSocket listener = new ServerSocket(PORT);
		server.db = DB.getInstance();
		System.out.println("Server Start.");

		try {
			while(true) {
				new Handler(listener.accept()).start();
			}
		}
		finally {
			listener.close();
			server.db.rs.close();
			server.db.stmt.close();
			server.db.con.close();
			System.out.println("Server Close.");
		}
	}

	private static class Handler extends Thread{
		private Socket socket;
		private BufferedReader in;
		private PrintWriter out;
		private ObjectOutputStream oout;
		private String userID;
		private Room gameRoom;

		public Handler(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {
				JSONParser parser = new JSONParser();
				JSONObject inJSON;
				JSONObject outJSON;
				Room room;
				User user;
				int stringIndex;
				int index;

				OutputStream outputStream = socket.getOutputStream();
				oout = new ObjectOutputStream(outputStream);

				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(outputStream, true);

				while(true) {
					outJSON = new JSONObject();
					outJSON.put("type", "LOGIN");
					outJSON.put("state", "WAIT");
					out.println(outJSON.toString());

					String input = in.readLine();

					inJSON = (JSONObject) parser.parse(input);

					userID = inJSON.get("userID").toString();
					String pw = inJSON.get("pw").toString();

					if(userID.isEmpty() || pw.isEmpty())
						continue;

					User newUser = login(userID, pw);
					HashMap<Integer, String> roomListForFirst = new HashMap<Integer, String>();
					
					if(!roomList.isEmpty()) {
						for(Room r : roomList) {
							roomListForFirst.put(r.getRoomNum(), r.getRoomName());
						}
					}

					if(newUser != null) {
						synchronized(userList) {
							if(!userList.containsKey(userID)) {
								newUser.setWriter(out);
								newUser.setOout(oout);
								userList.put(userID, newUser);
								writers.put(out, oout);
								outJSON.put("type", "LOGIN");
								outJSON.put("state", "SUCCESS");
								outJSON.put("userName", newUser.getName());
								out.println(outJSON);
								oout.reset();
								oout.writeObject(roomListForFirst);
								break;
							}
						}
					}
					else {
						outJSON.put("type", "LOGIN");
						outJSON.put("state", "FAIL");
						out.println(outJSON.toString());
					}
				}

				while(true) {
					inJSON = new JSONObject();
					String input = in.readLine();
					stringIndex = input.indexOf("{");

					inJSON = (JSONObject) parser.parse(input.substring(stringIndex));

					if(inJSON.isEmpty())
						continue;

					room = null;
					user = null;
					index = -1;
					
					String type = inJSON.get("type").toString();
					
					switch(type) {
					case "CREATEROOM" :
						gameRoom = createRoom(inJSON);
						if(gameRoom != null) {
							User u = userList.get(userID);
							u.setNowLocation(1);
							userList.put(inJSON.get("userID").toString(), u);
							
							outJSON.put("type", "CREATEROOM");
							outJSON.put("state", "SUCCESS");
							outJSON.put("roomNum", atomicInteger.get());
							out.println(outJSON.toString());
							oout.writeObject(gameRoom.getRoomFrame());
							
							refreshRoomList();
						}
						else {
							outJSON.put("type", "CREATEROOM");
							outJSON.put("state", "FAIL");
							out.println(outJSON.toString());
						}
						break;
						
					case "ENTERROOM" :
						int i = Integer.parseInt(inJSON.get("roomNum").toString()) - 1;
						room = roomList.get(i);
						user = userList.get(inJSON.get("userID").toString());
						RoomFrame roomFrame = room.getRoomFrame();
						
						if(room.enterRoom(user)) {
							user.setNowLocation(1);
							userList.put(inJSON.get("userID").toString(), user);
							outJSON.put("type", "ENTERROOM");
							outJSON.put("state", "SUCCESS");
							outJSON.put("roomNum", i + 1);
							out.println(outJSON.toString());
							roomFrame.idLabel2.setText(inJSON.get("userID").toString());
							room.setRoomFrame(roomFrame);
							oout.writeObject(roomFrame);
						} 
						else {
							outJSON.put("type", "ENTERROOM");
							outJSON.put("state", "FAIL");
							out.println(outJSON.toString());
						}
						break;
						
					case "CHAT" :
						index = Integer.parseInt(inJSON.get("roomNum").toString()) - 1;
						room = roomList.get(index);
						room.broadcast(inJSON);
						
						break;
						
					case "GAMEREADY" :
						index = Integer.parseInt(inJSON.get("roomNum").toString()) - 1;
						room = roomList.get(index);
						room.ready();
						roomList.add(index, room);
						
						outJSON.put("type", "GAMEREADY");
						outJSON.put("type", "SUCCESS");
						out.println(outJSON.toString());
						break;
						
					case "GAMESTART" :
						index = Integer.parseInt(inJSON.get("roomNum").toString()) - 1;
						room = roomList.get(index);
						if(!room.gameStart()) {
							outJSON.put("type", "GAMESTART");
							outJSON.put("type", "FAIL");
							out.println(outJSON.toString());
						}
						break;
						
					case "ANSWER" :
						index = Integer.parseInt(inJSON.get("roomNum").toString()) - 1;
						System.out.println(inJSON.get("roomNum").toString());
						room = roomList.get(index);
						room.checkAnswer(inJSON, userID);
						break;
						
					case "EXITROOM" :
						user = userList.get(inJSON.get("userID").toString());
						user.setNowLocation(1);
						userList.put(inJSON.get("userID").toString(), user);
						
						room = roomList.get(Integer.parseInt(inJSON.get("roomNum").toString()) - 1);
						room.exitRoom(user);
						if(room.closeRoom())//방 인원 0명이면 제거
							roomList.remove(room.getRoomNum() - 1);
						
						outJSON.put("type", "REFRESHROOM");
						out.println(outJSON.toString());
						
						HashMap<Integer, String> roomLists = new HashMap<Integer, String>();
						
						if(!roomList.isEmpty()) {
							for(Room r : roomList) {
								roomLists.put(r.getRoomNum(), r.getRoomName());
							}
						}
						
						oout.writeObject(roomLists);
						break;
					}
				}		
			} 
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					in.close();
					out.close();
					oout.close();
					synchronized(userList) {
						userList.remove(userID);
					}
					synchronized(writers) {
						writers.remove(out);
					}
				} catch(Exception e) {}
			}
		}

		private void broadcast(JSONObject json) {
			Iterator<PrintWriter> it = writers.keySet().iterator();
			
			while(it.hasNext()) {
				PrintWriter writer = it.next();
				writer.println(json.toString());
			}		
		}
		
		private void refreshRoomList() throws IOException {
			HashMap<Integer, String> roomListForNewRoom = new HashMap<Integer, String>();
			
			if(!roomList.isEmpty()) {
				for(Room r : roomList) {
					roomListForNewRoom.put(r.getRoomNum(), r.getRoomName());
				}
			}
			
			Iterator<String> it = userList.keySet().iterator();
			JSONObject json = new JSONObject();
			json.put("type", "REFRESHROOM");
			
			while(it.hasNext()) {
				String userName = it.next();
				User u = userList.get(userName);
				
				if(u.getNowLocation() != 0)
					continue;
				else {
					u.getWriter().println(json.toString());
					u.getOout().writeObject(roomListForNewRoom);
				}
			}
		}

		private Room createRoom(JSONObject json) {
			synchronized(roomList) {
				User userMaster = userList.get(json.get("userID").toString());
				String mode = json.get("mode").toString();
				String roomName = json.get("roomName").toString();
				int peopleNum = Integer.parseInt(json.get("peopleNum").toString());
				if(roomName.isEmpty() || mode.isEmpty() || userMaster == null)
					return null;

				int roomNum = atomicInteger.incrementAndGet();
				userList.put(json.get("userID").toString(), userMaster);
				Room room = new Room(roomNum);
				RoomFrame roomFrame = new RoomFrame(roomNum, json.get("userID").toString());
				
				room.setMode(mode);
				room.setRoomName(roomName);
				room.setRoomNum(roomNum);
				room.setRoomMaster(userMaster);
				room.setRoomFrame(roomFrame);
				room.setMaxPersonnel(peopleNum);
				roomList.add(room);
				return room;
			}
		}

		private User login(String id, String pw) {
			try {
				PreparedStatement pstm = 
						(PreparedStatement) db.con.prepareStatement ("SELECT user_id, user_pw, user_name FROM USER_INFO WHERE user_id = ? AND user_pw = ?");
				pstm.setString(1, id);
				pstm.setString(2, pw);
				db.rs = pstm.executeQuery();

				if(db.rs != null) {
					while(db.rs.next()) {
						String dbID = db.rs.getString("user_id");
						String dbPW = db.rs.getString("user_pw");
						String dbName = db.rs.getString("user_name");

						if(id.equals(dbID) && dbPW.equals(dbPW)) {
							User newUser = new User(dbID, dbName);
							return newUser;
						}

					}
					return null;
				}
				else
					return null;

			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
}
