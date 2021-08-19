package com.tsoft.bot.frontend.steps.login;

import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.pages.pages.login.CollectionBooking;
import com.tsoft.bot.frontend.pages.pages.login.LoginPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;
import org.openqa.selenium.WebDriver;

public class LoginSteps {
    private WebDriver driver;
    private final CollectionBooking collectionbooking = new CollectionBooking(driver);
    public String id;

    public LoginSteps() {
        this.driver = Hook.getDriver();
    }

    @Given("^Iniciamos con la generacion de token$")
    public void iniciamosConLaGeneracionDeToken() throws Throwable{
        collectionbooking.autenticacionToken();
        //throw new PendingException();
    }

    @When("^Creamos una reserva de coleccion \"([^\"]*)\"$")
    public void creamosUnaReservaDeColeccion(String arg0) throws Throwable {
        id=collectionbooking.crearReserva();
    }

    @Then("^Modificamos la reserva de coleccion$")
    public void modificamosLaReservaDeColeccion() throws Throwable{
        collectionbooking.ActualizarReserva(id);
    }

    @And("^Obtenemos todas las reservas de colecion$")
    public void obtenemosTodasLasReservasDeColecion() throws Throwable{
        collectionbooking.ObtenerReservas();
    }

    @And("^Eliminamos la reserva de coleccion$")
    public void eliminamosLaReservaDeColeccion() throws Throwable{
        collectionbooking.EliminarReserva(id);
    }
}
