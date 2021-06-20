package com.company.testtask2.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "TESTTASK2_ITEM_TAKEN")
@Entity(name = "testtask2_ItemTaken")
@NamePattern("%s|takenDvd")
public class ItemTaken extends StandardEntity {
    private static final long serialVersionUID = -2168363581236063340L;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CURRENT_OWNER_ID")
    private DVDOwner currentOwner;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TAKEN_DVD_ID")
    private DVD takenDvd;

    public DVD getTakenDvd() {
        return takenDvd;
    }

    public void setTakenDvd(DVD takenDvd) {
        this.takenDvd = takenDvd;
    }

    public DVDOwner getCurrentOwner() {
        return currentOwner;
    }

    public void setCurrentOwner(DVDOwner currentOwner) {
        this.currentOwner = currentOwner;
    }
}