package com.springmvctest1.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext sc) throws ServletException {

		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(AppConfig.class);
		context.setServletContext(sc);
		
		ServletRegistration.Dynamic servlet = sc.addServlet("dynamic",
				new DispatcherServlet(context));

		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}

}
