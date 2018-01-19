package com.kkk.vtctrade.vo.master;

import java.math.BigInteger;
import java.util.Date;

public class MTradeRecord
{
	private String tradeRecordId;
	private String virtualCoinId;
	private Date tradeTime;
	private double tradePrice;
	private double tradeNum;
	private BigInteger tradeId;
	private int tradeType;
	private int tradeStatus;
	private Date tradeRecordCreateTime;
	
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
	
	public String getTradeRecordId()
	{
		return tradeRecordId;
	}
	public void setTradeRecordId(String tradeRecordId)
	{
		this.tradeRecordId = tradeRecordId;
	}
	public String getVirtualCoinId()
	{
		return virtualCoinId;
	}
	public void setVirtualCoinId(String virtualCoinId)
	{
		this.virtualCoinId = virtualCoinId;
	}
	public Date getTradeTime()
	{
		return tradeTime;
	}
	public void setTradeTime(Date tradeTime)
	{
		this.tradeTime = tradeTime;
	}
	public double getTradePrice()
	{
		return tradePrice;
	}
	public void setTradePrice(double tradePrice)
	{
		this.tradePrice = tradePrice;
	}
	public double getTradeNum()
	{
		return tradeNum;
	}
	public void setTradeNum(double tradeNum)
	{
		this.tradeNum = tradeNum;
	}
	public BigInteger getTradeId()
	{
		return tradeId;
	}
	public void setTradeId(BigInteger tradeId)
	{
		this.tradeId = tradeId;
	}
	public int getTradeType()
	{
		return tradeType;
	}
	public void setTradeType(int tradeType)
	{
		this.tradeType = tradeType;
	}
	public int getTradeStatus()
	{
		return tradeStatus;
	}
	public void setTradeStatus(int tradeStatus)
	{
		this.tradeStatus = tradeStatus;
	}
	public Date getTradeRecordCreateTime()
	{
		return tradeRecordCreateTime;
	}
	public void setTradeRecordCreateTime(Date tradeRecordCreateTime)
	{
		this.tradeRecordCreateTime = tradeRecordCreateTime;
	}

}
