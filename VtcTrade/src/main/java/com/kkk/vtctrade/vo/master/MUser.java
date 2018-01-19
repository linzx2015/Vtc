package com.kkk.vtctrade.vo.master;

import java.util.Date;

public class MUser
{
	private String userId;
	private String userName;
	private String userPassword;
	private Date userCreateTime;
	public String getUserId()
	{
		return userId;
	}
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getUserPassword()
	{
		return userPassword;
	}
	public void setUserPassword(String userPassword)
	{
		this.userPassword = userPassword;
	}
	public Date getUserCreateTime()
	{
		return userCreateTime;
	}
	public void setUserCreateTime(Date userCreateTime)
	{
		this.userCreateTime = userCreateTime;
	}
	
}
