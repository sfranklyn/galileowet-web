/*
 * UsersRoles.java
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
@Table(name = "users_roles")
@NamedQueries({
    @NamedQuery(name = "UsersRoles.selectAll",
    query = "SELECT u FROM UsersRoles u"),
    @NamedQuery(name = "UsersRoles.selectAllCount",
    query = "SELECT COUNT(u) FROM UsersRoles u"),
    @NamedQuery(name = "UsersRoles.selectByUserId",
    query = "SELECT u FROM UsersRoles u WHERE " +
    "u.usersRolesPK.userId = :userId"),
    @NamedQuery(name = "UsersRoles.selectByUserNameRoleName",
    query = "SELECT u FROM UsersRoles u WHERE " +
    "u.users.userName = :userName AND " +
    "u.roles.roleName = :roleName")
})
public class UsersRoles implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsersRolesPK usersRolesPK;
    @Column(name = "users_roles_desc", length = 100)
    private String usersRolesDesc;
    @Basic(optional = false)
    @Column(name = "users_roles_version", nullable = false)
    private int usersRolesVersion;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Roles roles;

    public UsersRoles() {
    }

    public UsersRoles(UsersRolesPK usersRolesPK) {
        this.usersRolesPK = usersRolesPK;
    }

    public UsersRoles(UsersRolesPK usersRolesPK, int usersRolesVersion) {
        this.usersRolesPK = usersRolesPK;
        this.usersRolesVersion = usersRolesVersion;
    }

    public UsersRoles(int userId, int roleId) {
        this.usersRolesPK = new UsersRolesPK(userId, roleId);
    }

    public UsersRolesPK getUsersRolesPK() {
        return usersRolesPK;
    }

    public void setUsersRolesPK(UsersRolesPK usersRolesPK) {
        this.usersRolesPK = usersRolesPK;
    }

    public String getUsersRolesDesc() {
        return usersRolesDesc;
    }

    public void setUsersRolesDesc(String usersRolesDesc) {
        this.usersRolesDesc = usersRolesDesc;
    }

    public int getUsersRolesVersion() {
        return usersRolesVersion;
    }

    public void setUsersRolesVersion(int usersRolesVersion) {
        this.usersRolesVersion = usersRolesVersion;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
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
        hash += (usersRolesPK != null ? usersRolesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersRoles)) {
            return false;
        }
        UsersRoles other = (UsersRoles) object;
        if ((this.usersRolesPK == null && other.usersRolesPK != null) || (this.usersRolesPK != null && !this.usersRolesPK.equals(other.usersRolesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
