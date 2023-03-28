package com.regpet.api.utils;

public class TextUtils {
    public static String capitalizeName(String text) {
        String[] splitedName = text.split("\\s");
        StringBuilder stringBuilder = new StringBuilder();

        for (String namePart : splitedName) {
            if (namePart.length() > 2) {
                stringBuilder
                        .append(namePart.substring(0, 1).toUpperCase())
                        .append(namePart.substring(1).toLowerCase())
                        .append(" ");
            } else {
                stringBuilder.append(namePart.toLowerCase()).append(" ");
            }
        }

        return stringBuilder.toString().trim();
    }
}
