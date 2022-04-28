package com.U2.backend.DataObjects;

import com.U2.backend.DataObjectContracts.HierarchyType;
import com.U2.backend.DataObjectContracts.IEvent;
import com.U2.backend.DataObjectContracts.IVenue;

import java.util.Date;

public class Event implements IEvent {
    private String _id;
    private boolean _published;
    private String _start;
    private String _end;
    private String _doorsOpen;
    private String _infoUri;
    private String _description;
    private String _productionParentId;
    private IVenue _venue;
    private String _name;
    private String _imageUrl;
    private HierarchyType _hierarchyType;

    public Event (String id, boolean published, String start, String end, String doorsOpen, String infoUri, String description,
                  String productionParentId, HierarchyType hierarchyType,  IVenue venue, String name, String imageUrl){
        _id = id;
        _venue = venue;
        _name = name;
        _imageUrl = imageUrl;
        _published = published;
        _start = start;
        _end = end;
        _doorsOpen = doorsOpen;
        _infoUri = infoUri;
        _description = description;
        _productionParentId = productionParentId;
        _hierarchyType = hierarchyType;
    }

    @Override
    public String getId() {
        return _id;
    }

    @Override
    public IVenue getVenue() {
        return _venue;
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public String getImageUrl() {
        return _imageUrl;
    }

    @Override
    public boolean isPublished() {
        return _published;
    }

    @Override
    public String getStart() {
        return _start;
    }

    @Override
    public String getEnd() {
        return _end;
    }

    @Override
    public String getDoorsOpen() {
        return _doorsOpen;
    }

    @Override
    public String getInfoUri() {
        return _infoUri;
    }

    @Override
    public String getDescription() {
        return _description;
    }

    @Override
    public String getProductionParentId() {
        return _productionParentId;
    }

    @Override
    public HierarchyType getHierarchyType() {
        return _hierarchyType;
    }
}
