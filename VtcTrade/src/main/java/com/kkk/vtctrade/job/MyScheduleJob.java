package com.kkk.vtctrade.job;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import com.kkk.vtctrade.common.util.ConstantFinalUtil;
import com.kkk.vtctrade.common.util.MultiThreadUtil;

/**
 * 具体需要执行的任务
 * @author lzx
 * */
public class MyScheduleJob implements Job
{
	private final int callMdTag=0;
	private final int callMddTag=1;
	private final int callRdTag=2;
	
	//具体执行的任务
	public void execute(JobExecutionContext ctx) throws JobExecutionException
	{
		JobKey jobKey=ctx.getJobDetail().getKey();
		//附加参数的map
		JobDataMap jobDataMap=ctx.getJobDetail().getJobDataMap();
		String jobId=jobDataMap.getString("currentJobId");
		ConstantFinalUtil.loggerMsg.info("---currentJob---{}---{}--",jobKey,jobId);
		
		//使用多线程执行查询任务
		List<MultiThreadUtil> multiThreadUtilList=new ArrayList<MultiThreadUtil>();
		for(int i=0;i<3;i++)
		{
			MultiThreadUtil multiThreadUtil=new MultiThreadUtil();
			switch (i)
			{
				case 0:
					multiThreadUtil.setCallTag(callMdTag);					
					break;
				case 1:
					multiThreadUtil.setCallTag(callMddTag);					
					break;
				case 2:
					multiThreadUtil.setCallTag(callRdTag);					
					break;
				default:
					break;
			}
			//将当前线程对象加入到多线程队列中
			multiThreadUtilList.add(multiThreadUtil);
		}
		
		try
		{
			//启动一个multiThreadUtilList.size()大小的线程池
			ExecutorService executorService=Executors.newFixedThreadPool(multiThreadUtilList.size());
			executorService.invokeAll(multiThreadUtilList);
			//当所有线程执行完之后,才能停下来
			executorService.shutdown();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
