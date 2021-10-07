package enums;

import lombok.Getter;

public enum RunType {
    TRIVY_IMAGE("Trivy Scan"),
    TRIVY_FILE("Trivy Scan"),
    SEMGREP("Semgrep JSON Report");

    @Getter
    private String runType;

    RunType(String runType) {
        this.runType = runType;
    }
}
