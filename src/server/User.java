package server;


public class User {
	private String id = "";
	private String name = "";
	private int nowLocation;
	
	public User(String id, String name) {
		this.id = id;
		nowLocation = 0;
	}

	public int getNowLocation() {
		return nowLocation;
	}

	public void setNowLocation(int nowLocation) {
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
}
