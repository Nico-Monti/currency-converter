package com.CurrencyConverter.service;
import com.CurrencyConverter.model.ExchangeRateApiClient;
import com.CurrencyConverter.model.Currency;
import com.CurrencyConverter.util.InputHandler;

public class Menu {
    private static CurrencyManager manager;
    private static String codeA;
    private static String codeB;
    private static double amount;
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
                    System.out.println(manager.SupportedCurrencyCodesMap().size());
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
        ExchangeRateApiClient apiRequest = new ExchangeRateApiClient();
        Conversion conversion;
        System.out.println("\n******************************************\n");
        System.out.print("""
                Enter 1 to return to the main menu or
                Search currency code
                """);
        codeA = handler.readCurrency(manager);
        if(codeA.equals("1")){
            System.out.println("Cancelling...\n");
            mainMenu();
        }

        System.out.print("Search another currency code");
        codeB = handler.readCurrency(manager);
        if (codeB.equals("1")) mainMenu();

        if(codeA.equals(codeB)){
            System.out.println("Currencies must be different!");
            exchangeCurrencies();
        }

        Currency currencyA = new Currency(codeA, manager.getSupportedCurrencyCodes().get(codeA));
        Currency currencyB = new Currency(codeB, manager.getSupportedCurrencyCodes().get(codeB));

        System.out.printf("Amount of money that you want to convert from [%s] to [%s]",currencyA.getCode(),currencyB.getCode());
        amount = handler.readAmount();

        conversion = apiRequest.conversion(currencyA, currencyB, amount);
        manager.addConversion(conversion);

        System.out.println("Result: ");
        System.out.print(conversion);
        manager.saveConversion();
        System.out.println();
        mainMenu();
    }
}
