package com.kkk.vtctrade.service.market.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kkk.vtctrade.common.service.BaseServiceImpl;
import com.kkk.vtctrade.common.util.ConstantFinalUtil;
import com.kkk.vtctrade.common.util.PageInfoUtil;
import com.kkk.vtctrade.dao.master.MarketDeepSellDao;
import com.kkk.vtctrade.service.market.MarketDeepSellService;
import com.kkk.vtctrade.vo.common.PromptInfo;
import com.kkk.vtctrade.vo.master.MMarketDeepSell;

@Service
public class MarketDeepSellServiceImpl extends BaseServiceImpl implements MarketDeepSellService
{
	@Autowired
	private MarketDeepSellDao marketDeepSellDao;

	public PromptInfo insertOneService(MMarketDeepSell marketDeepSell)
	{
		int res =-1;
		try
		{
			res= marketDeepSellDao.insertOne(marketDeepSell);
			return this.getPromptInfo(res, marketDeepSell.getDeepSellId());
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("-insert-Exception-{}-",e);
			return this.getPromptInfo(res, e.toString());
		}
	}

	public PromptInfo updateOneService(MMarketDeepSell marketDeepSell)
	{
		int res =-1;
		try
		{
			res= marketDeepSellDao.updateOne(marketDeepSell);
			return this.getPromptInfo(res, marketDeepSell.getDeepSellId());
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("-update-Exception-{}-",e);
			return this.getPromptInfo(res, e.toString());
		}
	}

	public PromptInfo deleteOneService(MMarketDeepSell marketDeepSell)
	{
		int res =-1;
		try
		{
			res= marketDeepSellDao.deleteOne(marketDeepSell);
			return this.getPromptInfo(res, marketDeepSell.getDeepSellId());
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("-delete-Exception-{}-",e);
			return this.getPromptInfo(res, e.toString());
		}
	}

	public MMarketDeepSell findOneService(Map<String, Object> paramMap)
	{
		return this.marketDeepSellDao.findOne(paramMap);
	}

	public List<MMarketDeepSell> findMultiService(Map<String, Object> paramMap,PageInfoUtil pageInfoUtil)
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
			List<MMarketDeepSell> marketDeepSellList=this.marketDeepSellDao.findMulti(paramMap);
			//查出总记录数
			if(marketDeepSellList.size()==1)
			{
				//总记录数已经存放在id中
				pageInfoUtil.setTotalRecord(Integer.parseInt(marketDeepSellList.get(0).getDeepSellId()));
			}
			paramMap.put("sort_column",paramMap.get("sort_column"));
			paramMap.put("sort_type",paramMap.get("sort_type"));
			paramMap.put("pageCond", "false");
			paramMap.put("page", pageInfoUtil.getCurrentRecord());
			paramMap.put("pagenum", pageInfoUtil.getPageSize());
			return this.marketDeepSellDao.findMulti(paramMap);
		}
			return this.marketDeepSellDao.findMulti(paramMap);
	}

}
