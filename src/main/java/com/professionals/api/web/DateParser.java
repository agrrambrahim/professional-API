package com.professionals.api.web;

import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public class DateParser {
    public final static int YEAR = 1;
    public final static int MONTH = 2;
    public final static int DAY = 2;

    public static Date fromString(String input) {
        StringBuilder number = new StringBuilder("");
        String duration = "";
        Calendar cal = Calendar.getInstance();
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) number.append(c);
            else
                break; //break after crossing the first char

        }
        duration = input.substring(number.length(), input.length());
        int intNumber = Integer.parseInt(number.toString());
        switch (duration.toLowerCase()) {
            case "days":
            case "day":
                cal.add(Calendar.DATE, -intNumber);
                break;
            case "month":
            case "months":
                cal.add(Calendar.MONTH, -intNumber);
                break;
            case "years":
            case "year":
                cal.add(Calendar.YEAR, -intNumber);
                break;
        }
        return cal.getTime();

    }

    public static Period getPeriodFrom(String input) {
        StringBuilder number = new StringBuilder("");
        String duration = "";
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) number.append(c);
            else
                break; //break after crossing the first char

        }
        duration = input.substring(number.length(), input.length());
        int intNumber = Integer.parseInt(number.toString());
        switch (duration.toLowerCase()) {
            case "days":
            case "day":
                return Period.ofDays(intNumber);
            case "month":
            case "months":
                return Period.ofMonths(intNumber);
            case "years":
            case "year":
                return Period.ofYears(intNumber);
        }
        return null;
    }
}
