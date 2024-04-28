import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        boolean continuar = true;

        while (continuar) {
            try {
                InputReader inputReader = new InputReader();
                String baseCurrency = inputReader.readCurrency("Introduce la divisa de origen (ej. USD): ");
                String targetCurrency = inputReader.readCurrency("Introduce la divisa de destino (ej. EUR): ");

                double exchangeRate = CurrencyAPI.getExchangeRate(baseCurrency.toUpperCase(), targetCurrency.toUpperCase());
                System.out.println("Tasa de cambio de " + baseCurrency + " a " + targetCurrency + ": " + exchangeRate);

                double amount = inputReader.readAmount("Introduce la cantidad en " + baseCurrency + ": ");

                CurrencyConverter converter = new CurrencyConverter();
                double convertedAmount = converter.convert(amount, exchangeRate);
                System.out.println("El equivalente de " + amount + " " + baseCurrency + " en " + targetCurrency + " es: " + convertedAmount);

                continuar = inputReader.continuar("¿Desea realizar otra conversión? (s/n): ");
            } catch (IOException e) {
                System.err.println("Error al obtener la tasa de cambio: " + e.getMessage());
            }
        }
    }
}
