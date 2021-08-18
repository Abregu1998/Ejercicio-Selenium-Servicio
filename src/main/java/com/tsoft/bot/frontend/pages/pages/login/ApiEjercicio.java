package com.tsoft.bot.frontend.pages.pages.login;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiEjercicio {

    @Test
    void getToken(){
        //Specify base URI
        RestAssured.baseURI="https://restful-booker.herokuapp.com";

        //Request Object
        RequestSpecification httpRequest=RestAssured.given();

        //Response Object
        Response response=httpRequest.request(Method.GET,"/booking");

        //print response in console
        String responseBody=response.getBody().asString();
        System.out.println("Response Body is:"+responseBody);

        //status code validation
        int statusCode=response.getStatusCode();
        System.out.println("Status code is: "+statusCode);
        Assert.assertEquals(statusCode, 200);

    }
}
