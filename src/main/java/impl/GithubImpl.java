package impl;

import controller.Authorization;
import controller.Constants;
import controller.Routes;
import dto.ApiRequestSpecification;
import enums.HttpMethod;
import exception.ApiException;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import util.Apiclient;

public class GithubImpl extends Apiclient {

    public void publishResult(String payload) throws ApiException {
        ApiRequestSpecification.ApiRequestSpecificationBuilder apiRequestSpecificationBuilder = ApiRequestSpecification.builder();
        apiRequestSpecificationBuilder.baseUrl(Routes.GITHUB_COMMENT_ON_REPO.GithubURL(Constants.GITHUB_REPOSITORY_WITH_OWNER, Constants.PR_NUM))
                .body(payload)
                .authorization(Authorization.GITHUB)
                .contentType(ContentType.JSON);
        Response response = executeRequest(HttpMethod.POST, apiRequestSpecificationBuilder.build());
        ApiException.validateApiResponse(response, 201);
    }
}
