package com.U2.backend.Data.DataObjectContracts;

import java.util.List;

public interface ICategory {

    void addEvent(IEvent event);
    void addTag(String tag);
    String getName();
    List<IEvent> getEvents();
    List<String> getTags();
    List<String> getTagsToDisplay();
    List<IEvent> getEventsToDisplay();

    void updateElementsToDisplay();
}
