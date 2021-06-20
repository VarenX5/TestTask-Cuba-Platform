package com.company.testtask2.web.screens.dvdowner;

import com.haulmont.cuba.gui.screen.*;
import com.company.testtask2.entity.DVDOwner;

@UiController("testtask2_DVDOwner.edit")
@UiDescriptor("dvd-owner-edit.xml")
@EditedEntityContainer("dVDOwnerDc")
@LoadDataBeforeShow
public class DVDOwnerEdit extends StandardEditor<DVDOwner> {
}