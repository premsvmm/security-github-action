package service;

import dto.defectdojo.CreateEngagementDTO;
import dto.defectdojo.ImportScanDTO;
import enums.RunType;

public interface DefectDojo {
    public Integer createEngagement(CreateEngagementDTO createEngagementDTO);

    public Integer getProductId();

    public void uploadScan(ImportScanDTO importScanDTO);

    public String getTestRunName(RunType runType);

    public Boolean validateProductExists(String productName);

    public Boolean validateEngagementPresent(RunType runType);

    public Boolean closeEngagement(Integer engagementId);

    public Boolean reopenEngagement(Integer engagementId);
}
