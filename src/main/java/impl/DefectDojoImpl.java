package impl;

import controller.Authorization;
import controller.Routes;
import dto.ApiMultiPartSpecBuilder;
import dto.ApiRequestSpecification;
import dto.defectdojo.CreateEngagementDTO;
import dto.defectdojo.ImportScanDTO;
import enums.HttpMethod;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import util.Apiclient;
import util.Utils;

import java.util.HashMap;
import java.util.Map;

public class DefectDojoImpl extends Apiclient {

    public void getProductType() {

    }

    public void createProduct() {

    }

    public void getProduct() {

    }

    public CreateEngagementDTO createEngagement(CreateEngagementDTO createEngagementRequestDTO) {
        ApiRequestSpecification.ApiRequestSpecificationBuilder apiRequestSpecificationBuilder = ApiRequestSpecification.builder();
        apiRequestSpecificationBuilder.baseUrl(Routes.DEFECTDOJO_CREATE_ENGAGMENT.DefectDojoURL())
                .body(Utils.convertDTOToJson(createEngagementRequestDTO))
                .authorization(Authorization.DEFECT_DOJO)
                .contentType(ContentType.JSON);
        Response response = executeRequest(HttpMethod.POST, apiRequestSpecificationBuilder.build());
        return (CreateEngagementDTO) Utils.covertResponseToDto(response, CreateEngagementDTO.class.getName());
    }

    public void getEngagement() {

    }

    public void importScan(ImportScanDTO importScanDTO) {
        Map<String, String> formParams = new HashMap<String, String>();
        formParams.put("scan_type", importScanDTO.getScanType());
        formParams.put("engagement", String.valueOf(importScanDTO.getEngagement()));
        formParams.put("file", importScanDTO.getFile());
        ApiRequestSpecification.ApiRequestSpecificationBuilder apiRequestSpecificationBuilder = ApiRequestSpecification.builder();
        apiRequestSpecificationBuilder.baseUrl(Routes.DEFECTDOJO_IMPORT_SCAN.DefectDojoURL())
                .formParams(formParams)
                .body(Utils.convertDTOToJson(importScanDTO))
                .authorization(Authorization.DEFECT_DOJO)
                .multiPartSpecBuilder(ApiMultiPartSpecBuilder.builder().content(formParams).controlName("file").fileName(importScanDTO.getFile()).mimeType(ContentType.MULTIPART).build())
                .contentType(ContentType.MULTIPART);
        Response response = executeRequest(HttpMethod.POST, apiRequestSpecificationBuilder.build());
    }

}
