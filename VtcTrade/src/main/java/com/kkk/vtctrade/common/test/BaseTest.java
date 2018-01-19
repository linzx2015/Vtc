package com.kkk.vtctrade.common.test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.kkk.vtctrade.VtcApplication;

@RunWith(SpringJUnit4ClassRunner.class)  //// SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes=VtcApplication.class) 			///@SpringApplicationConfiguration方法过时在1.4以后使用SpringBootTest
@WebAppConfiguration    //当前是Web项目，Junit需要模拟ServletContext，需要给测试类加上@WebAppConfiguration。
public class BaseTest
{
	private Map<String,Object> paramMap=new HashMap<String,Object>();
	
	protected String getRandomUUID()
	{
		UUID uuid=UUID.randomUUID();
		return uuid.toString();
	}
	
	protected Map<String,Object> getParamMap()
	{
		this.paramMap.clear();
		return this.paramMap;
	}
}
