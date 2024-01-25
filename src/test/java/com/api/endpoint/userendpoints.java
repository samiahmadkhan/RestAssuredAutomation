package com.api.endpoint;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.api.payload.user;

import io.restassured.response.Response;


public class userendpoints {
	
	public static Response createUser(user Payload) {
		Response res=given().
		contentType("application/json").
		accept("application/json").
		body(Payload).
		when().post(Routes.post_url);
		
		return res;
		
	}
	
	public static Response getUser(String username) {
		Response res=given().
				pathParam("username", username).
				contentType("application/json").
				accept("application/json").
				when().get(Routes.get_url);
				
				return res;
	}
	
	public static Response updateUserDetails(user Payload,String username) {
		
		
		Response res=given().
				contentType("application/json").
				pathParam("username", username).
				accept("application/json").
				body(Payload).
				when().put(Routes.update_url);
				
				return res;
	}
	
public static Response deleteUser(String username) {
		
	Response res=given().
			pathParam("username", username).
			contentType("application/json").
			accept("application/json").
			when().delete(Routes.delete_url);
			
			return res;
	}
}
