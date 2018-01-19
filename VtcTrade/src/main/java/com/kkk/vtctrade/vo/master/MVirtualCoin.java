package com.kkk.vtctrade.vo.master;

import java.util.Date;
/**
 * m_virtual_coin
 * 虚拟货币信息表
 * **/
public class MVirtualCoin
{
	private String virtualCoinId;
	private String virtualCoinName;
	private int virtualCoinType;
	private Date vitualCoinCreateTime;
	private Date vitualCoinUpdateTime;
	private String virtualCreateUser;
	private int virtualStatus;
	public String getVirtualCoinId()
	{
		return virtualCoinId;
	}
	public void setVirtualCoinId(String virtualCoinId)
	{
		this.virtualCoinId = virtualCoinId;
	}
	public String getVirtualCoinName()
	{
		return virtualCoinName;
	}
	public void setVirtualCoinName(String virtualCoinName)
	{
		this.virtualCoinName = virtualCoinName;
	}
	public int getVirtualCoinType()
	{
		return virtualCoinType;
	}
	public void setVirtualCoinType(int virtualCoinType)
	{
		this.virtualCoinType = virtualCoinType;
	}
	public Date getVitualCoinCreateTime()
	{
		return vitualCoinCreateTime;
	}
	public void setVitualCoinCreateTime(Date vitualCoinCreateTime)
	{
		this.vitualCoinCreateTime = vitualCoinCreateTime;
	}
	public Date getVitualCoinUpdateTime()
	{
		return vitualCoinUpdateTime;
	}
	public void setVitualCoinUpdateTime(Date vitualCoinUpdateTime)
	{
		this.vitualCoinUpdateTime = vitualCoinUpdateTime;
	}
	public String getVirtualCreateUser()
	{
		return virtualCreateUser;
	}
	public void setVirtualCreateUser(String virtualCreateUser)
	{
		this.virtualCreateUser = virtualCreateUser;
	}
	public int getVirtualStatus()
	{
		return virtualStatus;
	}
	public void setVirtualStatus(int virtualStatus)
	{
		this.virtualStatus = virtualStatus;
	}
}
