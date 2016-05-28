package com.yasic.cashier.Presenter;

import android.widget.Toast;

import com.yasic.cashier.JavaBean.CallbackBean;
import com.yasic.cashier.JavaBean.Product;
import com.yasic.cashier.JavaBean.PromotionInfo;
import com.yasic.cashier.Model.CashierModel;
import com.yasic.cashier.View.CashierView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Yasic on 2016/5/27.
 */
public class CashierPresenter extends BasePresenterActivity<CashierView> {
    CashierModel cashierModel = new CashierModel();
    @Override
    protected void onBindBVI() {
        BVIView.setPresenter(this);
        getProductFromModel();
        cashierModel.getPromotionInfo();
    }

    private void getProductFromModel() {
        Observable.create(new Observable.OnSubscribe<CallbackBean<List<Product>>>() {
            @Override
            public void call(Subscriber<? super CallbackBean<List<Product>>> subscriber) {
                CallbackBean<List<Product>> callbackBean = cashierModel.getProducts();
                subscriber.onNext(callbackBean);
                subscriber.onCompleted();
            }
        })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action1<CallbackBean<List<Product>>>() {
                @Override
                public void call(CallbackBean<List<Product>> listCallbackBean) {
                    if (listCallbackBean.getCode() == 0){
                        getPromotionFromModel(listCallbackBean.getResponse());
                    }
                    else {
                        if (listCallbackBean.getCode() == 1 && listCallbackBean.getErrorMessage() != ""){
                            Toast.makeText(getApplication(), listCallbackBean.getErrorMessage(), Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Error appears during getting products", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    Toast.makeText(getApplicationContext(), "Error appears during getting products", Toast.LENGTH_LONG).show();
                }
            });
    }

    private void getPromotionFromModel(final List<Product> productList){
        Observable.create(new Observable.OnSubscribe<CallbackBean<List<PromotionInfo>>>() {
            @Override
            public void call(Subscriber<? super CallbackBean<List<PromotionInfo>>> subscriber) {
                CallbackBean<List<PromotionInfo>> callbackBean = cashierModel.getPromotionInfo();
                subscriber.onNext(callbackBean);
                subscriber.onCompleted();
            }
        })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action1<CallbackBean<List<PromotionInfo>>>() {
                @Override
                public void call(CallbackBean<List<PromotionInfo>> listCallbackBean) {
                    if (listCallbackBean.getCode() == 0){
                        dealProduct(productList, listCallbackBean.getResponse());
                    }
                    else {
                        if (listCallbackBean.getCode() == 1 && listCallbackBean.getErrorMessage() != ""){
                            Toast.makeText(getApplication(), listCallbackBean.getErrorMessage(), Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Error appears during getting promotion information", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    Toast.makeText(getApplicationContext(), "Error appears during getting promotion information", Toast.LENGTH_LONG).show();
                    dealProduct(productList, null);
                }
            });
    }

    private void dealProduct(List<Product> productList, List<PromotionInfo> promotionInfoList){

    }

    @Override
    protected Class<CashierView> getBVIClass() {
        return CashierView.class;
    }

}
