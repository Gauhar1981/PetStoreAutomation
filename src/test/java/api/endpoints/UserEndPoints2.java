package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

// Created to perform CRUD (Create, Retrieve, Update and Delete) requests to the user API.

public class UserEndPoints2 {
	
	// method created for getting urls from properties file
	public static ResourceBundle get_url()
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes");   // Load properties file
		return routes;
	}
	
	public static Response createUser(User payload)
	{
		String posturl=get_url().getString("post_url");
		Response response=given()
		  .contentType(ContentType.JSON)
		  .accept(ContentType.JSON)
		  .body(payload)
		
		.when()
		  // .post(Routes.post_url);
		     .post(posturl);
		
		return response;
	}
	
	public static Response readUser(String userName)
	{
		String geturl=get_url().getString("get_url");
		
		Response response=given()
		  .pathParam("username", userName)	
		  
		.when()
		   //.get(Routes.get_url);
		     .get(geturl);
		
		return response;
	}
	
	public static Response updateUser(String userName, User payload)
	{
		String updateurl=get_url().getString("update_url");
		
		Response response=given()
		  .contentType(ContentType.JSON)
		  .accept(ContentType.JSON)
		  .pathParam("username", userName)
		  .body(payload)
		
		.when()
		   //.put(Routes.update_url);
		     .put(updateurl);
		
		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		String deleteurl=get_url().getString("delete_url");
		
		Response response=given()
		  .pathParam("username", userName)	
		  
		.when()
		   //.delete(Routes.delete_url);
		     .delete(deleteurl);
		
		return response;
	}

}
