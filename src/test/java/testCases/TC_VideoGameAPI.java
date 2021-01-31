package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class TC_VideoGameAPI {	
	//given -- precondition	
	//when -- test steps	
	//then -- checking the validation
	
	@Test(priority=1)
	public void test_getAllVideoGames()
	{
		given()
			.contentType("application/json")
		.when()
			.get("http://localhost:9000/app/videogames")
		.then()
			.statusCode(200)
			.log().body();
	}
	@Test(priority=2)
	public void test_addNewVoideoGame()
	{
		HashMap data=new HashMap();
		data.put("id", "101");
		data.put("name", "Spider-Man");
		data.put("releaseDate", "2020-10-01");
		data.put("reviewScore", "5");
		data.put("category", "Adventure");
		data.put("rating", "Universal");
		
				
		Response res=
			given()
				.contentType("application/json")
				.body(data)
			.when()
				.post("http://localhost:9000/app/videogames")
			.then()
				.statusCode(200)
				.log().body()
				.extract().response();
		
		String jsonString=res.asString();
		Assert.assertEquals(jsonString.contains("Record Added Successfully"), true);
		
	}
	
	@Test(priority=3)
	public void updateVideoGame()
	{
		HashMap data=new HashMap();
		data.put("id", "101");
		data.put("name", "Pacman");
		data.put("releaseDate", "2020-10-01");
		data.put("reviewScore", "5");
		data.put("category", "Adventure");
		data.put("rating", "Universal");
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.put("http://localhost:9000/app/videogames/101")
		.then()
			.statusCode(200)
			.log().body();
		
	}
	
	@Test(priority=4)
	public void test_getVideoGame()
	{
		given()
		
		.when()
			.get("http://localhost:9000/app/videogames/101")
		.then()
			.statusCode(200)
			.log().body();
	}
	
	@Test(priority=5)
	public void test_DeleteVideoGame()
	{
		Response res=
		given()
			.contentType("application/json")
		.when()
			.delete("http://localhost:9000/app/videogames/101")
		.then()
			.statusCode(200)
			.log().body()
			.extract().response();
		
		String jsonString=res.asString();
		Assert.assertEquals(jsonString.contains("Record Deleted Successfully"), true);
	}
	
	
	
	
	
	
	

}
