package com.company.testtask2.core;

import com.company.testtask2.entity.DVD;
import com.company.testtask2.entity.DVDOwner;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.security.entity.User;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component(DvdUserDefaultCreator.NAME)
public class DvdUserDefaultCreator {
    public static final String NAME = "testtask2_DvdUserDefaultCreator";
    @Inject
    private DataManager dataManager;

    public DVDOwner createDvdOwner(User secUser) {
        DVDOwner newUser = dataManager.create(DVDOwner.class);
        newUser.setFirstName(secUser.getFirstName());
        newUser.setLastName(secUser.getLastName());
        newUser.setEmail(secUser.getEmail());
        newUser.setSystemUser(secUser);
        dataManager.commit(newUser);
        return newUser;
    }

    public DVD createDVD(String name, DVDOwner discOwner) {
        DVD newDvd = dataManager.create(DVD.class);
        newDvd.setName(name);
        newDvd.setOwner(discOwner);
        dataManager.commit(newDvd);
        return newDvd;
    }

    public void removeUsers(List<User> listOfUsers) {
        for (User user : listOfUsers) {
            dataManager.remove(dataManager.getReference(User.class, user.getId()));
        }
    }

    public void createAdminDvdOwner(User secUser){
        DVDOwner newUser = dataManager.create(DVDOwner.class);
        newUser.setFirstName("Admin first name");
        newUser.setLastName("Admin last name");
        newUser.setEmail(secUser.getEmail());
        newUser.setSystemUser(secUser);
        dataManager.commit(newUser);
    }
}