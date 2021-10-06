package dto;

import io.restassured.http.ContentType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiMultiPartSpecBuilder {
    //the body of the payload
    private Object content;
    //name of the field
    private String controlName;
    private ContentType mimeType;
    private String charset;
    private String fileName;
}
