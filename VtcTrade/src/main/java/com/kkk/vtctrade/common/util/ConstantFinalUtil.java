package com.kkk.vtctrade.common.util;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 静态常量工具类
 * @author lzx
 * */
public class ConstantFinalUtil 
{
	public static final Logger loggerMsg = LogManager.getLogger(ConstantFinalUtil.class);
	/* 加载配置文件 */
	public static ResourceBundle SYS_BUNDLE = ResourceBundle.getBundle("application");
	public static ResourceBundle API_BUNDLE = ResourceBundle.getBundle("apitradedata");
	public static final String[] coinArray={"btc","eth","etc","ltc","doge","ybc"};
	
	/* 资源文件 */
	public static JSONObject RESOURCE_JSON = new JSONObject() ;
	/* 信息提示对象 */
	public static JSONObject INFO_JSON = new JSONObject();
	/* 配置提示对象 */
	public static JSONObject CONFIG_JSON = new JSONObject();
	// http连接超时时间
	public static final int CONNECT_TIMEOUT = 5000;
	// http读取信息超时时间
	public static final int READ_TIMEOUT = 5000;
	// 全部随机字符的字母表
	public static String ALLSTR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	//存储全部货币
	public static Map<String,Object> VIRTUAL_COIN_MAP=new ConcurrentHashMap<String, Object>();
	
	public static Map<String,Object> SERVICE_MAP=new ConcurrentHashMap<String, Object>();
	
	/* 初始化 */
	/* 静态代码块:初始化一次 */
	static
	{
		/* 读取JSON配置文件 */
		FileUtil fileUtil = new FileUtil();
		String resourceStr = "" ; 
		try
		{
			resourceStr = fileUtil.readFile(ConstantFinalUtil.class.getClassLoader().getResourceAsStream("promptInfo.json"));
			RESOURCE_JSON = (JSONObject) JSON.parse(resourceStr);
			
			/* 获取json配置文件中info信息 */
			INFO_JSON = RESOURCE_JSON.getJSONObject("info");
			/* 获取json配置文件中info信息 */
			CONFIG_JSON = RESOURCE_JSON.getJSONObject("config");
			ConstantFinalUtil.loggerMsg.info("JSON配置文件");
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.info("JSON配置文件转换报错了,字符串:{}",resourceStr,e);
		}
	}
	
	public static String getRandomUUID()
	{
		UUID uuid=UUID.randomUUID();
		return uuid.toString();
	}
	
	public static String[] getCoinDataUrl(String preUrl)
	{
		String arr[]=new String[coinArray.length];
		for(int i=0;i<arr.length;i++)
		{
			arr[i]=API_BUNDLE.getString(preUrl+"."+coinArray[i]);
		}
		return arr;
	}
}
