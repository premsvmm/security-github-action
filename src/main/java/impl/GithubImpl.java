package impl;

import controller.Authorization;
import controller.Constants;
import controller.Routes;
import dto.ApiRequestSpecification;
import dto.defectdojo.CreateEngagementDTO;
import enums.HttpMethod;
import exception.ApiException;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import util.Apiclient;
import util.Utils;

public class GithubImpl extends Apiclient {

    public void publishResult(String payload) throws ApiException {
        ApiRequestSpecification.ApiRequestSpecificationBuilder apiRequestSpecificationBuilder = ApiRequestSpecification.builder();
        apiRequestSpecificationBuilder.baseUrl(Routes.GITHUB_REPO_URL.GithubURL(Constants.GITHUB_REPOSITORY_WITH_OWNER,Constants.PR_NUM))
                .body(payload)
                .authorization(Authorization.GITHUB)
                .contentType(ContentType.JSON);
        Response response = executeRequest(HttpMethod.POST, apiRequestSpecificationBuilder.build());
        ApiException.validateApiResponse(response, 200);
    }
}
