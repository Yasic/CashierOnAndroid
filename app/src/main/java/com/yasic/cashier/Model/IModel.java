package com.yasic.cashier.Model;


import com.yasic.cashier.JavaBean.CallbackBean;

/**
 * Created by Yasic on 2016/5/18.
 */
public interface IModel {
    CallbackBean getProducts();
    CallbackBean getPromotionInfo();
}
