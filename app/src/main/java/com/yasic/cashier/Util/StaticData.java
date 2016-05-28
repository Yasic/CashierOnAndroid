package com.yasic.cashier.Util;

import com.yasic.cashier.JavaBean.Product;

/**
 * Created by Yasic on 2016/5/28.
 */
public class StaticData {
    public static Product ITEM000000 = new Product("ITEM000000", "可口可乐", "瓶", "食品", "碳酸饮料", 3.00);
    public static Product ITEM000001 = new Product("ITEM000001", "雪碧", "瓶", "食品", "碳酸饮料", 2.50);
    public static Product ITEM000002 = new Product("ITEM000001", "电池", "个", "日用品", "电器", 5.00);
    private static StaticData staticData = null;
    private StaticData(){
        init();
    }

    private void init(){
    }

    public static StaticData getInstance() {
        if(staticData == null){
            synchronized (StaticData.class){
                if(staticData == null){
                    staticData = new StaticData();
                }
            }
        }
        return staticData;
    }

    public Product getProductInfo(String code){
        if (code.equals("#ITEM000000")){
            return ITEM000000;
        }
        if (code.equals("#ITEM000001")){
            return ITEM000001;
        }
        if (code.equals("#ITEM000002")){
            return ITEM000002;
        }
        return null;
    }

    public double getPromotion(String promotionType){
        if (promotionType.equals("FIVE_PERCENT_DISCOUNT")) return 0.95;
        if (promotionType.equals("TEN_PERCENT_DISCOUNT")) return 0.90;
        if (promotionType.equals("FIVE_PERCENT_DISCOUNT")) return 0.95;
        if (promotionType.equals("FIFTEEN_PERCENT_DISCOUNT")) return 0.85;
        if (promotionType.equals("TWENTY_PERCENT_DISCOUNT")) return 0.80;
        if (promotionType.equals("TWENTY_FIVE_PERCENT_DISCOUNT")) return 0.75;
        return 1.00;
    }
}
