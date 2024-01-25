package com.api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.api.endpoint.userendpoints;
import com.api.payload.user;
import com.api.utilities.readXlSheetUtility;
import com.github.javafaker.Faker;

import io.restassured.response.Response;

public class UserTest_UsingDataProvider {
	Faker faker;
	user data;
	
	@DataProvider
	public Object[][] getDataFromExcel(){
		Object[][] Exdata = null;
		try {
			 Exdata=readXlSheetUtility.getData("/Users/samikhan/Documents/GIT/Bitbucket/RestAssuredFramework/"
			 		+ "RestAssured/src/test/resources/datasheet/Book.xlsx",
					"Sheet1");
			System.out.println(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Exdata;
		
	}
	
	@Test(priority=0, dataProvider = "getDataFromExcel")
	public void post(String id,String username, String firstName, String lastName,String email,String password,String phone,String userStatus) {
		
		data=new user();
		//data.setId(Integer.parseInt(id));
		data.setId(12);
		data.setUsername(username);
		data.setFirstName(firstName);
		data.setLastName(lastName);
		data.setEmail(email);
		data.setPassword(password);
		data.setPhone(phone);
		
		Response response=userendpoints.createUser(data);
		System.out.println("___________________________________________");
		response.then().log().all();
		System.out.println("hey");
		Assert.assertEquals(response.statusCode(), 200,"Assertion Failed" );
		System.out.println("___________________________________________");
	}

}
