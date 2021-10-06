package service;

import controller.Constants;
import dto.ProductDTO;
import dto.defectdojo.CreateEngagementDTO;
import dto.defectdojo.ImportScanDTO;
import enums.RunType;
import impl.DefectDojoImpl;
import lombok.Data;

@Data
public abstract class DefectDojoAbstract implements DefectDojo {
    protected RunType runType;
    protected DefectDojoImpl defectDojo;
    protected Integer productId;
    protected Integer engagementId;
    protected String engagementStatus;

    public String getTestRunName() {
        return runType.getRunType() + " : " + Constants.GITHUB_REPOSITORY + "/" + Constants.GITHUB_REF;
    }

    public Boolean validateEngagementPresent() {
        ProductDTO productDTO = defectDojo.getEngagement(getTestRunName(), productId);
        if (productDTO.getCount() == 1) {
            engagementId = productDTO.getResults().get(0).getId();
            engagementStatus = productDTO.getResults().get(0).getStatus();
            return true;
        } else {
            return false;
        }
    }

    public Boolean validateProductExists(String productName) {
        ProductDTO productDTO = defectDojo.getProduct(productName);
        if (productDTO.getCount() == 1) {
            productId = productDTO.getResults().get(0).getId();
            return true;
        } else {
            return false;
        }
    }

    public Boolean closeEngagement(Integer engagementId) {
        return defectDojo.closeEngagement(engagementId);
    }

    public Boolean reopenEngagement(Integer engagementId) {
        return defectDojo.reOpenEngagement(engagementId);
    }

    public Integer createEngagement(CreateEngagementDTO createEngagementDTO) {
        CreateEngagementDTO responseEngagement = defectDojo.createEngagement(createEngagementDTO);
        return responseEngagement.getId();
    }

    public void uploadScan(ImportScanDTO importScanDTO) {
        defectDojo.importScan(importScanDTO);
    }

}
