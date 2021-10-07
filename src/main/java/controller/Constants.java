package controller;

import enums.RunType;

public class Constants {
    public static String DEFECT_DOJO_BASE_URL;
    public static String DEFECT_DOJO_TOKEN;
    public static String UPLOAD_FILE_PATH;
    public static RunType RUN_TYPE;
    public static String GITHUB_SHA;
    public static String GITHUB_REF;
    public static String GITHUB_REPOSITORY;
    public static String PR_NUM;
    public static String GITHUB_API_URL;
    public static String GITHUB_REPOSITORY_WITH_OWNER;
    public static String GITHUB_TOKEN;
    //colour Format
    public static String ANSI_YELLOW = "\u001B[33m";
    public static String ANSI_RESET = "\u001B[0m";
    public static String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static String ANSI_GREEN_BACKGROUND = "\u001B[42m";

    public static void setProperties() {
        DEFECT_DOJO_BASE_URL = System.getProperty("DEFECT_DOJO_BASE_URL") == null ? "http://localhost:8080" : System.getProperty("DEFECT_DOJO_BASE_URL");
        DEFECT_DOJO_TOKEN = System.getProperty("DEFECT_DOJO_TOKEN") == null ? "359eb2221f12909b3619c83f54c40f5a9022c71c" : System.getProperty("DEFECT_DOJO_TOKEN");
        RUN_TYPE = System.getProperty("RUN_TYPE") == null ? RunType.TRIVY_IMAGE : RunType.valueOf(System.getProperty("RUN_TYPE").toUpperCase());
        GITHUB_SHA = System.getenv("GITHUB_SHA") == null ? "96a03ab70c18adbb29ce4e5a825a6379acfdacd3" : System.getenv("GITHUB_SHA");
        GITHUB_REF = System.getenv("GITHUB_REF") == null ? "security_trivy_integration" : System.getenv("GITHUB_REF");
        UPLOAD_FILE_PATH = System.getProperty("UPLOAD_FILE_PATH") == null ? "/Users/prem.kumar/Desktop/razorpay/payment-links/trivy-results.sarif" : System.getProperty("UPLOAD_FILE_PATH");
        GITHUB_REPOSITORY = System.getenv("GITHUB_REPOSITORY") == null ? "razorpay/payment-links" : System.getenv("GITHUB_REPOSITORY");
        GITHUB_REPOSITORY_WITH_OWNER = System.getenv("GITHUB_REPOSITORY") == null ? "razorpay/payment-links" : System.getenv("GITHUB_REPOSITORY");
        GITHUB_API_URL = System.getenv("GITHUB_API_URL") == null ? "https://api.github.com" : System.getenv("GITHUB_API_URL");
        GITHUB_TOKEN = System.getProperty("GITHUB_TOKEN") == null ? "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" : System.getProperty("GITHUB_TOKEN");
        PR_NUM = System.getProperty("PR_NUM") == null ? "941" : System.getProperty("PR_NUM");

        //Condition To Format Constants
        if (GITHUB_REPOSITORY.contains("/")) {
            GITHUB_REPOSITORY = GITHUB_REPOSITORY.split("/")[1];
        }

        if (GITHUB_REF.contains("refs/heads/")) {
            GITHUB_REF = GITHUB_REF.replace("refs/heads/", "");
        }
    }
}
