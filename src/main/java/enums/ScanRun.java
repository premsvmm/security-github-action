package enums;

import lombok.Getter;

public enum ScanRun {
    TRIVY_IMAGE("Trivy Scan"),
    TRIVY_FILE("Trivy Scan"),
    SEMGREP("semgrep");

    @Getter
    private String scanType;

    ScanRun(String scanType){
        this.scanType = scanType;
    }
}
