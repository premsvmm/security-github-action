package service;

import dto.defectdojo.FindingsDetailsDTO;
import exception.ApiException;
import impl.GithubImpl;
import lombok.Data;

import java.text.DecimalFormat;

@Data
public class GithubService {
    private GithubImpl github;

    public GithubService() {
        github = new GithubImpl();
    }

    public void sentFindingReportDetails(FindingsDetailsDTO findingsDetailsDTO) throws ApiException {
        github.publishResult(buildPayload(getSecurityIssueDetails(findingsDetailsDTO), generateGChart(findingsDetailsDTO)));
    }


    public static String buildPayload(String message, String resultsChart) {
        message = message.replaceAll("\n", "\\\\n");
        String payload = "{\"body\":\"" + message + "![image-title](" + resultsChart + ")\"}";
        return payload;
    }

    public static String getSecurityIssueDetails(FindingsDetailsDTO findingsDetailsDTO) {
        StringBuilder details = new StringBuilder();
        details.append("| Severity | Issue Title |" + "\n");
        details.append("| --- | --- |" + "\n");
        for (String key : findingsDetailsDTO.getIssueDetails().keySet()) {
            details.append("| `" + findingsDetailsDTO.getIssueDetails().get(key) + "` |  **" + key + "** |" + "\n");
        }
        details.append("\n\n\n");
        return details.toString();
    }

    private String generateGChart(FindingsDetailsDTO findingsDetailsDTO) {
        DecimalFormat df2 = new DecimalFormat();
        df2.setMaximumFractionDigits(0);
        int total = findingsDetailsDTO.getCriticalCount() + findingsDetailsDTO.getHighCount() + findingsDetailsDTO.getMediumCount();
        if (total == 0) {
            total = -1;
        }
        float criticalP = (findingsDetailsDTO.getCriticalCount() * 100) / total;
        float highP = (findingsDetailsDTO.getHighCount() * 100) / total;
        float mediumP = (findingsDetailsDTO.getMediumCount() * 100) / total;
        String urlBegin = "https://chart.apis.google.com/chart?cht=p&chs=500x250&chdl=Critical|High|Medium&chl=%s|%s|%s&chco=2eb82e|ff4d4d|ffd27f&chts=000000,24&chd=t:%s,%s,%s&chdls=20&chds=a";
        String chartUrl = String.format(urlBegin, df2.format(criticalP) + "%", df2.format(highP) + "%", df2.format(mediumP) + "%", findingsDetailsDTO.getCriticalCount(), findingsDetailsDTO.getHighCount(), findingsDetailsDTO.getMediumCount());
        chartUrl = chartUrl.replaceAll(" ", "%20");
        return chartUrl;
    }


    private String generatePayload(String message) {
        return "{\"body\":\"" + message.toString().replace("\n", "\\n") + "\"}";
    }
}
