package com.ityongman.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DatasourceConfig {
	
	/* 
	 * 解决 dataSource or dataSourceClassName or jdbcUrl is required 问题
	 */
	@Bean(name = "primaryDataSourceProperties")
	@Qualifier("primaryDataSourceProperties")
	@ConfigurationProperties(prefix="spring.datasource.primary")
	public DataSourceProperties primaryDataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean(name = "primaryDataSource")
	@Qualifier("primaryDataSource")
	@ConfigurationProperties(prefix="spring.datasource.primary")
	public DataSource primaryDataSource() {
		return primaryDataSourceProperties().initializeDataSourceBuilder().build();
	}
	
	@Bean(name = "secondDataSourceProperties")
	@Primary
	@Qualifier("secondDataSourceProperties")
	@ConfigurationProperties(prefix="spring.datasource.second")
	public DataSourceProperties secondDataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean(name = "secondDataSource")
	@Qualifier("secondDataSource")
	@Primary
	@ConfigurationProperties(prefix="spring.datasource.second")
	public DataSource secondDataSource() {
		return secondDataSourceProperties().initializeDataSourceBuilder().build();
	}
	
	@Bean(name = "primaryJdbcTemplate")
	public JdbcTemplate primaryJdbcTemplate(@Qualifier("primaryDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Bean(name = "secondJdbcTemplate")
	public JdbcTemplate secondJdbcTemplate(@Qualifier("secondDataSource") DataSource dataSource){
		return new JdbcTemplate(dataSource);	
	}
}
