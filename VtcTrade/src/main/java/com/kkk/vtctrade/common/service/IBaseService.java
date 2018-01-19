package com.kkk.vtctrade.common.service;

import java.util.List;
import java.util.Map;
import com.kkk.vtctrade.common.util.PageInfoUtil;
import com.kkk.vtctrade.vo.common.PromptInfo;

/**
 * 公共父类服务接口
 * @author lzx
 * */
public interface IBaseService<T> 
{
	/**
	 * 插入一条记录服务
	 * @param t
	 * @return JSONObject 格式{code:"1",info:"添加成功",data:{effectRows:"11","id":"11"}}
	 * */
	PromptInfo insertOneService(T t);
	
	/**
	 * 更新一条记录服务
	 * @param t
	 * @return JSONObject 格式{code:"1",info:"添加成功",data:{effectRows:"11","id":"11"}}
	 * */
	PromptInfo updateOneService(T t);
	
	/**
	 * 删除一条记录服务
	 * @param t
	 * @return JSONObject 格式{code:"1",info:"添加成功",data:{effectRows:"11","id":"11"}}
	 * */
	PromptInfo deleteOneService(T t);
	
	/**
	 * 查询一条记录服务
	 * @param paramMap
	 * @return T
	 * */
	public T findOneService(Map<String,Object> paramMap);
	
	/**
	 * 查询多条记录服务
	 * @param paramMap
	 * @return List<T>
	 * */
	public List<T> findMultiService(Map<String,Object> paramMap,PageInfoUtil pageInfoUtil);
}
