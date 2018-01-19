package com.kkk.vtctrade.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Component;


/**
 * 常用日期格式转化工具类
 * @author lzx
 * */
@Component("dateFormatUtil")
public class DateFormatUtil
{
	private static final SimpleDateFormat sdfDate=new SimpleDateFormat(ConstantFinalUtil.SYS_BUNDLE.getString("format.date"));
	private static final SimpleDateFormat sdfDateTime=new SimpleDateFormat(ConstantFinalUtil.SYS_BUNDLE.getString("format.datetime"));
	/**
	 * 进行日期时间格式转换  yyyy-MM-dd HH:mm:ss
	 * @param nowDate
	 * @return String
	 * */
	public static String formatDateTime(Date nowDate)
	{
		return sdfDateTime.format(nowDate);
	}
	
	/**
	 * 进行日期格式转换  yyyy-MM-dd
	 * @param nowDate
	 * @return String
	 * */
	public static String formatDate(Date nowDate)
	{
		return sdfDate.format(nowDate);
	}
	
	/**
	 * 字符串转日期时间
	 * @param now
	 * @return Date
	 * */
	public static Date parseDateTime(String now)
	{
		try
		{
			return sdfDateTime.parse(now);
		} catch (ParseException e)
		{
		}
		return null;
	}
	
	/**
	 * 字符串转日期时间,linux需要额外*1000
	 * */
	public static Date parseDateTimeLinux(String time)
	{
		Long timestamp = Long.parseLong(time)*1000;    
	    Date date=new java.util.Date(timestamp);   
	    return date;
	}
	
	/**
	 * 字符串转化日期
	 * @param String now
	 * */
	public static Date parseDate(String now)
	{
		try
		{
			return sdfDate.parse(now);
		} catch (ParseException e)
		{
		}
		return null;
	}
}
