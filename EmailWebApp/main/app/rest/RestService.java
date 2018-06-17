package app.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import app.entities.Email;
import app.entities.User;

@Path("/service")
public class RestService {

	Gson gson = new Gson();
	
	@GET
	@Path("/getHistory")
	@Produces(MediaType.APPLICATION_JSON)
	public String getIt() {
		String UserAsJsonString = gson.toJson(new User(12,"Manfred"));
		return UserAsJsonString;
	}
	
	@GET
    @Path("/getEmailByUsername/{username}")
	@Produces(MediaType.APPLICATION_JSON)
    public String getEmailByUsername(@PathParam("username") String username) {
        String email = gson.toJson(new Email(0, "Hello there", "Daily Gretting", null));
        System.out.println("Requested:" +email);
        return email;
    }

	@GET
    @Path("/login/{username}")
    public String loginUser(@PathParam("username") String username) {
        //TODO
		//If user is already in db, then do nothing
		//else insert new user with "username"
		
		return "";
    }
}
