package dto.defectdojo;

import dto.ProductDTO;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Data
public class FindingsDetailsDTO {
    private List<ProductDTO.Result> results;
    private Integer criticalCount = 0;
    private Integer highCount = 0;
    private Integer mediumCount = 0;
    private Integer lowCount = 0;
    private Integer infoCount = 0;
    private Map<String, String> issueDetails;

    public FindingsDetailsDTO(List<ProductDTO.Result> results) {
        this.results = results;
        issueDetails = new TreeMap<>();
        getIssuesCount();
    }

    private void getIssuesCount() {
        for (ProductDTO.Result result : results) {
            issueDetails.put(result.getTitle(), result.getSeverity());
            if (result.getSeverity().equalsIgnoreCase("Critical")) {
                criticalCount++;
            } else if (result.getSeverity().equalsIgnoreCase("High")) {
                highCount++;
            } else if (result.getSeverity().equalsIgnoreCase("Medium")) {
                mediumCount++;
            } else if (result.getSeverity().equalsIgnoreCase("Low")) {
                lowCount++;
            } else if (result.getSeverity().equalsIgnoreCase("Info")) {
                infoCount++;
            }
        }
    }
}
