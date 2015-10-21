/*
 * Pccs.java
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
@Table(name = "pccs", uniqueConstraints = {@UniqueConstraint(columnNames = {"pcc_pcc"})})
@NamedQueries({
    @NamedQuery(name = "Pccs.selectAll",
    query = "SELECT p FROM Pccs p"),
    @NamedQuery(name = "Pccs.selectAllCount",
    query = "SELECT COUNT(p) FROM Pccs p"),
    @NamedQuery(name = "Pccs.selectByPcc",
    query = "SELECT p FROM Pccs p WHERE p.pccPcc = :pccPcc"),
    @NamedQuery(name = "Pccs.selectByPccCount",
    query = "SELECT COUNT(p) FROM Pccs p WHERE p.pccPcc = :pccPcc")
})
public class Pccs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pcc_id", nullable = false)
    private Integer pccId;
    @Basic(optional = false)
    @Column(name = "pcc_pcc", nullable = false, length = 6)
    private String pccPcc;
    @Basic(optional = false)
    @Column(name = "pcc_desc", nullable = false, length = 200)
    private String pccDesc;
    @Basic(optional = false)
    @Column(name = "pcc_version", nullable = false)
    private int pccVersion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pccId")
    private Collection<Users> usersCollection;

    public Pccs() {
    }

    public Pccs(Integer pccId) {
        this.pccId = pccId;
    }

    public Pccs(Integer pccId, String pccPcc, String pccDesc, int pccVersion) {
        this.pccId = pccId;
        this.pccPcc = pccPcc;
        this.pccDesc = pccDesc;
        this.pccVersion = pccVersion;
    }

    public Integer getPccId() {
        return pccId;
    }

    public void setPccId(Integer pccId) {
        this.pccId = pccId;
    }

    public String getPccPcc() {
        return pccPcc;
    }

    public void setPccPcc(String pccPcc) {
        this.pccPcc = pccPcc;
    }

    public String getPccDesc() {
        return pccDesc;
    }

    public void setPccDesc(String pccDesc) {
        this.pccDesc = pccDesc;
    }

    public int getPccVersion() {
        return pccVersion;
    }

    public void setPccVersion(int pccVersion) {
        this.pccVersion = pccVersion;
    }

    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pccId != null ? pccId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pccs)) {
            return false;
        }
        Pccs other = (Pccs) object;
        if ((this.pccId == null && other.pccId != null) || (this.pccId != null && !this.pccId.equals(other.pccId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
