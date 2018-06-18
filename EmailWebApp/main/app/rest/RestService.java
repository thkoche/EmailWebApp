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
	DBConn dbConn = new DBConn();
	
	@GET
	@Path("/getHistory")
	@Produces(MediaType.APPLICATION_JSON)
	public String getIt() {
		String UserAsJsonString = gson.toJson(new User(12,"Manfred"));
		return UserAsJsonString;
	}
	
	@GET
	@Path("/getHistoryByUsername/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getHistoryByUsername(@PathParam("username") String username) {
		List<Email> emails = new ArrayList<Email>();
		
		try {
			emails = dbConn.getEmails(username);
		} catch (SQLException e) {
			System.out.println("Failed to get emaillist from "+username);
			e.printStackTrace();
		}
		return gson.toJson(emails);
	}
	
	@GET
    @Path("/getEmailByUsername/{username}")
	@Produces(MediaType.APPLICATION_JSON)
    public String getEmailByUsername(@PathParam("username") String username) {
        String email = gson.toJson(new Email(0, "Hello there", "Daily Gretting", null, 0));
        System.out.println("Requested:" +email);
        return email;
    }

	@GET
    @Path("/login/{username}")
    public String loginUser(@PathParam("username") String username) {
		
		try {
			dbConn.insertUser(username);
			System.out.println("New user "+username+" created!");
		} catch (SQLException e) {
			System.out.println("User "+username+" logged in!");
		}
		
		return "";
    }
}
