/*
 * Users.java
 * 
 * Created on Aug 12, 2009, 10:31:36 AM
 */

package galileowet.jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 *
 * @author Samuel Franklyn
 */
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_name"})})
@NamedQueries({
    @NamedQuery(name = "Users.selectAll",
    query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.selectAllCount",
    query = "SELECT COUNT(u) FROM Users u"),
    @NamedQuery(name = "Users.selectByUserName",
    query = "SELECT u FROM Users u WHERE u.userName = :userName"),
    @NamedQuery(name = "Users.selectByUserIdUrl",
    query = "SELECT u FROM Users u, UsersRoles ur, UrlsRoles urls " +
    "WHERE " +
    "u.userId = :userId AND " +
    "u = ur.users AND " +
    "ur.roles = urls.roles AND " +
    "urls.urlsRolesPK.urlRole = :urlRole"),
    @NamedQuery(name = "Users.likeUserName",
    query = "SELECT u FROM Users u WHERE u.userName LIKE :userName")
})
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Basic(optional = false)
    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;
    @Basic(optional = false)
    @Column(name = "user_hcm", nullable = false, length = 50)
    private String userHcm;
    @Basic(optional = false)
    @Column(name = "user_password", nullable = false, length = 128)
    private String userPassword;
    @Basic(optional = false)
    @Column(name = "user_fname", nullable = false, length = 100)
    private String userFname;
    @Basic(optional = false)
    @Column(name = "user_lname", nullable = false, length = 100)
    private String userLname;
    @Column(name = "user_company_name", length = 50)
    private String userCompanyName;
    @Column(name = "user_address1", length = 50)
    private String userAddress1;
    @Column(name = "user_address2", length = 50)
    private String userAddress2;
    @Column(name = "user_city", length = 30)
    private String userCity;
    @Column(name = "user_state", length = 30)
    private String userState;
    @Column(name = "user_zip", length = 10)
    private String userZip;
    @Column(name = "user_country", length = 2)
    private String userCountry;
    @Column(name = "user_phone", length = 30)
    private String userPhone;
    @Column(name = "user_fax", length = 30)
    private String userFax;
    @Column(name = "user_mobile", length = 15)
    private String userMobile;
    @Column(name = "user_pager", length = 15)
    private String userPager;
    @Column(name = "user_email", length = 200)
    private String userEmail;
    @Basic(optional = false)
    @Column(name = "user_version", nullable = false)
    private int userVersion;
    @Basic(optional = false)
    @Column(name = "user_last_login", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date userLastLogin;
    @JoinColumn(name = "pcc_id", referencedColumnName = "pcc_id", nullable = false)
    @ManyToOne(optional = false)
    private Pccs pccId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<UsersRoles> usersRolesCollection;

    public Users() {
    }

    public Users(Integer userId) {
        this.userId = userId;
    }

    public Users(Integer userId, String userName, String userPassword, String userFname, String userLname, int userVersion) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userFname = userFname;
        this.userLname = userLname;
        this.userVersion = userVersion;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHcm() {
        return userHcm;
    }

    public void setUserHcm(String userHcm) {
        this.userHcm = userHcm;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserFname() {
        return userFname;
    }

    public void setUserFname(String userFname) {
        this.userFname = userFname;
    }

    public String getUserLname() {
        return userLname;
    }

    public void setUserLname(String userLname) {
        this.userLname = userLname;
    }

    public String getUserCompanyName() {
        return userCompanyName;
    }

    public void setUserCompanyName(String userCompanyName) {
        this.userCompanyName = userCompanyName;
    }

    public String getUserAddress1() {
        return userAddress1;
    }

    public void setUserAddress1(String userAddress1) {
        this.userAddress1 = userAddress1;
    }

    public String getUserAddress2() {
        return userAddress2;
    }

    public void setUserAddress2(String userAddress2) {
        this.userAddress2 = userAddress2;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getUserZip() {
        return userZip;
    }

    public void setUserZip(String userZip) {
        this.userZip = userZip;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserFax() {
        return userFax;
    }

    public void setUserFax(String userFax) {
        this.userFax = userFax;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserPager() {
        return userPager;
    }

    public void setUserPager(String userPager) {
        this.userPager = userPager;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getUserVersion() {
        return userVersion;
    }

    public void setUserVersion(int userVersion) {
        this.userVersion = userVersion;
    }

    public Pccs getPccId() {
        return pccId;
    }

    public void setPccId(Pccs pccId) {
        this.pccId = pccId;
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
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public Date getUserLastLogin() {
        return userLastLogin;
    }

    public void setUserLastLogin(Date userLastLogin) {
        this.userLastLogin = userLastLogin;
    }

}
