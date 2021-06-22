package com.company.testtask2.core.roles;

import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.*;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.role.EntityAttributePermissionsContainer;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import com.haulmont.cuba.security.role.ScreenPermissionsContainer;
import com.haulmont.cuba.security.role.SpecificPermissionsContainer;

@Role(name = UsersFullAccessNoAdminRole.NAME, isDefault = true)
public class UsersFullAccessNoAdminRole extends AnnotatedRoleDefinition {
    public final static String NAME = "users-full-access-no-admin";

    @EntityAccess(entityName = "*", operations = {EntityOp.CREATE, EntityOp.READ, EntityOp.UPDATE, EntityOp.DELETE})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }
    @EntityAttributeAccess(entityName = "*", modify = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }

    @ScreenAccess(screenIds = {"help","settings","aboutWindow","application-testtask2","testtask2_DVD.edit",
            "testtask2_DVD.browse","testtask2_DVDOwned.browse","testtask2_ItemsTakenFromUser.browse",
            "testtask2_ItemTaken.browse","sec$User.changePassword","sec$User.edit"})
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }
    @SpecificAccess( permissions = "*")
    @Override
    public SpecificPermissionsContainer specificPermissions() {
        return super.specificPermissions();
    }
}
