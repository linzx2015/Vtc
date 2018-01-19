package com.kkk.vtctrade.vo.master;

import java.util.Date;

/**
 * quartz 任务调度配置信息
 * @author lzx
 * */
public class MJobConfig
{
	private String jobId;
	private String jobName;
	private String jobGroup;
	private String jobCronExpression;
	private String jobTrigger;
	private int jobStatus;
	private String jobDescription;
	private Date jobCreateTime;
	public String getJobId()
	{
		return jobId;
	}
	public void setJobId(String jobId)
	{
		this.jobId = jobId;
	}
	public String getJobName()
	{
		return jobName;
	}
	public void setJobName(String jobName)
	{
		this.jobName = jobName;
	}
	public String getJobGroup()
	{
		return jobGroup;
	}
	public void setJobGroup(String jobGroup)
	{
		this.jobGroup = jobGroup;
	}
	public String getJobCronExpression()
	{
		return jobCronExpression;
	}
	public void setJobCronExpression(String jobCronExpression)
	{
		this.jobCronExpression = jobCronExpression;
	}
	public String getJobTrigger()
	{
		return jobTrigger;
	}
	public void setJobTrigger(String jobTrigger)
	{
		this.jobTrigger = jobTrigger;
	}
	public int getJobStatus()
	{
		return jobStatus;
	}
	public void setJobStatus(int jobStatus)
	{
		this.jobStatus = jobStatus;
	}
	public String getJobDescription()
	{
		return jobDescription;
	}
	public void setJobDescription(String jobDescription)
	{
		this.jobDescription = jobDescription;
	}
	public Date getJobCreateTime()
	{
		return jobCreateTime;
	}
	public void setJobCreateTime(Date jobCreateTime)
	{
		this.jobCreateTime = jobCreateTime;
	}
}
