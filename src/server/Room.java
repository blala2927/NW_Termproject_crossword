package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import info.Map;
import info.RoomFrame;

public class Room {
	private int roomNum;
	private String roomName;
	private String mode;
	private User roomMaster;
	private int maxPersonnel;
	private RoomFrame roomFrame;
	private Map map;
	private int readyNum = 0;
	private List<User> userList = new ArrayList<User>();

	public Room(int roomNum) {
		this.roomNum = roomNum;
	}

	public boolean enterRoom(User newUser) {
		if(userList.size() == maxPersonnel)
			return false;
		synchronized(userList) {
			JSONObject json = new JSONObject();
			json.put("type", "NEWUSER");
			json.put("newUserID", newUser.getId());
			for(int i = 0; i < userList.size(); i++) {
				PrintWriter writer = userList.get(i).getWriter();
				writer.println(json.toString());
			}
			userList.add(newUser);
			return true;
		}
	}
	
	public void ready() {
		readyNum++;
	}
	
	public boolean gameStart() throws IOException { 
		if(readyNum != userList.size())
			return false;
		
		else {
			JSONObject json = new JSONObject();
			map = new Map();
			
			json.put("type", "GAMESTART");
			json.put("state", "SUCCESS");
			
			for(int i = 0; i < userList.size(); i++) {
				PrintWriter writer = userList.get(i).getWriter();
				writer.println(json.toJSONString());
				ObjectOutputStream oout = userList.get(i).getOout();
				oout.writeObject(map);
			}
			
			return true;
		}
	}
	
	public void exitRoom(User user) {
		synchronized(userList) {
			for(int i = 0; i < userList.size(); i++) {
				if(userList.get(i).getId().equals(user.getId())) {
					userList.remove(i);
					readyNum--;
				}
			}
		}
	}
	

	public boolean closeRoom() {
		if(userList.size() == 0)
			return true;
		else
			return false;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public User getRoomMaster() {
		return roomMaster;
	}

	public void setRoomMaster(User roomMaster) {
		this.roomMaster = roomMaster;
		userList.add(roomMaster);
	}

	public int getPersonnel() {
		return userList.size();
	}

	public int getMaxPersonnel() {
		return maxPersonnel;
	}

	public void setMaxPersonnel(int maxPersonnel) {
		this.maxPersonnel = maxPersonnel;
	}

	public RoomFrame getRoomFrame() {
		return roomFrame;
	}

	public void setRoomFrame(RoomFrame roomFrame) {
		this.roomFrame = roomFrame;
	}

	public void broadcast(String str) {
		JSONObject json = new JSONObject();
		json.put("type", "CHATTING");
		json.put("message", str);
		for(int i = 0; i < userList.size(); i++) {
			userList.get(i).getWriter().println(json.toString());
		}
	}
}
