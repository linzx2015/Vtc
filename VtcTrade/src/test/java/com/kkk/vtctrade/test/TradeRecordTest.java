package com.kkk.vtctrade.test;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSONObject;
import com.kkk.vtctrade.common.test.BaseTest;
import com.kkk.vtctrade.common.util.BtcPostUtil;
import com.kkk.vtctrade.common.util.ConstantFinalUtil;
import com.kkk.vtctrade.service.market.MJobConfigService;
import com.kkk.vtctrade.service.market.MTradeRecordService;
import com.kkk.vtctrade.service.market.MVirtualCoinService;
import com.kkk.vtctrade.service.market.MarketDetailService;
import com.kkk.vtctrade.service.user.BtcUserService;
import com.kkk.vtctrade.service.user.UserService;
import com.kkk.vtctrade.vo.common.PromptInfo;
import com.kkk.vtctrade.vo.master.MBtcUser;
import com.kkk.vtctrade.vo.master.MMarketDetail;
import com.kkk.vtctrade.vo.master.MTradeRecord;
import com.kkk.vtctrade.vo.master.MUser;
import com.kkk.vtctrade.vo.master.MVirtualCoin;

public class TradeRecordTest extends BaseTest
{
	@Autowired
	private MTradeRecordService mTradeRecordService;
	@Autowired
	private MVirtualCoinService mVirtualCoinService;
	@Autowired
	private MJobConfigService mJobConfigService;
	@Autowired
	private MarketDetailService marketDetailService;
	@Autowired 
	private UserService userService;
	@Autowired
	private BtcUserService btcUserService;
	
	@Test
	public void insertUserTest()
	{
		MUser user=new MUser();
		user.setUserId(this.getRandomUUID());
		user.setUserName("lzx2015");
		user.setUserPassword("****");
		user.setUserCreateTime(new Date());
		PromptInfo promptInfo=userService.insertOneService(user);
		ConstantFinalUtil.loggerMsg.info("--info--{}",promptInfo);
	}
	
	@Test
	public void insertBtcUserTest()
	{
		JSONObject accountJSON=BtcPostUtil.getAccountInfo();
		MBtcUser btcUser=new MBtcUser();
		String uuid=this.getRandomUUID();
		btcUser.setUserPrimaryId(uuid);
		btcUser.setUserBtcUid(accountJSON.getString("uid"));
		btcUser.setUserAsset(accountJSON.getDoubleValue("asset"));
		btcUser.setUserNameAuth(accountJSON.getIntValue("nameauth"));
		btcUser.setUserBtcBalance(accountJSON.getDoubleValue("btc_balance"));
		btcUser.setUserBtcReserved(accountJSON.getDoubleValue("btc_reserved"));
		btcUser.setUserCnyBalance(accountJSON.getDoubleValue("cny_balance"));
		btcUser.setUserCnyReserved(accountJSON.getDoubleValue("cny_reserved"));
		btcUser.setUserDogeBalance(accountJSON.getDoubleValue("doge_balance"));
		btcUser.setUserDogeReserved(accountJSON.getDoubleValue("doge_reserved"));
		Map<String,Object> paramMap=this.getParamMap();
		paramMap.put("userName", "lzx2015");
		MUser user=this.userService.findOneService(paramMap);
		btcUser.setUserId(user.getUserId());
		btcUser.setUserLtcBalance(accountJSON.getDoubleValue("ltc_balance"));
		btcUser.setUserLtcReserved(accountJSON.getDoubleValue("ltc_reserved"));
		btcUser.setUserMobileFlag(accountJSON.getIntValue("moflag"));
		btcUser.setUserRequestTime(new Date());
		btcUser.setUserYbcBalance(accountJSON.getDoubleValue("ybc_balance"));
		btcUser.setUserYbcReserved(accountJSON.getDoubleValue("ybc_reserved"));
		btcUser.setUserNonce(accountJSON.getString("nonce"));
		ConstantFinalUtil.loggerMsg.info("--btcUser--{}",btcUser.getUserAsset());
		PromptInfo promptInfo=this.btcUserService.insertOneService(btcUser);
		ConstantFinalUtil.loggerMsg.info("--info--{}",promptInfo);
		paramMap.clear();
		paramMap.put("userPrimaryId", uuid);
		MBtcUser mBtcUser=this.btcUserService.findOneService(paramMap);
		ConstantFinalUtil.loggerMsg.info("--btcUser-2-{}",mBtcUser.getUserAsset());
	}
	
	@Test
	public void insertVitualCoinTest()
	{
//		common.market.eth=https://api.btctrade.com/api/ticker?coin=eth
//			common.market.etc=https://api.btctrade.com/api/ticker?coin=etc
//			common.market.ltc=https://api.btctrade.com/api/ticker?coin=ltc
//			common.market.doge=https://api.btctrade.com/api/ticker?coin=doge
//			common.market.ybc=
		MVirtualCoin mVirtualCoin=new MVirtualCoin();
		mVirtualCoin.setVirtualCoinId(getRandomUUID());
		mVirtualCoin.setVirtualCoinName("ETH");
		mVirtualCoin.setVirtualCoinType(0);
		mVirtualCoin.setVitualCoinCreateTime(new Date());
		mVirtualCoin.setVitualCoinUpdateTime(new Date());
		mVirtualCoin.setVirtualCreateUser("EEE");
		mVirtualCoin.setVirtualStatus(0);
		PromptInfo promptInfo=mVirtualCoinService.insertOneService(mVirtualCoin);
		ConstantFinalUtil.loggerMsg.info("--insert--{}",promptInfo);
	}
	
	@Test
	public void insertTradeRecordTest()
	{
		MTradeRecord mTradeRecord=new MTradeRecord();
		mTradeRecord.setTradeRecordId(this.getRandomUUID());
		mTradeRecord.setTradeNum(10.125);
		mTradeRecord.setTradePrice(0.111);
		mTradeRecord.setTradeRecordCreateTime(new Date());
		mTradeRecord.setTradeStatus(0);
		mTradeRecord.setTradeTime(new Date());
		mTradeRecord.setTradeType(0);
		mTradeRecord.setVirtualCoinId("07e495c5-a6ad-4b3f-9c3d-c6ec11a31e97");
		mTradeRecord.setTradeId(new BigInteger("889422663"));
		PromptInfo promptInfo=mTradeRecordService.insertOneService(mTradeRecord);
		ConstantFinalUtil.loggerMsg.info("--insert--{}",promptInfo);
	}
	
	@Test
	public void findTradeRecordTest()
	{
		Map<String,Object> paramMap=this.getParamMap();
		paramMap.put("tradeRecordId", "19f0d2e8-c969-42da-ae64-c23b1ef6cb2d");
		MTradeRecord mTradeRecord=this.mTradeRecordService.findOneService(paramMap);
		MVirtualCoin mVirtualCoin=mTradeRecord.getVirtualCoin();
		ConstantFinalUtil.loggerMsg.info("--find trade record--{}",mTradeRecord);
		paramMap.clear();
		paramMap.put("virtualCoinId", "07e495c5-a6ad-4b3f-9c3d-c6ec11a31e97");
		MVirtualCoin mVirtualCoin2=this.mVirtualCoinService.findOneService(paramMap);
		ConstantFinalUtil.loggerMsg.info("--find trade record--{}",mVirtualCoin2);
	}
	
	@Test
	public void insertJobConfigTest()
	{
		Map<String,Object> paramMap=this.getParamMap();
		//List<MJobConfig> jobConfigList=this.mJobConfigService.findMultiService(paramMap, null);
//		MJobConfig jobConfig=new MJobConfig();
//		jobConfig.setJobId(this.getRandomUUID());
//		jobConfig.setJobName("job1");
//		jobConfig.setJobGroup("group1");
//		jobConfig.setJobCronExpression("0/10 * * * * ?");
//		jobConfig.setJobTrigger("trigger1");
//		jobConfig.setJobStatus(0);
//		jobConfig.setJobCreateTime(new Date(1504927261));
//		jobConfig.setJobDescription("第二个测试的任务");
//		PromptInfo promptInfo=this.mJobConfigService.insertOneService(jobConfig);
//		ConstantFinalUtil.loggerMsg.info("--jobConfig-insert-{}",promptInfo);
		
		List<MMarketDetail> marketDetailList=marketDetailService.findMultiService(paramMap, null);
		String aa="common.market.btc";   
		ConstantFinalUtil.loggerMsg.info("---date----{}-",aa.substring(aa.lastIndexOf(".")+1,aa.length()));
//		paramMap.put("jobId", "402e81e0-2906-433b-ac55-918212b790fa");
//		MJobConfig jobConfig=this.mJobConfigService.findOneService(paramMap);
		
	}
}
