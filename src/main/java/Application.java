import controller.Constants;
import dto.defectdojo.CreateEngagementDTO;
import dto.defectdojo.ImportScanDTO;
import service.DefectDojoAbstract;
import service.DefectDojoFactory;

import static controller.Constants.*;

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

    public static void uploadReportToDefectDojo() throws Exception {
        DefectDojoAbstract defectDojo = DefectDojoFactory.getRunType();
        validateProductExists(defectDojo);
        Integer engagementId = createEngagementIfNotPresentAndReturnEngagementId(defectDojo);
        uploadScan(defectDojo);
        Boolean result = defectDojo.closeEngagement(engagementId);
        System.out.println(result == true ? ANSI_GREEN_BACKGROUND + "Engagement closed" + ANSI_RESET : ANSI_RED_BACKGROUND + "Engagement Failed to closed" + ANSI_RESET);
    }

    public static void validateProductExists(DefectDojoAbstract defectDojo) throws Exception {
        if (defectDojo.validateProductExists(Constants.GITHUB_REPOSITORY)) {
            return;
        }
        throw new Exception(ANSI_RED_BACKGROUND + "Please Create Project In Defect Dojo For Product : " + Constants.GITHUB_REPOSITORY + ANSI_RESET);
    }

    public static Integer createEngagementIfNotPresentAndReturnEngagementId(DefectDojoAbstract defectDojo) {
        if (defectDojo.validateEngagementPresent()) {
            reOpenEngagementIfCompleted(defectDojo);
            return defectDojo.getEngagementId();
        } else {
            CreateEngagementDTO createEngagementDTO = new CreateEngagementDTO().getCreateEngagementDTO(defectDojo.getTestRunName(), defectDojo.getProductId());
            return defectDojo.createEngagement(createEngagementDTO);
        }
    }

    public static void reOpenEngagementIfCompleted(DefectDojoAbstract defectDojo) {
        if (defectDojo.getEngagementStatus().equalsIgnoreCase("Completed")) {
            defectDojo.reopenEngagement(defectDojo.getEngagementId());
        }
    }

    public static void uploadScan(DefectDojoAbstract defectDojo) {
        ImportScanDTO importScanDTO = new ImportScanDTO(defectDojo.getRunType(), defectDojo.getEngagementId());
        defectDojo.uploadScan(importScanDTO);
    }
}
