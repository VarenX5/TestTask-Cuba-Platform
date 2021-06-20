package com.company.testtask2.web.actions;

import com.company.testtask2.entity.DVD;
import com.company.testtask2.entity.DVDOwner;
import com.company.testtask2.entity.ItemTaken;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.MetadataTools;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.components.ActionType;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.actions.ItemTrackingAction;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.security.entity.User;

import javax.inject.Inject;
import java.util.List;

@ActionType("takeDvd")
public class TakeDvdAction extends ItemTrackingAction {

    @Inject
    private MetadataTools metadataTools;

    @Inject
    private DataManager dataManager;

    public TakeDvdAction(String id) {
        super(id);
        setCaption("Take");
    }

    @Override
    public void actionPerform(Component component) {

        Entity selected = getTarget().getSingleSelected();
        String instanceName = metadataTools.getInstanceName(selected);

        User secUser = AppBeans.get(UserSessionSource.class).getUserSession().getUser();
        List<DVDOwner> listOfOwners = dataManager.load(DVDOwner.class).query("select u from testtask2_DVDOwner u where u.systemUser = ?1", secUser).list();

        List<DVD> listOfDVD = dataManager.load(DVD.class).query("select u from testtask2_DVD u where u.name = ?1 " +
                "and u.owner <> ?2", instanceName, listOfOwners.get(0)).list();
        ItemTaken itemTaken = dataManager.create(ItemTaken.class);
        itemTaken.setCurrentOwner(listOfOwners.get(0));
        itemTaken.setTakenDvd(listOfDVD.get(0));
        dataManager.commit(itemTaken);
    }
}
