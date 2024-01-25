package com.api.endpoint;

public class Routes {
	public static String Baseurl="https://petstore.swagger.io/v2";
	
	public static String post_url=Baseurl+"/user";
	public static String get_url=Baseurl+"/user/{username}";
	public static String update_url=Baseurl+"/user/{username}";
	public static String delete_url=Baseurl+"/user/{username}";
	
    public static String Pet_Baseurl="https://petstore.swagger.io/v2";
	
	public static String Pet_post_url=Pet_Baseurl+"/pet";
	public static String Pet_get_url=Pet_Baseurl+"/pet/{petID}";

}
