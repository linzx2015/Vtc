package com.kkk.vtctrade.common.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * http的工具类
 * 
 * HTTP协议:
 * 把HTTP协议理解为写信的过程;
 * 通过firebug,可以查看每一个链接向服务器发出的详细信息;
 * 请求头======向服务器写信的信封
 * 响应头======服务器的回信的信封
 * 
 * 参数=====向服务器写信的内容(信封里面的内容)
 * 响应=====服务器的回信的信封里面的内容
 * 
 * @author lzx 
 *
 */
public class HttpUtil
{
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException
	{
		String PUBLIC_KEY=ConstantFinalUtil.API_BUNDLE.getString("btctrade.api.public.key");
		String PRIVATE_KEY=ConstantFinalUtil.API_BUNDLE.getString("btctrade.api.private.key");
		String urlStr=ConstantFinalUtil.API_BUNDLE.getString("btctrade.user.info");
        //nonce 值每次都不可以相同,可以自己做值记录,每次加1 也可以用毫秒的时间戳
        String nonce = System.currentTimeMillis() + "";

        String params = "key=" + PUBLIC_KEY + "&nonce=" + nonce + "&version=2";

        String signature = EncryptUtil.hmac_sha256(params, EncryptUtil.MD5(PRIVATE_KEY));

        params += "&signature=" + signature;

        String result = HttpUtil.methodPost(urlStr, params);

        ConstantFinalUtil.loggerMsg.info("-getAccountInfo--{}--",result);
	}
	/**
	 * httpget方式请求
	 * 参数==发信
	 * @param urlStr 请求地址
	 * @param headerMap 信封的内容
	 * @param paramsMap 信的内容
	 * @return 返回url对应的网页内容;回信的内容
	 */
	public static String methodGet(String urlStr)
	{
		StringBuffer sb = new StringBuffer();
		
		/* get提交 */
		try
		{
			/* 根据指定url字符串创建一个url对象 */
			URL url = new URL(urlStr);
			/* 查看一个urlConnection具体是什么
			 * 强转HttpURLConnection; 因为urlConnection本身就是HttpURLConnection;通过debug看到的
			 *  */
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection() ; 
			
			/* 设置超时时间 */
			urlConnection.setConnectTimeout(ConstantFinalUtil.CONNECT_TIMEOUT);
			urlConnection.setReadTimeout(ConstantFinalUtil.READ_TIMEOUT);
			
			/* 获取输入流 */
			InputStream is = urlConnection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String line = "" ; 
			while((line = br.readLine()) != null )
			{
				sb.append(line);
			}
			return sb.toString();
		} catch (Exception e)
		{
			ConstantFinalUtil.loggerMsg.error("发送get请求失败了;URL:{},参数:{},返回:{}"
					,urlStr,sb , e);
		}
		return null; 
	}
	
	/**
     * 发送http请求 获取结果
     *
     * @param requestUrl 请求的地址
     * @param params     post的参数
     * @return 返回的结果
     */
    public static String methodPost(String requestUrl, String params) {

        String result = null;
        HttpURLConnection connection = null;
        InputStreamReader in = null;
        try {
            URL url = new URL(requestUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Charset", "utf-8");
            DataOutputStream dop = new DataOutputStream(
                    connection.getOutputStream());
            if (params != null) {
                dop.writeBytes(params);
            }
            dop.flush();
            dop.close();
            in = new InputStreamReader(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(in);
            StringBuffer strBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                strBuffer.append(line);
            }
            result = strBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
