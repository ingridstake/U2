package com.U2.backend.DataObjects;

import com.U2.backend.DataObjectContracts.IVenue;

public class Venue implements IVenue {
    private String _id;
    private String _city;
    private String _adress;
    private String _name;

    public Venue(String id, String city, String adress, String name){
        _id = id;
        _city = city;
        _adress = adress;
        _name = name;
    }

    @Override
    public String getId() {
        return _id;
    }

    @Override
    public String getCity() {
        return _city;
    }

    @Override
    public String getAddress() {
        return _adress;
    }

    @Override
    public String getName() {
        return _name;
    }
}
