package org.protoqol.logging;

import org.apache.log4j.*;
import org.protoqol.logging.io.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.io.*;
import java.net.*;

import static org.apache.log4j.xml.DOMConfigurator.configureAndWatch;
import static org.protoqol.logging.io.Io.closeQuietly;

public class Log4jServletContextListener implements ServletContextListener {

    private static final Logger log = Logger.getLogger(Log4jServletContextListener.class);

    public Log4jServletContextListener() {
    }

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            int log4jRefreshInterval = Integer.parseInt(System.getProperty("log4j.refreshMs", "10000"));
            String log4jConfigPath = servletContextEvent.getServletContext().getInitParameter("log4jConfigPath");
            initialiseLog4J(log4jConfigPath, log4jRefreshInterval);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private static void initialiseLog4J(String log4jPath, int log4jRefreshInterval) {
         URL log4jUrl = null;
         InputStream inputStream = null;
         try {
             log4jUrl = Thread.currentThread().getContextClassLoader().getResource(log4jPath);

             if (log4jUrl == null) {
                 throw new RuntimeException("Could not find log4j configuration on classpath @[" + log4jPath + "]");
             }
             configureAndWatch(log4jUrl.getFile(), log4jRefreshInterval);

             log.info("Log4J initialised with log configuration [" + log4jUrl.toExternalForm() + "]");
             log.info("Log4J Root Logging Level     : " + Logger.getRootLogger().getLevel());
             log.info("Log4J Refresh check interval : " + log4jRefreshInterval);

         } catch (Throwable t) {
             String uri = (log4jUrl != null) ? log4jUrl.toExternalForm() : "not found (was null), was looking for '" + log4jPath + "'";
             throw new RuntimeException("Could not initialise log4j @ [" + uri + "]"  , t);
         } finally {
             closeQuietly(inputStream);
         }
     }


    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.info("Servlet context destroyed");
    }

}
