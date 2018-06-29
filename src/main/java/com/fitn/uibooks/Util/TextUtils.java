package com.fitn.uibooks.Util;

public class TextUtils {
    public  String sanitize(String textToSanitize){
        return textToSanitize.replaceAll("\\s+", " ");
    }
}
