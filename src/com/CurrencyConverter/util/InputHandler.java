package com.CurrencyConverter.util;

import com.CurrencyConverter.service.CurrencyManager;
import java.util.Scanner;

public class InputHandler {
    private static final Scanner kb = new Scanner(System.in);

    private static final int MIN_OPTION = 1;
    private static final int MAX_OPTION = 5;


    public int readMainOption(){
        System.out.print(":");
        try {
            String input = kb.nextLine().trim();
            int temp = Integer.valueOf(input);
            if(temp>=MIN_OPTION && temp<=MAX_OPTION){
                return temp;
            }else{
                System.out.println("Choose a valid option!");
            }
        }catch (NumberFormatException e){
            System.out.print("Check what you're typing!\n");
            System.out.print(":");
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        return readMainOption();
    }

    public String readCurrency(CurrencyManager manager){
        System.out.print(":");
        String input = kb.nextLine().trim().toUpperCase();
        if(input.equals("1") || (input.length()==3 && input.chars().allMatch(Character::isLetter) && manager.getAvailableCurrencies().get(input)!=null)){
            return input;
        }else{
            System.out.println("Choose a supported currency! For example: USD, EUR, etc.");
            return readCurrency(manager);
        }

    }

    public double readMoney(){
        System.out.print(":");
        var input = kb.nextLine().replace(',','.').trim();
        double money = 0;
        try {
            if(input.chars().anyMatch(Character::isLetter) || input.chars().anyMatch(Character::isAlphabetic)){
                System.out.println("Type a number!");
                return readMoney();
            }
            money = Double.valueOf(input);

        } catch (NumberFormatException e) {
            System.out.println("ERROR: "+e.getMessage());;
            e.getStackTrace();
        }
        return money;
    }


}
