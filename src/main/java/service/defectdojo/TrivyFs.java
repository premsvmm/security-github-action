package service.defectdojo;

import controller.Constants;
import enums.RunType;
import impl.DefectDojoImpl;
import service.DefectDojoAbstract;

public class TrivyFs extends DefectDojoAbstract {

    public TrivyFs() {
        defectDojo = new DefectDojoImpl();
        runType = RunType.TRIVY_FILE;
    }

    public String getTestRunName() {
        return "Trivy FS" + " : " + Constants.GITHUB_REPOSITORY + "/" + Constants.GITHUB_REF;
    }
}
