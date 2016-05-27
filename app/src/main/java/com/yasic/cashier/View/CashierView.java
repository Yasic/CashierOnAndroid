package com.yasic.cashier.View;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yasic.cashier.Presenter.CashierPresenter;
import com.yasic.cashier.R;

/**
 * Created by Yasic on 2016/5/27.
 */
public class CashierView implements BaseViewInterface<CashierPresenter, Fragment> {
    private View view;
    private CashierPresenter cashierPresenter;
    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.activity_cashier, container, false);
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public void setPresenter(CashierPresenter cashierPresenter) {
        this.cashierPresenter = cashierPresenter;
    }

    @Override
    public void setPresenter(Fragment fragment) {

    }

}
