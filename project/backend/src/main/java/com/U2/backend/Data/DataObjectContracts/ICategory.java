package com.U2.backend.Data.DataObjectContracts;

import java.util.List;

public interface ICategory {

    void addEvents(List<IEvent> events);
    void addTag(String tag);
    String getName();
    List<IEvent> getEvents();
    List<String> getTags();
}
