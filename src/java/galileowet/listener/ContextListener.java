/*
 * ContextListener.java
 * 
 * Created on Apr 17, 2009, 6:45:09 PM
 */
package galileowet.listener;

import galileowet.ejb.service.AppServiceRemote;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Samuel Franklyn
 */
public class ContextListener implements ServletContextListener {

    private static final Logger log = Logger.getLogger(ContextListener.class.getName());
    @EJB
    private AppServiceRemote appServiceRemote = null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        log.log(Level.INFO, "GWET0001: Context initialized {0}", sc.getContextPath());
        appServiceRemote.initDb();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.log(Level.INFO, "GWET0001: Context destroyed {0}", sce.getServletContext().getContextPath());
    }
}
