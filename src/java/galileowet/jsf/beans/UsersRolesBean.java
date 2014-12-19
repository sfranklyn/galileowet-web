/*
 * UsersRolesBean.java
 * 
 * Created on Sep 7, 2009, 10:26:22 AM
 */

package galileowet.jsf.beans;

import com.google.inject.Inject;
import com.google.inject.servlet.SessionScoped;
import galileowet.ejb.dao.UsersDaoRemote;
import galileowet.ejb.datamodel.RolesDataModelBean;
import galileowet.ejb.datamodel.RolesDataModelRemote;
import galileowet.ejb.datamodel.UsersRolesDataModelBean;
import galileowet.ejb.datamodel.UsersRolesDataModelRemote;
import galileowet.ejb.service.UsersRolesServiceRemote;
import galileowet.jpa.Roles;
import galileowet.jpa.Users;
import galileowet.jpa.UsersRoles;
import galileowet.jpa.UsersRolesPK;
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
@Controller(name = "usersRoles", scope = Scope.SESSION)
@SessionScoped
public class UsersRolesBean {

    private final Integer noOfRows = 30;
    private final Integer fastStep = 10;
    private final DatabaseDataModel dataModel = new DatabaseDataModel();
    private UsersRoles usersRoles = null;
    private UsersRolesDataModelRemote usersRolesDataModelRemote = null;
    @Inject
    private VisitBean visit;
    @Inject
    UsersRolesServiceRemote usersRolesServiceRemote = null;
    @Inject
    RolesDataModelRemote rolesDataModelRemote = null;
    @Inject
    UsersDaoRemote usersDaoRemote = null;

    public String create() {
        usersRoles = new UsersRoles();
        usersRoles.setUsersRolesPK(new UsersRolesPK());
        usersRoles.setUsers(new Users());
        usersRoles.setRoles(new Roles());
        return "redirect:secure/users_rolesCreate";
    }

    @SuppressWarnings("unchecked")
    public String saveCreate() {
        String result = "secure/users_rolesCreate";

        List<String> errorList = usersRolesServiceRemote.saveCreate(usersRoles, visit.getLocale());
        if (errorList.size() > 0) {
            for (String error : errorList) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        error, ""));
            }
        } else {
            result = "redirect:secure/users_roles";
        }

        return result;
    }

    public String read() {
        usersRoles = (UsersRoles) dataModel.getRowData();
        return "redirect:secure/users_rolesRead";
    }

    public String delete() {
        usersRoles = (UsersRoles) dataModel.getRowData();
        return "redirect:secure/users_rolesDelete";
    }

    @SuppressWarnings("unchecked")
    public String saveDelete() {
        String result = "secure/users_rolesDelete";

        List<String> errorList = usersRolesServiceRemote.saveDelete(usersRoles, visit.getLocale());
        if (errorList.size() > 0) {
            for (String error : errorList) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        error, ""));
            }
        } else {
            result = "redirect:secure/users_roles";
        }

        return result;
    }

    public List autoCompleteUsers(Object suggest) {
        String strSuggest = (String) suggest;
        return usersDaoRemote.selectLikeUserName(strSuggest);
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

    public Integer getNoOfRows() {
        return noOfRows;
    }

    public Integer getFastStep() {
        return fastStep;
    }

    @Inject
    public void setUsersRolesDataModelRemote(UsersRolesDataModelRemote usersRolesDataModelRemote) {
        dataModel.setSelect(UsersRolesDataModelBean.SELECT_ALL);
        dataModel.setSelectCount(UsersRolesDataModelBean.SELECT_ALL_COUNT);
        dataModel.setSelectParam(null);
        dataModel.setWrappedData(usersRolesDataModelRemote);
        this.usersRolesDataModelRemote = usersRolesDataModelRemote;
    }

    public UsersRolesDataModelRemote getUsersRolesDataModelRemote() {
        return usersRolesDataModelRemote;
    }

    public DatabaseDataModel getDataModel() {
        return dataModel;
    }

    public UsersRoles getUsersRoles() {
        return usersRoles;
    }

    public void setUsersRoles(UsersRoles usersRoles) {
        this.usersRoles = usersRoles;
    }
}
