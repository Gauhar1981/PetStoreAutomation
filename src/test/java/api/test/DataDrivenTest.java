package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTest {
	
	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void testPostUser(String userID, String userName, String fname, String lname, String useremail, String pwd, String ph)
	{
		User userpayLoad=new User();
		
		userpayLoad.setId(Integer.parseInt(userID));
		userpayLoad.setUsername(userName);;
		userpayLoad.setFirstName(fname);
		userpayLoad.setLastName(lname);
		userpayLoad.setEmail(useremail);
		userpayLoad.setPassword(pwd);
		userpayLoad.setPhone(ph);
		
		Response response=UserEndPoints.createUser(userpayLoad);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	} 
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String userName)
	{
		Response response= UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

}
