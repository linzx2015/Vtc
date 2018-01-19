package com.kkk.vtctrade.service.user.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kkk.vtctrade.common.service.BaseServiceImpl;
import com.kkk.vtctrade.common.util.ConstantFinalUtil;
import com.kkk.vtctrade.common.util.PageInfoUtil;
import com.kkk.vtctrade.dao.master.MBtcUserDao;
import com.kkk.vtctrade.service.user.BtcUserService;
import com.kkk.vtctrade.vo.common.PromptInfo;
import com.kkk.vtctrade.vo.master.MBtcUser;

@Service
public class BtcUserServiceImpl extends BaseServiceImpl implements BtcUserService
{
	@Autowired
	private MBtcUserDao mBtcUserDao;
	
	@Override
	public PromptInfo insertOneService(MBtcUser btcUser)
	{
		int res = -1;
		try
		{
			res = mBtcUserDao.insertOne(btcUser);
			return this.getPromptInfo(res, btcUser.getUserBtcUid());
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("-insert-Exception-{}-", e);
			return this.getPromptInfo(res, e.toString());
		}
	}

	@Override
	public PromptInfo updateOneService(MBtcUser btcUser)
	{
		int res = -1;
		try
		{
			res = mBtcUserDao.updateOne(btcUser);
			return this.getPromptInfo(res, btcUser.getUserBtcUid());
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("-insert-Exception-{}-", e);
			return this.getPromptInfo(res, e.toString());
		}
	}

	@Override
	public PromptInfo deleteOneService(MBtcUser btcUser)
	{
		int res = -1;
		try
		{
			res = mBtcUserDao.deleteOne(btcUser);
			return this.getPromptInfo(res, btcUser.getUserBtcUid());
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("-insert-Exception-{}-", e);
			return this.getPromptInfo(res, e.toString());
		}
	}
	
	@Override
	public MBtcUser findOneService(Map<String, Object> paramMap)
	{
		return this.mBtcUserDao.findOne(paramMap);
	}

	@Override
	public List<MBtcUser> findMultiService(Map<String, Object> paramMap,PageInfoUtil pageInfoUtil)
	{
		return null;
	}
}
