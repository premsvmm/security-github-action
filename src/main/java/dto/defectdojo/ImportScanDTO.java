package dto.defectdojo;

import com.google.gson.annotations.SerializedName;
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
}
