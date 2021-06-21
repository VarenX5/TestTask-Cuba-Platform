package com.company.testtask2.web.screens.dvd;

import com.company.testtask2.entity.DVDOwner;
import com.company.testtask2.web.beans.TakenItemsList;
import com.company.testtask2.web.beans.WebCurrentLoggedOwner;
import com.haulmont.cuba.core.global.*;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.screen.*;
import com.company.testtask2.entity.DVD;
import com.haulmont.cuba.gui.screen.LookupComponent;
import com.haulmont.cuba.security.entity.User;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.List;

@UiController("testtask2_DVD.browse")
@UiDescriptor("dvd-browse.xml")
@LookupComponent("dVDsTable")
@LoadDataBeforeShow
public class DVDBrowse extends StandardLookup<DVD> {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(DVDBrowse.class);
    @Inject
    private DataManager dataManager;
    @Inject
    private TakenItemsList takenItemsList;
    @Inject
    private WebCurrentLoggedOwner webCurrentLoggedOwner;
    private DVDOwner currentDVDOwner;

    @Install(to = "dVDsDl", target = Target.DATA_LOADER)
    private List<DVD> dVDsDlLoadDelegate(LoadContext<DVD> loadContext) {
        try {
            currentDVDOwner = webCurrentLoggedOwner.getCurrentDVDOwner();
        } catch (Exception e){
            log.error("Error", e);
        }
        LoadContext<DVD> loadDVDContext = LoadContext.create(DVD.class).
                setQuery(LoadContext.
                        createQuery("select u from testtask2_DVD u where u.owner <> :ownerId").
                        setParameter("ownerId", currentDVDOwner)).setView("dvd-view");
        List<DVD> listOfDVDs = dataManager.loadList(loadDVDContext);
        List<DVD> listOfTakenDVD = takenItemsList.getListOfTakenDVD();
        listOfDVDs.removeIf(listOfTakenDVD::contains); //removing all dvds whose were taken by others users

        return listOfDVDs;

    }

    @Subscribe("dVDsTable.take")
    public void onDVDsTableTake(Action.ActionPerformedEvent event) {
        refreshScreen();
    }


    @Subscribe("refreshBtn")
    public void onRefreshBtnClick(Button.ClickEvent event) {
        refreshScreen();
    }

    public void refreshScreen(){
        getScreenData().loadAll();
    }
}