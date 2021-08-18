package com.tsoft.bot.frontend.pages.pages.login;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiEjercicio3 {
    @Test
    void getUser(){
        //Specify base URI
        RestAssured.baseURI="https://reqres.in/api";

        //Request Object
        RequestSpecification httpRequest=RestAssured.given();

        //Response Object
        Response response=httpRequest.request(Method.GET,"/users/2");

        //print response in console
        String responseBody=response.getBody().asString();
        System.out.println("Response Body is:"+responseBody);

        //status code validation
        int statusCode=response.getStatusCode();
        System.out.println("Status code is: "+statusCode);
        Assert.assertEquals(statusCode, 200);

    }

    @Test
    void PutUser(){
        //Specify base URI
        RestAssured.baseURI="https://reqres.in/api";

        //Request Object
        RequestSpecification httpRequest=RestAssured.given();

        //Response Object
        JSONObject requestParamans=new JSONObject();

        requestParamans.put("name","morpheus");
        requestParamans.put("job","zion resident");

        httpRequest.body(requestParamans.toJSONString());

        //Response Object
        Response response=httpRequest.request(Method.PUT,"/users/2");

        //print response in console
        String responseBody=response.getBody().asString();
        System.out.println("Response Body is:"+responseBody);

        //status code validation
        int statusCode=response.getStatusCode();
        System.out.println("Status code is: "+statusCode);
        Assert.assertEquals(statusCode, 200);

    }

    @Test
    void LoginUnsuccessful(){
        //Specify base URI
        RestAssured.baseURI="https://reqres.in/api";

        //Request Object
        RequestSpecification httpRequest=RestAssured.given();

        //Response Object
        JSONObject requestParamans=new JSONObject();

        requestParamans.put("email","peter@klaven");

        httpRequest.body(requestParamans.toJSONString());

        //Response Object
        Response response=httpRequest.request(Method.POST,"/login");

        //print response in console
        String responseBody=response.getBody().asString();
        System.out.println("Response Body is:"+responseBody);

        //status code validation
        int statusCode=response.getStatusCode();
        System.out.println("Status code is: "+statusCode);
        Assert.assertEquals(statusCode, 400);

    }

}
