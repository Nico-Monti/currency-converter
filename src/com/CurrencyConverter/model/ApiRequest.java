package com.CurrencyConverter.model;

import com.CurrencyConverter.service.Conversion;
import com.google.gson.*;
import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Properties;

public class ApiRequest {
    private static Properties prop;
    private static String apiKey;
    private static HttpClient client;
    private static Gson gson;

    public ApiRequest(){
        prop = new Properties();
        client = HttpClient.newHttpClient();
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /************************************************************/
    public Conversion conversion(String currency, String otherCurrency, double amount) {
        Conversion conversion;
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm:ss");

        try {
            prop.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }

        apiKey = prop.getProperty("API_KEY");
        URI adress = URI.create("https://v6.exchangerate-api.com/v6/"+apiKey+"/pair/"+currency+"/"+otherCurrency+"/"+amount);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(adress)
                .GET()
                .build();

        try {
            HttpResponse<String> response = null;
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            JsonElement element = JsonParser.parseString(response.body());
            JsonObject object = element.getAsJsonObject();
            double conversionResult = object.get("conversion_result").getAsDouble();
            String conversionDate = dateTime.format(formatTime);
            return new Conversion(currency, amount, otherCurrency, conversionResult, conversionDate);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error: "+ e.getMessage());
        }
    }



    public double getExchangeRate(String currency, String otherCurrency) {
        try {
            prop.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
        apiKey = prop.getProperty("API_KEY");

        URI adress = URI.create("https://v6.exchangerate-api.com/v6/"+apiKey+"/pair/"+currency+"/"+otherCurrency);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(adress)
                .GET()
                .build();

        try {
            HttpResponse<String> response = null;
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            RecordCurrency monedaRecord = gson.fromJson(response.body(), RecordCurrency.class);
            return monedaRecord.conversion_rate();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error: "+ e.getMessage());
        }
    }



    public void supportedCodes(){
        try {
            prop.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));

        }catch (IOException e){
            System.out.println("ERROR: "+e.getMessage());
            e.getStackTrace();
        }

        apiKey = prop.getProperty("API_KEY");
        URI adress = URI.create("https://v6.exchangerate-api.com/v6/"+apiKey+"/codes");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(adress)
                .GET()
                .build();

        try {
            HttpResponse<String> response = null;
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            SupportedCodes currencies = gson.fromJson(response.body(), SupportedCodes.class);

            JsonElement element = JsonParser.parseString(response.body());
            JsonObject object = element.getAsJsonObject();

            FileWriter escritura = new FileWriter("supportedCodes.json");
            escritura.write(gson.toJson(object));
            escritura.close();
        } catch (IOException | InterruptedException e) {
            System.out.println("Error: "+ e.getMessage());
            e.getStackTrace();
        }
    }



    public HashMap<String,String> getSupportedCodes() {
        int bina;
        String json = "";
        try {
            File file = new File(System.getProperty("user.dir") + "\\supportedCodes.json");
            FileReader reader = new FileReader(file);
            bina = reader.read();
            while (bina != -1) {
                json = json.concat("" + (char) bina);
                bina = reader.read();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
        SupportedCodes currencies = gson.fromJson(json, SupportedCodes.class);
        return (HashMap<String, String>) currencies.asMap();
    }
}