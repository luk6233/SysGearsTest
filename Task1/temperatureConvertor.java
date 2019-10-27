package Task1;

import org.jsoup.select.Evaluator;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class temperatureConvertor {
    private static List<Character> scale;

    static {
        scale = new ArrayList<>();
        scale.add('C');
        scale.add('K');
        scale.add('F');
    }

    public static String convertor (String temperature) {
        char scaleChar = temperature.charAt(temperature.length() - 1);
        if (!scale.contains(scaleChar)) {
            throw new IllegalArgumentException("Wrong scale");
        }

        int value = 0;
        String val = temperature.substring(0, temperature.length() - 1);
        try {
            value = Integer.parseInt(val);
        } catch (NumberFormatException e) {
            System.out.println("Wrong value");
        }

        int celsius;
        int fahrenheit;
        int kelvin;
        String result ="";

        switch (scaleChar) {
            case 'K':
                celsius = (int) Math.round(value - 273.15);
                fahrenheit = (int) Math.round(1.8 * (value - 273.15) + 32);
                result = String.format("{C: %d, F: %d}", celsius, fahrenheit);
                break;

            case 'F':
                celsius = (int) Math.round(5.0 * (value - 32) / 9);
                kelvin = (int) Math.round(5.0 * (value - 32) / 9 + 273.15);
                result = String.format("{C: %d, K: %d}", celsius, kelvin);
                break;

            case 'C':
                kelvin = (int) Math.round(value + 273.15);
                fahrenheit = (int) Math.round( 1.8 * value + 32);
                result = String.format("{K: %d, F: %d}", kelvin, fahrenheit);
                break;

        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("273K");
    }
}
