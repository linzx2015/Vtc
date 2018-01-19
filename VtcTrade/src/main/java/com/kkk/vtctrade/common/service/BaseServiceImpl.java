package com.kkk.vtctrade.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.kkk.vtctrade.common.util.ConstantFinalUtil;
import com.kkk.vtctrade.vo.common.PromptInfo;

public abstract class BaseServiceImpl
{
	//生成用于前端提示的对象
	private final PromptInfo promptInfo=new PromptInfo();
	private static final Map<String,Object> resultMap=new ConcurrentHashMap<String, Object>();
	private static final List<Map<String,Object>> infoList=new ArrayList<Map<String,Object>>();
	private static final Map<String,List<Map<String,Object>>> infoMap=new ConcurrentHashMap<String, List<Map<String,Object>>>();
	private final int SUCCESS_CODE=0;
	private final int FAIL_CODE=1;
	
	private Map<String,List<Map<String,Object>>> getInfoData(String id,int res)
	{
		resultMap.clear();
		resultMap.put("id", id);
		resultMap.put("effectRows", res);
		
		infoList.clear();
		infoList.add(resultMap);
		
		infoMap.clear();
		infoMap.put("info", infoList);
		return infoMap;
	}
	
	protected synchronized PromptInfo getPromptInfo(int res,String resId)
	{
		if(res>0)
		{
			this.promptInfo.setErrorCode(SUCCESS_CODE);
			this.promptInfo.setData(ConstantFinalUtil.INFO_JSON.getString(SUCCESS_CODE+""));
			this.promptInfo.setInfoList(this.getInfoData(resId, res));
		}else
		{
			this.promptInfo.setErrorCode(FAIL_CODE);
			this.promptInfo.setData(ConstantFinalUtil.INFO_JSON.getString(FAIL_CODE+""));
		}
		return this.promptInfo;
	}
}
