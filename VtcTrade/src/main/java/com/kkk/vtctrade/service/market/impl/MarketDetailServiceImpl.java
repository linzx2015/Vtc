package com.kkk.vtctrade.service.market.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kkk.vtctrade.common.service.BaseServiceImpl;
import com.kkk.vtctrade.common.util.ConstantFinalUtil;
import com.kkk.vtctrade.common.util.PageInfoUtil;
import com.kkk.vtctrade.dao.master.MarketDetailDao;
import com.kkk.vtctrade.service.market.MarketDetailService;
import com.kkk.vtctrade.vo.common.PromptInfo;
import com.kkk.vtctrade.vo.master.MMarketDetail;

@Service
public class MarketDetailServiceImpl extends BaseServiceImpl implements MarketDetailService
{
	@Autowired
	private MarketDetailDao marketDetailDao;
	
	public PromptInfo insertOneService(MMarketDetail marketDetail)
	{
		int res =-1;
		try
		{
			res= marketDetailDao.insertOne(marketDetail);
			return this.getPromptInfo(res, marketDetail.getMarketDetailId());
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("-insert-Exception-{}-",e);
			return this.getPromptInfo(res, e.toString());
		}
	}

	public PromptInfo updateOneService(MMarketDetail marketDetail)
	{
		int res =-1;
		try
		{
			res= marketDetailDao.updateOne(marketDetail);
			return this.getPromptInfo(res, marketDetail.getMarketDetailId());
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("-update-Exception-{}-",e);
			return this.getPromptInfo(res, e.toString());
		}
	}

	public PromptInfo deleteOneService(MMarketDetail marketDetail)
	{
		int res =-1;
		try
		{
			res= marketDetailDao.deleteOne(marketDetail);
			return this.getPromptInfo(res, marketDetail.getMarketDetailId());
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("-delete-Exception-{}-",e);
			return this.getPromptInfo(res, e.toString());
		}
	}

	public MMarketDetail findOneService(Map<String, Object> paramMap)
	{
		return this.marketDetailDao.findOne(paramMap);
	}
	
	public List<MMarketDetail> findMultiService(Map<String, Object> paramMap,PageInfoUtil pageInfoUtil)
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
			List<MMarketDetail> marketDetailList=this.marketDetailDao.findMulti(paramMap);
			//查出总记录数
			if(marketDetailList.size()==1)
			{
				//总记录数已经存放在id中
				pageInfoUtil.setTotalRecord(Integer.parseInt(marketDetailList.get(0).getMarketDetailId()));
			}
			paramMap.put("sort_column",paramMap.get("sort_column"));
			paramMap.put("sort_type",paramMap.get("sort_type"));
			paramMap.put("pageCond", "false");
			paramMap.put("page", pageInfoUtil.getCurrentRecord());
			paramMap.put("pagenum", pageInfoUtil.getPageSize());
			return this.marketDetailDao.findMulti(paramMap);
		}
			return this.marketDetailDao.findMulti(paramMap);
	}
}
