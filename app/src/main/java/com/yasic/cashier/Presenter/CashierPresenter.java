package com.yasic.cashier.Presenter;

import com.yasic.cashier.Model.CashierModel;
import com.yasic.cashier.View.CashierView;

/**
 * Created by Yasic on 2016/5/27.
 */
public class CashierPresenter extends BasePresenterActivity<CashierView> {
    CashierModel cashierModel = new CashierModel();
    @Override
    protected void onBindBVI() {
        BVIView.setPresenter(this);
        cashierModel.getProducts();
    }

    @Override
    protected Class<CashierView> getBVIClass() {
        return CashierView.class;
    }
}
