package cn.itcast.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: ssmparent
 * @description:
 * @author: Mr.Cai
 * @create: 2019-06-19 01:24
 */
public class DateUtils {
    //日期轉字符串
    public static String DatetoString(Date date,String patt){
        SimpleDateFormat sdf=new SimpleDateFormat(patt);
        String format = sdf.format(date);
        return format;
    }
    public static Date StringtoDate(String str,String patt) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat(patt);
        Date date = sdf.parse(str);
        return date;
    }

    public static void main(String[] args) {
        String s = DatetoString(new Date(),"yyyy-MM-dd HH:mm:ss");
        System.out.println(s);
    }
}
