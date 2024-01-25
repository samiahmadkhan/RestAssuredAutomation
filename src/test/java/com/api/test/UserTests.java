package com.api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.api.endpoint.userendpoints;
import com.api.payload.user;
import com.github.javafaker.Faker;

import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	user data;
	
	@BeforeClass
	public void setUp() {
		faker=new Faker();
		data=new user();
		data.setId(faker.idNumber().hashCode());
		data.setUsername(faker.name().username());
		data.setFirstName(faker.name().firstName());
		data.setLastName(faker.name().lastName());
		data.setEmail(faker.internet().emailAddress());
		data.setPassword(faker.internet().password());
		data.setPhone(faker.phoneNumber().cellPhone());
	}
	
	@Test(priority = 0)
	public void setUserPOST() {
		Response response=userendpoints.createUser(data);
		System.out.println("___________________________________________");
		response.then().log().all();
		System.out.println("hey");
		Assert.assertEquals(response.statusCode(), 200,"Assertion Failed" );
		System.out.println("___________________________________________");
	}
	
	@Test(priority = 1)
	public void getUserDetails() {
		Response response=userendpoints.getUser(data.getUsername());
		System.out.println("___________________________________________");
		System.out.println("************* User Details ************");
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200,"Assertion Failed" );
		System.out.println("___________________________________________");
	}
	
	@Test(priority = 3)
	public void updateUserPUT() {
		faker=new Faker();
		data=new user();
		data.setId(faker.idNumber().hashCode());
		data.setUsername(faker.name().username());
		data.setFirstName(faker.name().firstName());
		data.setLastName(faker.name().lastName());
		
		Response response=userendpoints.updateUserDetails(data,data.getUsername());
		System.out.println("___________________________________________");
		System.out.println("************* Updated ************");
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200,"Assertion Failed" );
		System.out.println("___________________________________________");
	}
	
	@Test(priority = 4)
	public void deleteUser() {
		Response response=userendpoints.deleteUser(data.getUsername());
		System.out.println("___________________________________________");
		System.out.println("************* User Deleted ************");
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200,"Assertion Failed" );
		System.out.println("___________________________________________");
		
		Response getUser=userendpoints.getUser(data.getUsername());
		System.out.println("___________________________________________");
		System.out.println("************* User Details ************");
		getUser.then().log().all();
		System.out.println("___________________________________________");
		
	}

}
