package com.kkk.vtctrade.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class BtcPostUtil
{
	private static final String PUBLIC_KEY=ConstantFinalUtil.API_BUNDLE.getString("btctrade.api.public.key");
	private static final String PRIVATE_KEY=ConstantFinalUtil.API_BUNDLE.getString("btctrade.api.private.key");
	/**
	 * 获取账户信息
	 * 
	 * **/
	public static JSONObject getAccountInfo()
	{
		String urlStr=ConstantFinalUtil.API_BUNDLE.getString("btctrade.user.info");
        //nonce 值每次都不可以相同,可以自己做值记录,每次加1 也可以用毫秒的时间戳
        String nonce = System.currentTimeMillis() + "";

        String params = "key=" + PUBLIC_KEY + "&nonce=" + nonce + "&version=2";

        String signature = EncryptUtil.hmac_sha256(params, EncryptUtil.MD5(PRIVATE_KEY));

        params += "&signature=" + signature;

        JSONObject accountJSON=null;
		try
		{
			String result = HttpUtil.methodPost(urlStr, params);
			ConstantFinalUtil.loggerMsg.info("-getAccountInfo--{}--",result);
			accountJSON = JSON.parseObject(result);
			accountJSON.put("nonce", nonce);
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.info("--getAccountInfo fail--{}",e);
		}
		return accountJSON;
	}
}
