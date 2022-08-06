package steps;

import config.ApiConfig;
import config.EnvConfig;
import factoryRequest.FactoryRequest;
import factoryRequest.RequestInformation;
import helper.JsonHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class MyStepdefs {

    Response response;
    RequestInformation information = new RequestInformation();
    Map<String,String> variables = new HashMap<>();

    @Given("I have access to Todo.ly API")
    public void iHaveAccessToTodoLyAPI() {

    }

    @And("I get the token to use in the next request")
    public void iGetTheTokenToUseInTheNextRequest() {
        String base64= Base64.getEncoder().encodeToString((EnvConfig.user+":"+EnvConfig.pwd).getBytes());

        RequestInformation infoAuthBasic = new RequestInformation();
        infoAuthBasic.setUrl(EnvConfig.host+"/api/authentication/token.json")
                .setHeaders(ApiConfig.AUTHORIZATION,"Basic "+base64);

        response= FactoryRequest.make("get").send(infoAuthBasic);
        String token= response.then().extract().path("TokenString");

        information.setHeaders(ApiConfig.TOKEN,token);
    }

    @When("I send a {} request {} with body")
    public void iSendAPOSTRequestApiItemsJsonWithBody(String method,String url,String body) {
        information.setUrl(EnvConfig.host+replaceVar(url)).setBody(replaceVar(body));
        response=FactoryRequest.make(method.toLowerCase()).send(information);
    }

    private String replaceVar(String value){
        for ( String key: variables.keySet()) {
            value=value.replace(key,variables.get(key));
        }
        return value;
    }

    @Then("the response code should be {int}")
    public void theResponseCodeShouldBe(int expectedResult) {
        response.then().statusCode(expectedResult);
    }

    @And("the response body should be")
    public void theResponseBodyShouldBe(String expectedResultJson) {
        boolean isEqual= JsonHelper.assertEqualJson(replaceVar(expectedResultJson),response.body().asString());
        Assertions.assertTrue(isEqual,"ERROR! los json son diferentes");
    }

    @And("I save the {string} in the variable: {string}")
    public void iSaveTheInTheVariable(String attributeJson, String variable) {
        String value= response.then().extract().path(attributeJson)+"";
        variables.put(variable,value);
        System.out.println("INFO> Guardando el valor "+variables.get(variable)+" en la variable: "+variable);
    }
}