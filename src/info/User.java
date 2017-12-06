package info;

import java.net.Socket;

public class User {
	private String id = "";
	private String name = "";
	private String nowLocation;
	private Socket socket;
	
	public User(String id, String name) {
		this.id = id;
	}

	public String getNowLocation() {
		return nowLocation;
	}

	public void setNowLocation(String nowLocation) {
		this.nowLocation = nowLocation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public boolean isEmpty() {
		if(id.isEmpty() || name.isEmpty())
			return true;
		else
			return false;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}
