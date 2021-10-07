package service;

import controller.Constants;
import dto.ProductDTO;
import dto.defectdojo.CreateEngagementDTO;
import dto.defectdojo.FindingsDetailsDTO;
import dto.defectdojo.ImportScanDTO;
import enums.RunType;
import exception.ApiException;
import impl.DefectDojoImpl;
import lombok.Data;

@Data
public abstract class DefectDojoAbstract implements DefectDojo {
    protected RunType runType;
    protected DefectDojoImpl defectDojo;
    protected Integer productId;
    protected Integer engagementId;
    protected String engagementStatus;

    public Boolean validateEngagementPresent() throws ApiException {
        ProductDTO productDTO = defectDojo.getEngagement(getTestRunName(), productId);
        if (productDTO.getCount() == 1) {
            engagementId = productDTO.getResults().get(0).getId();
            engagementStatus = productDTO.getResults().get(0).getStatus();
            return true;
        } else {
            return false;
        }
    }

    public Boolean validateProductExists(String productName) throws ApiException {
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

    public Integer createEngagement(CreateEngagementDTO createEngagementDTO) throws ApiException {
        CreateEngagementDTO responseEngagement = defectDojo.createEngagement(createEngagementDTO);
        engagementId = responseEngagement.getId();
        return responseEngagement.getId();
    }

    public void uploadScan(ImportScanDTO importScanDTO) throws ApiException {
        defectDojo.importScan(importScanDTO);
    }

    public FindingsDetailsDTO getEngagementFindingCounts() throws ApiException {
        ProductDTO productDTO = defectDojo.getEngagementFindingsCount(engagementId);
        FindingsDetailsDTO findingsDetailsDTO = new FindingsDetailsDTO(productDTO.getResults());
        System.out.println("Critical : " + findingsDetailsDTO.getCriticalCount());
        System.out.println("High : " + findingsDetailsDTO.getHighCount());
        System.out.println("Medium : " + findingsDetailsDTO.getMediumCount());
        System.out.println("Low : " + findingsDetailsDTO.getLowCount());
        System.out.println(findingsDetailsDTO.getIssueDetails());
        return findingsDetailsDTO;
    }

}
