package com.longi.dap.tookits;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 */
public class DateUtil {

    private static final Logger log = Logger.getLogger (DateUtil.class);


    /**
     * 根据时间类型比较时间大小
     *
     * @param source traget type "YYYY-MM-DD" "yyyyMMdd HH:mm:ss"  类型可自定义
     * @return 0 ：source和traget时间相同
     * 1 ：source在traget之后
     * -1：source比traget之前
     * @throws Exception
     */
    public static int dateCompare(String source, String traget, String type) {
        int ret = 2;
        try{
            SimpleDateFormat format = new SimpleDateFormat (type);
            ret = format.parse (source).compareTo (format.parse (traget));
        }catch(ParseException e){
            log.error("对比时间方法 dateCompare 报错： "+e);
        }
        return ret;
    }

    public static Date timeParse(String date, String pattern) {
        Date dateParsed = new Date ();
        try {
            dateParsed = new SimpleDateFormat (pattern).parse (date);
        } catch (ParseException e) {
            e.printStackTrace ();
        }
        return dateParsed;
    }

    public static String timeFormat(Date date, String pattern) {
        String formatTime = new SimpleDateFormat (pattern).format (date);
        return formatTime;
    }

    public static String getCurrDateForAccess() {
        Date date = new Date ();
        SimpleDateFormat sdf = new SimpleDateFormat ("dd.MM.yyyy-HH:mm:ss");
        return sdf.format (date);
    }

    public static String getCurrDate() {
        Date date = new Date ();
        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        return sdf.format (date);
    }

    public static String getCurrMonth() {
        Date date = new Date ();
        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM");
        return sdf.format (date);
    }

    public static String getCurrYear() {
        Date date = new Date ();
        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy");
        return sdf.format (date);
    }

    /**
     * @return java.util.Date
     * @Description 转化非正常格式时间为正常格式
     * @Date 11:52 2019-7-23
     * @Param [dateStr, originalFormat]
     **/
    public static Date str2Date(String dateStr, String originalFormat) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat (originalFormat);
        SimpleDateFormat sdf2 = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        return sdf2.parse (sdf2.format (sdf1.parse (dateStr)));
    }

    /**
     * 获取当前时间之前或之后秒钟 second
     */
    public static String getTimeBySecond(int second) {
        Calendar calendar = Calendar.getInstance ();
        calendar.add (Calendar.SECOND, second);
        return new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss").format (calendar.getTime ());
    }

    /**
     * 获取当前时间之前或之后秒钟 second
     */
    public static String getTimeByMinute(int second) {
        Calendar calendar = Calendar.getInstance ();
        calendar.add (Calendar.SECOND, second);
        return new SimpleDateFormat ("HH:mm").format (calendar.getTime ());
    }

    /**
     * 获取当前时间之前或之后的几天 day
     */
    public static String getDateBefore(int days) {
        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
        Date prevDay = null;
        try {
            Date day = sdf.parse (sdf.format (new Date ()));
            long ms = day.getTime () - days * 24 * 3600 * 1000L;
            prevDay = new Date (ms);
        } catch (ParseException e) {
            log.error ("get yesterday error：" + e);
            e.printStackTrace ();
        }
        return sdf.format (prevDay);
    }

    /**
     * 获取当前时间之前或之后的几yue month
     */
    public static String getDateAfterMonth(String dateStr,int month) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        try {
            Date date = sdf.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH,1);
            calendar.add(Calendar.MONTH, 1);
            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            log.error ("get getDateAfterMonth error：" + e);
        }
        return null;


    }

    /**
     * 获取当前时间之后的几天 day
     */
    public static String getDateAfterDays(String date,int days) {
        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
        Date prevDay = null;
        try {
            Date day = sdf.parse (sdf.format (DateUtil.timeParse(date,"yyyy-MM-dd")));
            long ms = day.getTime () + days * 24 * 3600 * 1000L;
            prevDay = new Date (ms);
        } catch (ParseException e) {
            log.error ("get yesterday error：" + e);
            e.printStackTrace ();
        }
        return sdf.format (prevDay);

    }

    /**
     * 时间戳转换成日期格式字符串
     * @param seconds 精确到秒的字符串
     * @param formatStr
     * @return
     */
    public static String timeStamp2Date(String seconds,String format) {
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
            return "";
        }
        if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds+"000")));
    }
    /**
     * 日期格式字符串转换成时间戳
     * @param date 字符串日期
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date_str,String format){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime()/1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static void closeResource(ResultSet rs, PreparedStatement psm, Connection con) throws SQLException {
        if(null != rs){
            rs.close ();
        }
        if(null != psm){
            psm.close ();
        }
        if(null != con){
            con.close ();
        }
    }

    public static void main(String[] args) throws ParseException {
        String ff = "2020-05-10 18:48:17";
        System.out.print(DateUtil.date2TimeStamp(ff,"yyyy-MM-dd HH:mm:ss"));

    }


}
