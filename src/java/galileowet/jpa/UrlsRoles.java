/*
 * UrlsRoles.java
 * 
 * Created on Aug 12, 2009, 10:31:36 AM
 */
package galileowet.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 *
 * @author Samuel Franklyn
 */
@Entity
@Table(name = "urls_roles")
@NamedQueries({
    @NamedQuery(name = "UrlsRoles.selectAll",
    query = "SELECT u FROM UrlsRoles u ORDER BY u.roles.roleName,u.urlsRolesPK.urlRole"),
    @NamedQuery(name = "UrlsRoles.selectAllCount",
    query = "SELECT COUNT(u) FROM UrlsRoles u"),
    @NamedQuery(name = "UrlsRoles.selectByUrlRoleName",
    query = "SELECT u FROM UrlsRoles u WHERE " +
    "u.urlsRolesPK.urlRole = :urlRole AND " +
    "u.roles.roleName = :roleName")
})
public class UrlsRoles implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UrlsRolesPK urlsRolesPK;
    @Basic(optional = false)
    @Column(name = "url_role_version", nullable = false)
    private int urlRoleVersion;
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Roles roles;

    public UrlsRoles() {
    }

    public UrlsRoles(UrlsRolesPK urlsRolesPK) {
        this.urlsRolesPK = urlsRolesPK;
    }

    public UrlsRoles(UrlsRolesPK urlsRolesPK, int urlRoleVersion) {
        this.urlsRolesPK = urlsRolesPK;
        this.urlRoleVersion = urlRoleVersion;
    }

    public UrlsRoles(String urlRole, int roleId) {
        this.urlsRolesPK = new UrlsRolesPK(urlRole, roleId);
    }

    public UrlsRolesPK getUrlsRolesPK() {
        return urlsRolesPK;
    }

    public void setUrlsRolesPK(UrlsRolesPK urlsRolesPK) {
        this.urlsRolesPK = urlsRolesPK;
    }

    public int getUrlRoleVersion() {
        return urlRoleVersion;
    }

    public void setUrlRoleVersion(int urlRoleVersion) {
        this.urlRoleVersion = urlRoleVersion;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (urlsRolesPK != null ? urlsRolesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UrlsRoles)) {
            return false;
        }
        UrlsRoles other = (UrlsRoles) object;
        if ((this.urlsRolesPK == null && other.urlsRolesPK != null) || (this.urlsRolesPK != null && !this.urlsRolesPK.equals(other.urlsRolesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
