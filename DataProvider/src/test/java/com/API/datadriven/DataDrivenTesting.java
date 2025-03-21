package com.API.datadriven;

import static io.restassured.RestAssured.*;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DataDrivenTesting 
{
	@Test(dataProvider ="LoginTestData")
	public static void UserProfile(String email,String password)
	{
		HashMap<String,String> payload=new HashMap<String,String>();
		payload.put("email",email);
		payload.put("password",password);
		
		baseURI="https://api.escuelajs.co";
		
		Response response=given()
			.header("Content-Type", "application/json")
			.contentType(ContentType.JSON)
			.body(payload)
		.when()
			.post("/api/v1/auth/login");
			
		System.out.println(response.asPrettyString());
		Assert.assertEquals(201,response.statusCode(),"Please check the Payload");
	
	}
	
	@DataProvider(name="LoginTestData")
	public static Object[] [] TestData()
	{
		return new Object[] []
		{
			{"john@mail.com","changeme"},
			{"john@mail.com","12345678"},
			{"john@mail.co.in","changeme"},
			{"john@mail.in","changed"},
			{"",""},
		};
	}
}
