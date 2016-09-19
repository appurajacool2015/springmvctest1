package com.springmvctest1.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages ={"com.springmvctest1"})
public class AppConfig extends WebMvcConfigurationSupport{
	
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver view = new InternalResourceViewResolver();
		view.setPrefix("/WEB-INF/views/");
		view.setSuffix(".jsp");
		return view;
	}

	public MessageSource getMessageSource(){
		ResourceBundleMessageSource msgSource = new ResourceBundleMessageSource();
		msgSource.setBasenames("messages");
		return msgSource;
	}
}
