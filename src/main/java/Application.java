import controller.Constants;
import dto.defectdojo.CreateEngagementDTO;
import dto.defectdojo.ImportScanDTO;
import enums.RunType;
import service.DefectDojoAbstract;
import service.DefectDojoFactory;

public class Application {
    public static void main(String[] args) {
        try {
            Constants.setProperties();
            uploadReportToDefectDojo();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void uploadReportToDefectDojo() {
        DefectDojoAbstract defectDojo = DefectDojoFactory.getRunType();
        if (defectDojo.validateProductExists(Constants.GITHUB_REPOSITORY)) {
            Integer engagementId = null;
            if (!defectDojo.validateEngagementPresent(RunType.TRIVY_IMAGE)) {
                CreateEngagementDTO createEngagementDTO = new CreateEngagementDTO().getCreateEngagementDTO(defectDojo.getTestRunName(RunType.TRIVY_IMAGE), defectDojo.getProductId());
                engagementId = defectDojo.createEngagement(createEngagementDTO);
            } else {
                engagementId = defectDojo.getEngagementId();
                if (defectDojo.getEngagementStatus().equalsIgnoreCase("Completed")) {
                    defectDojo.reopenEngagement(engagementId);
                }
            }
            ImportScanDTO importScanDTO = new ImportScanDTO(RunType.TRIVY_IMAGE, engagementId);
            defectDojo.uploadScan(importScanDTO);
            Boolean result = defectDojo.closeEngagement(engagementId);
            System.out.println(result == true ? "Engagement closed" : "Engagement Failed to closed");
        } else {
            System.out.println("Please Create Project In Defect Dojo For Product : " + Constants.GITHUB_REPOSITORY);
        }
    }
}
