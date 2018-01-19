package com.kkk.vtctrade.common.util;

import java.util.Iterator;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * String特定格式数据
 * 
 * @author lzx
 **/
public class StringConvertUtil
{

	/**
	 * 转换json
	 * 
	 * @param str
	 * @return JSONObject
	 * */
	public static JSONObject toJSON(String str)
	{
		JSONObject json = JSON.parseObject(str);
		return json;
	}
	
	/**
	 * 转换json数组
	 * @param str
	 * @return JSONArray
	 * */
	public static JSONArray toARRAY(String str)
	{
		JSONArray array=JSON.parseArray(str);
		return array;
	}

	public static String concatParam(Map<String,String> paramMap)
	{
		/* 如何将服务器写数据呢?
		 *https就使用HttpsURLConnection
		 * 通过firebug发现post请求,发送的是以下字符串
		 * returnUrl=&email=22%4022.com&password=111111ddd&code=dddd
		 *  */
		StringBuffer paramSb = new StringBuffer();
		for (Iterator iterator = paramMap.keySet().iterator(); iterator.hasNext();)
		{
			String key = (String) iterator.next();
			/* 获取的是一个一个的key */
			paramSb.append(key + "=" + paramMap.get(key) + "&");
		}
		String paramStr=paramSb.toString();
		paramStr=paramStr.substring(0, paramStr.length()-1);
		return paramStr;
	}
}
