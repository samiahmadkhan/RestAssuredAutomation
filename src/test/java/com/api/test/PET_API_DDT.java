package com.api.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.api.endpoint.petEndPoints;
import com.api.utilities.readXlSheetUtility;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
public class PET_API_DDT {
	static String id="";
	
	Faker f;
	JSONObject obj;
	//@DataProvider
	public Object[][] getData_Pet() {
		Object[][] dataPet = null;
		try {
			dataPet=readXlSheetUtility.getData("/Users/samikhan/Documents/GIT/Bitbucket/RestAssuredFramework/"
			 		+ "RestAssured/src/test/resources/datasheet/Book.xlsx",
					"Sheet1");
			System.out.println(dataPet);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataPet;
	}
	
	@BeforeClass
	public void setData() {
	    obj=new JSONObject();
		obj.put("id","123");
		obj.put("name","Doggie1");
		
		JSONArray ar=new JSONArray();
		Map mp=new LinkedHashMap<>(2);
		mp.put("id","123");
		mp.put("name","Doggie1");
		ar.put(mp);
		//Map mp1=new LinkedHashMap<>(2);
		mp.put("id","289");
		mp.put("name","best");
		ar.put(mp);
		obj.put("tags", ar);
		obj.put("status","available");
		
		System.out.println(obj);
	}
	@Test(priority = 0)
	public void Post_Pet() {
		
		Response postRes=petEndPoints.createPet(obj.toString());
		System.out.println("__________________PET POST RES_________");
		System.out.println(postRes.then().log().all());
		Assert.assertEquals(postRes.getStatusCode(),200);
		
		String body=postRes.getBody().asString();
		JSONObject jo=new JSONObject(body);
		System.out.println("TAGS ARE = ");
		
		JSONArray ja=jo.getJSONArray("tags");
		
		ArrayList<String> ar=new ArrayList<String>();
		
		//verify all the name under Tags is matching with expected json array name under tags
		for(int i=0;i<ja.length();i++) {
			ar.add(ja.getJSONObject(i).get("name").toString());
			Assert.assertEquals(ar.get(i), obj.getJSONArray("tags").getJSONObject(i).get("name"));
			}
		
		id=jo.get("id").toString();
	}
	
	@Test(dependsOnMethods = {"Post_Pet"})
	public void get_Pet() {
		Response getRes=petEndPoints.getPet(id);
		getRes.then().log().all();
	}

}
