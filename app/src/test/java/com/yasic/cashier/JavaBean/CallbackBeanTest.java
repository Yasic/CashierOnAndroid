package com.yasic.cashier.JavaBean;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yasic on 2016/5/27.
 */
public class CallbackBeanTest {
    private CallbackBean<String> callbackBean;

    @Before
    public void setUp() throws Exception {
        callbackBean = new CallbackBean<String>(0, "", "test right");
    }

    @Test
    public void testGetCode() throws Exception {
        assertEquals(0, callbackBean.getCode(), 0);
    }

    @Test
    public void testGetErrorMessage() throws Exception {
        assertEquals("", callbackBean.getErrorMessage());
    }

    @Test
    public void testGetResponse() throws Exception {
        assertEquals("test right", callbackBean.getResponse());
    }
}