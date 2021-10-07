package service;

import dto.defectdojo.CreateEngagementDTO;
import dto.defectdojo.ImportScanDTO;
import exception.ApiException;

public interface DefectDojo {
    public Integer createEngagement(CreateEngagementDTO createEngagementDTO) throws ApiException;

    public Integer getProductId();

    public void uploadScan(ImportScanDTO importScanDTO) throws ApiException;

    public String getTestRunName();

    public Boolean validateProductExists(String productName) throws ApiException;

    public Boolean validateEngagementPresent() throws ApiException;

    public Boolean closeEngagement(Integer engagementId);

    public Boolean reopenEngagement(Integer engagementId);
}
