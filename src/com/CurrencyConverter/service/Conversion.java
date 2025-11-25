package com.CurrencyConverter.service;

public class Conversion {
    private String currencyA;
    private double amountMoney;
    private String currencyB;
    private double conversionResult;
    private String conversionDate;

    public Conversion(){}

    public Conversion(String currencyA, double amountMoney, String currencyB, double conversionResult, String conversionDate){
        this.currencyA = currencyA;
        this.amountMoney = amountMoney;
        this.currencyB = currencyB;
        this.conversionResult = conversionResult;
        this.conversionDate = conversionDate;
    }
    public String getCurrencyA() {
        return currencyA;
    }

    public String getCurrencyB() {
        return currencyB;
    }

    public double getConversionResult() {
        return conversionResult;
    }

    public String getConversionDate() {
        return conversionDate;
    }

    @Override
    public String toString() {
        return (String.format("%s%d = %s%d | Date: %s",
                currencyA, amountMoney, currencyB, conversionResult, conversionDate));

    }
}
