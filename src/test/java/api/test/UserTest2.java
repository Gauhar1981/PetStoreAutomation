package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest2 {
	
	Faker faker;
	User userpayLoad;
	
	public Logger logger;    // for logs
	
	@BeforeClass	
	public void setup()
	{
		faker=new Faker();
		
		userpayLoad=new User();
		
		userpayLoad.setId(faker.idNumber().hashCode());
		userpayLoad.setUsername(faker.name().username());
		userpayLoad.setFirstName(faker.name().firstName());
		userpayLoad.setLastName(faker.name().lastName());
		userpayLoad.setEmail(faker.internet().safeEmailAddress());
		userpayLoad.setPassword(faker.internet().password(5, 10));
		userpayLoad.setPhone(faker.phoneNumber().cellPhone());
		
		// Code for logs
		
		logger=LogManager.getLogger(this.getClass());
		
		logger.debug("debugging..........");		
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("************** Creating User **************");
		Response response=UserEndPoints2.createUser(userpayLoad);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("************** User is Created **************");
	}
	
	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("************** Reading User Info**************");
		Response response=UserEndPoints2.readUser(this.userpayLoad.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("************** User Info is Displayed**************");
		
	}
	
	@Test(priority=3)
    public void testUpdateUserByName()
    {
	//update data using payload	
		
		logger.info("************** Updating User **************");
		userpayLoad.setFirstName(faker.name().firstName());
		userpayLoad.setLastName(faker.name().lastName());
		userpayLoad.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndPoints2.updateUser(this.userpayLoad.getUsername(),userpayLoad);
		//response.then().log().all();
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("************** User Updated**************");
		
		//checking data after update
		Response responseAfterUpdate=UserEndPoints.readUser(this.userpayLoad.getUsername());		
		Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
		
		
    }
	
	@Test(priority=4)
	public void deleteUserByName()
	{
		logger.info("************** Deleting User **************");
		Response response=UserEndPoints2.deleteUser(this.userpayLoad.getUsername());
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("************** User Deleted**************");
	}
	
}
