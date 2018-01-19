package com.kkk.vtctrade.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.alibaba.fastjson.JSON;

/**
 * 文件操作工具类
 * @author lzx
 */
public class FileUtil
{
	/**
	 * 读取单个文件的内容
	 * @param srcFile	原文件内容
	 * @return	String
	 */
	public String readFile(File srcFile)
	{
		try
		{
			return this.readFile(new FileInputStream(srcFile));
		} catch (FileNotFoundException e)
		{
			ConstantFinalUtil.loggerMsg.error("文件不存在;文件路径:{}",srcFile,e);
		}
		return "" ;
	}
	
	/**
	 * 读取一个文件内容,
	 * @param in 输入流
	 * @return
	 */
	public String readFile(InputStream in)
	{
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null ;
		try
		{
			br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String line = "" ; 
			while((line = br.readLine()) != null)
			{
				//System.out.println(line);
				/* 处理注释 */
				line = line.trim() ;
				if("".equalsIgnoreCase(line) || line.startsWith("//"))
				{
					continue ; 
				}
				
				sb.append(line + "\r\n");
			}
		}catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("读取输入流内容失败了:",e);
		}finally
		{
			try
			{
				if(br != null)
				{
					br.close();
				}
			} catch (IOException e)
			{
				ConstantFinalUtil.loggerMsg.error("读取输入流内容关闭时失败了:",e);
			}
		}
		return sb.toString() ;
	}
	
	public static void main(String[] args)
	{
		FileUtil fileUtil = new FileUtil();
		File sourceFile = new File("D:/Dev_SoftWare/MyEclipse_Install/20170405/VtcTrade/src/main/resources/promptInfo.json");
		String res = fileUtil.readFile(sourceFile);
		System.out.println(res);
		/* 测试解析JSON */
		JSON.parse(res);
	}
}
