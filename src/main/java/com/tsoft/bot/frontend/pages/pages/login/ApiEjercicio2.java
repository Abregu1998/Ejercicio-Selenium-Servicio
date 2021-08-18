package com.tsoft.bot.frontend.pages.pages.login;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiEjercicio2 {
    @Test
    void EjercicioPost(){
        //Specify base URI
        RestAssured.baseURI="https://restful-booker.herokuapp.com";

        //Request Object
        RequestSpecification httpRequest=RestAssured.given();

        //Response Object
        JSONObject requestParamans=new JSONObject();

        requestParamans.put("username","admin");
        requestParamans.put("password","password123");

        httpRequest.header("Content-Type","application/json");
        httpRequest.body(requestParamans.toJSONString());

        //Response object
        Response response=httpRequest.request(Method.POST,"/auth");

        //print response in console
        String responseBody=response.getBody().asString();
        System.out.println("Response Body is:"+responseBody);

        //status code validation
        int statusCode=response.getStatusCode();
        System.out.println("Status code is: "+statusCode);
        Assert.assertEquals(statusCode, 200);

        String contentType=response.header("Content-Type");// capture details of Content-Type header
        System.out.println("Content Type is:"+contentType);
        Assert.assertEquals(contentType, "application/json; charset=utf-8");

        String server=response.header("Server");// capture details of Content-Type header
        System.out.println("Content Type is:"+server);
        Assert.assertEquals(server, "Cowboy");

    }
}
