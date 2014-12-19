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
        log.info("GWET0001:Session count=" + sessionCount.incrementAndGet() + " " + httpSession.getId());
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
                    log.info("GWET0001:" + req);
                    String resp = xs.terminalSubmit(req, visit.getSutaKey());
                    log.info("GWET0001:" + resp);
                    req = "SOF";
                    log.info("GWET0001:" + req);
                    resp = xs.terminalSubmit(req, visit.getSutaKey());
                    log.info("GWET0001:" + resp);
                    xs.endSession(Integer.MIN_VALUE + 1, visit.getSutaKey());
                    xs.destroySuta(visit.getSutaKey());
                    log.info("GWET0001:SUTA destroyed");
                }
            } catch (Exception ex) {
                Logger.getLogger(SessionServletListener.class.getName()).log(Level.SEVERE, "GWET0001:" + ex.toString(), ex);
            }
        }
        if (sessionCount.get() > 0) {
            log.info("GWET0001:Session count=" + sessionCount.decrementAndGet() + " " + httpSession.getId());
        }
    }
}
