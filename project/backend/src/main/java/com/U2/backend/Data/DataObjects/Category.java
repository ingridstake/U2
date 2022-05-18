package com.U2.backend.Data.DataObjects;

import com.U2.backend.Data.DataObjectContracts.ICategory;
import com.U2.backend.Data.DataObjectContracts.ICategoryTag;
import com.U2.backend.Data.DataObjectContracts.IEvent;
import com.U2.backend.Data.DataObjectFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Category implements ICategory {
    private String name;
    private List<IEvent>events;
    private List<ICategoryTag> tags;

    public Category(String name){
        this.name = name;
        events = new ArrayList<>();
        tags = new ArrayList<>();
    }

    public void addEvent(IEvent event){
        events.add(event);
        for (var tag: event.getTags()) {
            addTag(tag);
        }
    }

    public void addEvents(List<IEvent> events) {
        for (var event : events) {
            addEvent(event);
        }
    }

    public void addTag(String tag){
        var tagObj = tags.stream().filter(t -> t.getName().equals(tag)).findFirst().orElse(null);
        if (tagObj == null){
            if(!tag.contains("#") && !tag.contains("-")){
                tags.add(DataObjectFactory.createCategoryTag(tag.toLowerCase()));
            }
        } else {
            tagObj.incrementCount();
        }
    }

    public List<String> getTags(){
        Collections.sort(tags, new CustomComparator());
        var upperBound = 9;
        if(tags.size()-1 < upperBound){
            return tags.stream().map(ICategoryTag::getName).toList();
        }
        return tags.subList(0, 9).stream().map(ICategoryTag::getName).toList();
    }

    public String getName() {
        return name;
    }

    public List<IEvent> getEvents(){
        return events;
    }

    private class CustomComparator implements Comparator<ICategoryTag>{
        @Override
        public int compare(ICategoryTag o1, ICategoryTag o2) {
            return Integer.compare(o1.getCount(), o2.getCount());

        }
    }
}