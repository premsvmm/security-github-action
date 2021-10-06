import controller.Constants;
import enums.ScanRun;
import service.DefectDojo;
import service.TrivyScan;

public class Application {
    public static void main(String[] args) {
        Constants.setProperties();
        uploadReportToDefectDojo();
    }

    public static void uploadReportToDefectDojo() {
        switch (Constants.SCAN_RUN) {
            case TRIVY_IMAGE:
                DefectDojo defectDojoTrivyScane = new TrivyScan();
                Integer engementId=defectDojoTrivyScane.createEngagement(defectDojoTrivyScane.getTestRunName(), Constants.BRANCH_TAG, defectDojoTrivyScane.getProductId());
                defectDojoTrivyScane.uploadScan(ScanRun.TRIVY_IMAGE.getScanType(),engementId,Constants.UPLOAD_FILE_PATH);
                break;
        }
    }
}
