package com.yasic.cashier.JavaBean;

/**
 * Created by Yasic on 2016/5/27.
 */
public class CallbackBean<T extends Object> {
    private int code;
    private String errorMessage;
    private T response;

    public CallbackBean(int code, String errorMessage, T response){
        if (code == 0){
            this.errorMessage = "";
            this.response = response;
        }
        if (code > 0){
            this.errorMessage = errorMessage;
            this.response = null;
        }
    }

    public int getCode() {
        return code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public T getResponse() {
        return response;
    }
}
