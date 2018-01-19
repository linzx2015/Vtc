package com.kkk.vtctrade.service.market.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kkk.vtctrade.common.service.BaseServiceImpl;
import com.kkk.vtctrade.common.util.ConstantFinalUtil;
import com.kkk.vtctrade.common.util.PageInfoUtil;
import com.kkk.vtctrade.dao.master.MarketDeepBuyDao;
import com.kkk.vtctrade.service.market.MarketDeepBuyService;
import com.kkk.vtctrade.vo.common.PromptInfo;
import com.kkk.vtctrade.vo.master.MMarketDeepBuy;

@Service
public class MarketDeepBuyServiceImpl extends BaseServiceImpl implements MarketDeepBuyService
{
	@Autowired
	private MarketDeepBuyDao marketDeepBuyDao;

	public PromptInfo insertOneService(MMarketDeepBuy marketDeepSell)
	{
		int res =-1;
		try
		{
			res= marketDeepBuyDao.insertOne(marketDeepSell);
			return this.getPromptInfo(res, marketDeepSell.getDeepBuyId());
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("-insert-Exception-{}-",e);
			return this.getPromptInfo(res, e.toString());
		}
	}

	public PromptInfo updateOneService(MMarketDeepBuy marketDeepSell)
	{
		int res =-1;
		try
		{
			res= marketDeepBuyDao.updateOne(marketDeepSell);
			return this.getPromptInfo(res, marketDeepSell.getDeepBuyId());
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("-update-Exception-{}-",e);
			return this.getPromptInfo(res, e.toString());
		}
	}

	public PromptInfo deleteOneService(MMarketDeepBuy marketDeepSell)
	{
		int res =-1;
		try
		{
			res= marketDeepBuyDao.deleteOne(marketDeepSell);
			return this.getPromptInfo(res, marketDeepSell.getDeepBuyId());
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("-delete-Exception-{}-",e);
			return this.getPromptInfo(res, e.toString());
		}
	}

	public MMarketDeepBuy findOneService(Map<String, Object> paramMap)
	{
		return this.marketDeepBuyDao.findOne(paramMap);
	}

	public List<MMarketDeepBuy> findMultiService(Map<String, Object> paramMap,PageInfoUtil pageInfoUtil)
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
			List<MMarketDeepBuy> marketDeepBuyList=this.marketDeepBuyDao.findMulti(paramMap);
			//查出总记录数
			if(marketDeepBuyList.size()==1)
			{
				//总记录数已经存放在id中
				pageInfoUtil.setTotalRecord(Integer.parseInt(marketDeepBuyList.get(0).getDeepBuyId()));
			}
			paramMap.put("sort_column",paramMap.get("sort_column"));
			paramMap.put("sort_type",paramMap.get("sort_type"));
			paramMap.put("pageCond", "false");
			paramMap.put("page", pageInfoUtil.getCurrentRecord());
			paramMap.put("pagenum", pageInfoUtil.getPageSize());
			return this.marketDeepBuyDao.findMulti(paramMap);
		}
			return this.marketDeepBuyDao.findMulti(paramMap);
	}
}
