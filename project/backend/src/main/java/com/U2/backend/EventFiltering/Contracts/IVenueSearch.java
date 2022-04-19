package com.U2.backend.EventFiltering.Contracts;

import org.json.JSONObject;

public interface IVenueSearch {
    JSONObject getVenue(String id);
    String getVenueCity(String id);
}
