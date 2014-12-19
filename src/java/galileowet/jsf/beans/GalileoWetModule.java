/*
 * GalileoWetModule.java
 * 
 * Created on Aug 10, 2009, 10:39:08 AM
 */
package galileowet.jsf.beans;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.jndi.JndiIntegration;
import galileowet.ejb.dao.RolesDaoRemote;
import galileowet.ejb.dao.UsersDaoRemote;
import galileowet.ejb.datamodel.*;
import galileowet.ejb.service.*;
import javax.naming.Context;
import javax.naming.InitialContext;


/**
 *
 * @author Samuel Franklyn
 */
public class GalileoWetModule implements Module {

    public void configure(Binder binder) {
        binder.bind(Context.class).to(InitialContext.class);
        binder.bind(UsersDaoRemote.class).toProvider(
                JndiIntegration.fromJndi(UsersDaoRemote.class, "galileowet.ejb.dao.UsersDaoRemote"));
        binder.bind(RolesDataModelRemote.class).toProvider(
                JndiIntegration.fromJndi(RolesDataModelRemote.class, "galileowet.ejb.datamodel.RolesDataModelRemote"));
        binder.bind(RolesDaoRemote.class).toProvider(
                JndiIntegration.fromJndi(RolesDaoRemote.class, "galileowet.ejb.dao.RolesDaoRemote"));
        binder.bind(UsersDataModelRemote.class).toProvider(
                JndiIntegration.fromJndi(UsersDataModelRemote.class, "galileowet.ejb.datamodel.UsersDataModelRemote"));
        binder.bind(UsersServiceRemote.class).toProvider(
                JndiIntegration.fromJndi(UsersServiceRemote.class, "galileowet.ejb.service.UsersServiceRemote"));
        binder.bind(RolesServiceRemote.class).toProvider(
                JndiIntegration.fromJndi(RolesServiceRemote.class, "galileowet.ejb.service.RolesServiceRemote"));
        binder.bind(PccsDataModelRemote.class).toProvider(
                JndiIntegration.fromJndi(PccsDataModelRemote.class, "galileowet.ejb.datamodel.PccsDataModelRemote"));
        binder.bind(PccsServiceRemote.class).toProvider(
                JndiIntegration.fromJndi(PccsServiceRemote.class, "galileowet.ejb.service.PccsServiceRemote"));
        binder.bind(UsersRolesDataModelRemote.class).toProvider(
                JndiIntegration.fromJndi(UsersRolesDataModelRemote.class, "galileowet.ejb.datamodel.UsersRolesDataModelRemote"));
        binder.bind(UsersRolesServiceRemote.class).toProvider(
                JndiIntegration.fromJndi(UsersRolesServiceRemote.class, "galileowet.ejb.service.UsersRolesServiceRemote"));
        binder.bind(UrlsRolesDataModelRemote.class).toProvider(
                JndiIntegration.fromJndi(UrlsRolesDataModelRemote.class, "galileowet.ejb.datamodel.UrlsRolesDataModelRemote"));
        binder.bind(UrlsRolesServiceRemote.class).toProvider(
                JndiIntegration.fromJndi(UrlsRolesServiceRemote.class, "galileowet.ejb.service.UrlsRolesServiceRemote"));
        binder.bind(ConfigsDataModelRemote.class).toProvider(
                JndiIntegration.fromJndi(ConfigsDataModelRemote.class, "galileowet.ejb.datamodel.ConfigsDataModelRemote"));
        binder.bind(ConfigsServiceRemote.class).toProvider(
                JndiIntegration.fromJndi(ConfigsServiceRemote.class, "galileowet.ejb.service.ConfigsServiceRemote"));
        binder.bind(XmlSelect.class).toProvider(
                JndiIntegration.fromJndi(XmlSelect.class, "galileowet.ejb.service.XmlSelect"));
    }
}
