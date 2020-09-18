package com.seek.stocks.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 线程安全  时间转换工具类
 */
public class DateUtil {

    public static final DateTimeFormatter dateForm = DateTimeFormatter.ofPattern("yyyyMMdd");

    /**
     * jdk8 获取时间字符串
     * yyyyMMdd
     * @param date
     * @return
     */
    public static String toDateString (Date date){
        ZoneId  zoneId = ZoneId.systemDefault();
        LocalDateTime now = date.toInstant().atZone(zoneId).toLocalDateTime();
        return now.format(dateForm);
    }

    /**
     * jdk8 字符串转换成date
     * @param dateString
     * @return
     */
    public static Date toDate(String dateString){
        LocalDate localDate = LocalDate.parse(dateString, dateForm);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }
}
