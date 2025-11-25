package com.CurrencyConverter.service;

import com.CurrencyConverter.model.ApiRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CurrencyManager {
    private Map<String, String> availableCurrencies = new HashMap<>();;
    private ArrayList<Conversion> conversions= new ArrayList<>();

    public void getStarted(){
        //Iniciar availableCurrencies
        // si no tengo el .json o si la fecha del .json no es de hoy lo consulto de nuevo
        var request = new ApiRequest();
        try{
            File file = new File(System.getProperty("user.dir")+"\\supportedCodes.json");
            FileReader reader = new FileReader(file);
            // System.out.println(file.getAbsoluteFile().lastModified());
            availableCurrencies = request.getSupportedCodes();
        }catch (FileNotFoundException e) {
            System.out.println("Couldn't find any data of the available codes, requesting API...");
            request.supportedCodes();
            getStarted();
        }
        //si tengo el .json y la fecha es de hoy. Lo cargo en hashMap
    }

    public Map<String, String> getAvailableCurrencies() {
        return availableCurrencies;
    }

    public void showSupportedCurrencies(){
        System.out.println("smth");
    }

    public void addConversion(Conversion conversion){
        System.out.println("pepe");
    }
}
