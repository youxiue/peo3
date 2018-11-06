package com.itheima.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class StringToDateDemo implements Converter<String,Date> {

    @Override
    public Date convert(String source) {//source接收页面的参数数据
        //当source为空时则不能进入日期类型转换
        if(source==null){
            throw new RuntimeException("接收的参数值为null");
        }
        //类型转换
        Date date=null;
        DateFormat dateFormat=getSimpleDateFormat(source);
        try {
            date= dateFormat.parse(source);
            return date;
        } catch (Exception e) {
            throw new RuntimeException("类型转换失败");
        }
    }

    private SimpleDateFormat getSimpleDateFormat(String source){
        SimpleDateFormat sdf = null;
        if(Pattern.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$", source)){
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        }else if(Pattern.matches("^\\d{4}\\d{2}\\d{2}$", source)){
            sdf = new SimpleDateFormat("yyyyMMdd");
        }else if(Pattern.matches("^\\d{4}/\\d{2}/\\d{2}$", source)){
            sdf = new SimpleDateFormat("yyyy/MM/dd");
        }

        return sdf;
    }

}
