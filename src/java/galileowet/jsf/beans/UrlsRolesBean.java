/*
 * UrlsRolesBean.java
 * 
 * Created on Sep 7, 2009, 2:08:00 PM
 */
package galileowet.jsf.beans;

import com.google.inject.Inject;
import com.google.inject.servlet.SessionScoped;
import galileowet.ejb.datamodel.RolesDataModelBean;
import galileowet.ejb.datamodel.RolesDataModelRemote;
import galileowet.ejb.datamodel.UrlsRolesDataModelBean;
import galileowet.ejb.datamodel.UrlsRolesDataModelRemote;
import galileowet.ejb.service.UrlsRolesServiceRemote;
import galileowet.jpa.Roles;
import galileowet.jpa.UrlsRoles;
import galileowet.jpa.UrlsRolesPK;
import galileowet.jsf.model.DatabaseDataModel;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.optimus.config.Scope;
import org.primefaces.optimus.config.annotations.Controller;

/**
 *
 * @author Samuel Franklyn
 */
@Controller(name = "urlsRoles", scope = Scope.SESSION)
@SessionScoped
public class UrlsRolesBean {

    private final Integer noOfRows = 15;
    private final Integer fastStep = 10;
    private final DatabaseDataModel dataModel = new DatabaseDataModel();
    private UrlsRolesDataModelRemote urlsRolesDataModelRemote = null;
    private UrlsRoles urlsRoles = null;
    @Inject
    private VisitBean visit;
    @Inject
    private UrlsRolesServiceRemote urlsRolesServiceRemote = null;
    @Inject
    private RolesDataModelRemote rolesDataModelRemote = null;

    public String create() {
        urlsRoles = new UrlsRoles();
        urlsRoles.setUrlsRolesPK(new UrlsRolesPK());
        urlsRoles.setRoles(new Roles());
        return "redirect:secure/urls_rolesCreate";
    }

    @SuppressWarnings("unchecked")
    public String saveCreate() {
        String result = "secure/urls_rolesCreate";

        List<String> errorList = urlsRolesServiceRemote.saveCreate(urlsRoles, visit.getLocale());
        if (errorList.size() > 0) {
            for (String error : errorList) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        error, ""));
            }
        } else {
            result = "redirect:secure/urls_roles";
        }

        return result;
    }

    public String read() {
        urlsRoles = (UrlsRoles) dataModel.getRowData();
        return "redirect:secure/urls_rolesRead";
    }

    public String delete() {
        urlsRoles = (UrlsRoles) dataModel.getRowData();
        return "redirect:secure/urls_rolesDelete";
    }

    @SuppressWarnings("unchecked")
    public String saveDelete() {
        String result = "secure/urls_rolesDelete";

        List<String> errorList = urlsRolesServiceRemote.saveDelete(urlsRoles, visit.getLocale());
        if (errorList.size() > 0) {
            for (String error : errorList) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        error, ""));
            }
        } else {
            result = "redirect:secure/urls_roles";
        }

        return result;
    }

    public List<SelectItem> getRoleNameList() {
        List<SelectItem> roleNameList = new ArrayList<SelectItem>();
        List rolesList = new ArrayList();
        rolesList = rolesDataModelRemote.getAll(RolesDataModelBean.SELECT_ALL, null, 0, Short.MAX_VALUE);
        for (int idx = 0; idx < rolesList.size(); idx++) {
            Roles roles = (Roles) rolesList.get(idx);
            SelectItem selectItem = new SelectItem(roles.getRoleName());
            roleNameList.add(selectItem);
        }
        return roleNameList;
    }

    public UrlsRoles getUrlsRoles() {
        return urlsRoles;
    }

    public void setUrlsRoles(UrlsRoles urlsRoles) {
        this.urlsRoles = urlsRoles;
    }

    public Integer getNoOfRows() {
        return noOfRows;
    }

    public Integer getFastStep() {
        return fastStep;
    }

    public DatabaseDataModel getDataModel() {
        return dataModel;
    }

    @Inject
    public void setUrlsRolesDataModelRemote(UrlsRolesDataModelRemote urlsRolesDataModelRemote) {
        dataModel.setSelect(UrlsRolesDataModelBean.SELECT_ALL);
        dataModel.setSelectCount(UrlsRolesDataModelBean.SELECT_ALL_COUNT);
        dataModel.setSelectParam(null);
        dataModel.setWrappedData(urlsRolesDataModelRemote);
        this.urlsRolesDataModelRemote = urlsRolesDataModelRemote;
    }

    public UrlsRolesDataModelRemote getUrlsRolesDataModelRemote() {
        return urlsRolesDataModelRemote;
    }
}
