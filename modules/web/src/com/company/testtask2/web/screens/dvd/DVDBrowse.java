package com.company.testtask2.web.screens.dvd;

import com.company.testtask2.entity.DVDOwner;
import com.haulmont.cuba.core.global.*;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.screen.*;
import com.company.testtask2.entity.DVD;
import com.haulmont.cuba.security.entity.User;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@UiController("testtask2_DVD.browse")
@UiDescriptor("dvd-browse.xml")
@LookupComponent("dVDsTable")
@LoadDataBeforeShow
public class DVDBrowse extends StandardLookup<DVD> {
    @Inject
    private DataManager dataManager;
    @Named("dVDsTable.take")
    private BaseAction dVDsTableTake;

    @Install(to = "dVDsDl", target = Target.DATA_LOADER)
    private List<DVD> dVDsDlLoadDelegate(LoadContext<DVD> loadContext) {
        User secUser = AppBeans.get(UserSessionSource.class).getUserSession().getUser();
        List<DVDOwner> list = dataManager.load(DVDOwner.class).query("select u from testtask2_DVDOwner u where u.systemUser = ?1", secUser).list();

        LoadContext<DVD> loadDVDContext = LoadContext.create(DVD.class).
                setQuery(LoadContext.
                        createQuery("select u from testtask2_DVD u where u.owner <> :ownerId").
                        setParameter("ownerId", list.get(0))).setView("dvd-view");

//        List<DVD> listOfDVDs = dataManager.loadList(loadDVDContext);
//        listOfDVDs.removeIf(dvd -> dvd.getItemTaken() != null); //removing all dvds whose were taken bu others users
//        return listOfDVDs;
        return dataManager.loadList(loadDVDContext);
    }

    @Subscribe("dVDsTable.take")
    public void onDVDsTableTake(Action.ActionPerformedEvent event) {


    }

}