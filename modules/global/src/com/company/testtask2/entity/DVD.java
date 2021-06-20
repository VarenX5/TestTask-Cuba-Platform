package com.company.testtask2.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "TESTTASK2_DVD")
@Entity(name = "testtask2_DVD")
@NamePattern("%s|name")
public class DVD extends StandardEntity {
    private static final long serialVersionUID = -2188278216883572619L;

    @Column(name = "NAME")
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "OWNER_ID")
    private DVDOwner owner;

    public DVDOwner getOwner() {
        return owner;
    }

    public void setOwner(DVDOwner owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}