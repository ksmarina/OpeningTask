package ru.opening.rest.test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.opening.rest.businessObjects.UserFromList;
import ru.opening.rest.services.UserCheckService;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserListTest {
    private List<UserFromList> users;
    private String testUrl = "https://reqres.in/api/users?page=2";

    @Test
    public void restServiceTest() {
        Response usersJson = given()
                .when().get(testUrl)
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();
        users = UserCheckService.getUsersList(usersJson);
        for (UserFromList user : users) {
            Assert.assertTrue(UserCheckService.isUserValid(user), "User from list have empty fields");
        }
    }
}
