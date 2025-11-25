package com.CurrencyConverter.service;
import com.CurrencyConverter.model.ApiRequest;
import com.CurrencyConverter.util.InputHandler;

public class Menu {
    private static int option;
    private static InputHandler handler;
    private static String currencyCodeA;
    private static String currencyCodeB;
    private static CurrencyManager manager;
    private static ApiRequest apiRequest;
    private static double rateChange;
    private static double amountMoney;
    private static double conversionResult;

    public Menu(){
        manager = new CurrencyManager();
        handler = new InputHandler();
        apiRequest = new ApiRequest();
    }

    public void mainMenu(){
        manager.getStarted();
        do{
            this.showOptions();
            option = handler.readMainOption();
            switch (option){
                case 1:
                    exchangeCurrencies();
                    break;
                case 2:
                    //showHistory
                    break;
                case 3:
                    //showAvailableCurrencies
                    break;
                case 4:
                    //hardcoded example
                default:
                    break;
            }
        }while (option!=5);
    }

    private void showOptions(){
        System.out.print("""
                \n******************************************
                            Currency converter
                
                1) Exchange currencies
                2) Show the conversions history.
                3) View supported currencies.
                4) Show conversion examples.
                5) Exit
                
                Select an option""");
    }

    private void exchangeCurrencies(){
        Conversion conversion = new Conversion();
        System.out.print("""
                Enter 1 to return to the main menu or
                Search currency code
                """);
        currencyCodeA = handler.readCurrency(manager);
        if(currencyCodeA.equals("1")) mainMenu();

        System.out.print("Search another currency code");
        currencyCodeB = handler.readCurrency(manager);
        if (currencyCodeB.equals("1")) mainMenu();

        if(currencyCodeA.equals(currencyCodeB)){
            System.out.println("Currencies must be different...\n**********************");
            exchangeCurrencies();
        }

        System.out.print(String.format("Amount of money that you want to convert from [%s] to [%s]",currencyCodeA,currencyCodeB));
        amountMoney = handler.readMoney();

        conversion = apiRequest.conversion(currencyCodeA, currencyCodeB, amountMoney);
        manager.addConversion(conversion);

        System.out.print(conversion);
        mainMenu();
    }
}
