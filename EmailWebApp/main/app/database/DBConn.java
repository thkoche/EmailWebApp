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
	
	public DBConn() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost/emailwebapp?user=root&password=root");
	}
	
	public void insertUser(User user) throws SQLException {
		String sql = "insert into user(id, name, emailAddress) values(?, ?, ?);";
		stm = con.prepareStatement(sql);
		stm.setInt(1, user.getId());
		stm.setString(2, user.getName());
		stm.setString(3, user.getEmailAddress());
		stm.execute();
	}
	
	public void sendEmail(Email email, int sender) throws SQLException {
		String sql = "insert into email(id, message, title, user_id) values(?, ?, ?, ?);";
		stm = con.prepareStatement(sql);
		stm.setInt(1, email.getId());
		stm.setString(2, email.getMessage());
		stm.setString(3, email.getTitle());
		stm.setInt(4, sender);
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
	
	public List<Email> getEmails(User user) throws SQLException {
		ArrayList<Email> emails = new ArrayList<Email>();
		
		String sql = "select * from email where email.id in (select email_id from receiver where ? = receiver.user_id);";
		stm = con.prepareStatement(sql);
		stm.setInt(1, user.getId());
		
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
	
	public void deleteEmail(Email email, User user) throws SQLException {
		String sql = "delete from receiver where user_id = ? and email_id = ?;";
		stm = con.prepareStatement(sql);
		stm.setInt(1, user.getId());
		stm.setInt(2, email.getId());
		stm.execute();
	}
	
}