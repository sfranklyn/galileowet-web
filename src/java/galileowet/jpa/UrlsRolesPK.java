/*
 * UrlsRolesPK.java
 * 
 * Created on Aug 12, 2009, 10:31:36 AM
 */

package galileowet.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 *
 * @author Samuel Franklyn
 */
@Embeddable
public class UrlsRolesPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "url_role", nullable = false, length = 250)
    private String urlRole;
    @Basic(optional = false)
    @Column(name = "role_id", nullable = false)
    private int roleId;

    public UrlsRolesPK() {
    }

    public UrlsRolesPK(String urlRole, int roleId) {
        this.urlRole = urlRole;
        this.roleId = roleId;
    }

    public String getUrlRole() {
        return urlRole;
    }

    public void setUrlRole(String urlRole) {
        this.urlRole = urlRole;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (urlRole != null ? urlRole.hashCode() : 0);
        hash += (int) roleId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UrlsRolesPK)) {
            return false;
        }
        UrlsRolesPK other = (UrlsRolesPK) object;
        if ((this.urlRole == null && other.urlRole != null) || (this.urlRole != null && !this.urlRole.equals(other.urlRole))) {
            return false;
        }
        if (this.roleId != other.roleId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
