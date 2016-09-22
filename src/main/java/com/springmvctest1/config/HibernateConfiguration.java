package com.springmvctest1.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource(value ={"com.springmvctest1"})
public class HibernateConfiguration {

	@Autowired
	private Environment environment;
	
	//LocalSessionFactory
	//		setDataSource
	//			jdbc.driverClassName
	//			jdbc.url
	//			jdbc.username
	//			jdbc.password
	//		setHibernateProperties
	//			hibernate.dialect
	//			hibernate.show_sql
	//			hibernate.format_sql
	//			hibernate.hbm2ddl.auto
	//		setPackagesToScan
	//HibernateTransacionManager
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		
		LocalSessionFactoryBean session = new LocalSessionFactoryBean();
		session.setDataSource(dataSource());
		session.setHibernateProperties(hibernateProperties());
		session.setPackagesToScan(new String [] {"com.springmvctest1.model"});
		return session;
	}
	
	private Properties hibernateProperties() {
		
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		properties.setProperty("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.setProperty("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		properties.setProperty("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
		return properties;
	}

	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
		dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
		return dataSource;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s){
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}
}
