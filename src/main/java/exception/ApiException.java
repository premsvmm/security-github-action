package exception;

import io.restassured.response.Response;
import lombok.Data;

import static controller.Constants.ANSI_RED_BACKGROUND;
import static controller.Constants.ANSI_RESET;

@Data
public class ApiException extends Exception {
    private Response response;
    private String message;

    public static Boolean validateApiResponse(Response response, int statusCode) throws ApiException {
        if (response.getStatusCode() != statusCode) {
            throw new ApiException(ANSI_RED_BACKGROUND+"Server Encounter an issue : API Response Return Status Code : " + response.getStatusCode()+ANSI_RESET, response);
        }
        return true;
    }

    public ApiException(String msg, Response response) {
        super(msg);
        this.message = msg;
        this.response = response;
    }

    public ApiException(String msg) {
        super(msg);
    }
}


