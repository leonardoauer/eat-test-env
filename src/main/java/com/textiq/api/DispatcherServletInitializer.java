package com.textiq.api;

import javax.servlet.ServletContext;

import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.textiq.api.config.AppConfig;

public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { AppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected void registerDispatcherServlet(ServletContext servletContext) {
		super.registerDispatcherServlet(servletContext);
		servletContext.addListener(new RequestContextListener());
//		servletContext.getSessionCookieConfig().setHttpOnly(true);
	}
}
