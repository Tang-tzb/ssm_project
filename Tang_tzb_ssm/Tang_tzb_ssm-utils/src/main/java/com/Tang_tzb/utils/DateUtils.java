package com.Tang_tzb.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String date2String(Date date, String patt){
        DateFormat dateFormat = new SimpleDateFormat(patt);
        String fomat = dateFormat.format(date);
        return fomat;
    }

    public static Date String2date(String date,String patt) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(patt);
        Date parse = dateFormat.parse(date);
        return parse;
    }
}
