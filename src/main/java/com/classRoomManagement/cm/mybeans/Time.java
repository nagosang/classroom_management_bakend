package com.classRoomManagement.cm.mybeans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Configuration
public class Time {
    private static String serverTimeZone;


    /**
     * 从配置文件中获取时区
     *
     * @param stz 时区
     */
    @Value("${appConfig.serverTimeZone}")
    public void getServerTimeZone(String stz) {
        Time.serverTimeZone = stz;
    }


    /**
     * 获取系统当前时间
     *
     * @return 系统当前当前时间
     */
    public static Date getCurrentDate() {
        TimeZone timeZone = TimeZone.getTimeZone(serverTimeZone);
        TimeZone.setDefault(timeZone);
        return new Date();
    }

    public static String getDate(){
        SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
        return format.format(Time.getCurrentDate());
    }

    public static int getDay(){
        SimpleDateFormat format=new SimpleDateFormat("E");
        if (format.format(Time.getCurrentDate()).equals("周一")) {
            return 1;
        }
        else if (format.format(Time.getCurrentDate()).equals("周二")) {
            return 2;
        }
        else if (format.format(Time.getCurrentDate()).equals("周三")) {
            return 3;
        }
        else if (format.format(Time.getCurrentDate()).equals("周四")) {
            return 4;
        }
        else if (format.format(Time.getCurrentDate()).equals("周五")) {
            return 5;
        }
        else if (format.format(Time.getCurrentDate()).equals("周六")) {
            return 6;
        }
        else {
            return 7;
        }
    }

    public static int getDay(Date date){
        SimpleDateFormat format=new SimpleDateFormat("E");
        if (format.format(date).equals("周一")) {
            return 1;
        }
        else if (format.format(date).equals("周二")) {
            return 2;
        }
        else if (format.format(date).equals("周三")) {
            return 3;
        }
        else if (format.format(date).equals("周四")) {
            return 4;
        }
        else if (format.format(date).equals("周五")) {
            return 5;
        }
        else if (format.format(date).equals("周六")) {
            return 6;
        }
        else {
            return 7;
        }
    }

    public static int getTimes() {
        SimpleDateFormat format=new SimpleDateFormat("HHmm");
        int t = Integer.parseInt(format.format(Time.getCurrentDate()));
        if (t>=805 && t<=850){
            return 1;
        }
        else if(t>850 && t<=935){
            return 2;
        }
        else if(t>=945 && t<=1030){
            return 3;
        }
        else if(t>=1035 && t<=1120){
            return 4;
        }
        else if(t>=1125 && t<=1210){
            return 5;
        }
        else if(t>=1320 && t<=1405){
            return 6;
        }
        else if(t>=1410 && t<=1455){
            return 7;
        }
        else if(t>=1505 && t<=1550){
            return 8;
        }
        else if(t>=1555 && t<=1640){
            return 9;
        }
        else if(t>=1800 && t<=1845){
            return 10;
        }
        else if(t>=1850 && t<=1935){
            return 11;
        }
        else if(t>=1940 && t<=2025){
            return 10;
        }
        else{
            return 0;
        }
    }

    public static String toString(Date date){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  format.format(date);
    }

    public static String stringTomorrow(String strDate){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(strDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            Date newTime = calendar.getTime();
            return formatter.format(newTime);
        }
        catch (Exception e){
            e.printStackTrace();
            return formatter.format(Time.getCurrentDate());
        }
    }

    public static Date strToDate(String strDate){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(strDate);
            return date;
        }
        catch (Exception e){
            e.printStackTrace();
            return Time.getCurrentDate();
        }
    }
}
