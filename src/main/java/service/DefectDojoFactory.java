package service;

import controller.Constants;
import service.defectdojo.Semgrep;
import service.defectdojo.TrivyFs;
import service.defectdojo.TrivyScan;

public class DefectDojoFactory {

    public static DefectDojoAbstract getRunType() {
        switch (Constants.RUN_TYPE) {
            case TRIVY_IMAGE:
                return new TrivyScan();
            case TRIVY_FILE:
                return new TrivyFs();
            case SEMGREP:
                return new Semgrep();
        }
        return null;
    }
}
