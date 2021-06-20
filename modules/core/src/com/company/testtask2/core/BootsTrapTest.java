package com.company.testtask2.core;

import com.company.testtask2.entity.DVDOwner;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.PasswordEncryption;
import com.haulmont.cuba.core.sys.AppContext;
import com.haulmont.cuba.security.app.Authentication;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.group.AccessGroupsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

@Component(BootsTrapTest.NAME)
public class BootsTrapTest implements AppContext.Listener {
    public static final String NAME = "testtask2_BootsTrapTest";

    private final Logger log = LoggerFactory.getLogger(getClass());
    @Inject
    private DataManager dataManager;
    @Inject
    private Authentication authentication;
    @Inject
    private AccessGroupsService accessGroupsService;
    @Inject
    private PasswordEncryption passwordEncryption;
    @Inject
    private DvdUserDefaultCreator dvdOwnerDefaultCreator;
    @Inject
    private SystemUserCreator systemUserCreator;

    @PostConstruct
    public void init() {

        AppContext.addListener(this);
    }


    @Override
    public void applicationStarted() {
        authentication.begin();
        try {
            checkAndCreateJohnWickSystemUser();
            checkAndCreateFrodoBegginsSystemUser();

        } finally {
            authentication.end();
        }

    }

    @Override
    public void applicationStopped() {

    }

    private void checkAndCreateJohnWickSystemUser() {
        Boolean isJohnExist = isSystemUserExist("johnwick");
        if (isJohnExist) {
            User newSecUser = systemUserCreator.createSystemUser("johnwick", "1", "John Wick", "John", "Wick", "jw24@gmail.com", null);
            DVDOwner newDvdOwner = dvdOwnerDefaultCreator.createDvdOwner(newSecUser);
            dvdOwnerDefaultCreator.createDVD("John Wick 1 - Parabellum", newDvdOwner);
            dvdOwnerDefaultCreator.createDVD("John Wick 2 - Revenge", newDvdOwner);
            dvdOwnerDefaultCreator.createDVD("John Wick 3 - Peace of Mind", newDvdOwner);
        } else {
//                dataManager.remove(userList.get(0));
//                for(User user:userList){
//                    dataManager.remove(dataManager.getReference(User.class, user.getId()));
//                }
            //dataManager.remove(userList.get(0));
        }
    }
    private void checkAndCreateFrodoBegginsSystemUser(){
        Boolean isFrodoExist = isSystemUserExist("frodo");
        if (isFrodoExist){
            User newSecUser = systemUserCreator.createSystemUser("frodo", "1", "Frodo Beggins", "Frodo", "Beggins", "frodoBegg@lotr.com", null);
            DVDOwner newDvdOwner = dvdOwnerDefaultCreator.createDvdOwner(newSecUser);
            dvdOwnerDefaultCreator.createDVD("Lotr Ost – Shire theme", newDvdOwner);
            dvdOwnerDefaultCreator.createDVD("Lotr Ost – Middle Earth theme", newDvdOwner);
            dvdOwnerDefaultCreator.createDVD("Lotr Ost - Ambience", newDvdOwner);
        }
    }

    private Boolean isSystemUserExist(String username) {
        log.info("Checking existence of the " + username + " user");
        List<User> userList = dataManager.load(User.class).query("select u from sec$User u where u.login = :username")
                .parameter("username", username)
                .list();
        if(userList.size() == 0){
            log.info("User with login " + username + " doesn't exist!");
            return true;
        } else {
            log.info("User with login " + username + " does really exist!");
            return false;
        }

    }
}