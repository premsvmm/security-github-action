package service;

import controller.Constants;
import dto.ProductDTO;
import enums.RunType;
import impl.DefectDojoImpl;
import lombok.Data;

@Data
public abstract class DefectDojoAbstract implements DefectDojo {
    protected DefectDojoImpl defectDojo;
    protected Integer productId;
    protected Integer engagementId;
    protected String engagementStatus;

    public String getTestRunName(RunType runType) {
        return runType.getRunType() + " : " + Constants.GITHUB_REPOSITORY + "/" + Constants.GITHUB_REF;
    }

    public Boolean validateEngagementPresent(RunType runType) {
        ProductDTO productDTO = defectDojo.getEngagement(getTestRunName(runType), productId);
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

}
