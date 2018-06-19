package app.rest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import app.database.DBConn;
import app.entities.Email;
import app.entities.User;

@Path("/service")
public class RestService {

	Gson gson = new Gson();
	DBConn dbConn;

	@GET
	@Path("/getHistoryByUsername/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getHistoryByUsername(@PathParam("username") String username) {
		List<Email> emails = new ArrayList<Email>();

		try {
			dbConn = new DBConn();
			emails = dbConn.getEmails(username);
		} catch (SQLException e) {
			System.out.println("Failed to get emaillist from " + username);
			e.printStackTrace();
		}

		return gson.toJson(emails);
	}

	@GET
	@Path("/showEmail/{emailId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String showEmail(@PathParam("emailId") int emailId) {
		Email email = null;
		try {
			dbConn = new DBConn();
			email = dbConn.getEmail(emailId);
		} catch (SQLException e) {
			System.out.println("Failed to get email " + emailId);
			e.printStackTrace();
		}
		return gson.toJson(email);
	}

	@GET
	@Path("/login/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public String loginUser(@PathParam("username") String username) {
		try {
			/* load driver on login */
			Class.forName("com.mysql.jdbc.Driver");
			dbConn = new DBConn();
			dbConn.insertUser(username);
			System.out.println("New user " + username + " created!");
		} catch (SQLException e) {
			System.out.println("User " + username + " logged in!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return "{}";
	}

	@GET
	@Path("/deleteEmail/{username}/{emailId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteEmail(@PathParam("username") String username, @PathParam("emailId") int emailId) {
		try {
			dbConn = new DBConn();
			dbConn.deleteEmail(emailId, username);
			System.out.println("Deleted Email: " + emailId + " for " + username);
		} catch (Exception e) { // Replace with "SQL"Exception...
			System.out.println("Failed to delete email: " + emailId + " for " + username);
		}
		return "{}";
	}

	@GET
	@Path("/sendEmail/{username}/{message}/{title}/{receivers}")
	@Produces(MediaType.APPLICATION_JSON)
	public String sendEmail(@PathParam("username") String username, @PathParam("message") String message,
			@PathParam("title") String title, @PathParam("receivers") String receivers) {

		Email email = null;
		List<User> receiversList = splitReceivers(receivers);
		try {
			dbConn = new DBConn();
			email = new Email(0, message, title, receiversList, dbConn.getUserId(username));
			dbConn.sendEmail(email, username);
			System.out.println("Sent Email by " + username);
		} catch (Exception e) { // Replace with "SQL"Exception...
			System.out.println("Failed to send email by " + username);
		}
		return "{}";
	}

	private List<User> splitReceivers(String receivers) {
		String[] username, receiversAddresses;
		dbConn = new DBConn();
		List<User> receiversList = new ArrayList<User>();
		receiversAddresses = receivers.split(";");
		for (String useraddress : receiversAddresses) {
			username = useraddress.split("@");
			try {
				receiversList.add(new User(dbConn.getUserId(username[0]), username[0]));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return receiversList;
	}
	
	@GET
	@Path("/getReceiverList")
	@Produces(MediaType.APPLICATION_JSON)
	public String getReceiverList() {
		List<String> emailAddresses = null;
		try {
			dbConn = new DBConn();
			emailAddresses = dbConn.getEmailAddresses();
		} catch (SQLException e) {
			System.out.println("Failed to get address list "+e);
		}
		System.out.println(gson.toJson(emailAddresses));
		return gson.toJson(emailAddresses);
	}
}
