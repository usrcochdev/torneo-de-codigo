package com.pichincha.codechallenge.utils;

import java.text.DecimalFormat;

public class MaskUtils {
    private static final DecimalFormat formatAsMoney = new DecimalFormat("0.00");

    public static String maskAsMoney(Double source) {
        return "$".concat(String.valueOf(formatAsMoney.format(source)));
    }
}
