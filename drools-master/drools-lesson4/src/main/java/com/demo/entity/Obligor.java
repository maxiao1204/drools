package com.demo.entity;

import java.util.Map;

public class Obligor {

    private String customerName;
    private int orr;
    private String classification;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getOrr() {
        return orr;
    }

    public void setOrr(int orr) {
        this.orr = orr;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }
}
