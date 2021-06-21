package com.company.testtask2.web.screens.dvd;

import com.company.testtask2.entity.DVDOwner;
import com.company.testtask2.web.beans.TakenItemsList;
import com.company.testtask2.web.beans.WebCurrentLoggedOwner;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.gui.screen.*;
import com.company.testtask2.entity.DVD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

@UiController("testtask2_DVDOwned.browse")
@UiDescriptor("dvd-owned-browse.xml")
@LookupComponent("dVDsTable")
@LoadDataBeforeShow
public class DVDOwnedBrowse extends StandardLookup<DVD> {
    private static final Logger log = LoggerFactory.getLogger(DVDBrowse.class);
    @Inject
    private DataManager dataManager;
    @Inject
    private WebCurrentLoggedOwner webCurrentLoggedOwner;
    private DVDOwner currentDVDOwner;
    @Inject
    private TakenItemsList takenItemsList;

    @Install(to = "dVDsDl", target = Target.DATA_LOADER)
    private List<DVD> dVDsDlLoadDelegate(LoadContext<DVD> loadContext) {
        try {
            currentDVDOwner = webCurrentLoggedOwner.getCurrentDVDOwner();
        } catch (Exception e){
            log.error("Error", e);
        }

        LoadContext<DVD> loadDVDContext =LoadContext.create(DVD.class).
                setQuery(LoadContext.
                        createQuery("select u from testtask2_DVD u where u.owner = :ownerId").
                        setParameter("ownerId",currentDVDOwner));

        List<DVD> listOfDVDs = dataManager.loadList(loadDVDContext);
        List<DVD> listOfTakenDVD = takenItemsList.getListOfTakenDVD();
        listOfDVDs.removeIf(listOfTakenDVD::contains); //removing all dvds whose were taken by others users

        return listOfDVDs;
    }
}