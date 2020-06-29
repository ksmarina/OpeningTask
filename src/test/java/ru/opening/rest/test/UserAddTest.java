package ru.opening.rest.test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.opening.rest.businessObjects.NewUser;
import ru.opening.rest.businessObjects.NewUserAnswer;
import ru.opening.rest.services.UserCheckService;

import javax.json.Json;
import javax.json.JsonObject;

import static io.restassured.RestAssured.given;

public class UserAddTest {
    private String testUrl = "https://reqres.in/api/users";
    private NewUser user;
    private JsonObject json;

    @BeforeClass
    public void createUser(){
        user = new NewUser("morpheus", "leader");
        json = Json.createObjectBuilder()
                .add("name", user.getName())
                .add("job", user.getJob()).build();
    }

    @Test
    public void addUserTest() {
        Response response = given().contentType(ContentType.JSON)
                .header("Content-Type", "application/json")
                .body(json)
                .when().post(testUrl)
                .then().statusCode(201).extract().response();
        NewUserAnswer answer = UserCheckService.getNewUserAnswer(response);

        Assert.assertEquals(user.getName(), answer.getName());
        Assert.assertEquals(user.getJob(), answer.getJob());
    }
}
