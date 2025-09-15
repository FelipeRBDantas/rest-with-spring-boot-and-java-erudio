package br.com.feliperbdantas.requestConverters;

import br.com.feliperbdantas.exception.UnsupportedMathOperationException;

public class NumberConverter {
    public static Double convertToDouble(String strNumber) {
        if (strNumber == null || strNumber.isBlank())
            throw new UnsupportedMathOperationException("Please set a numeric value!");

        String number  = strNumber.replace(",", ".");

        return Double.parseDouble(number);
    }

    public static boolean isNumeric(String strNumber) {
        if (strNumber == null || strNumber.isBlank()) return false;

        String number  = strNumber.replace(",", ".");

        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
