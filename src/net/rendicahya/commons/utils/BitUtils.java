package net.rendicahya.commons.utils;

import org.apache.commons.lang3.StringUtils;

public class BitUtils {

    public static String toBitString(int i) {
        StringBuilder bitString = new StringBuilder();

        do {
            bitString.append(String.valueOf(i % 2));
        } while ((i /= 2) > 0);

        return bitString.reverse().toString();
    }

    public static int toInt(String bitString) {
        bitString = StringUtils.reverse(bitString);
        int length = bitString.length();
        int result = 0;

        for (int i = 0; i < length; i++) {
            if (Integer.parseInt(String.valueOf(bitString.charAt(i))) == 1) {
                result += Math.pow(2, i);
            }
        }

        return result;
    }
}
