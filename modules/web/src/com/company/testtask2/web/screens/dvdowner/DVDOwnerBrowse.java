package com.company.testtask2.web.screens.dvdowner;

import com.haulmont.cuba.gui.screen.*;
import com.company.testtask2.entity.DVDOwner;

@UiController("testtask2_DVDOwner.browse")
@UiDescriptor("dvd-owner-browse.xml")
@LookupComponent("dVDOwnersTable")
@LoadDataBeforeShow
public class DVDOwnerBrowse extends StandardLookup<DVDOwner> {
}