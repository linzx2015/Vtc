package com.kkk.vtctrade.vo.master;

import java.util.Date;

/**
 * 深度行情 购买信息
 * m_market_deep_buy
 * */
public class MMarketDeepBuy
{
	private String deepBuyId;
	private double deepBuyPrice;
	private double deepBuyNum;
	private Date deepBuyCreateTime;
	private int deepBuyStatus;
	public String getDeepBuyId()
	{
		return deepBuyId;
	}
	public void setDeepBuyId(String deepBuyId)
	{
		this.deepBuyId = deepBuyId;
	}
	public double getDeepBuyPrice()
	{
		return deepBuyPrice;
	}
	public void setDeepBuyPrice(double deepBuyPrice)
	{
		this.deepBuyPrice = deepBuyPrice;
	}
	public double getDeepBuyNum()
	{
		return deepBuyNum;
	}
	public void setDeepBuyNum(double deepBuyNum)
	{
		this.deepBuyNum = deepBuyNum;
	}
	public Date getDeepBuyCreateTime()
	{
		return deepBuyCreateTime;
	}
	public void setDeepBuyCreateTime(Date deepBuyCreateTime)
	{
		this.deepBuyCreateTime = deepBuyCreateTime;
	}
	public int getDeepBuyStatus()
	{
		return deepBuyStatus;
	}
	public void setDeepBuyStatus(int deepBuyStatus)
	{
		this.deepBuyStatus = deepBuyStatus;
	}
}
