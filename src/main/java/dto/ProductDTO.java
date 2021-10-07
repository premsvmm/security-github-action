package dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductDTO {
    private Integer count;
    private List<Result> results;
    private String severity;

    @Data
    public class Result {
        private Integer id;
        @SerializedName("findings_count")
        private Integer findingsCount;
        @SerializedName("prod_type")
        private Integer prodType;
        private String status;
        private String title;
        private String severity;
    }
}
