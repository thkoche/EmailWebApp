package app.entities;

public class User {

	private int id;
	private String name;
	private String emailAddress;
	
	
	public User(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		emailAddress = name+"@aau-g4.at";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	
}
