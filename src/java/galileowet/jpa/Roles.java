/*
 * Roles.java
 * 
 * Created on Aug 12, 2009, 10:31:36 AM
 */

package galileowet.jpa;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 *
 * @author Samuel Franklyn
 */
@Entity
@Table(name = "roles", uniqueConstraints = {@UniqueConstraint(columnNames = {"role_name"})})
@NamedQueries({
    @NamedQuery(name = "Roles.selectAll",
    query = "SELECT r FROM Roles r"),
    @NamedQuery(name = "Roles.selectAllCount",
    query = "SELECT COUNT(r) FROM Roles r"),
    @NamedQuery(name = "Roles.isAdminByUserName",
    query = "SELECT r " +
    "FROM Roles r, Users u, UsersRoles ur " +
    "WHERE " +
    "r = ur.roles AND " +
    "u = ur.users AND " +
    "u.userName = :userName AND " +
    "r.roleName = 'ADM'"),
    @NamedQuery(name = "Roles.selectMenuByUserName",
    query = "SELECT r.roleMenu " +
    "FROM Roles r, Users u, UsersRoles ur " +
    "WHERE " +
    "r = ur.roles AND " +
    "u = ur.users AND " +
    "u.userName = :userName"),
    @NamedQuery(name = "Roles.selectByUserName",
    query = "SELECT r " +
    "FROM Roles r, Users u, UsersRoles ur " +
    "WHERE " +
    "r = ur.roles AND " +
    "u = ur.users AND " +
    "u.userName = :userName"),
    @NamedQuery(name = "Roles.selectByRoleName",
    query = "SELECT r FROM Roles r WHERE r.roleName = :roleName")
})
public class Roles implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "role_id", nullable = false)
    private Integer roleId;
    @Basic(optional = false)
    @Column(name = "role_name", nullable = false, length = 50)
    private String roleName;
    @Basic(optional = false)
    @Column(name = "role_desc", nullable = false, length = 100)
    private String roleDesc;
    @Basic(optional = false)
    @Column(name = "role_menu", nullable = false, length = 50)
    private String roleMenu;
    @Basic(optional = false)
    @Column(name = "role_version", nullable = false)
    private int roleVersion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roles")
    private Collection<UrlsRoles> urlsRolesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roles")
    private Collection<UsersRoles> usersRolesCollection;

    public Roles() {
    }

    public Roles(Integer roleId) {
        this.roleId = roleId;
    }

    public Roles(Integer roleId, String roleName, String roleDesc, String roleMenu, int roleVersion) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleDesc = roleDesc;
        this.roleMenu = roleMenu;
        this.roleVersion = roleVersion;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public String getRoleMenu() {
        return roleMenu;
    }

    public void setRoleMenu(String roleMenu) {
        this.roleMenu = roleMenu;
    }

    public int getRoleVersion() {
        return roleVersion;
    }

    public void setRoleVersion(int roleVersion) {
        this.roleVersion = roleVersion;
    }

    public Collection<UrlsRoles> getUrlsRolesCollection() {
        return urlsRolesCollection;
    }

    public void setUrlsRolesCollection(Collection<UrlsRoles> urlsRolesCollection) {
        this.urlsRolesCollection = urlsRolesCollection;
    }

    public Collection<UsersRoles> getUsersRolesCollection() {
        return usersRolesCollection;
    }

    public void setUsersRolesCollection(Collection<UsersRoles> usersRolesCollection) {
        this.usersRolesCollection = usersRolesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleId != null ? roleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roles)) {
            return false;
        }
        Roles other = (Roles) object;
        if ((this.roleId == null && other.roleId != null) || (this.roleId != null && !this.roleId.equals(other.roleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
