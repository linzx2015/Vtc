package com.kkk.vtctrade.vo.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 给前端提示的对象
 * @author lzx
 * */
public class PromptInfo implements Serializable
{
	private static final long serialVersionUID = 1149173743123398266L;
	private int errorCode;
	private String data;
	private Map<String,List<Map<String,Object>>> infoList;
	public int getErrorCode()
	{
		return errorCode;
	}
	public void setErrorCode(int errorCode)
	{
		this.errorCode = errorCode;
	}
	public String getData()
	{
		return data;
	}
	public void setData(String data)
	{
		this.data = data;
	}
	public Map<String, List<Map<String, Object>>> getInfoList()
	{
		return infoList;
	}
	public void setInfoList(Map<String, List<Map<String, Object>>> infoList)
	{
		this.infoList = infoList;
	}
	@Override
	public String toString()
	{
		return "PromptInfo [data=" + data + ", errorCode=" + errorCode
				+ ", infoList=" + infoList + "]";
	}
}
