package com.yasic.cashier.JavaBean;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by Yasic on 2016/5/27.
 */
public class ProductTest {
    private Product product1, product2, product3;
    @Before
    public void setUp() throws Exception {
        product1 = new Product("ITEM000000", "可口可乐", "瓶", "食品", "碳酸饮料", 3.00);
        product2 = new Product("ITEM000001", "雪碧", "瓶", "食品", "碳酸饮料", 2.50);
    }

    @Test
    public void testGetBarcode() throws Exception {
        assertEquals("ITEM000000", product1.getBarcode());
        assertEquals("ITEM000001", product2.getBarcode());
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("可口可乐", product1.getName());
        assertEquals("雪碧", product2.getName());
    }

    @Test
    public void testGetUnit() throws Exception {
        assertEquals("瓶", product1.getUnit());
        assertEquals("瓶", product2.getUnit());
    }

    @Test
    public void testGetCategory() throws Exception {
        assertEquals("食品", product1.getCategory());
        assertEquals("食品", product2.getCategory());
    }

    @Test
    public void testGetSubCateGory() throws Exception {
        assertEquals("碳酸饮料", product1.getSubCategory());
        assertEquals("碳酸饮料", product2.getSubCategory());
    }

    @Test
    public void testGetPrice() throws Exception {
        assertEquals(3.00, product1.getPrice(), 0.0);
        assertEquals(2.50, product2.getPrice(), 0.0);
    }

    @Test(expected = NumberFormatException.class)
    public void testPositive() throws Exception{
        product3 = new Product("ITEM000001", "雪碧", "瓶", "食品", "碳酸饮料", -2.50);
        fail("testPositive fail");
    }

    @Test(expected = NumberFormatException.class)
    public void testPriceSize() throws Exception{
        Product product3 = new Product("ITEM000001", "雪碧", "瓶", "食品", "碳酸饮料", 1000);
        fail("testPriceSize fail");
    }
}