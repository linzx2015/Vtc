package com.kkk.vtctrade.config.ds;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import com.alibaba.druid.pool.DruidDataSource;
import com.kkk.vtctrade.common.util.ConstantFinalUtil;

@Configuration
@MapperScan(basePackages=ClusterDataSourceConfig.PACKAGE,sqlSessionFactoryRef="clusterSqlSessionFactory")
public class ClusterDataSourceConfig
{
	static final String PACKAGE="com.kkk.vtctrade.dao.cluster";
	static final String MAPPER_LOCATION="classpath:mapper/cluster/*Mapper.xml";
	
//	@Value("${cluster.datasource.url}")
//	private String cluster_url;
//	
//	@Value("${cluster.datasource.username}")
//	private String cluster_username;
//	
//	@Value("${cluster.datasource.password}")
//	private String cluster_password;
//	
//	@Value("${cluster.datasource.driverClassName}")
//	private String cluster_driver;
	
	@Bean("clusterDataSource")
	public DataSource clusterDataSource()
	{
		DruidDataSource dataSource=new DruidDataSource();
		dataSource.setUrl(ConstantFinalUtil.SYS_BUNDLE.getString("cluster.datasource.url"));
		dataSource.setUsername(ConstantFinalUtil.SYS_BUNDLE.getString("cluster.datasource.username"));
		dataSource.setPassword(ConstantFinalUtil.SYS_BUNDLE.getString("cluster.datasource.password"));
		dataSource.setDriverClassName(ConstantFinalUtil.SYS_BUNDLE.getString("cluster.datasource.driverClassName"));
		return dataSource;
	}
	
	@Bean("clusterTransactionManager")
	public DataSourceTransactionManager clusterTransactionManager()
	{
		return new DataSourceTransactionManager(clusterDataSource());
	}
	
	@Bean("clusterSqlSessionFactory")
	public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("clusterDataSource")DataSource clusterDataSource) throws Exception
	{
		final SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(clusterDataSource);
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(ClusterDataSourceConfig.MAPPER_LOCATION));
		return sqlSessionFactoryBean.getObject();
	}
	
}