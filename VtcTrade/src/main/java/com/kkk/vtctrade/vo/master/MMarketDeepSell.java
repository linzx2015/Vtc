package com.kkk.vtctrade.vo.master;

import java.util.Date;

/**
 * 深度行情卖出信息
 * m_market_deep_sell
 * */
public class MMarketDeepSell
{
	private String deepSellId;
	private double deepSellPrice;
	private double deepSellNum;
	private Date deepSellCreateTime;
	private int deepSellStatus;
	public String getDeepSellId()
	{
		return deepSellId;
	}
	public void setDeepSellId(String deepSellId)
	{
		this.deepSellId = deepSellId;
	}
	public double getDeepSellPrice()
	{
		return deepSellPrice;
	}
	public void setDeepSellPrice(double deepSellPrice)
	{
		this.deepSellPrice = deepSellPrice;
	}
	public double getDeepSellNum()
	{
		return deepSellNum;
	}
	public void setDeepSellNum(double deepSellNum)
	{
		this.deepSellNum = deepSellNum;
	}
	public Date getDeepSellCreateTime()
	{
		return deepSellCreateTime;
	}
	public void setDeepSellCreateTime(Date deepSellCreateTime)
	{
		this.deepSellCreateTime = deepSellCreateTime;
	}
	public int getDeepSellStatus()
	{
		return deepSellStatus;
	}
	public void setDeepSellStatus(int deepSellStatus)
	{
		this.deepSellStatus = deepSellStatus;
	}
}
