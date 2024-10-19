public class Task6 {
    public static double convertCurrency(String fromCurrency, String toCurrency, double money){
        double[] valutes = {41, 1, 0.90, 1.50};

        double fromCurrRate = getCurrencyRate(fromCurrency, valutes);
        double toCurrRate = getCurrencyRate(toCurrency, valutes);

        return money / fromCurrRate * toCurrRate;
    }


    private static double getCurrencyRate(String currency, double[] valutes) {
        switch (currency) {
            case "USD":
                return valutes[1];
            case "UAH":
                return valutes[0];
            case "EUR":
                return valutes[2];
            case "CAN":
                return valutes[3];
            default:
                return 0;

        }
    }

    public static void main(String[] args) {
        double converted = convertCurrency("UAH", "EUR",4700 );
        System.out.println("Converted amount: " + converted);
    }
}
