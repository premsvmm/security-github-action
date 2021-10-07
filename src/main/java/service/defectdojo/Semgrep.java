package service.defectdojo;

import controller.Constants;
import enums.RunType;
import impl.DefectDojoImpl;
import service.DefectDojoAbstract;

public class Semgrep extends DefectDojoAbstract {

    public Semgrep() {
        defectDojo = new DefectDojoImpl();
        runType = RunType.SEMGREP;
    }

    public String getTestRunName() {
        return "Semgrep " + " : " + Constants.GITHUB_REPOSITORY + "/" + Constants.GITHUB_REF;
    }
}
