package server;

import java.util.List;

public class Room {
	private int roomNum;
	private String roomName;
	private String mode;
	private User roomMaster;
	private int maxPersonnel;
	private List<User> userList;

	public Room(User roomMaster) {
		this.setRoomMaster(roomMaster);
		userList.add(roomMaster);
	}

	public Room(int roomNum) {
		this.roomNum = roomNum;
	}

	public boolean enterRoom(User newUser) {
		if(userList.size() > maxPersonnel)
			return false;
		synchronized(userList) {
			userList.add(newUser);
			return true;
		}
	}
	
	public void closeRoom() {
		userList.clear();
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
}
