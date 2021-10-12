package enums;

import lombok.Getter;

public enum ProductNameFromRepoName {
    api("cps_poc");

    @Getter
    private String productName;

    ProductNameFromRepoName(String productName) {
        this.productName = productName;
    }
}
