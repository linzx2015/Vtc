package com.kkk.vtctrade.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.kkk.vtctrade.common.controller.BaseController;
import com.kkk.vtctrade.service.user.UserService;
import com.kkk.vtctrade.vo.master.MUser;

@RestController
public class UserRestController extends BaseController
{
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/api/user",method=RequestMethod.GET)
	public MUser findOne(@RequestParam(value="mUserName",required=true)String mUserName,@RequestParam(value="cUserName",required=true)String cUserName)
	{
		Map<String,Object> paramMap=this.getParamMap();
		paramMap.put("mName", mUserName);
		paramMap.put("cName",cUserName);
		return this.userService.findOneService(paramMap);
	}
}
