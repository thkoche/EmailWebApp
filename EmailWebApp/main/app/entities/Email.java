package app.entities;

import java.util.List;

public class Email {

	private int id;
	private String message;
	private String title;
	private List<User> receivers;
	private int senderId;

	public Email(int id, String message, String title, List<User> receivers, int senderId) {
		this.id = id;
		this.message = message;
		this.title = title;
		this.receivers = receivers;
		this.senderId = senderId;
	}  
	
	public Email(int id, String message, String title, int senderId) {
		this.id = id;
		this.message = message;
		this.title = title;
		this.senderId = senderId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<User> getReceivers() {
		return receivers;
	}

	public void setReceivers(List<User> receivers) {
		this.receivers = receivers;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	
	

}
