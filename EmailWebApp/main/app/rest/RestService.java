package app.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import app.entities.User;

@Path("/service")
public class RestService {

	Gson gson = new Gson();
	
	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as "text/plain" media type.
	 * 
	 * call: http://localhost:8080/EmailWebApp/rest/service
	 *
	 * @return String that will be returned as a text/plain response.
	 */
//	@GET
//	@Path("/getHistory")
//	@Produces(MediaType.TEXT_PLAIN)
//	public String getIt() {
//		return "Hello from rest service";
//	}
	
	@GET
	@Path("/getHistory")
	@Produces(MediaType.APPLICATION_JSON)
	public String getIt() {
		String UserAsJsonString = gson.toJson(new User(12,"Manfred"));
		return UserAsJsonString;
	}

}
