package com.company.testtask2.web.actions;

import com.company.testtask2.entity.DVD;
import com.company.testtask2.entity.DVDOwner;
import com.company.testtask2.entity.ItemTaken;
import com.company.testtask2.web.beans.TakenItemsList;
import com.company.testtask2.web.beans.WebCurrentLoggedOwner;
import com.company.testtask2.web.screens.dvd.DVDBrowse;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.MetadataTools;
import com.haulmont.cuba.gui.components.ActionType;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.actions.ItemTrackingAction;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ActionType("takeDvd")
public class TakeDvdAction extends ItemTrackingAction {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(DVDBrowse.class);
    @Inject
    private MetadataTools metadataTools;
    @Inject
    private DataManager dataManager;
    @Inject
    private WebCurrentLoggedOwner webCurrentLoggedOwner;
    private DVDOwner currentDVDOwner;


    public TakeDvdAction(String id) {
        super(id);
        setCaption("Take");
    }

    @Override
    public void actionPerform(Component component) {
        Entity selected = getTarget().getSingleSelected();
        String instanceName = metadataTools.getInstanceName(selected);

        try {
            currentDVDOwner = webCurrentLoggedOwner.getCurrentDVDOwner();
        } catch (Exception e) {
            log.error("Error", e);
        }

        List<DVD> listOfDVD = dataManager.load(DVD.class).query("select u from testtask2_DVD u where u.name = ?1 " +
                "and u.owner <> ?2", instanceName, currentDVDOwner).list();
        ItemTaken itemTaken = dataManager.create(ItemTaken.class);
        itemTaken.setCurrentOwner(currentDVDOwner);
        itemTaken.setTakenDvd(listOfDVD.get(0));
        if (!checkIfItemTakenExist(listOfDVD.get(0), currentDVDOwner)) {
            dataManager.commit(itemTaken);
        }
    }

    private boolean checkIfItemTakenExist(DVD dvd, DVDOwner currentDvdOwner) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("dvd", dvd);
        parameters.put("currentOwner", currentDvdOwner);
        List<ItemTaken> list = dataManager.load(ItemTaken.class)
                .query("select u from testtask2_ItemTaken u where u.takenDvd = :dvd " +
                        "and u.currentOwner = :currentOwner").setParameters(parameters).list();
        return list.size() != 0;
    }
}
