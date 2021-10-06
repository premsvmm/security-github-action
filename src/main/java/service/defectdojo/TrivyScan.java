package service.defectdojo;

import dto.defectdojo.CreateEngagementDTO;
import dto.defectdojo.ImportScanDTO;
import impl.DefectDojoImpl;
import service.DefectDojoAbstract;

public class TrivyScan extends DefectDojoAbstract {

    public TrivyScan() {
        defectDojo = new DefectDojoImpl();
    }

    public Integer createEngagement(CreateEngagementDTO createEngagementDTO) {
        CreateEngagementDTO responseEngagement = defectDojo.createEngagement(createEngagementDTO);
        return responseEngagement.getId();
    }

    public void uploadScan(ImportScanDTO importScanDTO) {
        defectDojo.importScan(importScanDTO);
    }

}
