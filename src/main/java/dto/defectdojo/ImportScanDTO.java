package dto.defectdojo;

import com.google.gson.annotations.SerializedName;
import controller.Constants;
import enums.RunType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ImportScanDTO {
    @SerializedName("scan_type")
    private String scanType;
    private Integer engagement;
    private String file;
    @SerializedName("commit_hash")
    private String commitHash;
    @SerializedName("branch_tag")
    private String branchTag;

    public ImportScanDTO(RunType runType, Integer engagement) {
        this.scanType = runType.getRunType();
        this.engagement = engagement;
        this.file = Constants.UPLOAD_FILE_PATH;
        this.commitHash = Constants.GITHUB_SHA;
        this.branchTag = Constants.GITHUB_REF;
    }
}
