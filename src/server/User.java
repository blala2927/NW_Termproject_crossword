package server;

import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class User {
	private String id = "";
	private String name = "";
	private PrintWriter writer;
	private ObjectOutputStream oout;
	private int nowLocation = 0;
	
	public User(String id, String name) {
		this.id = id;
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

	public ObjectOutputStream getOout() {
		return oout;
	}


	public void setOout(ObjectOutputStream oout) {
		this.oout = oout;
	}


	public PrintWriter getWriter() {
		return writer;
	}


	public void setWriter(PrintWriter writer) {
		this.writer = writer;
	}

	public int getNowLocation() {
		return nowLocation;
	}

	public void setNowLocation(int nowLocation) {
		this.nowLocation = nowLocation;
	}
}
