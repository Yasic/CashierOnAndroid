package com.yasic.cashier.JavaBean;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by Yasic on 2016/5/27.
 */
public class PromotionInfoTest {
    PromotionInfo promotionInfo1;

    @Before
    public void setUp() throws Exception {
        promotionInfo1 = new PromotionInfo("ITEM000000", "FIVE_PERCENT_DISCOUNT");
    }

    @Test
    public void testGetBarcode() throws Exception {
        assertEquals("ITEM000000", promotionInfo1.getBarcode());
    }

    @Test
    public void testGetPromotionType() throws Exception {
        assertEquals("FIVE_PERCENT_DISCOUNT", promotionInfo1.getPromotionType());
    }

    @Test
    public void testSetBarcode() throws Exception {
        promotionInfo1.setBarcode("ITEM0000001");
    }

    @Test(expected = Exception.class)
    public void testSetPromotionTypeofWrongInfo() throws Exception {
        promotionInfo1.setPromotionType("ttt");
        fail();
    }

    @Test(expected = Exception.class)
    public void testSetPromotionTypeofShortInfo() throws Exception {
        promotionInfo1.setPromotionType("PERCENT_DISCOUNT");
        fail();
    }

    @Test(expected = Exception.class)
    public void testSetPromotionTypeofLongInfo() throws Exception {
        promotionInfo1.setPromotionType("t_t_FIVE_PERCENT_DISCOUNT");
        fail();
    }
}