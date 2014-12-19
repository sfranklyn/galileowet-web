/*
 * ConfigsBean.java
 * 
 * Created on Sep 7, 2009, 3:22:23 PM
 */
package galileowet.jsf.beans;

import com.google.inject.Inject;
import com.google.inject.servlet.SessionScoped;
import galileowet.ejb.datamodel.ConfigsDataModelBean;
import galileowet.ejb.datamodel.ConfigsDataModelRemote;
import galileowet.ejb.service.ConfigsServiceRemote;
import galileowet.jpa.Configs;
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
@Controller(name = "configs", scope = Scope.SESSION)
@SessionScoped
public class ConfigsBean {

    private final Integer noOfRows = 30;
    private final Integer fastStep = 10;
    private final DatabaseDataModel dataModel = new DatabaseDataModel();
    private ConfigsDataModelRemote configsDataModelRemote = null;
    private Configs configs = null;
    @Inject
    private VisitBean visit;
    @Inject
    private ConfigsServiceRemote configsServiceRemote = null;

    public String create() {
        configs = new Configs();
        return "redirect:secure/configsCreate";
    }

    public String saveCreate() {
        String result = "secure/configsCreate";

        List<String> errorList = configsServiceRemote.saveCreate(configs, visit.getLocale());
        if (errorList.size() > 0) {
            for (String error : errorList) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        error, ""));
            }
        } else {
            result = "redirect:secure/configs";
        }

        return result;
    }

    public String read() {
        configs = (Configs) dataModel.getRowData();
        return "redirect:secure/configsRead";
    }

    public String update() {
        configs = (Configs) dataModel.getRowData();
        return "redirect:secure/configsUpdate";
    }

    public String saveUpdate() {
        String result = "secure/configsUpdate";

        List<String> errorList = configsServiceRemote.saveUpdate(configs, visit.getLocale());
        if (errorList.size() > 0) {
            for (String error : errorList) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        error, ""));
            }
        } else {
            result = "redirect:secure/configs";
        }

        return result;
    }

    public String delete() {
        configs = (Configs) dataModel.getRowData();
        return "redirect:secure/configsDelete";
    }

    public String saveDelete() {
        String result = "secure/configsDelete";

        List<String> errorList = configsServiceRemote.saveDelete(configs, visit.getLocale());
        if (errorList.size() > 0) {
            for (String error : errorList) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        error, ""));
            }
        } else {
            result = "redirect:secure/configs";
        }

        return result;
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
    public void setConfigsDataModelRemote(ConfigsDataModelRemote configsDataModelRemote) {
        dataModel.setSelect(ConfigsDataModelBean.SELECT_ALL);
        dataModel.setSelectCount(ConfigsDataModelBean.SELECT_ALL_COUNT);
        dataModel.setSelectParam(null);
        dataModel.setWrappedData(configsDataModelRemote);
        this.configsDataModelRemote = configsDataModelRemote;
    }

    public Configs getConfigs() {
        return configs;
    }

    public void setConfigs(Configs configs) {
        this.configs = configs;
    }

    public ConfigsDataModelRemote getConfigsDataModelRemote() {
        return configsDataModelRemote;
    }
}
