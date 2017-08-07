package net.djtek.stockticker.utils;

public class StringUtils {
    public static String trimDoubleQuotes(String str){
        if (str != null){
            if (str.startsWith("\"")){
                str = str.substring(1);
            }
            if (str.endsWith("\"")){
                str = str.substring(0, str.length() - 1);
            }
        }
        return str;
    }
}