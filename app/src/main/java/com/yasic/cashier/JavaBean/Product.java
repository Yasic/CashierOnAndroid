package com.yasic.cashier.JavaBean;

import java.text.DecimalFormat;

/**
 * Created by Yasic on 2016/5/27.
 */
public class Product {
    private String barcode;
    private String name;
    private String unit;
    private String category;
    private String subCategory;
    private double price;

    public Product(String barcode, String name, String unit, String category, String subCategory, double price) {
        this.barcode = barcode;
        this.name = name;
        this.unit = unit;
        this.category = category;
        this.subCategory = subCategory;
        if (price < 0){
            throw new NumberFormatException("Price need to be positive");
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        this.price = Double.valueOf(decimalFormat.format(price));
    }

    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public String getCategory() {
        return category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public double getPrice() {
        return price;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public void setPrice(double price) {
        if (price < 0){
            throw new NumberFormatException("Price need to be positive");
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        this.price = Double.valueOf(decimalFormat.format(price));
        this.price = price;
    }
}
