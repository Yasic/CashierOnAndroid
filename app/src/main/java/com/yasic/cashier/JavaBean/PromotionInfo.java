package com.yasic.cashier.JavaBean;

/**
 * Created by Yasic on 2016/5/27.
 */
public class PromotionInfo {
    private String barcode;
    private String promotionType;

    public PromotionInfo(String barcode, String promotionType) throws Exception{
        this.barcode = barcode;
        checkTheFormatOfPromotinType(promotionType);
        this.promotionType = promotionType;
    }

    private void checkTheFormatOfPromotinType(String promotionType) throws Exception{
        String[] temp = promotionType.split("_");
        if (!temp[temp.length - 1].equals("DISCOUNT") || !temp[temp.length - 2].equals("PERCENT")){
            throw new Exception("Promotion is wrong");
        }
        if (temp.length < 3){
            throw new Exception("Promotion has little information");
        }
        if (temp.length > 4){
            throw new Exception("Promotion is wrong");
        }
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

    public void setPromotionType(String promotionType) throws Exception{
        checkTheFormatOfPromotinType(promotionType);
        this.promotionType = promotionType;
    }
}
