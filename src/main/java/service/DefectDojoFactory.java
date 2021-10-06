package service;

import controller.Constants;
import service.defectdojo.TrivyScan;

public class DefectDojoFactory {

    public static DefectDojoAbstract getRunType() {
        switch (Constants.RUN_TYPE) {
            case TRIVY_IMAGE:
                return new TrivyScan();
            case TRIVY_FILE:
            case SEMGREP:
        }
        return null;
    }
}
