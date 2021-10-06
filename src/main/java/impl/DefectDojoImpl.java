package impl;

import controller.Authorization;
import controller.Routes;
import dto.ApiMultiPartSpecBuilder;
import dto.ApiRequestSpecification;
import dto.ProductDTO;
import dto.defectdojo.CreateEngagementDTO;
import dto.defectdojo.ImportScanDTO;
import enums.HttpMethod;
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

    public ProductDTO getProduct(String productName) {
        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put("name", productName);
        ApiRequestSpecification.ApiRequestSpecificationBuilder apiRequestSpecificationBuilder = ApiRequestSpecification.builder();
        apiRequestSpecificationBuilder.baseUrl(Routes.DEFECTDOJO_GET_PRODUCT.DefectDojoURL())
                .queryParams(queryParam)
                .authorization(Authorization.DEFECT_DOJO)
                .contentType(ContentType.JSON);
        Response response = executeRequest(HttpMethod.GET, apiRequestSpecificationBuilder.build());
        return (ProductDTO) Utils.covertResponseToDto(response, ProductDTO.class.getName());
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

    public ProductDTO getEngagement(String engagementName, Integer productId) {
        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put("name", engagementName);
        queryParam.put("product", productId);
        ApiRequestSpecification.ApiRequestSpecificationBuilder apiRequestSpecificationBuilder = ApiRequestSpecification.builder();
        apiRequestSpecificationBuilder.baseUrl(Routes.DEFECTDOJO_GET_ENGAGEMENT.DefectDojoURL())
                .queryParams(queryParam)
                .authorization(Authorization.DEFECT_DOJO)
                .contentType(ContentType.JSON);
        Response response = executeRequest(HttpMethod.GET, apiRequestSpecificationBuilder.build());
        return (ProductDTO) Utils.covertResponseToDto(response, ProductDTO.class.getName());
    }

    public void importScan(ImportScanDTO importScanDTO) {
        Map<String, String> formParams = new HashMap<String, String>();
        formParams.put("scan_type", importScanDTO.getScanType());
        formParams.put("engagement", String.valueOf(importScanDTO.getEngagement()));
        formParams.put("file", importScanDTO.getFile());
        formParams.put("commit_hash", importScanDTO.getCommitHash());
        formParams.put("branch_tag", importScanDTO.getBranchTag());
        ApiRequestSpecification.ApiRequestSpecificationBuilder apiRequestSpecificationBuilder = ApiRequestSpecification.builder();
        apiRequestSpecificationBuilder.baseUrl(Routes.DEFECTDOJO_IMPORT_SCAN.DefectDojoURL())
                .formParams(formParams)
                .body(Utils.convertDTOToJson(importScanDTO))
                .authorization(Authorization.DEFECT_DOJO)
                .multiPartSpecBuilder(ApiMultiPartSpecBuilder.builder().content(formParams).controlName("file").fileName(importScanDTO.getFile()).mimeType(ContentType.MULTIPART).build())
                .contentType(ContentType.MULTIPART);
        Response response = executeRequest(HttpMethod.POST, apiRequestSpecificationBuilder.build());
    }

    public Boolean closeEngagement(Integer engagementId) {
        ApiRequestSpecification.ApiRequestSpecificationBuilder apiRequestSpecificationBuilder = ApiRequestSpecification.builder();
        apiRequestSpecificationBuilder.baseUrl(Routes.DEFECTDOJO_CLOSE_ENGAGEMENT.DefectDojoURL(String.valueOf(engagementId)))
                .authorization(Authorization.DEFECT_DOJO)
                .contentType(ContentType.JSON);
        Response response = executeRequest(HttpMethod.POST, apiRequestSpecificationBuilder.build());
        if (response.statusCode() == 200)
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }

    public Boolean reOpenEngagement(Integer engagementId) {
        ApiRequestSpecification.ApiRequestSpecificationBuilder apiRequestSpecificationBuilder = ApiRequestSpecification.builder();
        apiRequestSpecificationBuilder.baseUrl(Routes.DEFECTDOJO_REOPEN_ENGAGEMENT.DefectDojoURL(String.valueOf(engagementId)))
                .authorization(Authorization.DEFECT_DOJO)
                .contentType(ContentType.JSON);
        Response response = executeRequest(HttpMethod.POST, apiRequestSpecificationBuilder.build());
        if (response.statusCode() == 200)
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }

}
