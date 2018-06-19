package app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.entities.Email;
import app.entities.User;

public class DBConn {
	
	private Connection con;
	private PreparedStatement stm;
	
	public DBConn() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/emailwebapp?user=root&password=root");
		} catch (SQLException e) {
			System.out.println("Failed to connect to db");
			e.printStackTrace();
		}
	}
	
	public void insertUser(String name) throws SQLException {
		String sql = "insert into user(name, emailAddress) values(?, ?);";
		stm = con.prepareStatement(sql);
		stm.setString(1, name);
		stm.setString(2, name + "@aau-g4.at");
		stm.execute();
	}
	
	public void sendEmail(Email email, String name) throws SQLException {
		int id = getUserId(name);
		String sql = "insert into email(message, title, user_id) values(?, ?, ?);";
		stm = con.prepareStatement(sql);
		stm.setString(1, email.getMessage());
		stm.setString(2, email.getTitle());
		stm.setInt(3, id);
		stm.execute();
		
		sql = "select max(id) from email";
		stm = con.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();
		rs.next();
		int email_id = rs.getInt(1);
		
		sql = "insert into receiver(user_id, email_id) values(?, ?);";
		stm = con.prepareStatement(sql);
		for(User u: email.getReceivers()) {
			stm.setInt(1, u.getId());
			stm.setInt(2, email_id);
			stm.execute();
		}
		
	}
	
	public List<Email> getEmails(String name) throws SQLException {
		ArrayList<Email> emails = new ArrayList<Email>();
		
		String sql = "select * from email where email.id in (select email_id from receiver where receiver.user_id = (select id from user where name = ?));";
		stm = con.prepareStatement(sql);
		stm.setString(1, name);
		
		ResultSet rs = stm.executeQuery();
		while(rs.next()) {
			emails.add(new Email(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
		}
		
		return emails;
	}
	
	public String getUserName(int id) throws SQLException {
		String sql = "select name from user where id = ?;";
		stm = con.prepareStatement(sql);
		stm.setInt(1, id);
		
		ResultSet rs = stm.executeQuery();
		rs.next();
		return rs.getString(1);
	}
	
	private int getUserId(String name) throws SQLException {
		String sql = "select id from user where name = ?;";
		stm = con.prepareStatement(sql);
		stm.setString(1, name);
		ResultSet rs = stm.executeQuery();
		rs.next();
		return rs.getInt(1);
	}
	
	public void deleteEmail(int email_id, String name) throws SQLException {
		int id = getUserId(name);
		String sql = "delete from receiver where user_id = ? and email_id = ?;";
		stm = con.prepareStatement(sql);
		stm.setInt(1, id);
		stm.setInt(2, email_id);
		stm.execute();
	}
	
	public List<String> getEmailAddresses() throws SQLException {
		String sql = "select emailAddress from user";
		stm = con.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();
		ArrayList<String> emails = new ArrayList<String>();
		while(rs.next()) {
			emails.add(rs.getString(1));
		}
		return emails;
	}
	
	public Email getEmail(int id) throws SQLException {
		String sql = "select * from email where id = ?;";
		stm = con.prepareStatement(sql);
		stm.setInt(1, id);
		ResultSet rs = stm.executeQuery();
		rs.next();
		return new Email(id, rs.getString(2), rs.getString(3), rs.getInt(4));
	}
	
}
