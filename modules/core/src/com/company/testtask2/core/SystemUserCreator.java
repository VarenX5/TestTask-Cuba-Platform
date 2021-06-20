package com.company.testtask2.core;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.PasswordEncryption;
import com.haulmont.cuba.security.entity.Group;
import com.haulmont.cuba.security.entity.User;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.UUID;

@Component(SystemUserCreator.NAME)
public class SystemUserCreator {
    public static final String NAME = "testtask2_SystemUserCreator";
    @Inject
    private DataManager dataManager;
    @Inject
    private PasswordEncryption passwordEncryption;

    public User createSystemUser(String login, String password, String name, String firstName, String lastName, String email, Group group) {
        User newUser = dataManager.create(User.class);
        newUser.setLogin(login);
        newUser.setPassword(passwordEncryption.getPasswordHash(newUser.getId(), password));
        newUser.setName(name);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        //newUser.setUserRoles();
        if(group==null){
            newUser.setGroup(dataManager.getReference(Group.class, UUID.fromString("0fa2b1a5-1d68-4d69-9fbd-dff348347f93")));
        } else {
            newUser.setGroup(group);
        }
        //setting role

//        newUser.setUserRoles(dataManager.getReference(UserRole));

        dataManager.commit(newUser);
        return newUser;
    }
}