import controller.Constants;
import enums.ScanRun;
import service.DefectDojo;
import service.TrivyScan;

import java.util.Properties;

public class Application {
    public static void main(String[] args) {
        Constants.setProperties();
        System.out.println("All System Properties");
        Properties properties = System.getProperties();
        properties.forEach((k, v) -> System.out.println(k + ":" + v));
        System.out.println("All Environement Variable");
        System.out.println(System.getenv());
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
