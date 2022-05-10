package com.U2.backend.Data.DataObjects;

import com.U2.backend.Data.DataObjectContracts.HierarchyType;
import com.U2.backend.Data.DataObjectContracts.IEvent;
import com.U2.backend.Data.DataObjectContracts.IVenue;

import java.util.List;

public class Event implements IEvent {
    private String id;
    private boolean published;
    private String start;
    private String end;
    private String doorsOpen;
    private String infoUri;
    private String description;
    private String productionParentId;
    private IVenue venue;
    private String name;
    private String imageUrl;
    private HierarchyType hierarchyType;
    private List<String> tags;
    private String shopUri;

    public Event (String id, boolean published, String start, String end, String doorsOpen, String infoUri, String description,
                  String productionParentId, HierarchyType hierarchyType,  IVenue venue, String name, String imageUrl, List<String> tags, String shopUri){
        this.id = id;
        this.venue = venue;
        this.name = name;
        this.published = published;
        this.start = start;
        this.end = end;
        this.doorsOpen = doorsOpen;
        this.infoUri = infoUri;
        this.description = description;
        this.productionParentId = productionParentId;
        this.hierarchyType = hierarchyType;
        this.tags = tags;
        this.shopUri = shopUri;

        if (!imageUrl.equals("null")){
            this.imageUrl = imageUrl;
        }
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public IVenue getVenue() {
        return venue;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public boolean isPublished() {
        return published;
    }

    @Override
    public String getStart() {
        return start;
    }

    @Override
    public String getEnd() {
        return end;
    }

    @Override
    public String getDoorsOpen() {
        return doorsOpen;
    }

    @Override
    public String getInfoUri() {
        return infoUri;
    }

    @Override
    public String getShopUri() {
        return shopUri;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getProductionParentId() {
        return productionParentId;
    }

    @Override
    public HierarchyType getHierarchyType() {
        return hierarchyType;
    }
}
