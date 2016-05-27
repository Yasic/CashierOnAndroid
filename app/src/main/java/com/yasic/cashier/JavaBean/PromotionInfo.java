package com.yasic.cashier.JavaBean;

/**
 * Created by Yasic on 2016/5/27.
 */
public class PromotionInfo {
    private String barcode;
    private String promotionType;

    public PromotionInfo(String barcode, String promotionType) {
        this.barcode = barcode;
        this.promotionType = promotionType;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getPromotionType() {
        return promotionType;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setPromotionType(String promotionType) {
        this.promotionType = promotionType;
    }
}
