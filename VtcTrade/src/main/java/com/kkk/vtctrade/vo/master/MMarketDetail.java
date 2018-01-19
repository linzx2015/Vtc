package com.kkk.vtctrade.vo.master;

import java.util.Date;

/**
 * m_market_detail 
 * 当前行情信息表
 * */
public class MMarketDetail
{
	private String marketDetailId;
	private String virtualCoinId;
	private double marketHighPrice;
	private double marketLowPrice;
	private double marketBuyPrice;
	private double marketSellPrice;
	private double marketLastPrice;
	private double marketTradeNum;
	private Date marketReturnTime;
	private Date marketCreateTime;
	private int marketStatus;
	//一条交易记录对应一种货币
	private MVirtualCoin virtualCoin;
	public MVirtualCoin getVirtualCoin()
	{
		return virtualCoin;
	}
	public void setVirtualCoin(MVirtualCoin virtualCoin)
	{
		this.virtualCoin = virtualCoin;
	}
	
	public String getMarketDetailId()
	{
		return marketDetailId;
	}
	public void setMarketDetailId(String marketDetailId)
	{
		this.marketDetailId = marketDetailId;
	}
	public String getVirtualCoinId()
	{
		return virtualCoinId;
	}
	public void setVirtualCoinId(String virtualCoinId)
	{
		this.virtualCoinId = virtualCoinId;
	}
	public double getMarketHighPrice()
	{
		return marketHighPrice;
	}
	public void setMarketHighPrice(double marketHighPrice)
	{
		this.marketHighPrice = marketHighPrice;
	}
	public double getMarketLowPrice()
	{
		return marketLowPrice;
	}
	public void setMarketLowPrice(double marketLowPrice)
	{
		this.marketLowPrice = marketLowPrice;
	}
	public double getMarketBuyPrice()
	{
		return marketBuyPrice;
	}
	public void setMarketBuyPrice(double marketBuyPrice)
	{
		this.marketBuyPrice = marketBuyPrice;
	}
	public double getMarketSellPrice()
	{
		return marketSellPrice;
	}
	public void setMarketSellPrice(double marketSellPrice)
	{
		this.marketSellPrice = marketSellPrice;
	}
	public double getMarketLastPrice()
	{
		return marketLastPrice;
	}
	public void setMarketLastPrice(double marketLastPrice)
	{
		this.marketLastPrice = marketLastPrice;
	}
	public double getMarketTradeNum()
	{
		return marketTradeNum;
	}
	public void setMarketTradeNum(double marketTradeNum)
	{
		this.marketTradeNum = marketTradeNum;
	}
	public Date getMarketReturnTime()
	{
		return marketReturnTime;
	}
	public void setMarketReturnTime(Date marketReturnTime)
	{
		this.marketReturnTime = marketReturnTime;
	}
	public Date getMarketCreateTime()
	{
		return marketCreateTime;
	}
	public void setMarketCreateTime(Date marketCreateTime)
	{
		this.marketCreateTime = marketCreateTime;
	}
	public int getMarketStatus()
	{
		return marketStatus;
	}
	public void setMarketStatus(int marketStatus)
	{
		this.marketStatus = marketStatus;
	}
}
