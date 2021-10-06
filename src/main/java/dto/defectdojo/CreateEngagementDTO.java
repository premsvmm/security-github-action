package dto.defectdojo;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEngagementDTO {

    private String name;
    @SerializedName("target_start")
    private String targetStart;
    @SerializedName("target_end")
    private String targetEnd;
    @SerializedName("branch_tag")
    private String branchTag;
    private Integer product;
    private String status;
    @SerializedName("engagement_type")
    private String engagementType;
    @SerializedName("deduplication_on_engagement")
    private Boolean deduplicationOnEngagement;

    //response
    private Integer id;

    public CreateEngagementDTO getCreateEngagementDTO(String name, String branchTag, Integer product) {
        CreateEngagementDTO.CreateEngagementDTOBuilder createEngagementDTOBuilder = CreateEngagementDTO.builder();
        createEngagementDTOBuilder.name(name);
        createEngagementDTOBuilder.branchTag(branchTag);
        createEngagementDTOBuilder.product(product);
        createEngagementDTOBuilder.status("In Progress");
        createEngagementDTOBuilder.engagementType("CI/CD");
        createEngagementDTOBuilder.deduplicationOnEngagement(true);
        LocalDate localDate = java.time.LocalDate.now();
        createEngagementDTOBuilder.targetStart(localDate.toString());
        createEngagementDTOBuilder.targetEnd(localDate.toString());
        return createEngagementDTOBuilder.build();
    }
}
