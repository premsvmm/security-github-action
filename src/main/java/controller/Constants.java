package controller;

import enums.ScanRun;

public class Constants {
    public static String DEFECT_DOJO_BASE_URL;
    public static String DEFECT_DOJO_TOKEN;
    public static String UPLOAD_FILE_PATH;
    public static ScanRun SCAN_RUN;
    public static String GITHUB_SHA;
    public static String BRANCH_TAG;
    public static String GITHUB_REPOSITORY;

    public static void setProperties() {
        DEFECT_DOJO_BASE_URL = System.getProperty("DEFECT_DOJO_BASE_URL") == null ? "http://localhost:8080" : System.getProperty("DEFECT_DOJO_BASE_URL");
        DEFECT_DOJO_TOKEN = System.getProperty("DEFECT_DOJO_TOKEN") == null ? "359eb2221f12909b3619c83f54c40f5a9022c71c" : System.getProperty("DEFECT_DOJO_TOKEN");
        SCAN_RUN = System.getProperty("SCAN_RUN") == null ? ScanRun.TRIVY_IMAGE : ScanRun.valueOf(System.getProperty("SCAN_RUN").toUpperCase());
        GITHUB_SHA = System.getenv("GITHUB_SHA") == null ? "96a03ab70c18adbb29ce4e5a825a6379acfdacd3" : System.getenv("GITHUB_SHA");
        BRANCH_TAG = System.getenv("GITHUB_REF") == null ? "security_trivy_integration" : System.getenv("GITHUB_REF");
        UPLOAD_FILE_PATH = System.getProperty("UPLOAD_FILE_PATH") == null ? "/Users/prem.kumar/Desktop/razorpay/payment-links/trivy.sarif" : System.getProperty("UPLOAD_FILE_PATH");
    }
}
