package com.kkk.vtctrade.vo.common;

/**
 * quartz任务调度配置
 * @author lzx
 * */
public class QuartzJobConfig
{	
	private String jobId;
	private String jobName;
	private String jobGroup;
	private String jobCronExpression;
	private String jobStatus;
	private String jobDescription;
	private String jobClassName;
	private String jobMethodName;
	
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
	public String getJobStatus()
	{
		return jobStatus;
	}
	public void setJobStatus(String jobStatus)
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
	public String getJobClassName()
	{
		return jobClassName;
	}
	public void setJobClassName(String jobClassName)
	{
		this.jobClassName = jobClassName;
	}
	public String getJobMethodName()
	{
		return jobMethodName;
	}
	public void setJobMethodName(String jobMethodName)
	{
		this.jobMethodName = jobMethodName;
	}
}
