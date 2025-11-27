# 🪙 Currency Converter
### Challenge ONE | Oracle + Alura LATAM

![Java](https://img.shields.io/badge/Java-25-red)
![Status](https://img.shields.io/badge/Status-%20Working-yellow)
![License](https://img.shields.io/badge/License-MIT-green)
![Gson](https://img.shields.io/badge/Gson-2.10+-85EA2D?logo=google&logoColor=white)
![API, AAAA](https://img.shields.io/badge/API-ExchangeRateAPI-blue)

>This project is a currency converter that allows you to convert between **+160 currencies** in real time using the <a href="https://www.exchangerate-api.com/">**ExchangeRate-API**</a> and also is one of the first **Alura + Oracle Next Education** challenges.

## 📝 Project Description <a name="id1"></a>

This project aims to apply key Java programming concepts, such as:
- APIs consumption,
- Parsing JSON responses with the **Gson** library,
- Handling custom exceptions,
- File management,
- User interaction through the console.

---
## 🔧 Functionalities <a name="id2"></a>
The program runs from the console and includes several features:
- ✅ An interactive and dynamic menu.
- ✅ Conversion between more than 160 currencies.
- ✅ Conversion history with timestamps.
- ✅ Validation of currencies, amounts, and allowed options.
- ✅ Automatic and persistent saving of all conversions.
- ✅ API consumption.
- ✅ Project based on the standard package structure: models, services, and utilities.
- ✅ Modular code.

---
## 🗒️ Class Diagram <a name="id3"></a>

```mermaid
---
config:
  theme: 'base'
  wrap: 'true'
  themeVariables:
    primaryColor: '#fff'
    primaryTextColor: '#000'
    primaryBorderColor: '#000'
    lineColor: '#fff'
    fontFamily: 'Inter, Arial'
    fontSize: '17px'
---
classDiagram
    Menu -- InputHandler
    Menu -- ExchangeRateApiClient
    CurrencyManager -- ExchangeRateApiClient
    CurrencyManager -- FileManager
    CurrencyManager -- Conversion
    CurrencyManager -- Menu
    
    ExchangeRateApiClient -- CurrencyCodes
    Conversion -- Currency
    
    
    class Currency {
        - String code
        - String name
        + getCode() String
        + getName() String
        + toString() String
    }

    class ExchangeRateApiClient {
        - String apiKey
        - HttpClient client
        - Gson gson
        - Properties props

        + ExchangeRateApiClient()
        - loadProperties() Properties 
        + convert(Currency from, Currency to, double amount) Conversion 
        + fetchSupportedCodes() Map~String, String~
    }

    class CurrencyCodes {
        <<Record>>
        List~List~String~~ supported_codes
        + asMap() Map~String, String~ 
    }

    class Conversion {
        - Currency fromCurrency
        - double amount
        - Currency toCurrency
        - double result
        - String date

        + Conversion(Currency from, double amount, Currency to, double result, String date)
        + toString() String 
    }

    class CurrencyManager {
        - Map~String, String~ supportedCodes
        - Stack~Conversion~ conversions
        - int MAX_HOUR$ = 6
        - String PATH_SUPPORTED_CODES$
        - String PATH_CONVERSIONS$

        + void start()
        + getConversions() Stack~Conversion~ 
        + getSupportedCodes() Map~String, String~ 
        + showSupportedCurrencies() void 
        + showConversionsFromFile() void 
        + addConversion(Conversion c) void 
        + saveConversion() void 
    }

    class Menu {
        - CurrencyManager manager
        - String codeA
        - String codeB
        - double amount
        - InputHandler handler
        - int option

        + Menu()
        + mainMenu() void
        + howOptions() void
        + convertCurrency() void
    }

    class InputHandler {
        - Scanner keyboard
        - int MIN_OPTION$ = 1
        - int MAX_OPTION$ = 4

        + readMainOption() int 
        + readCurrency(CurrencyManager manager) String
        + readAmount() double
    }

    class FileManager {
        + readFile(File file)$ String 
        + writeConversionsFile(String recoveredFile, Stack~Conversion~ conversions)$ void
    }
```

---
## 🛠️ Technologies <a name="id4"></a>

|Technology | Use |
|----------|-----|
| **Java** | Main programming language |
| **Gson-2.13.2** | Response parsing with (`JsonParser`, `JsonObject`, etc.) |
| **ExchangeRate-API** | Real-time data source |
| **Postman** | Software for testing the API responses |

---
## ⚙️ Requisites <a name="id5"></a>

- A free API key of [ExchangeRate-API](https://www.exchangerate-api.com/)

--- 

## ❓ How to use <a name="id6"></a>

1. Clone the repository:

```bash

git clone https://github.com/Nico-Monti/currency-converter.git

```

2. Open the `config.properties` file in the project root:

```config.properties
API_KEY = YourKey
```

3. Add gson-2.13.2.jar to the project structure.
4. Compile and run in your favorite IDE.
5. Follow the instructions in the console.

---
## 🎞️ Screenshots <a name="id7"></a>

<div align="center" style="border:1px solid #ccc; padding:10px;">
  <img src="https://github.com/user-attachments/assets/b9601c0e-737c-4778-a764-cb1334b196c9" />
  <img src="https://github.com/user-attachments/assets/be18a22f-c0cd-4561-b172-411d1bf65513" />
  <img width="800px" src="https://github.com/user-attachments/assets/4ecbd466-a051-4349-b706-59f756d355f8" />
  <img src="https://github.com/user-attachments/assets/2bc8e239-38ba-4953-b861-d58dbb1c9d53" />
  <img src="https://github.com/user-attachments/assets/225cdee9-d62e-4346-8f85-ef403d5f4c88" />
</div>

---
## ⚖️ License <a name="id8"></a>

This project is under the MIT license. [Click here](LICENSE) for more details.
