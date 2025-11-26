package com.CurrencyConvertor.service;
import com.CurrencyConvertor.model.ApiRequest;
import com.CurrencyConvertor.model.Currency;
import com.CurrencyConvertor.util.InputHandler;

public class Menu {
    private static CurrencyManager manager;
    private static String currencyCodeA;
    private static String currencyCodeB;
    private static double amountMoney;
    private static InputHandler handler;
    private static int option;

    public Menu(){
        manager = new CurrencyManager();
        handler = new InputHandler();
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
                    manager.showConversionsFile();
                    break;
                case 3:
                    manager.showSupportedCurrencies();
                    System.out.println(manager.getSupportedCurrencyCodesMap().size());
                break;
                case 4:
                    System.out.println("""
                            
                            Leaving the program..
                            Thanks :)""");
                default:
                    break;
            }
        }while (option!=4);
    }

    private void showOptions(){
        System.out.print("""
                ******************************************
                            Currency converter
          
                1) Exchange currencies.
                2) Show the conversions history.
                3) View supported currencies.
                4) Exit.
                
                Select an option""");
    }

    private void exchangeCurrencies(){
        ApiRequest apiRequest = new ApiRequest();
        Conversion conversion;
        System.out.println("\n******************************************\n");
        System.out.print("""
                Enter 1 to return to the main menu or
                Search currency code
                """);
        currencyCodeA = handler.readCurrency(manager);
        if(currencyCodeA.equals("1")){
            System.out.println("Cancelling...\n");
            mainMenu();
        }

        System.out.print("Search another currency code");
        currencyCodeB = handler.readCurrency(manager);
        if (currencyCodeB.equals("1")) mainMenu();

        if(currencyCodeA.equals(currencyCodeB)){
            System.out.println("Currencies must be different!");
            exchangeCurrencies();
        }

        Currency currencyA = new Currency(currencyCodeA, manager.getSupportedCurrencyCodes().get(currencyCodeA));
        Currency currencyB = new Currency(currencyCodeB, manager.getSupportedCurrencyCodes().get(currencyCodeB));

        System.out.printf("Amount of money that you want to convert from [%s] to [%s]",currencyA.getCode(),currencyB.getCode());
        amountMoney = handler.readMoney();

        conversion = apiRequest.conversion(currencyA, currencyB, amountMoney);
        manager.addConversion(conversion);

        System.out.println("Result: ");
        System.out.print(conversion);
        manager.saveConversionMade();
        System.out.println();
        mainMenu();
    }
}
