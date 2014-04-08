package net.rendicahya.commons.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public class RcStringUtils {

    private static Pattern replaceCamelCasePattern;
    private static DecimalFormat rupiahFormat;

    public static String replaceCamelCase(String input, String replacement) {
        if (replaceCamelCasePattern == null) {
            replaceCamelCasePattern = Pattern.compile("(?<=[a-z])(?=[A-Z])");
        }

        return replaceCamelCasePattern.matcher(input).replaceAll(replacement);
    }

    public static String centerPad(String leftStr, String rightStr, int size) {
        return centerPad(leftStr, rightStr, " ", size);
    }

    public static String centerPad(String leftStr, String rightStr, String padStr, int size) {
        StringBuilder str = new StringBuilder(leftStr);
        str.append(StringUtils.repeat(padStr, size - leftStr.length() - rightStr.length()));
        str.append(rightStr);

        return str.toString();
    }

    public static String expFormatRupiah(Number input, boolean prependRp) {
        if (rupiahFormat == null) {
            DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
            decimalFormatSymbols.setCurrencySymbol(prependRp ? "Rp " : "");
            decimalFormatSymbols.setMonetaryDecimalSeparator(',');
            decimalFormatSymbols.setGroupingSeparator('.');

            rupiahFormat = (DecimalFormat) NumberFormat.getCurrencyInstance();
            rupiahFormat.setDecimalFormatSymbols(decimalFormatSymbols);
        }

        return rupiahFormat.format(input);
    }

    public static String formatRupiah(long in) {
        return formatRupiah(in, true);
    }

    public static String formatRupiah(long in, boolean prependRp) {
        StringBuilder number = new StringBuilder(String.valueOf(in));

        if (in >= 1000) {
            for (int i = number.length() - 3; i > 0; i -= 3) {
                number.insert(i, '.');
            }
        }

        if (prependRp) {
            number.insert(0, "Rp ");
        }

        return number.toString();
    }

    public static int extractInt(String in) {
        return extractInt(in, 0);
    }

    public static int extractInt(String in, int defaultValue) {
        long longValue = extractLong(in);

        return longValue < Integer.MAX_VALUE && longValue > Integer.MIN_VALUE ? (int) extractLong(in) : defaultValue;
    }

    public static long extractLong(String in) {
        return extractLong(in, 0);
    }

    public static long extractLong(String in, long defaultValue) {
        if (in == null || in.length() == 0) {
            return defaultValue;
        }

        in = StringUtils.deleteWhitespace(in);

        if (StringUtils.isNumeric(in)) {
            return Long.parseLong(in);
        }

        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (char ch : in.toCharArray()) {
            if (Character.isDigit(ch) || (first && ch == '-')) {
                result.append(ch);
                first = false;
            }
        }

        return result.length() > 0
                ? Long.parseLong(result.toString())
                : defaultValue;
    }

    public static float extractFloat(String in) {
        return (float) extractDouble(in);
    }

    public static double extractDouble(String in) {
        return extractDouble(in, 0);
    }

    public static double extractDouble(String in, double defaultValue) {
        if (in == null || in.length() == 0) {
            return defaultValue;
        }

        in = StringUtils.deleteWhitespace(in);

        if (StringUtils.isNumeric(in)) {
            return Double.parseDouble(in);
        }

        StringBuilder result = new StringBuilder();
        boolean first = true;
        boolean commaAppended = false;

        for (char ch : in.toCharArray()) {
            if (!commaAppended && ch == '.') {
                result.append(ch);
                commaAppended = true;

                continue;
            }

            if (Character.isDigit(ch) || (first && ch == '-')) {
                result.append(ch);
                first = false;
            }
        }

        return result.length() > 0
                ? Double.parseDouble(result.toString())
                : defaultValue;
    }

    public static int[] toIntArray(String src) {
        if (src == null || src.length() == 0) {
            return null;
        }

        int length = src.length();
        int[] dst = new int[length];

        for (int i = 0; i < length; i++) {
            dst[i] = Integer.parseInt(String.valueOf(src.charAt(i)));
        }

        return dst;
    }

    public static String toString(int[][] arr, String divider, String divider2) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        StringBuilder dst = new StringBuilder();

        for (int[] i : arr) {
            dst.append(toString(i, divider2));
            dst.append(divider);
        }

        return dst.toString();
    }

    public static String toString(int[] arr) {
        return toString(arr, "");
    }

    public static String toString(int[] arr, String divider) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        StringBuilder dst = new StringBuilder();

        for (int i : arr) {
            dst.append(i);
            dst.append(divider);
        }

        return dst.toString();
    }

    public static String toString(double[] arr) {
        return toString(arr, "");
    }

    public static String toString(double[] arr, String divider) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        StringBuilder dst = new StringBuilder();

        for (double d : arr) {
            dst.append(d);
            dst.append(divider);
        }

        return dst.toString();
    }

    public static double[] toDoubleArray(String str) {
        double[] result = new double[str.length()];

        for (int i = 0; i < result.length; i++) {
            result[i] = Double.parseDouble("" + str.charAt(i));
        }

        return result;
    }

    public static String concat(Object... objects) {
        StringBuilder stringBuilder = new StringBuilder();

        for (Object object : objects) {
            stringBuilder.append(object);
        }

        return stringBuilder.toString();
    }
}
