package com.firstApplication;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/UserService")

public class UserService {
	UserDao userDao = new UserDao();
	private static final String SUCCESS_RESULT="{ \"result\" : \"success\"}";
	private static final String FAILURE_RESULT="{ \"result\" : \"failure\"}";
	   
	@GET
	@Path("/users")
	@Produces(MediaType.TEXT_HTML)
	public String getUsers() {
		return user_http_response(userDao.getAllUsers());
	}
	

	   @GET
	   @Path("/users/{userid}")
	   @Produces(MediaType.TEXT_HTML)
	   public String getUser(@PathParam("userid") int userid){
	      return user_http_response(userDao.getUser(userid));
	   }

	   @POST
	   @Path("/users")
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes(MediaType.APPLICATION_JSON)
	   public String createUser(User user) throws IOException{
	     // User user = new User(id, name, profession);
	      int result = userDao.addUser(user);
	      if(result == 1){
	         return SUCCESS_RESULT;
	      }
	      return FAILURE_RESULT;
	   }

	   @PUT
	   @Path("/users")
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes(MediaType.APPLICATION_JSON)
	   public String updateUser(User user) throws IOException{
	      //User user = new User(id, name, profession);
	      int result = userDao.updateUser(user);
	      if(result == 1){
	         return SUCCESS_RESULT;
	      }
	      return FAILURE_RESULT;
	   }

	   @DELETE
	   @Path("/users/{userid}")
	   @Produces(MediaType.APPLICATION_JSON)
	   public String deleteUser(@PathParam("userid") int userid){
	      int result = userDao.deleteUser(userid);
	      if(result == 1){
	         return SUCCESS_RESULT;
	      }
	      return FAILURE_RESULT;
	   }
	   
	   private String constructBody(String input) {
		   return "<html> <body> <table border=2 style=\"width:100%\"> <tr> <td>id</td> <td>name</td> <td>profession</td> </tr>"+ input + "</table> </body></html>";
	   }
	   
	   private String user_to_html(User user) {
		   return "<tr> <td>" + user.getId() + "</td> <td>" + user.getName() + "</td> <td>" + user.getProfession()+ "</td></tr>";
	   }
	   
	   private String user_http_response(List<User> users) {
		   String acc = "";
		   for(User user: users){
		       acc = acc + user_to_html(user);
		    }
	   		return constructBody(acc);
	   }
	   
	   private String user_http_response(User user) {
	   		return constructBody(user_to_html(user));
	   }
	   
	   
	   
	   
	   

}