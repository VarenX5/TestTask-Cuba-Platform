package com.company.testtask2.web.screens.itemtaken;

import com.company.testtask2.entity.DVDOwner;
import com.company.testtask2.web.beans.WebCurrentLoggedOwner;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.gui.screen.*;
import com.company.testtask2.entity.ItemTaken;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.List;

@UiController("testtask2_ItemsTakenFromUser.browse")
@UiDescriptor("items-taken-from-user-browse.xml")
@LookupComponent("itemTakensTable")
@LoadDataBeforeShow
public class ItemsTakenFromUserBrowse extends StandardLookup<ItemTaken> {
    @Inject
    private DataManager dataManager;

    private DVDOwner currentDVDOwner;
    @Inject
    private WebCurrentLoggedOwner webCurrentLoggedOwner;
    @Inject
    private Logger log;

    @Install(to = "itemTakensDl", target = Target.DATA_LOADER)
    private List<ItemTaken> itemTakensDlLoadDelegate(LoadContext<ItemTaken> loadContext) {

        try {
            currentDVDOwner = webCurrentLoggedOwner.getCurrentDVDOwner();
        } catch (Exception e){
            log.error("Error", e);
        }

        LoadContext<ItemTaken> loadItemTakenContext = LoadContext.create(ItemTaken.class)
                .setQuery(LoadContext.createQuery("select u from testtask2_ItemTaken u where u.takenDvd.owner = :currentUser  ")
                        .setParameter("currentUser",currentDVDOwner)).setView("itemTakenFromUser-view");

        return dataManager.loadList(loadItemTakenContext);
    }
}