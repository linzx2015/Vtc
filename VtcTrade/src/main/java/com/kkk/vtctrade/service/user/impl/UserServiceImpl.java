package com.kkk.vtctrade.service.user.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kkk.vtctrade.common.service.BaseServiceImpl;
import com.kkk.vtctrade.common.util.ConstantFinalUtil;
import com.kkk.vtctrade.common.util.PageInfoUtil;
import com.kkk.vtctrade.dao.master.MUserDao;
import com.kkk.vtctrade.service.user.UserService;
import com.kkk.vtctrade.vo.common.PromptInfo;
import com.kkk.vtctrade.vo.master.MUser;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService
{
	@Autowired
	private MUserDao userDao;

	@Override
	public PromptInfo insertOneService(MUser user)
	{
		int res = -1;
		try
		{
			res = userDao.insertOne(user);
			return this.getPromptInfo(res, user.getUserId());
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("-insert-Exception-{}-", e);
			return this.getPromptInfo(res, e.toString());
		}
	}

	@Override
	public PromptInfo updateOneService(MUser user)
	{
		int res = -1;
		try
		{
			res = userDao.updateOne(user);
			return this.getPromptInfo(res, user.getUserId());
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("-update-Exception-{}-", e);
			return this.getPromptInfo(res, e.toString());
		}
	}

	@Override
	public PromptInfo deleteOneService(MUser user)
	{
		int res = -1;
		try
		{
			res = userDao.deleteOne(user);
			return this.getPromptInfo(res, user.getUserId());
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("-delete-Exception-{}-", e);
			return this.getPromptInfo(res, e.toString());
		}
	}

	@Override
	public MUser findOneService(Map<String, Object> paramMap)
	{
		return this.userDao.findOne(paramMap);
	}

	@Override
	public List<MUser> findMultiService(Map<String, Object> paramMap,PageInfoUtil pageInfoUtil)
	{

		return null;
	}

}
