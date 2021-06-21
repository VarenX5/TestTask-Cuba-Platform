package com.company.testtask2.web.screens.itemtaken;

import com.company.testtask2.entity.DVDOwner;
import com.company.testtask2.web.beans.WebCurrentLoggedOwner;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.gui.RemoveOperation;
import com.haulmont.cuba.gui.actions.list.RemoveAction;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.screen.*;
import com.company.testtask2.entity.ItemTaken;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@UiController("testtask2_ItemTaken.browse")
@UiDescriptor("item-taken-by-user-browse.xml")
@LookupComponent("itemTakensTable")
@LoadDataBeforeShow
public class ItemTakenByUserBrowse extends StandardLookup<ItemTaken> {
    @Inject
    private DataManager dataManager;
    private DVDOwner currentDVDOwner;
    @Inject
    private WebCurrentLoggedOwner webCurrentLoggedOwner;
    @Inject
    private Logger log;

    @Named("itemTakensTable.remove")
    private RemoveAction<ItemTaken> itemTakensTableRemove;
    @Inject
    private RemoveOperation removeOperation;
    @Inject
    private GroupTable<ItemTaken> itemTakensTable;

    @Install(to = "itemTakensDl", target = Target.DATA_LOADER)
    private List<ItemTaken> itemTakensDlLoadDelegate(LoadContext<ItemTaken> loadContext) {
        try {
            currentDVDOwner = webCurrentLoggedOwner.getCurrentDVDOwner();
        } catch (Exception e){
            log.error("Error", e);
        }

        LoadContext<ItemTaken> loadItemTakenContext = LoadContext.create(ItemTaken.class)
                .setQuery(LoadContext.createQuery("select u from testtask2_ItemTaken u where u.currentOwner = :currentUser")
                        .setParameter("currentUser",currentDVDOwner)).setView("itemTakenByUser-view");

        return dataManager.loadList(loadItemTakenContext);
    }

    @Subscribe("itemTakensTable.remove")
    public void onItemTakensTableRemove(Action.ActionPerformedEvent event) {

        removeOperation.builder(itemTakensTable)
                .withConfirmationTitle("Giving back DVD")
                .withConfirmationMessage("Do you want to give this DVD back to it's owner?")
                .remove();

    }





}