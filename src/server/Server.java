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
	private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
	private static HashSet<ObjectOutputStream> oouts = new HashSet<ObjectOutputStream>(); 
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
		private String userId;
		private Room gameRoom;

		public Handler(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {
				JSONParser parser = new JSONParser();
				JSONObject inJSON;
				JSONObject outJSON;
				int stringIndex;

				OutputStream outputStream = socket.getOutputStream(); 
				oout = new ObjectOutputStream(outputStream);

				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(outputStream, true);

				while(true) {
					//login
					outJSON = new JSONObject();
					outJSON.put("type", "LOGIN");
					outJSON.put("state", "WAIT");
					out.println(outJSON.toJSONString());

					String input = in.readLine();

					inJSON = (JSONObject) parser.parse(input);

					userId = inJSON.get("userID").toString();
					String pw = inJSON.get("pw").toString();

					if(userId.isEmpty() || pw.isEmpty())
						continue;

					User newUser = login(userId, pw);
					HashMap<Integer, String> roomListForFirst = new HashMap<>();
					
					if(!roomList.isEmpty()) {
						for(Room r : roomList) {
							roomListForFirst.put(r.getRoomNum(), r.getRoomName());
						}
					}


					if(newUser != null) {
						synchronized(userList) {
							if(!userList.containsKey(userId)) {
								userList.put(userId, newUser);
								writers.add(out);
								oouts.add(oout);
								outJSON.put("type", "LOGIN");
								outJSON.put("state", "SUCCESS");
								outJSON.put("userName", newUser.getName());
								out.println(outJSON);
								oout.writeObject(roomListForFirst);
								oout.flush();
								oout.reset();
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

					String type = inJSON.get("type").toString();

					switch(type) {
					case "CREATEROOM" :
						gameRoom = createRoom(inJSON);
						if(gameRoom != null) {
							outJSON.put("type", "CREATEROOM");
							outJSON.put("state", "SUCCESS");
							outJSON.put("roomNum", atomicInteger.get());
							out.println(outJSON.toString());
						}
						else {
							outJSON.put("type", "CREATEROOM");
							outJSON.put("state", "FAIL");
							out.println(outJSON.toString());
						}
						break;
					case "ENTERROOM" :
						break;
					case "EXITROOM" :
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
					synchronized(userList) {
						userList.remove(userId);
					}
				} catch(Exception e) {}
			}
		}

		private void broadcast(JSONObject json) {
			for(PrintWriter writer : writers) {
				writer.println(json.toString());
			}
		}
		
		private void broadcast(Object o) throws IOException {
			for(ObjectOutputStream oout : oouts) {
				oout.writeObject(o);
				oout.flush();
				oout.reset();
			}
		}

		private Room createRoom(JSONObject json) {
			synchronized(roomList) {
				User userMaster = userList.get(json.get("userID").toString());
				String mode = json.get("mode").toString();
				String roomName = json.get("roomName").toString();
				if(roomName.isEmpty() || mode.isEmpty() || userMaster == null)
					return null;

				int roomNum = atomicInteger.incrementAndGet();
				userMaster.setNowLocation(roomNum);
				userList.put(json.get("userID").toString(), userMaster);
				Room room = new Room(roomNum);

				room.setMode(mode);
				room.setRoomName(roomName);
				room.setRoomNum(roomNum);
				room.setRoomMaster(userMaster);
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
