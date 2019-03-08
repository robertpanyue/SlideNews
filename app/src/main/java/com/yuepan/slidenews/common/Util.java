package com.yuepan.slidenews.common;

public class Util {

    private Util() {

    }

    public static boolean isStringEmpty(String string) {
        return string == null || string.length() == 0;
    }
}
