/*
 * RolesBean.java
 * 
 * Created on Sep 4, 2009, 3:23:46 PM
 */

package galileowet.jsf.beans;

import com.google.inject.Inject;
import com.google.inject.servlet.SessionScoped;
import galileowet.ejb.datamodel.RolesDataModelBean;
import galileowet.ejb.datamodel.RolesDataModelRemote;
import galileowet.ejb.service.RolesServiceRemote;
import galileowet.jpa.Roles;
import galileowet.jsf.model.DatabaseDataModel;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.optimus.config.Scope;
import org.primefaces.optimus.config.annotations.Controller;

/**
 *
 * @author Samuel Franklyn
 */
@Controller(name = "roles", scope = Scope.SESSION)
@SessionScoped
public class RolesBean {

    private final Integer noOfRows = 30;
    private final Integer fastStep = 10;
    private final DatabaseDataModel dataModel = new DatabaseDataModel();
    private RolesDataModelRemote rolesDataModelRemote = null;
    private Roles roles = null;
    @Inject
    private VisitBean visit;
    @Inject
    RolesServiceRemote rolesServiceRemote = null;

    public String create() {
        roles = new Roles();
        return "redirect:secure/rolesCreate";
    }

    public String saveCreate() {
        String result = "secure/rolesCreate";

        List<String> errorList = rolesServiceRemote.saveCreate(roles, visit.getLocale());
        if (errorList.size() > 0) {
            for (String error : errorList) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        error, ""));
            }
        } else {
            result = "redirect:secure/roles";
        }

        return result;
    }

    public String read() {
        roles = (Roles) dataModel.getRowData();
        return "redirect:secure/rolesRead";
    }

    public String update() {
        roles = (Roles) dataModel.getRowData();
        return "redirect:secure/rolesUpdate";
    }

    public String saveUpdate() {
        String result = "secure/rolesUpdate";

        List<String> errorList = rolesServiceRemote.saveUpdate(roles, visit.getLocale());
        if (errorList.size() > 0) {
            for (String error : errorList) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        error, ""));
            }
        } else {
            result = "redirect:secure/roles";
        }

        return result;
    }

    public String delete() {
        roles = (Roles) dataModel.getRowData();
        return "redirect:secure/rolesDelete";
    }

    public String saveDelete() {
        String result = "secure/rolesDelete";

        List<String> errorList = rolesServiceRemote.saveDelete(roles, visit.getLocale());
        if (errorList.size() > 0) {
            for (String error : errorList) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        error, ""));
            }
        } else {
            result = "redirect:secure/roles";
        }

        return result;
    }

    public Integer getNoOfRows() {
        return noOfRows;
    }

    public Integer getFastStep() {
        return fastStep;
    }

    @Inject
    public void setRolesDataModelRemote(RolesDataModelRemote rolesDataModelRemote) {
        dataModel.setSelect(RolesDataModelBean.SELECT_ALL);
        dataModel.setSelectCount(RolesDataModelBean.SELECT_ALL_COUNT);
        dataModel.setSelectParam(null);
        dataModel.setWrappedData(rolesDataModelRemote);
        this.rolesDataModelRemote = rolesDataModelRemote;
    }

    public RolesDataModelRemote getRolesDataModelRemote() {
        return rolesDataModelRemote;
    }

    public DatabaseDataModel getDataModel() {
        return dataModel;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }
}

