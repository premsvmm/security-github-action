package dto;

import controller.Authorization;
import io.restassured.http.ContentType;
import io.restassured.specification.MultiPartSpecification;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class ApiRequestSpecification {
    private String baseUrl;
    private Object body;
    private Map<String, String> header;
    private ContentType contentType;
    private Map<String, Object> pathParams;
    private Map<String, Object> queryParams;
    private Map<String, String> formParams;
    private ApiMultiPartSpecBuilder multiPartSpecBuilder;
    private Authorization authorization;
}
