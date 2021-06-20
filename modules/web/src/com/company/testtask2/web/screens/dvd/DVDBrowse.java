package com.company.testtask2.web.screens.dvd;

import com.company.testtask2.entity.DVDOwner;
import com.haulmont.cuba.core.global.*;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.model.Sorter;
import com.haulmont.cuba.gui.screen.*;
import com.company.testtask2.entity.DVD;
import com.haulmont.cuba.security.entity.User;

import javax.inject.Inject;
import java.util.List;
import java.util.function.Predicate;

@UiController("testtask2_DVD.browse")
@UiDescriptor("dvd-browse.xml")
@LookupComponent("dVDsTable")
@LoadDataBeforeShow
public class DVDBrowse extends StandardLookup<DVD> {
    @Inject
    private DataManager dataManager;
    @Inject
    private CollectionContainer<DVD> dVDsDc;

    @Install(to = "dVDsTable.create", subject = "initializer")
    private void dVDsTableCreateInitializer(DVD dVD) {

    }

    @Subscribe(target = Target.DATA_CONTEXT)
    public void onPreCommit(DataContext.PreCommitEvent event) {
        
    }

    public void onDVDsDlPreLoad(CollectionLoader.PostLoadEvent<DVD> event) throws Exception {
        User secUser = AppBeans.get(UserSessionSource.class).getUserSession().getUser();
        List<DVDOwner> list = dataManager.load(DVDOwner.class).query("select u from testtask2_DVDOwner u where u.systemUser = ?1", secUser).list();
        if (list.size()==0){
            throw new Exception("Such DVDOwner does not exist!");
        } else {
            List<DVD> loadedEntities = event.getLoadedEntities();
            loadedEntities.removeIf(entity -> entity.getOwner()==list.get(0));
            event.getLoadedEntities().removeIf(d->d.getOwner()!=list.get(0));
        }

    }

    @Install(to = "dVDsDl", target = Target.DATA_LOADER)
    private List<DVD> dVDsDlLoadDelegate(LoadContext<DVD> loadContext) {
        User secUser = AppBeans.get(UserSessionSource.class).getUserSession().getUser();
        List<DVDOwner> list = dataManager.load(DVDOwner.class).query("select u from testtask2_DVDOwner u where u.systemUser = ?1", secUser).list();

        LoadContext<DVD> loadDVDContext =LoadContext.create(DVD.class).
                setQuery(LoadContext.
                        createQuery("select u from testtask2_DVD u where u.owner <> :ownerId").
                        setParameter("ownerId",list.get(0))).setView("dvd-view");

        return dataManager.loadList(loadDVDContext);
    }


}