package com.U2.backend.Data.DataObjects;

import com.U2.backend.Data.DataObjectContracts.ICategory;
import com.U2.backend.Data.DataObjectContracts.IEvent;

import java.util.ArrayList;
import java.util.List;

public class Category implements ICategory {
    private String name;
    private List<IEvent>events;
    private List<String> tags;

    public Category(String name){
        this.name = name;
        events = new ArrayList<>();
        tags = new ArrayList<>();
    }

    public void addEvent(IEvent event){
        events.add(event);
    }

    public void addTag(String tag){
        if (!tags.contains(tag)){
            tags.add(tag);
        }
    }

    public String getName() {
        return name;
    }

    public List<IEvent> getEvents(){
        return events;
    }

    public List<String> getTags() {
        return tags;
    }
}
