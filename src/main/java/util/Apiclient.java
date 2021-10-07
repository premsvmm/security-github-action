package util;

import controller.Constants;
import dto.ApiMultiPartSpecBuilder;
import dto.ApiRequestSpecification;
import enums.HttpMethod;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static controller.Constants.ANSI_RESET;
import static controller.Constants.ANSI_YELLOW;

public class Apiclient {

    public Response executeRequest(HttpMethod httpMethod, ApiRequestSpecification apiRequestSpecification) {
        if (apiRequestSpecification.getAuthorization() != null) {
            Map<String, String> headers = null;
            if (apiRequestSpecification.getHeader() == null) {
                headers = new HashMap<>();
            } else {
                headers = apiRequestSpecification.getHeader();
            }
            switch (apiRequestSpecification.getAuthorization()) {
                case DEFECT_DOJO:
                    headers.put("Authorization", "Token " + Constants.DEFECT_DOJO_TOKEN);
                    apiRequestSpecification.setHeader(headers);
                    break;
            }
        }

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        if (apiRequestSpecification.getBody() != null) {
            requestSpecBuilder.setBody(apiRequestSpecification.getBody());
        }
        if (apiRequestSpecification.getContentType() != null) {
            requestSpecBuilder.setContentType(apiRequestSpecification.getContentType());
        }
        if (apiRequestSpecification.getHeader() != null) {
            requestSpecBuilder.addHeaders(apiRequestSpecification.getHeader());
        }
        if (apiRequestSpecification.getFormParams() != null) {
            requestSpecBuilder.addFormParams(apiRequestSpecification.getFormParams());
        }
        if (apiRequestSpecification.getMultiPartSpecBuilder() != null) {
            ApiMultiPartSpecBuilder multiPart = apiRequestSpecification.getMultiPartSpecBuilder();
            requestSpecBuilder.addMultiPart(multiPart.getControlName(), new File(multiPart.getFileName()), multiPart.getMimeType().toString());
        }
        if (apiRequestSpecification.getPathParams() != null) {
            requestSpecBuilder.addPathParams(apiRequestSpecification.getPathParams());
        }
        if (apiRequestSpecification.getQueryParams() != null) {
            requestSpecBuilder.addQueryParams(apiRequestSpecification.getQueryParams());
        }
        System.out.println("**************************************");
        System.out.println(ANSI_YELLOW + "HTTP METHOD : " + httpMethod + ANSI_RESET);
        System.out.println("BASE URL : " + apiRequestSpecification.getBaseUrl());
        System.out.println("HEADERS : ");
        for (String key : apiRequestSpecification.getHeader().keySet()) {
            if (!key.equalsIgnoreCase("Authorization"))
                System.out.print(key + " : " + apiRequestSpecification.getHeader().get(key)+" ");
        }
        System.out.println("FORM DATA : " + apiRequestSpecification.getFormParams());
        System.out.println("BODY : " + apiRequestSpecification.getBody());
        return execute(apiRequestSpecification.getBaseUrl(), httpMethod, requestSpecBuilder.build());
    }

    private Response execute(String baseUrl, HttpMethod httpMethod, RequestSpecification requestSpecification) {
        Response response = null;
        switch (httpMethod) {
            case POST:
                response = RestAssured.given().spec(requestSpecification).when().baseUri(baseUrl).post();
                break;
            case GET:
                response = RestAssured.given().spec(requestSpecification).when().baseUri(baseUrl).get();
                break;
        }
        System.out.println(ANSI_YELLOW + "Response:" + ANSI_RESET);
        System.out.println(response.getBody().asString());
        return response;
    }
}
