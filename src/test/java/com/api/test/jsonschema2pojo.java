package com.api.test;


import com.demo.json.Demo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class jsonschema2pojo {
    public static void main(String arr[]) throws IOException {
        ObjectMapper mapper=new ObjectMapper();
       Demo demoPojo= mapper.readValue(new File("/Users/samikhan/Documents/GIT/Bitbucket/RestAssuredFramework/RestAssured/src/main/resources/schema/demo.json"),
                Demo.class);
        System.out.println("Name: "+ demoPojo.getPerson().getName());
        System.out.println("Cars: "+ demoPojo.getPerson().getCars().getCar().get(0));


    }

}
