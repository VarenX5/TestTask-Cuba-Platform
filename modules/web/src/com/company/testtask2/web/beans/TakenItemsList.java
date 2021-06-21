package com.company.testtask2.web.beans;

import com.company.testtask2.entity.DVD;
import com.company.testtask2.entity.ItemTaken;
import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Component(TakenItemsList.NAME)
public class TakenItemsList {
    public static final String NAME = "testtask2_TakenItemsList";

    @Inject
    private DataManager dataManager;

    //creating a List with Ids of taken DVDs
    public List<DVD> getListOfTakenDVD(){
        List<ItemTaken> listOfTakenItems =  dataManager.load(ItemTaken.class)
                .query("select u from testtask2_ItemTaken u").list();
        List<DVD> listOfDVD = new ArrayList<DVD>();
        for(ItemTaken itemTaken: listOfTakenItems){
            listOfDVD.add(itemTaken.getTakenDvd());
        }

        return listOfDVD;
    }

    public List<ItemTaken> getListOfTakenItems(){
        return dataManager.load(ItemTaken.class).query("select u from testtask2_ItemTaken u").list();
    }
}