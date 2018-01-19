package com.kkk.vtctrade.config.ds;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import com.alibaba.druid.pool.DruidDataSource;
import com.kkk.vtctrade.common.util.ConstantFinalUtil;

@Configuration
//masterSqlSessionFactory定义一个唯一的主工厂实例,注解式mybatis配置
@MapperScan(basePackages=MasterDataSourceConfig.PACKAGE,sqlSessionFactoryRef="masterSqlSessionFactory")
public class MasterDataSourceConfig
{
	//精确到mater目录,区分cluster数据源
	static final String PACKAGE="com.kkk.vtctrade.dao.master";
	static final String MAPPER_LOCATION="classpath:mapper/master/*Mapper.xml";
	
	//生成一个dataSource,primary标识成主要的
	@Bean("masterDataSource")
	@Primary
	public DataSource masterDataSource()
	{
		DruidDataSource dataSource=new DruidDataSource();
		dataSource.setUrl(ConstantFinalUtil.SYS_BUNDLE.getString("master.datasource.url"));
		dataSource.setUsername(ConstantFinalUtil.SYS_BUNDLE.getString("master.datasource.username"));
		dataSource.setPassword(ConstantFinalUtil.SYS_BUNDLE.getString("master.datasource.password"));
		dataSource.setDriverClassName(ConstantFinalUtil.SYS_BUNDLE.getString("master.datasource.driverClassName"));
		return dataSource;
	}
	
	@Bean("masterTransactionManager")
	@Primary
	public DataSourceTransactionManager masterTransactionManager()
	{
		return new DataSourceTransactionManager(masterDataSource());
	}
	
	//Qualifier标识传入的对象名称需要与masterDataSource一致
	@Bean("masterSqlSessionFactory")
	@Primary		
	public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource")DataSource masterDataSource)throws Exception
	{
		final SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(masterDataSource);
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MasterDataSourceConfig.MAPPER_LOCATION));
		return sqlSessionFactoryBean.getObject();
	}
}
