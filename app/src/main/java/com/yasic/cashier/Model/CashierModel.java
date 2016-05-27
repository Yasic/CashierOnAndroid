package com.yasic.cashier.Model;

import android.util.Log;

import com.google.gson.Gson;
import com.yasic.cashier.JavaBean.CallbackBean;
import com.yasic.cashier.JavaBean.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yasic on 2016/5/27.
 */
public class CashierModel implements IModel {

    @Override
    public CallbackBean getProducts() {
        List<Product> productList = new ArrayList<>();
        try {
            Product product1 = new Product("#ITEM000000", "可口可乐", "瓶", "食品", "碳酸饮料", 3.00);
            Product product2 = new Product("#ITEM000000", "可口可乐", "瓶", "食品", "碳酸饮料", 3.00);
            Product product3 = new Product("#ITEM000001", "雪碧", "瓶", "食品", "碳酸饮料", 2.50);
            Product product4 = new Product("#ITEM000001", "雪碧", "瓶", "食品", "碳酸饮料", 2.50);
            Product product5 = new Product("#ITEM000000", "电池", "个", "日用品", "电器", 5.00);
            productList.add(product1);
            productList.add(product2);
            productList.add(product3);
            productList.add(product4);
            productList.add(product5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        String jsonElements = gson.toJson(productList);
        Log.i("jsonElements", jsonElements);


        return null;
    }

    @Override
    public CallbackBean getPromotionInfo() {
        return null;
    }
}
