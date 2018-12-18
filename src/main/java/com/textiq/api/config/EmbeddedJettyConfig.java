package com.textiq.api.config;

import java.io.IOException;

import org.eclipse.jetty.server.NCSARequestLog;
import org.eclipse.jetty.server.RequestLog;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class EmbeddedJettyConfig {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmbeddedJettyConfig.class);
    
    private static final int PORT = 5000;
    
    private static final String CONTEXT_PATH = "/";
    private static final String CONFIG_LOCATION_PACKAGE = "com.textiq.api.config";
    private static final String MAPPING_URL = "/";
    private static final String WEBAPP_DIRECTORY = "webapp";
    private static final String LOGS_DIRECTORY = "./logs/yyyy_mm_dd.eat-project-request.log";

    public static void startJetty() throws Exception {
        LOGGER.debug("Starting server at port {}", PORT);
        Server server = new Server(PORT);
        
        server.setHandler(getServletContextHandler());
//    	server.setRequestLog(getRequestLog());
        
        addRuntimeShutdownHook(server);
        
        server.start();
        LOGGER.info("Server started at port {}", PORT);
        server.join();
    }

    private static ServletContextHandler getServletContextHandler() throws IOException {
    	
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS); 
        contextHandler.setErrorHandler(null);
        contextHandler.setContextPath(CONTEXT_PATH);
        contextHandler.setClassLoader(Thread.currentThread().getContextClassLoader());
        
        // Spring
        WebApplicationContext webAppContext = getWebApplicationContext();
        DispatcherServlet dispatcherServlet = new DispatcherServlet(webAppContext);
        ServletHolder springServletHolder = new ServletHolder("default", dispatcherServlet);
        contextHandler.addServlet(springServletHolder, MAPPING_URL);
        contextHandler.addEventListener(new ContextLoaderListener(webAppContext));
       // contextHandler.setResourceBase(new ClassPathResource(WEBAPP_DIRECTORY).getURI().toString());
        
        return contextHandler;
    }

    private static WebApplicationContext getWebApplicationContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(CONFIG_LOCATION_PACKAGE);
        return context;
    }
    
    private static RequestLog getRequestLog() {

    	NCSARequestLog requestLog = new NCSARequestLog(LOGS_DIRECTORY);
    	requestLog.setRetainDays(90);
    	requestLog.setAppend(true);
    	requestLog.setExtended(false);
    	requestLog.setLogTimeZone("GMT");
    	
    	return requestLog;
    }
    
    private static void addRuntimeShutdownHook(final Server server) {
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                if (server.isStarted()) {
                	server.setStopAtShutdown(true);
                    try {
                    	server.stop();
                    } catch (Exception e) {
                        System.out.println("Error while stopping jetty server: " + e.getMessage());
                        LOGGER.error("Error while stopping jetty server: " + e.getMessage(), e);
                    }
                }
            }
        }));
	}
}
