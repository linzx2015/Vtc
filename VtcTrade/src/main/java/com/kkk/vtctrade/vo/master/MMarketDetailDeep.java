package com.kkk.vtctrade.vo.master;

import java.util.Date;
import java.util.List;

/**
 * m_market_detail_deep
 * 深度行情信息表
 * */
public class MMarketDetailDeep
{
	private String marketDeepId;
	private String virtualCoinId;
	private String marketDeepSellId;
	private String marketDeepBuyId;
	private Date marketCreateTime;
	private int marketDeepStatus;
	
	//一条深度记录对应一种货币
	private MVirtualCoin virtualCoin;
	//一条深度记录对应多个买入
	private List<MMarketDeepBuy> marketDeepBuy;
	private List<MMarketDeepSell> marketDeepSell;
	
	public List<MMarketDeepBuy> getMarketDeepBuy()
	{
		return marketDeepBuy;
	}
	public void setMarketDeepBuy(List<MMarketDeepBuy> marketDeepBuy)
	{
		this.marketDeepBuy = marketDeepBuy;
	}
	public List<MMarketDeepSell> getMarketDeepSell()
	{
		return marketDeepSell;
	}
	public void setMarketDeepSell(List<MMarketDeepSell> marketDeepSell)
	{
		this.marketDeepSell = marketDeepSell;
	}
	public MVirtualCoin getVirtualCoin()
	{
		return virtualCoin;
	}
	public void setVirtualCoin(MVirtualCoin virtualCoin)
	{
		this.virtualCoin = virtualCoin;
	}
	public String getMarketDeepId()
	{
		return marketDeepId;
	}
	public void setMarketDeepId(String marketDeepId)
	{
		this.marketDeepId = marketDeepId;
	}
	public String getVirtualCoinId()
	{
		return virtualCoinId;
	}
	public void setVirtualCoinId(String virtualCoinId)
	{
		this.virtualCoinId = virtualCoinId;
	}
	public String getMarketDeepSellId()
	{
		return marketDeepSellId;
	}
	public void setMarketDeepSellId(String marketDeepSellId)
	{
		this.marketDeepSellId = marketDeepSellId;
	}
	public String getMarketDeepBuyId()
	{
		return marketDeepBuyId;
	}
	public void setMarketDeepBuyId(String marketDeepBuyId)
	{
		this.marketDeepBuyId = marketDeepBuyId;
	}
	public Date getMarketCreateTime()
	{
		return marketCreateTime;
	}
	public void setMarketCreateTime(Date marketCreateTime)
	{
		this.marketCreateTime = marketCreateTime;
	}
	public int getMarketDeepStatus()
	{
		return marketDeepStatus;
	}
	public void setMarketDeepStatus(int marketDeepStatus)
	{
		this.marketDeepStatus = marketDeepStatus;
	}
}
