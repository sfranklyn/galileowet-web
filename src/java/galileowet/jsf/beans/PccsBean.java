/*
 * PccsBean.java
 * 
 * Created on Sep 3, 2009, 12:51:37 PM
 */

package galileowet.jsf.beans;

import com.google.inject.Inject;
import com.google.inject.servlet.SessionScoped;
import galileowet.ejb.datamodel.PccsDataModelBean;
import galileowet.ejb.datamodel.PccsDataModelRemote;
import galileowet.ejb.service.PccsServiceRemote;
import galileowet.jpa.Pccs;
import galileowet.jsf.model.DatabaseDataModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.optimus.config.Scope;
import org.primefaces.optimus.config.annotations.Controller;

/**
 *
 * @author Samuel Franklyn
 */
@Controller(name = "pccs", scope = Scope.SESSION)
@SessionScoped
public class PccsBean {

    private final Integer noOfRows = 30;
    private final Integer fastStep = 10;
    private final DatabaseDataModel dataModel = new DatabaseDataModel();
    private Pccs pccs = new Pccs();
    private String pcc = "";
    private PropertyResourceBundle messageSource = null;
    @Inject
    private VisitBean visit = null;
    private PccsDataModelRemote pccsDataModelRemote = null;
    @Inject
    private PccsServiceRemote pccsServiceRemote = null;

    public String create() {
        pccs = new Pccs();
        return "redirect:secure/pccsCreate";
    }

    public String saveCreate() {
        String result = "secure/pccsCreate";

        List<String> errorList = pccsServiceRemote.saveCreate(pccs, visit.getLocale());
        if (errorList.size() > 0) {
            for (String error : errorList) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        error, ""));
            }
        } else {
            result = "redirect:secure/pccs";
        }

        return result;
    }

    public String delete() {
        pccs = (Pccs) dataModel.getRowData();
        return "redirect:secure/pccsDelete";
    }

    public String saveDelete() {
        String result = "secure/pccsDelete";

        List<String> errorList = pccsServiceRemote.saveDelete(pccs, visit.getLocale());
        if (errorList.size() > 0) {
            for (String error : errorList) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        error, ""));
            }
        } else {
            result = "redirect:secure/pccs";
        }

        return result;
    }

    public String read() {
        pccs = (Pccs) dataModel.getRowData();
        return "redirect:secure/pccsRead";
    }

    public String update() {
        pccs = (Pccs) dataModel.getRowData();
        return "redirect:secure/pccsUpdate";
    }

    public String saveUpdate() {
        String result = "secure/pccsUpdate";

        List<String> errorList = pccsServiceRemote.saveUpdate(pccs, visit.getLocale());
        if (errorList.size() > 0) {
            for (String error : errorList) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        error, ""));
            }
        } else {
            result = "redirect:secure/pccs";
        }

        return result;
    }

    public String findByPcc() {
        if (!pcc.equals("")) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("pccPcc", pcc);
            dataModel.setSelect(PccsDataModelBean.SELECT_BY_PCC);
            dataModel.setSelectCount(PccsDataModelBean.SELECT_BY_PCC_COUNT);
            dataModel.setSelectParam(param);
            dataModel.setWrappedData(pccsDataModelRemote);
        } else {
            dataModel.setSelect(PccsDataModelBean.SELECT_ALL);
            dataModel.setSelectCount(PccsDataModelBean.SELECT_ALL_COUNT);
            dataModel.setSelectParam(null);
            dataModel.setWrappedData(pccsDataModelRemote);
        }
        return "redirect:secure/pccs";
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

    public Pccs getPccs() {
        return pccs;
    }

    public void setPccs(Pccs pccs) {
        this.pccs = pccs;
    }

    public PropertyResourceBundle getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(PropertyResourceBundle messageSource) {
        this.messageSource = messageSource;
    }

    public VisitBean getVisit() {
        return visit;
    }

    public void setVisit(VisitBean visit) {
        this.visit = visit;
    }

    public PccsDataModelRemote getPccsDataModelRemote() {
        return pccsDataModelRemote;
    }

    @Inject
    public void setPccsDataModelRemote(PccsDataModelRemote pccsDataModelRemote) {
        dataModel.setSelect(PccsDataModelBean.SELECT_ALL);
        dataModel.setSelectCount(PccsDataModelBean.SELECT_ALL_COUNT);
        dataModel.setSelectParam(null);
        dataModel.setWrappedData(pccsDataModelRemote);
        this.pccsDataModelRemote = pccsDataModelRemote;
    }

    public PccsServiceRemote getPccsServiceRemote() {
        return pccsServiceRemote;
    }

    public void setPccsServiceRemote(PccsServiceRemote pccsServiceRemote) {
        this.pccsServiceRemote = pccsServiceRemote;
    }

    public String getPcc() {
        return pcc;
    }

    public void setPcc(String pcc) {
        this.pcc = pcc;
    }

}