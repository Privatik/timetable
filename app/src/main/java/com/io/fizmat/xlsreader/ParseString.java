package com.io.fizmat.xlsreader;

public class ParseString {

    public static String corectStringRead(String str)
    {
        if (str.equals("")) return str;
        if (str.equals("Ф   И   З   И   Ч   Е   С   К   А   Я        К   У   Л   Ь   Т   У   Р   А "))
            str = "ФИЗИЧЕСКАЯ КУЛЬТУРА";
        else
            str = str.trim().replaceAll("\\s+"," ");
        return str;
    }
}
