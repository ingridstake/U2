package com.U2.backend.Data.DataObjectContracts;

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
    String getDescription();
    String getProductionParentId();
    HierarchyType getHierarchyType();
}
