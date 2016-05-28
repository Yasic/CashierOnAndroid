package com.yasic.cashier.Model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yasic.cashier.JavaBean.CallbackBean;
import com.yasic.cashier.JavaBean.Product;
import com.yasic.cashier.JavaBean.PromotionInfo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yasic on 2016/5/27.
 */
public class CashierModel implements IModel {

    @Override
    public CallbackBean<List<Product>> getProducts() {
        CallbackBean<List<Product>> callbackBean;
        String jsonArrayForTest = getProductJsonData();
        Gson gson = new Gson();
        Type type = new TypeToken<List<Product>>(){}.getType();
        List<Product> productList = gson.fromJson(jsonArrayForTest, type);
        if (productList != null && productList.size() != 0) {
            callbackBean = new CallbackBean<>(0, "", productList);
            return callbackBean;
        } else {
            callbackBean = new CallbackBean<>(1, "There is no product now", null);
            return callbackBean;
        }
    }

    @Override
    public CallbackBean<List<PromotionInfo>> getPromotionInfo() {
        CallbackBean<List<PromotionInfo>> callbackBean;
        String promotionInfoJsonData = getPromotionJsonInfo();
        Gson gson = new Gson();
        Type type = new TypeToken<List<PromotionInfo>>(){}.getType();
        List<PromotionInfo>  promotionInfoList = gson.fromJson(promotionInfoJsonData, type);
        if (promotionInfoList != null && promotionInfoList.size() != 0) {
            callbackBean = new CallbackBean<>(0, "", promotionInfoList);
            return callbackBean;
        } else {
            callbackBean = new CallbackBean<>(1, "There is no product now", null);
            return callbackBean;
        }
    }

    /**
     * This function is just provided for testing the module of cashier
     * @return Json string of products
     */
    private String getProductJsonData() {
        return "[{\"barcode\":\"#ITEM000000\",\"category\":\"食品\",\"name\":\"可口可乐\",\"price\":3.0,\"subCategory\":\"碳酸饮料\",\"unit\":\"瓶\"},{\"barcode\":\"#ITEM000000\",\"category\":\"食品\",\"name\":\"可口可乐\",\"price\":3.0,\"subCategory\":\"碳酸饮料\",\"unit\":\"瓶\"},{\"barcode\":\"#ITEM000001\",\"category\":\"食品\",\"name\":\"雪碧\",\"price\":2.5,\"subCategory\":\"碳酸饮料\",\"unit\":\"瓶\"},{\"barcode\":\"#ITEM000001\",\"category\":\"食品\",\"name\":\"雪碧\",\"price\":2.5,\"subCategory\":\"碳酸饮料\",\"unit\":\"瓶\"},{\"barcode\":\"#ITEM000002\",\"category\":\"日用品\",\"name\":\"电池\",\"price\":5.0,\"subCategory\":\"电器\",\"unit\":\"个\"}]";
    }

    /**
     * This function is just provided for testing the module of cashier
     * @return Json string of promotion information
     */
    private String getPromotionJsonInfo() {
        List<PromotionInfo> promotionInfoList = new ArrayList<>();
        try {
            promotionInfoList.add(new PromotionInfo("#ITEM000000", "FIVE_PERCENT_DISCOUNT"));
            promotionInfoList.add(new PromotionInfo("#ITEM000001", "TEN_PERCENT_DISCOUNT"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        return gson.toJson(promotionInfoList);
    }
}
