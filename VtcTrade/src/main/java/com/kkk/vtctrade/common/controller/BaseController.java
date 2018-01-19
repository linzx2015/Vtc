package com.kkk.vtctrade.common.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import com.alibaba.fastjson.JSONObject;
import com.kkk.vtctrade.common.util.PageInfoUtil;

/**
 * 父类controller
 * @author lzx
 * */
public class BaseController {
	
	private Map<String,Object> paramMap=new HashMap<String,Object>();
	/**
	 * 用于获取Map对象
	 * */
	protected Map<String,Object> getParamMap()
	{
		paramMap.clear();
		return paramMap;
	}

	/**
	 * 生成uuid
	 * */
	protected String getRandomUUID()
	{
		UUID uuid=UUID.randomUUID();
		String uuidStr=uuid.toString().replaceAll("-", "");
		return uuidStr;
	}
	
	/**
	 * 将分页信息转换成json数据
	 * @param pageInfoUtil
	 * @return JSONObject
	 * */
	protected JSONObject processPageToJson(PageInfoUtil pageInfoUtil)
	{
		JSONObject dataJSON=new JSONObject();
		//总页数
		dataJSON.put("allPageNum", pageInfoUtil.getTotalPage());
		//当前页
		dataJSON.put("page",pageInfoUtil.getCurrentPage());
		//每页记录数
		dataJSON.put("pageRecordNum",pageInfoUtil.getCurrentRecord());
		//总条数
		dataJSON.put("allRecordNum",pageInfoUtil.getTotalRecord());
		return dataJSON;
	}
}
