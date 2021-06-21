package com.company.testtask2.web.beans;

import com.company.testtask2.entity.DVDOwner;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.security.entity.User;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component(WebCurrentLoggedOwner.NAME)
public class WebCurrentLoggedOwner {
    public static final String NAME = "testtask2_WebCurrentLoggedOwner";
    @Inject
    private DataManager dataManager;

    public User getCurrentSecUser(){
        return AppBeans.get(UserSessionSource.class).getUserSession().getUser();

    }

    public DVDOwner getCurrentDVDOwner() throws Exception {
        List<DVDOwner> list = dataManager.load(DVDOwner.class).query("select u from testtask2_DVDOwner u where u.systemUser = ?1", getCurrentSecUser()).list();
        if (list.size()==0){
            throw new Exception("Such DVDOwner does not exist, please contact your administrator.");
        } else {
            return list.get(0);
        }
    }
}
