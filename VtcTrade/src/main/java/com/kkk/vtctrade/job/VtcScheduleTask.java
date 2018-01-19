package com.kkk.vtctrade.job;

import java.util.List;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import com.kkk.vtctrade.vo.master.MJobConfig;

@Component  //必须有的注解
public class VtcScheduleTask
{
	@Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
	
	//进行多个任务操作
    public void scheduleMultiJobs(List<MJobConfig> jobConfigList) throws SchedulerException {
    	Scheduler scheduler = schedulerFactoryBean.getScheduler();
    	for(MJobConfig jobConfig:jobConfigList)
    	{
    		startJob(scheduler,jobConfig); 
    	}
    }
    
    private void startJob(Scheduler scheduler,MJobConfig jobConfig) throws SchedulerException{
    	//新建一个任务
        JobDetail jobDetail = JobBuilder.newJob(MyScheduleJob.class).withIdentity(jobConfig.getJobName(),jobConfig.getJobGroup()).
        		usingJobData("currentJobId", jobConfig.getJobId()).build(); 
        //设置cron表达式
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobConfig.getJobCronExpression()); 
        //新建一个任务触发器
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(jobConfig.getJobTrigger(), 
        		jobConfig.getJobGroup()) .withSchedule(scheduleBuilder).build(); 
        //添加到任务调度队列中
        scheduler.scheduleJob(jobDetail,cronTrigger); 
    }
}
