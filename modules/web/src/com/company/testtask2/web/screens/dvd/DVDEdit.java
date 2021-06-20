package com.company.testtask2.web.screens.dvd;

import com.company.testtask2.entity.DVDOwner;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.screen.*;
import com.company.testtask2.entity.DVD;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.entity.UserSessionEntity;

import javax.inject.Inject;
import java.util.List;

@UiController("testtask2_DVD.edit")
@UiDescriptor("dvd-edit.xml")
@EditedEntityContainer("dVDDc")
@LoadDataBeforeShow
public class DVDEdit extends StandardEditor<DVD> {
    @Inject
    private DataManager dataManager;

    @Subscribe
    public void onInitEntity(InitEntityEvent<DVD> event) throws Exception {

        User secUser = AppBeans.get(UserSessionSource.class).getUserSession().getUser();
        List<DVDOwner> list = dataManager.load(DVDOwner.class).query("select u from testtask2_DVDOwner u where u.systemUser = ?1", secUser).list();
        if (list.size()==0){
            throw new Exception("such DVDOwner does not exist!");
        } else {
            event.getEntity().setOwner(list.get(0));
        }
    }
}

