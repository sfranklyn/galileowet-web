/*
 * Authentication.java
 * 
 * Created on Feb 26, 2009, 2:44:48 PM
 */
package galileowet.filter;

import galileowet.jsf.beans.VisitBean;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Samuel Franklyn
 */
public class Authentication implements Filter {

    private static final Logger log = Logger.getLogger(Authentication.class.getName());

    public Authentication() {
        super();
        if (log.isLoggable(Level.FINE)) {
            log.fine("Authentication filter created");
        }
    }

    @Override
    public void doFilter(final ServletRequest request,
            final ServletResponse response,
            final FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;
        final VisitBean visit = (VisitBean) req.getSession().getAttribute("visit");
        if ((visit != null) && (visit.getUsers() == null)) {
            visit.setSecurePath(req.getContextPath() + req.getServletPath() + req.getPathInfo());
            res.sendRedirect(req.getContextPath() + "/faces/index.xhtml");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        log.info("Init Authentication filter");
    }

    @Override
    public void destroy() {
        log.info("Destroy Authentication filter");
    }
}
