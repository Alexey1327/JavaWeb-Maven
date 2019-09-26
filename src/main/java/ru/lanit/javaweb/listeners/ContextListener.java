package ru.lanit.javaweb.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.logging.Logger;

@WebListener
public class ContextListener implements ServletContextListener {

    private final Logger logger = Logger.getLogger(String.valueOf(ContextListener.class));

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        this.logger.info("Context initialized. \n");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        this.logger.info("Context destroyed. \n");
    }
}
