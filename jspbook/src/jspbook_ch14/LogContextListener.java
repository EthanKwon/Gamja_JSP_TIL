package jspbook_ch14;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.*;

@WebListener
public class LogContextListener implements ServletContextListener{
	Logger LOG;
	
	public void contextDestroyed(ServletContextEvent arg0) {
		LOG.info("LogContextListener stop");
	}
	
	public void contextInitialized (ServletContextEvent arg0) {
		LOG=LoggerFactory.getLogger(this.getClass());
		LOG.info("LogContextListener start");
	}

	

}
