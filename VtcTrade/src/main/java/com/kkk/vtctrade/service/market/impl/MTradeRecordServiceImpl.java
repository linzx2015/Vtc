package com.kkk.vtctrade.service.market.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kkk.vtctrade.common.service.BaseServiceImpl;
import com.kkk.vtctrade.common.util.ConstantFinalUtil;
import com.kkk.vtctrade.common.util.PageInfoUtil;
import com.kkk.vtctrade.dao.master.MTradeRecordDao;
import com.kkk.vtctrade.service.market.MTradeRecordService;
import com.kkk.vtctrade.vo.common.PromptInfo;
import com.kkk.vtctrade.vo.master.MTradeRecord;

@Service
public class MTradeRecordServiceImpl extends BaseServiceImpl implements MTradeRecordService
{
	@Autowired
	private MTradeRecordDao mTradeRecordDao;
	
	public PromptInfo insertOneService(MTradeRecord tradeRecord)
	{
		int res =-1;
		try
		{
			res= mTradeRecordDao.insertOne(tradeRecord);
			return this.getPromptInfo(res, tradeRecord.getTradeRecordId());
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("-insert-Exception-{}-",e);
			return this.getPromptInfo(res, e.toString());
		}
	}

	public PromptInfo updateOneService(MTradeRecord tradeRecord)
	{
		int res =-1;
		try
		{
			res= mTradeRecordDao.updateOne(tradeRecord);
			return this.getPromptInfo(res, tradeRecord.getTradeRecordId());
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("-update-Exception-{}-",e);
			return this.getPromptInfo(res, e.toString());
		}
	}

	public PromptInfo deleteOneService(MTradeRecord tradeRecord)
	{
		int res =-1;
		try
		{
			res= mTradeRecordDao.deleteOne(tradeRecord);
			return this.getPromptInfo(res, tradeRecord.getTradeRecordId());
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("-delete-Exception-{}-",e);
			return this.getPromptInfo(res, e.toString());
		}
	}

	public MTradeRecord findOneService(Map<String, Object> paramMap)
	{
		return this.mTradeRecordDao.findOne(paramMap);
	}

	public List<MTradeRecord> findMultiService(Map<String, Object> paramMap,PageInfoUtil pageInfoUtil)
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
			List<MTradeRecord> tradeRecordList=this.mTradeRecordDao.findMulti(paramMap);
			//查出总记录数
			if(tradeRecordList.size()==1)
			{
				//总记录数已经存放在id中
				pageInfoUtil.setTotalRecord(Integer.parseInt(tradeRecordList.get(0).getTradeRecordId()));
			}
			paramMap.put("sort_column",paramMap.get("sort_column"));
			paramMap.put("sort_type",paramMap.get("sort_type"));
			paramMap.put("pageCond", "false");
			paramMap.put("page", pageInfoUtil.getCurrentRecord());
			paramMap.put("pagenum", pageInfoUtil.getPageSize());
			return this.mTradeRecordDao.findMulti(paramMap);
		}
			return this.mTradeRecordDao.findMulti(paramMap);
	}
}
