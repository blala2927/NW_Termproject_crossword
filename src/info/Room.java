package info;

import java.util.List;

public class Room {
	private int roomNum;
	private String roomName;
	private String mode;
	private User roomMaster;
	private List<User> userList;
	
	public Room(User roomMaster) {
		this.setRoomMaster(roomMaster);
		userList.add(roomMaster);
	}
	
	public Room(int roomNum) {
		this.roomNum = roomNum;
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
	
}
