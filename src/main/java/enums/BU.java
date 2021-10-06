package enums;

import lombok.Getter;

public enum BU {
    PAYMENT(2);

    @Getter
    private Integer productId;

    BU(Integer productId) {
        this.productId = productId;
    }
}
