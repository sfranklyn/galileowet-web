/*
 * SessionServletListener.java
 * 
 * Created on Sep 1, 2009, 3:15:53 PM
 */
package galileowet.listener;

import galileowet.ejb.service.XmlSelect;
import galileowet.jsf.beans.VisitBean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Web application lifecycle listener.
 * @author Samuel Franklyn
 */
public class SessionServletListener implements HttpSessionListener {

    private static final Logger log = Logger.getLogger(SessionServletListener.class.getName());
    private final AtomicInteger sessionCount = new AtomicInteger();

    public void sessionCreated(HttpSessionEvent se) {
        HttpSession httpSession = se.getSession();
        log.log(Level.INFO, "GWET0001:Session count={0} {1}", new Object[]{sessionCount.incrementAndGet(), httpSession.getId()});
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession httpSession = se.getSession();
        Object obj = httpSession.getAttribute("visit");
        if (obj != null) {
            VisitBean visit = (VisitBean) obj;
            try {
                XmlSelect xs = visit.getXs();
                if (xs != null) {
                    String req = "I";
                    log.log(Level.INFO, "GWET0001:{0}", req);
                    String resp = xs.terminalSubmit(req, visit.getSutaKey());
                    log.log(Level.INFO, "GWET0001:{0}", resp);
                    req = "SOF";
                    log.log(Level.INFO, "GWET0001:{0}", req);
                    resp = xs.terminalSubmit(req, visit.getSutaKey());
                    log.log(Level.INFO, "GWET0001:{0}", resp);
                    xs.endSession(Integer.MIN_VALUE + 1, visit.getSutaKey());
                    xs.destroySuta(visit.getSutaKey());
                    log.info("GWET0001:SUTA destroyed");
                }
            } catch (Exception ex) {
                log.log(Level.SEVERE, "GWET0001:" + ex.toString(), ex);
            }
        }
        if (sessionCount.get() > 0) {
            log.log(Level.INFO, "GWET0001:Session count={0} {1}", new Object[]{sessionCount.decrementAndGet(), httpSession.getId()});
        }
    }
}
