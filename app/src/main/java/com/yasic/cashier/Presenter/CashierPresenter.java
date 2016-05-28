package com.yasic.cashier.Presenter;

import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.yasic.cashier.JavaBean.CallbackBean;
import com.yasic.cashier.JavaBean.Product;
import com.yasic.cashier.JavaBean.PromotionInfo;
import com.yasic.cashier.Model.CashierModel;
import com.yasic.cashier.R;
import com.yasic.cashier.Util.StaticData;
import com.yasic.cashier.View.CashierView;

import java.text.DecimalFormat;
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
        setSupportActionBar((Toolbar) BVIView.getView().findViewById(R.id.toolbar));
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
        Map<String, Integer> productMap = new HashMap<String, Integer>();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        StringBuffer output = new StringBuffer("*购物清单* ");
        for (Product product : productList){
            if (productMap.containsKey(product.getBarcode())){
                int temp = Integer.valueOf(productMap.get(product.getBarcode()).toString());
                temp++;
                productMap.put(product.getBarcode(), temp);
            }
            else {
                productMap.put(product.getBarcode(), 1);
            }
        }
        Iterator<Map.Entry<String, Integer>> iter = productMap.entrySet().iterator();
        Map.Entry<String, Integer> entry;
        String barcode;
        int num;
        double sumPrice = 0;
        int promotionFlag = 0;
        while (iter.hasNext()){
            entry = iter.next();
            barcode = entry.getKey();
            num = entry.getValue();
            Product temp = StaticData.getInstance().getProductInfo(barcode);
            if (promotionInfoList == null){
                sumPrice += temp.getPrice() *  num;
                if ( temp != null){
                    output.append("名称:").append(temp.getName()).append(",");
                    output.append("数量:").append(num).append(temp.getUnit()).append(",");
                    output.append("单价").append(Double.valueOf(decimalFormat.format(temp.getPrice()))).append("(元),");
                    output.append("小计:").append(Double.valueOf(decimalFormat.format(temp.getPrice() * num))).append("(元)");
                }
            }
            else {
                promotionFlag = 0;
                for (PromotionInfo promotionInfo : promotionInfoList){
                    if (promotionInfo.getBarcode().equals(barcode)){
                        sumPrice += temp.getPrice() * num * StaticData.getInstance().getPromotion(promotionInfo.getPromotionType());
                        output.append("名称:").append(temp.getName()).append(",");
                        output.append("数量:").append(num).append(temp.getUnit()).append(",");
                        output.append("单价").append(Double.valueOf(decimalFormat.format(temp.getPrice()))).append("(元),");
                        output.append("小计:").append(Double.valueOf(decimalFormat.format(temp.getPrice() * num * StaticData.getInstance().getPromotion(promotionInfo.getPromotionType())))).append("(元),");
                        output.append("优惠").append(Double.valueOf(decimalFormat.format(temp.getPrice() * num * (1 - StaticData.getInstance().getPromotion(promotionInfo.getPromotionType()))))).append("(元)");
                        promotionFlag = 1;
                        break;
                    }
                }
                if (promotionFlag == 0){
                    sumPrice += temp.getPrice() *  num;
                    output.append("名称:").append(temp.getName()).append(",");
                    output.append("数量:").append(num).append(temp.getUnit()).append(",");
                    output.append("单价").append(Double.valueOf(decimalFormat.format(temp.getPrice()))).append("(元),");
                    output.append("小计:").append(Double.valueOf(decimalFormat.format(temp.getPrice() * num))).append("(元)");
                }
            }
        }
        output.append("\n");
        output.append("总计:").append(Double.valueOf(decimalFormat.format(sumPrice))).append("(元)");
        BVIView.setTextView(output.toString());
    }

    @Override
    protected Class<CashierView> getBVIClass() {
        return CashierView.class;
    }

}
