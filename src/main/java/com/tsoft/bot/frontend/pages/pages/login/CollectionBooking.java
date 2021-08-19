package com.tsoft.bot.frontend.pages.pages.login;

import com.tsoft.bot.frontend.base.BaseClass;
import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.pages.objects.ExcelDataObjects;
import com.tsoft.bot.frontend.pages.objects.McssObjects;
import com.tsoft.bot.frontend.utility.ExcelReader;
import com.tsoft.bot.frontend.utility.GenerateWord;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;

public class CollectionBooking extends BaseClass {
    private WebDriver driver;
    private static GenerateWord generateWord = new GenerateWord();
    String mensaje;
    static String token;


    public CollectionBooking(WebDriver driver){
        super(driver);
        this.driver = Hook.getDriver();
    }

    private List<HashMap<String, String>> getData() throws Throwable {
        return ExcelReader.data(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME);
    }


    public void autenticacionToken() throws Throwable{

        mensaje = "Generando Token";

        try {
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

            token = response.jsonPath().get("token");


            //print response in console
            String responseBody=response.getBody().asString();
            System.out.println("Response Body es:"+responseBody);
            System.out.println("---------------------");
            //print input



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
        catch (Exception we)
        {
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            stepFail(driver,"Fallo en tiempo de respuesta : " + we.getMessage());
            generateWord.sendText("Fallo en tiempo de respuesta");
            generateWord.addImageToWord(driver);
        }


    }

    public String crearReserva() throws Throwable{

        mensaje = "Creando Reserva de Coleccion";
        String response = "";

        try {
            //Specify base URI
            RestAssured.baseURI="https://restful-booker.herokuapp.com";
            //Request Object
            RequestSpecification httpRequest=RestAssured.given();
            //Response Object
            JSONObject requestParamans=new JSONObject();

            requestParamans.put("firstname","Angelo");
            requestParamans.put("lastname","Abregu");
            requestParamans.put("totalprice","111");
            requestParamans.put("depositpaid","true");
            JSONObject bookingDates = new JSONObject();
            bookingDates.put("checkin","2018-01-01");
            bookingDates.put("checkout","2019-01-01");
            requestParamans.put("bookingdates",bookingDates);
            requestParamans.put("aditionalneeds","Breakfast");


            httpRequest.header("Content-Type","application/json");
            httpRequest.header("Accept","application/json");
            httpRequest.body(requestParamans.toJSONString());

            //Response object
            Response responses=httpRequest.request(Method.POST,"/booking");

            //print response in console
            String responseBody=responses.getBody().asString();
            System.out.println("Response Body is:"+responseBody);

            //Retornamos el registro creado
            response = responses.jsonPath().get("bookingid").toString();

            //status code validation
            int statusCode=responses.getStatusCode();
            System.out.println("Status code is: "+statusCode);
            Assert.assertEquals(statusCode, 200);

            String contentType=responses.header("Content-Type");// capture details of Content-Type header
            System.out.println("Content Type is:"+contentType);
            Assert.assertEquals(contentType, "application/json; charset=utf-8");

        }
        catch (Exception we)
        {
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            stepFail(driver,"Fallo en tiempo de respuesta : " + we.getMessage());
            generateWord.sendText("Fallo en tiempo de respuesta");
            generateWord.addImageToWord(driver);
        }

        return response;

    }

    public void ActualizarReserva(String id) throws Throwable{

        mensaje = "Actualizando la reserva";

        try {
            //Specify base URI
            RestAssured.baseURI="https://restful-booker.herokuapp.com";
            //Request Object
            RequestSpecification httpRequest=RestAssured.given();
            //Response Object
            JSONObject requestParamans=new JSONObject();

            requestParamans.put("firstname","James");
            requestParamans.put("lastname","Brown");
            requestParamans.put("totalprice","111");
            requestParamans.put("depositpaid","true");
            JSONObject bookingDates = new JSONObject();
            bookingDates.put("checkin","2018-01-01");
            bookingDates.put("checkout","2019-01-01");
            requestParamans.put("bookingdates",bookingDates);
            requestParamans.put("aditionalneeds","Breakfast");


            httpRequest.header("Content-Type","application/json");
            httpRequest.header("Accept","application/json");
            System.out.println(token);
            httpRequest.cookie("token",token);

            httpRequest.body(requestParamans.toJSONString());

            //Response object
            Response response=httpRequest.request(Method.PUT,"/booking/"+ id);

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

        }
        catch (Exception we)
        {
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            stepFail(driver,"Fallo en tiempo de respuesta : " + we.getMessage());
            generateWord.sendText("Fallo en tiempo de respuesta");
            generateWord.addImageToWord(driver);
        }


    }


    public void ObtenerReservas() throws Throwable{

        mensaje = "Obtenemos las reservas de coleccion";

        try {
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
        catch (Exception we)
        {
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            stepFail(driver,"Fallo en tiempo de respuesta : " + we.getMessage());
            generateWord.sendText("Fallo en tiempo de respuesta");
            generateWord.addImageToWord(driver);
        }


    }

    public void EliminarReserva(String id) throws Throwable{

        mensaje = "Eliminar la reserva de coleccion";

        try {
            //Specify base URI
            RestAssured.baseURI="https://restful-booker.herokuapp.com";

            //Request Object
            RequestSpecification httpRequest=RestAssured.given();

            httpRequest.cookie("token",token);
            //Response Object
            Response response = httpRequest.delete("https://restful-booker.herokuapp.com/booking/" + id);


            //print response in console
            String responseBody=response.getBody().asString();
            System.out.println("Response Body is:"+responseBody);

            //status code validation
            int statusCode=response.getStatusCode();
            System.out.println("Status code is: "+statusCode);
            Assert.assertEquals(statusCode, 201);

        }
        catch (Exception we)
        {
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            stepFail(driver,"Fallo en tiempo de respuesta : " + we.getMessage());
            generateWord.sendText("Fallo en tiempo de respuesta");
            generateWord.addImageToWord(driver);
        }

    }

}
