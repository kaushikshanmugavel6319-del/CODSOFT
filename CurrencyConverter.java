import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;

class ExchangeRateManager {

    private Map<String, Double> rates = new HashMap<>();

    public ExchangeRateManager() {

        // Base reference: USD
        rates.put("USD", 1.0);
        rates.put("INR", 83.15);
        rates.put("EUR", 0.92);
        rates.put("GBP", 0.79);
        rates.put("JPY", 157.30);
        rates.put("AUD", 1.51);
        rates.put("CAD", 1.36);
    }

    public boolean isValidCurrency(String code) {
        return rates.containsKey(code.toUpperCase());
    }

    public double getRate(String currency) {
        return rates.get(currency.toUpperCase());
    }

    public void displayCurrencies() {
        System.out.println("\nAvailable Currencies:");
        for (String currency : rates.keySet()) {
            System.out.print(currency + "  ");
        }
        System.out.println();
    }
}

class CurrencyConverterEngine {

    private ExchangeRateManager rateManager;

    public CurrencyConverterEngine(ExchangeRateManager rateManager) {
        this.rateManager = rateManager;
    }

    public double convert(String from, String to, double amount) {

        double usdAmount = amount / rateManager.getRate(from);
        return usdAmount * rateManager.getRate(to);
    }
}

class ConversionHistory {

    private ArrayList<String> history = new ArrayList<>();

    public void addRecord(String record) {
        history.add(record);
    }

    public void showHistory() {

        System.out.println("\n===== CONVERSION HISTORY =====");

        if (history.isEmpty()) {
            System.out.println("No conversions performed yet.");
            return;
        }

        for (String item : history) {
            System.out.println(item);
        }
    }
}

public class CurrencyConverter {

    private static String getSymbol(String currency) {

        switch (currency.toUpperCase()) {
            case "USD":
                return "$";
            case "INR":
                return "₹";
            case "EUR":
                return "€";
            case "GBP":
                return "£";
            case "JPY":
                return "¥";
            case "AUD":
                return "A$";
            case "CAD":
                return "C$";
            default:
                return "";
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ExchangeRateManager rateManager = new ExchangeRateManager();
        CurrencyConverterEngine converter =
                new CurrencyConverterEngine(rateManager);
        ConversionHistory history = new ConversionHistory();

        int choice;

        System.out.println("====================================");
        System.out.println("     SMART CURRENCY CONVERTER");
        System.out.println("====================================");

        do {

            System.out.println("\n1. Convert Currency");
            System.out.println("2. View Conversion History");
            System.out.println("3. Exit");
            System.out.print("Enter Choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    rateManager.displayCurrencies();

                    System.out.print("\nEnter Base Currency: ");
                    String from = sc.next().toUpperCase();

                    System.out.print("Enter Target Currency: ");
                    String to = sc.next().toUpperCase();

                    if (!rateManager.isValidCurrency(from)
                            || !rateManager.isValidCurrency(to)) {

                        System.out.println("Invalid currency code.");
                        break;
                    }

                    System.out.print("Enter Amount: ");
                    double amount = sc.nextDouble();

                    if (amount <= 0) {
                        System.out.println("Amount must be greater than zero.");
                        break;
                    }

                    double result =
                            converter.convert(from, to, amount);

                    String output =
                            String.format("%s%.2f %s = %s%.2f %s",
                                    getSymbol(from),
                                    amount,
                                    from,
                                    getSymbol(to),
                                    result,
                                    to);

                    System.out.println("\nConversion Result:");
                    System.out.println(output);

                    history.addRecord(output);

                    break;

                case 2:
                    history.showHistory();
                    break;

                case 3:
                    System.out.println("Thank you for using the converter!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 3);

        sc.close();
    }
}