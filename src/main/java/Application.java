import controller.Constants;
import dto.defectdojo.CreateEngagementDTO;
import dto.defectdojo.FindingsDetailsDTO;
import dto.defectdojo.ImportScanDTO;
import exception.ApiException;
import service.DefectDojoAbstract;
import service.DefectDojoFactory;
import service.GithubService;

import static controller.Constants.*;

public class Application {

    public static void main(String[] args) {
        try {
            Constants.setProperties();
            uploadReportToDefectDojo();
            printMessage(true, "success");
            System.exit(0);
        } catch (ApiException e) {
            System.out.println(e.getMessage());
            printMessage(false, "failed");
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            printMessage(false, "failed");
            System.exit(1);
        }
    }

    public static void uploadReportToDefectDojo() throws Exception {
        DefectDojoAbstract defectDojo = DefectDojoFactory.getRunType();
        validateProductExists(defectDojo);
        createEngagementIfNotPresentAndReturnEngagementId(defectDojo);
        uploadScan(defectDojo);
        closeEngagement(defectDojo);
        FindingsDetailsDTO findingsDetailsDTO = defectDojo.getEngagementFindingCounts();
        GithubService githubService = new GithubService();
        githubService.sentFindingReportDetails(findingsDetailsDTO);
    }

    public static void validateProductExists(DefectDojoAbstract defectDojo) throws Exception {
        if (defectDojo.validateProductExists(Constants.GITHUB_REPOSITORY)) {
            return;
        }
        throw new ApiException(ANSI_RED_BACKGROUND + "Please Create Project In Defect Dojo For Product : " + Constants.GITHUB_REPOSITORY + ANSI_RESET);
    }

    public static Integer createEngagementIfNotPresentAndReturnEngagementId(DefectDojoAbstract defectDojo) throws ApiException {
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

    public static void uploadScan(DefectDojoAbstract defectDojo) throws ApiException {
        ImportScanDTO importScanDTO = new ImportScanDTO(defectDojo.getRunType(), defectDojo.getEngagementId());
        defectDojo.uploadScan(importScanDTO);
    }

    public static void closeEngagement(DefectDojoAbstract defectDojo) {
        Boolean result = defectDojo.closeEngagement(defectDojo.getEngagementId());
        System.out.println(result == true ? ANSI_GREEN_BACKGROUND + "Engagement closed" + ANSI_RESET : ANSI_RED_BACKGROUND + "Engagement Failed to closed" + ANSI_RESET);
    }

    public static void printMessage(Boolean status, String message) {
        if (status) {
            System.out.println(ANSI_GREEN_BACKGROUND + message + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED_BACKGROUND + message + ANSI_RESET);
        }
    }
}
