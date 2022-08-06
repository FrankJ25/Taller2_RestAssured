package factoryRequest;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestDELETE implements IRequest{
    @Override
    public Response send(RequestInformation requestInformation) {
        Response response = given()
                    .headers(requestInformation.getHeaders())
                    .log().all()
                .when()
                    .delete(requestInformation.getUrl());

        response.then().log().all();

        return response;
    }
}
