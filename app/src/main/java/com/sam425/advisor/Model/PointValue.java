package com.sam425.advisor.Model;

public class PointValue {
    public String date;
    public String time;
    public String amount;

    public PointValue(){}

    public PointValue(String amount, String date) {
        this.amount = amount;
        this.date = date;
    }
    public String getxValue(){
        return amount;

    }
    public String getyValue(){
        return date;

    }
}