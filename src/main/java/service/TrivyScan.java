package service;

import dto.defectdojo.CreateEngagementDTO;
import dto.defectdojo.ImportScanDTO;
import impl.DefectDojoImpl;

public class TrivyScan implements DefectDojo {
    private DefectDojoImpl defectDojo;

    public TrivyScan() {
        defectDojo = new DefectDojoImpl();
    }

    public Integer createEngagement(String name, String branchTag, Integer product) {
        CreateEngagementDTO createEngagementDTO = new CreateEngagementDTO().getCreateEngagementDTO(name, branchTag, product);
        CreateEngagementDTO responseEngagement = defectDojo.createEngagement(createEngagementDTO);
        return responseEngagement.getId();
    }

    public void uploadScan(String scanType, Integer engagementId, String file) {
        ImportScanDTO importScanDTO = new ImportScanDTO(scanType, engagementId, file);
        defectDojo.importScan(importScanDTO);
    }

    @Override
    public String getTestRunName() {
        return "paymentlinks-security_trivy_integration";
    }

    public Integer getProductId() {
        return 1;
    }
}
