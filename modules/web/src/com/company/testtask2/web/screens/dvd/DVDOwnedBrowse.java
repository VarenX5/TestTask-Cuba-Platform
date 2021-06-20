package com.company.testtask2.web.screens.dvd;

import com.company.testtask2.entity.DVDOwner;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.screen.*;
import com.company.testtask2.entity.DVD;
import com.haulmont.cuba.security.entity.User;

import javax.inject.Inject;
import java.util.List;

@UiController("testtask2_DVDOwned.browse")
@UiDescriptor("dvd-owned-browse.xml")
@LookupComponent("dVDsTable")
@LoadDataBeforeShow
public class DVDOwnedBrowse extends StandardLookup<DVD> {
    @Inject
    private DataManager dataManager;

    @Install(to = "dVDsDl", target = Target.DATA_LOADER)
    private List<DVD> dVDsDlLoadDelegate(LoadContext<DVD> loadContext) {
        User secUser = AppBeans.get(UserSessionSource.class).getUserSession().getUser();
        List<DVDOwner> list = dataManager.load(DVDOwner.class).query("select u from testtask2_DVDOwner u where u.systemUser = ?1", secUser).list();

        LoadContext<DVD> loadDVDContext =LoadContext.create(DVD.class).
                setQuery(LoadContext.
                        createQuery("select u from testtask2_DVD u where u.owner = :ownerId").
                        setParameter("ownerId",list.get(0)));

        return dataManager.loadList(loadDVDContext);
    }
}