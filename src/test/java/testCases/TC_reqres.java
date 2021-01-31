package testCases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TC_reqres {
	
	@Test
	public void getUsers()
	{
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
			
		.then()
			.statusCode(200)
			.log().all()
			.body("data[4].first_name",equalTo("George"));
			
		
	}
	
	@Test
	public void addnewUser()
	{
		HashMap<String, Object> data=new HashMap<String, Object>();
		
	/*	data.put("id", 13);
		data.put("email", "george.bluth@reqres.in");
		data.put("first_name", "testGeorge");
		data.put("last_name", "testBluth");
		data.put("avatar", "https://reqres.in/img/faces/1-image.jpg"); */
		
		data.put("name", "Lakshman");
		data.put("job", "Teacher");
		
		
		Response res=
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users")
			
		.then()
			.statusCode(201)
			.log().body()
			.extract().response();
		
		String jsonString=res.asString();
		
	}
	
	
	
	
	@Test
	public void testPatch()
	{
		JSONObject request=new JSONObject();
		
		request.put("name", "Lakshman");
		request.put("job", "Teacher");
		
		given()
			.header("ContentType","application/json")
			.body(request.toJSONString())
		.when()
			.patch("https://reqres.in/api/users/2")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test
	public void getUser()
	{
		given()
		
		.when()
			.get("https://reqres.in/api/users/2")
		.then()
			.statusCode(200)
			.log().body()
			.body("data.id", equalTo(2))
			.body("data.email", equalTo("janet.weaver@reqres.in"));		
		
	}
	
	@Test
	public void testPut()
	{
		JSONObject request=new JSONObject();
		
		request.put("name", "LakshmanUp");
		request.put("job", "TeacherUp");
		
		given()
			.header("ContentType","application/json")
			.body(request.toJSONString())
		.when()
			.put("https://reqres.in/api/users/2")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	
	
	
	@Test
	public void testDelete()
	{				
		given()
		.when()
			.delete("https://reqres.in/api/users/2")
		.then()
			.statusCode(204)
			//.log().body();
			.log().all();
	}
	
	
	
	
	
	

}
