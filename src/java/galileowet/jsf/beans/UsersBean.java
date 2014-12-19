/*
 * UsersBean.java
 * 
 * Created on Sep 3, 2009, 4:41:28 PM
 */
package galileowet.jsf.beans;

import com.google.inject.Inject;
import com.google.inject.servlet.SessionScoped;
import galileowet.ejb.datamodel.UsersDataModelBean;
import galileowet.ejb.datamodel.UsersDataModelRemote;
import galileowet.ejb.service.UsersServiceRemote;
import galileowet.jpa.Pccs;
import galileowet.jpa.Users;
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
@Controller(name = "users", scope = Scope.SESSION)
@SessionScoped
public class UsersBean {

    private final Integer noOfRows = 30;
    private final Integer fastStep = 10;
    private final DatabaseDataModel dataModel = new DatabaseDataModel();
    @Inject
    private VisitBean visit;
    @Inject
    private UsersServiceRemote usersServiceRemote;
    private UsersDataModelRemote usersDataModelRemote = null;
    private String userFname = null;
    private Users users = null;
    private String userPassword1 = "";
    private String userPassword2 = "";

    public String changePassword() {
        users = visit.getUsers();
        userPassword1 = "";
        userPassword2 = "";
        return "redirect:secure/change_password";
    }

    @SuppressWarnings("unchecked")
    public String saveChangePassword() {
        String result = "secure/change_password";

        List<String> errorList = usersServiceRemote.saveChangePassword(users,
                userPassword1, userPassword2, visit.getLocale());
        if (errorList.size() > 0) {
            for (String error : errorList) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        error, ""));
            }
        } else {
            result = "redirect:secure/index";
        }

        return result;
    }

    public String create() {
        users = new Users();
        users.setPccId(new Pccs());
        users.getPccId().setPccPcc(" ");
        return "redirect:secure/usersCreate";
    }

    @SuppressWarnings("unchecked")
    public String saveCreate() {
        String result = "secure/usersCreate";
        users.getPccId().setPccPcc(users.getPccId().getPccPcc().trim().toUpperCase());

        List<String> errorList = usersServiceRemote.saveCreate(users,
                userPassword1, userPassword2, visit.getLocale());
        if (errorList.size() > 0) {
            for (String error : errorList) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        error, ""));
            }
            if ("".equals(users.getPccId().getPccPcc())) {
                users.getPccId().setPccPcc(" ");
            }
        } else {
            create();
            result = "redirect:secure/users";
        }
        return result;
    }

    public String read() {
        users = (Users) dataModel.getRowData();
        return "redirect:secure/usersRead";
    }

    public String update() {
        users = (Users) dataModel.getRowData();
        return "redirect:secure/usersUpdate";
    }

    public String saveUpdate() {
        String result = "secure/usersUpdate";

        List<String> errorList = usersServiceRemote.saveUpdate(users, visit.getLocale());
        if (errorList.size() > 0) {
            for (String error : errorList) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        error, ""));
            }
        } else {
            result = "redirect:secure/users";
        }

        return result;
    }

    public String delete() {
        users = (Users) dataModel.getRowData();
        return "redirect:secure/usersDelete";
    }

    public String saveDelete() {
        String result = "secure/users";

        List<String> errorList = usersServiceRemote.saveDelete(users, visit.getLocale());
        if (errorList.size() > 0) {
            for (String error : errorList) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        error, ""));
            }
        } else {
            result = "redirect:secure/users";
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
    public void setUsersDataModelRemote(UsersDataModelRemote usersDataModelRemote) {
        dataModel.setSelect(UsersDataModelBean.SELECT_ALL);
        dataModel.setSelectCount(UsersDataModelBean.SELECT_ALL_COUNT);
        dataModel.setSelectParam(null);
        dataModel.setWrappedData(usersDataModelRemote);
        this.usersDataModelRemote = usersDataModelRemote;
    }

    public UsersDataModelRemote getUsersDataModelRemote() {
        return usersDataModelRemote;
    }

    public DatabaseDataModel getDataModel() {
        return dataModel;
    }

    public String getUserFname() {
        return userFname;
    }

    public void setUserFname(String userFname) {
        this.userFname = userFname;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getUserPassword1() {
        return userPassword1;
    }

    public void setUserPassword1(String userPassword1) {
        this.userPassword1 = userPassword1;
    }

    public String getUserPassword2() {
        return userPassword2;
    }

    public void setUserPassword2(String userPassword2) {
        this.userPassword2 = userPassword2;
    }
}
