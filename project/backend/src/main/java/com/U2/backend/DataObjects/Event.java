package com.U2.backend.DataObjects;

import com.U2.backend.DataObjectContracts.IEvent;
import com.U2.backend.DataObjectContracts.IVenue;

public class Event implements IEvent {
    private String _id;
    private IVenue _venue;
    private String _name;
    private String _imageUrl;

    public Event (String id, IVenue venue, String name, String imageUrl){
        _id = id;
        _venue = venue;
        _name = name;
        _imageUrl = imageUrl;
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
}
