package com.ityongman.config;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		transactionManagerRef="transactionManagerSecondary",
		entityManagerFactoryRef="entityManagerFactorySecondary",
		basePackages={"com.ityongman.serivce"}
)
public class SecondConfig {
	@Autowired
	@Qualifier("secondDataSource")
	private DataSource secondDataSource ;
	
	@Bean(name = "entityManagerSecondary")
	public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
		return entityManagerFactorySecondary(builder).getObject().createEntityManager();
	}
	
	@Bean(name = "entityManagerFactorySecondary")
	public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(secondDataSource)
				.packages("")
				.properties(getVendorProperties())
				.persistenceUnit("secondaryPersistenceUnit")
				.build();
	}
	
	@Autowired
	private JpaProperties jpaProperties; 
	
	private Map<String, Object> getVendorProperties() {
		return jpaProperties.getHibernateProperties(new HibernateSettings());
	}
	
	@Bean(name = "transactionManagerSecondary")
	public PlatformTransactionManager transactionManagerSecondary(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(entityManagerFactorySecondary(builder).getObject());
	}
}
