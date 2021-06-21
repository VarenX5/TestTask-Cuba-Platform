package com.company.testtask2.web.screens.dvd;

import com.company.testtask2.web.beans.WebCurrentLoggedOwner;
import com.haulmont.cuba.gui.screen.*;
import com.company.testtask2.entity.DVD;

import javax.inject.Inject;

@UiController("testtask2_DVD.edit")
@UiDescriptor("dvd-edit.xml")
@EditedEntityContainer("dVDDc")
@LoadDataBeforeShow
public class DVDEdit extends StandardEditor<DVD> {
    @Inject
    private WebCurrentLoggedOwner webCurrentLoggedOwner;

    @Subscribe
    public void onInitEntity(InitEntityEvent<DVD> event) throws Exception {
            event.getEntity().setOwner(webCurrentLoggedOwner.getCurrentDVDOwner());
    }
}

