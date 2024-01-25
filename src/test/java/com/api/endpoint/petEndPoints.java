package com.api.endpoint;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class petEndPoints {

	public static Response createPet(String Postdata) {
		Response res=given().
		accept("application/json").
		contentType("application/json").
		body(Postdata).
		when().
		post(Routes.Pet_post_url);
		
		return res;

	}
	
	public static Response getPet(String petID) {
		Response res=given().
		accept("application/json").
		contentType("application/json").
		pathParam("petID", petID).
		when().get(Routes.Pet_get_url);
		return res;
	}
}
