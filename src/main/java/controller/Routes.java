package controller;

import lombok.Getter;

public enum Routes {
    DEFECTDOJO_CREATE_ENGAGMENT("/api/v2/engagements/"),
    DEFECTDOJO_GET_PRODUCT("/api/v2/products"),
    DEFECTDOJO_GET_ENGAGEMENT("/api/v2/engagements/"),
    DEFECTDOJO_CLOSE_ENGAGEMENT("/api/v2/engagements/%s/close/"),
    DEFECTDOJO_REOPEN_ENGAGEMENT("/api/v2/engagements/%s/reopen/"),
    DEFECTDOJO_FINDINGS("/api/v2/findings"),
    DEFECTDOJO_IMPORT_SCAN("/api/v2/import-scan/"),
    GITHUB_REPO_URL("/repos/%s/pulls/%s");


    @Getter
    private String pathVariable;

    Routes(String routes) {
        this.pathVariable = routes;
    }

    public String DefectDojoURL(String... args) {
        return Constants.DEFECT_DOJO_BASE_URL + String.format(this.pathVariable, args);
    }

    public String GithubURL(String... args) {
        return Constants.GITHUB_API_URL + String.format(this.pathVariable, args);
    }

}
