package com.kkk.vtctrade.common.util;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kkk.vtctrade.service.market.MTradeRecordService;
import com.kkk.vtctrade.service.market.MarketDeepBuyService;
import com.kkk.vtctrade.service.market.MarketDeepSellService;
import com.kkk.vtctrade.service.market.MarketDetailDeepService;
import com.kkk.vtctrade.service.market.MarketDetailService;
import com.kkk.vtctrade.vo.master.MMarketDeepBuy;
import com.kkk.vtctrade.vo.master.MMarketDeepSell;
import com.kkk.vtctrade.vo.master.MMarketDetail;
import com.kkk.vtctrade.vo.master.MMarketDetailDeep;
import com.kkk.vtctrade.vo.master.MTradeRecord;
import com.kkk.vtctrade.vo.master.MVirtualCoin;

/**
 * 多线程工具类：java1.6之后使用concurrent 
 * 优化原本Thread和Runnable的缺点
 * Callable==Runnable接口; Callable中的泛型必须和实现的Call方法返回值一致
 * 如何为多线程的属性赋值:set方法;在当前的多线程类中和spring没有关系
 * 
 * */
public class MultiThreadUtil implements Callable<Integer>
{
	private int callTag;
	private MarketDetailDeepService marketDetailDeepService=(MarketDetailDeepService) ConstantFinalUtil.SERVICE_MAP.get("marketDetailDeepService");
	private MarketDeepSellService marketDeepSellService=(MarketDeepSellService) ConstantFinalUtil.SERVICE_MAP.get("marketDeepSellService");
	private MarketDeepBuyService marketDeepBuyService=(MarketDeepBuyService) ConstantFinalUtil.SERVICE_MAP.get("marketDeepBuyService");
	private MarketDetailService marketDetailService=(MarketDetailService) ConstantFinalUtil.SERVICE_MAP.get("marketDetailService");
	private MTradeRecordService mTradeRecordService=(MTradeRecordService) ConstantFinalUtil.SERVICE_MAP.get("mTradeRecordService");
	
	public Integer call() throws Exception
	{
		switch (callTag)
		{
		case 0:
			saveMarketData();
			break;
		case 1:
			saveMarketDeepData();
			break;
		case 2:
			saveTradeRecordData();
			break;
		default:
			break;
		}
		return null;
	}
	
	private void saveMarketData()
	{
		//--getInfo--{time=1504927261, vol=4541.65, last=24300, sell=24400, buy=24300, high=28966, low=22847.13}
		String[] urlArray=ConstantFinalUtil.getCoinDataUrl("common.market");
		for(int i=0;i<urlArray.length;i++)
		{
			String getStr=HttpUtil.methodGet(urlArray[i]);
			JSONObject marketDetailJSON=StringConvertUtil.toJSON(getStr);
			String coinName=subPreUrl(urlArray[i]);
			MVirtualCoin virtualCoin=(MVirtualCoin) ConstantFinalUtil.VIRTUAL_COIN_MAP.get(coinName);
			ConstantFinalUtil.loggerMsg.info("--saveMarketData--{}--",coinName);
			if(marketDetailJSON!=null && marketDetailJSON.size()>0)
			{
				MMarketDetail marketDetail=new MMarketDetail();
				try
				{
					marketDetail.setMarketDetailId(ConstantFinalUtil.getRandomUUID());
					marketDetail.setVirtualCoinId(virtualCoin.getVirtualCoinId());
					marketDetail.setMarketBuyPrice(marketDetailJSON.getDoubleValue("buy"));
					marketDetail.setMarketCreateTime(new Date());
					marketDetail.setMarketHighPrice(marketDetailJSON.getDoubleValue("high"));
					marketDetail.setMarketLastPrice(marketDetailJSON.getDoubleValue("last"));
					marketDetail.setMarketLowPrice(marketDetailJSON.getDoubleValue("low"));
					marketDetail.setMarketReturnTime(DateFormatUtil.parseDateTimeLinux(marketDetailJSON.getString("time")));
					marketDetail.setMarketSellPrice(marketDetailJSON.getDoubleValue("sell"));
					marketDetail.setMarketStatus(0);
					marketDetail.setMarketTradeNum(marketDetailJSON.getDoubleValue("vol"));
					this.marketDetailService.insertOneService(marketDetail);
				} catch (Exception e)
				{
					ConstantFinalUtil.loggerMsg.error("-market detail-insert fail-{}",e);
				}
			}
		}
	}
	@SuppressWarnings("unchecked")
	private void saveMarketDeepData()
	{
		String[] urlArray=ConstantFinalUtil.getCoinDataUrl("deep.market");
		for(int i=0;i<urlArray.length;i++)
		{
			//请求所有币种的深度行情
			String getStr=HttpUtil.methodGet(urlArray[i]);
			JSONObject deepJSON=StringConvertUtil.toJSON(getStr);
			String coinName=subPreUrl(urlArray[i]);
			MVirtualCoin virtualCoin=(MVirtualCoin) ConstantFinalUtil.VIRTUAL_COIN_MAP.get(coinName);
			ConstantFinalUtil.loggerMsg.info("--saveMarketDeepData--{}--",coinName);
			//服务器端有给数据,才往下执行
			if(deepJSON!=null && deepJSON.getString("result").trim().equals("true"))
			{
				JSONArray buyArray=deepJSON.getJSONArray("bids");
				JSONArray sellArray=deepJSON.getJSONArray("asks");
				//增加卖出深度
				String sellUUID=ConstantFinalUtil.getRandomUUID();
				if(sellArray.size()>0)
				{
					for(int j=0;j<sellArray.size();j++)
					{
						List<String> list=(List<String>) sellArray.get(j);
						MMarketDeepSell mMarketDeepSell=new MMarketDeepSell();
						try
						{
							mMarketDeepSell.setDeepSellId(sellUUID);
							mMarketDeepSell.setDeepSellPrice(Double.parseDouble(list.get(0)));
							mMarketDeepSell.setDeepSellNum(Double.parseDouble(list.get(1)));
							mMarketDeepSell.setDeepSellStatus(0);
							mMarketDeepSell.setDeepSellCreateTime(new Date());
							this.marketDeepSellService.insertOneService(mMarketDeepSell);
							//ConstantFinalUtil.loggerMsg.info("--deep sell insert-success-{}",promptInfo);
						} catch (Exception e)
						{
							ConstantFinalUtil.loggerMsg.error("-deep sell-insert fail-{}",e);
						}
					}
				}
				//增加买入深度
				String buyUUID=ConstantFinalUtil.getRandomUUID();
				if(buyArray.size()>0)
				{
					for(int k=0;k<buyArray.size();k++)
					{
						List<String> list=(List<String>) buyArray.get(k);
						MMarketDeepBuy marketDeepBuy=new MMarketDeepBuy();
						try
						{
							marketDeepBuy.setDeepBuyId(buyUUID);
							marketDeepBuy.setDeepBuyPrice(Double.parseDouble(list.get(0)));
							marketDeepBuy.setDeepBuyNum(Double.parseDouble(list.get(1)));
							marketDeepBuy.setDeepBuyStatus(0);
							marketDeepBuy.setDeepBuyCreateTime(new Date());
							this.marketDeepBuyService.insertOneService(marketDeepBuy);
						//	ConstantFinalUtil.loggerMsg.info("--deep buy insert-success-{}",promptInfo);
						} catch (Exception e)
						{
							ConstantFinalUtil.loggerMsg.error("-deep buy-insert fail-{}",e);
						}
					}
				}
				//增加行情深度
				try
				{
					MMarketDetailDeep marketDetailDeep=new MMarketDetailDeep();
					marketDetailDeep.setMarketDeepId(ConstantFinalUtil.getRandomUUID());
					marketDetailDeep.setMarketDeepBuyId(buyUUID);
					marketDetailDeep.setMarketDeepSellId(sellUUID);
					marketDetailDeep.setMarketCreateTime(new Date());
					marketDetailDeep.setMarketDeepStatus(0);
					marketDetailDeep.setVirtualCoinId(virtualCoin.getVirtualCoinId());
					this.marketDetailDeepService.insertOneService(marketDetailDeep);
					//ConstantFinalUtil.loggerMsg.info("--deep insert-success-{}",promptInfo);
				} catch (Exception e)
				{
					ConstantFinalUtil.loggerMsg.error("-deep-insert fail-{}",e);
				}
			}
		}
	}
	private void saveTradeRecordData()
	{
		String[] urlArray=ConstantFinalUtil.getCoinDataUrl("success.trade");
		for(int i=0;i<urlArray.length;i++)
		{
			//请求所有币种的深度行情
			String getStr=HttpUtil.methodGet(urlArray[i]);
			JSONArray tradeArray=StringConvertUtil.toARRAY(getStr);
			String coinName=subPreUrl(urlArray[i]);
			//设置货币id
			MVirtualCoin virtualCoin=(MVirtualCoin) ConstantFinalUtil.VIRTUAL_COIN_MAP.get(coinName);
			ConstantFinalUtil.loggerMsg.info("--saveTradeRecordData--{}--",coinName);
			int size=tradeArray.size();
			if(tradeArray!=null && tradeArray.size()>0)
			{
				for(int j=0;j<size;j++)
				{
					try
					{
						JSONObject json=tradeArray.getJSONObject(j);
						MTradeRecord mTradeRecord=new MTradeRecord();
						mTradeRecord.setTradeRecordId(ConstantFinalUtil.getRandomUUID());
						mTradeRecord.setTradeId(json.getBigInteger("tid"));
						mTradeRecord.setTradeNum(json.getDoubleValue("amount"));
						mTradeRecord.setTradeRecordCreateTime(new Date());
						mTradeRecord.setTradeStatus(0);
						mTradeRecord.setTradeTime(DateFormatUtil.parseDateTimeLinux(json.getString("date")));
						if(json.getString("type").equalsIgnoreCase("buy"))
						{
							mTradeRecord.setTradeType(0);
						}else
						{
							mTradeRecord.setTradeType(1);
						}
						mTradeRecord.setVirtualCoinId(virtualCoin.getVirtualCoinId());
						this.mTradeRecordService.insertOneService(mTradeRecord);
//						ConstantFinalUtil.loggerMsg.info("--trade record insert-success-{}",promptInfo);
					} catch (Exception e)
					{
						ConstantFinalUtil.loggerMsg.error("-trade record-insert fail-{}",e);
					}
				}
			}
		}
	}
	
	private String subPreUrl(String coinName)
	{
		//https://api.btctrade.com/api/ticker?coin=btc
		coinName=coinName.substring(coinName.lastIndexOf("=")+1,coinName.length());
		return coinName.toUpperCase();
	}
	
	public void setCallTag(int callTag)
	{
		this.callTag = callTag;
	}
}
