package service.defectdojo;

import controller.Constants;
import enums.RunType;
import impl.DefectDojoImpl;
import service.DefectDojoAbstract;

public class TrivyScan extends DefectDojoAbstract {

    public TrivyScan() {
        defectDojo = new DefectDojoImpl();
        runType = RunType.TRIVY_IMAGE;
    }

    public String getTestRunName() {
        return "Trivy Scan" + " : " + Constants.GITHUB_REPOSITORY + "/" + Constants.GITHUB_REF;
    }
}
