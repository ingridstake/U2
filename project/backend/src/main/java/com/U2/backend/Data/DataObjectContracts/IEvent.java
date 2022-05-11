package com.U2.backend.Data.DataObjectContracts;

import java.util.List;

public interface IEvent {
    String getId();
    IVenue getVenue();
    String getName();
    String getImageUrl();
    boolean isPublished();
    String getStart();
    String getEnd();
    String getDoorsOpen();
    String getInfoUri();
    String getShopUri();
    String getDescription();
    String getProductionParentId();
    HierarchyType getHierarchyType();
    List<String> getTags();
}
