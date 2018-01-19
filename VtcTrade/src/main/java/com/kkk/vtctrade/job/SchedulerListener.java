package com.kkk.vtctrade.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import com.kkk.vtctrade.common.util.ConstantFinalUtil;
import com.kkk.vtctrade.service.market.MJobConfigService;
import com.kkk.vtctrade.service.market.MTradeRecordService;
import com.kkk.vtctrade.service.market.MVirtualCoinService;
import com.kkk.vtctrade.service.market.MarketDeepBuyService;
import com.kkk.vtctrade.service.market.MarketDeepSellService;
import com.kkk.vtctrade.service.market.MarketDetailDeepService;
import com.kkk.vtctrade.service.market.MarketDetailService;
import com.kkk.vtctrade.vo.master.MJobConfig;
import com.kkk.vtctrade.vo.master.MVirtualCoin;

@Configuration
public class SchedulerListener implements ApplicationListener<ContextRefreshedEvent>
{
	@Autowired
	public VtcScheduleTask vtcScheduleTask;
	@Autowired
	private MJobConfigService mJobConfigService;
	@Autowired
	private MVirtualCoinService mVirtualCoinService;
	@Autowired
	public MarketDetailDeepService marketDetailDeepService;
	@Autowired
	public MTradeRecordService mTradeRecordService;
	@Autowired
	public MarketDetailService marketDetailService;
	@Autowired
	private MarketDeepSellService marketDeepSellService;
	@Autowired
	private MarketDeepBuyService marketDeepBuyService;
	
	
	//重写系统方法,供其他处获取schedulerFactoryBean对象
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean()
	{
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		return schedulerFactoryBean;
	}

	// 执行需要的任务调度
	public void onApplicationEvent(ContextRefreshedEvent event)
	{
		try
		{
			ConstantFinalUtil.SERVICE_MAP.put("marketDetailService", marketDetailService);
			ConstantFinalUtil.SERVICE_MAP.put("marketDetailDeepService", marketDetailDeepService);
			ConstantFinalUtil.SERVICE_MAP.put("mTradeRecordService", mTradeRecordService);
			ConstantFinalUtil.SERVICE_MAP.put("marketDeepSellService", marketDeepSellService);
			ConstantFinalUtil.SERVICE_MAP.put("marketDeepBuyService", marketDeepBuyService);
			Map<String,Object> paramMap=new HashMap<String,Object>();
			//加载所有货币
			List<MVirtualCoin> mVirtualCoinList=mVirtualCoinService.findMultiService(paramMap, null);
			if(mVirtualCoinList.size()>0)
			{
				for(MVirtualCoin virtualCoin:mVirtualCoinList)
				{
					//货币名称全部大写
					ConstantFinalUtil.VIRTUAL_COIN_MAP.put(virtualCoin.getVirtualCoinName().toUpperCase(),virtualCoin);
				}
			}
			List<MJobConfig> jobConfigList=this.mJobConfigService.findMultiService(paramMap, null);
			if(jobConfigList.size()>0)
			{
				vtcScheduleTask.scheduleMultiJobs(jobConfigList);
			}else
			{
				ConstantFinalUtil.loggerMsg.info("-onApplicationEvent-当前没有任务调度配置---");
			}
		} catch (SchedulerException e)
		{
			ConstantFinalUtil.loggerMsg.error("-onApplicationEvent-error-{}-",e);
		}
	}
	
	
}