package com.kkk.vtctrade.service.market.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kkk.vtctrade.common.service.BaseServiceImpl;
import com.kkk.vtctrade.common.util.ConstantFinalUtil;
import com.kkk.vtctrade.common.util.PageInfoUtil;
import com.kkk.vtctrade.dao.master.MJobConfigDao;
import com.kkk.vtctrade.service.market.MJobConfigService;
import com.kkk.vtctrade.vo.common.PromptInfo;
import com.kkk.vtctrade.vo.master.MJobConfig;

@Service
public class MJobConfigServiceImpl extends BaseServiceImpl implements MJobConfigService
{
    @Autowired
    private MJobConfigDao mJobConfigDao;
    
	public PromptInfo insertOneService(MJobConfig jobConfig)
	{
		int res =-1;
		try
		{
			res= mJobConfigDao.insertOne(jobConfig);
			return this.getPromptInfo(res, jobConfig.getJobId());
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("-insert-Exception-{}-",e);
			return this.getPromptInfo(res, e.toString());
		}
	}

	public PromptInfo updateOneService(MJobConfig jobConfig)
	{
		int res =-1;
		try
		{
			res= mJobConfigDao.updateOne(jobConfig);
			return this.getPromptInfo(res, jobConfig.getJobId());
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("-update-Exception-{}-",e);
			return this.getPromptInfo(res, e.toString());
		}
	}

	public PromptInfo deleteOneService(MJobConfig jobConfig)
	{
		int res =-1;
		try
		{
			res= mJobConfigDao.deleteOne(jobConfig);
			return this.getPromptInfo(res, jobConfig.getJobId());
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("-delete-Exception-{}-",e);
			return this.getPromptInfo(res, e.toString());
		}
	}

	public MJobConfig findOneService(Map<String, Object> paramMap)
	{
		return mJobConfigDao.findOne(paramMap);
	}

	public List<MJobConfig> findMultiService(Map<String, Object> paramMap,PageInfoUtil pageInfoUtil)
	{
		//模糊查询的拼接由后台进行控制,避免用户乱输入
		if(paramMap.get("keywords")!=null)
		{
			paramMap.put("keywords","%"+paramMap.get("keywords")+"%");
		}
		//进行分页
		if(pageInfoUtil!=null)
		{
			//先查询全部记录
			paramMap.put("pageCond", "true");
			List<MJobConfig> marketDeepBuyList=this.mJobConfigDao.findMulti(paramMap);
			//查出总记录数
			if(marketDeepBuyList.size()==1)
			{
				//总记录数已经存放在id中
				pageInfoUtil.setTotalRecord(Integer.parseInt(marketDeepBuyList.get(0).getJobId()));
			}
			paramMap.put("sort_column",paramMap.get("sort_column"));
			paramMap.put("sort_type",paramMap.get("sort_type"));
			paramMap.put("pageCond", "false");
			paramMap.put("page", pageInfoUtil.getCurrentRecord());
			paramMap.put("pagenum", pageInfoUtil.getPageSize());
			return this.mJobConfigDao.findMulti(paramMap);
		}
			return this.mJobConfigDao.findMulti(paramMap);
	}
}
