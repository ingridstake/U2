package com.U2.backend.EventFiltering.CommandHandlers;

import com.U2.backend.EventFiltering.Contracts.IVenueSearch;
import com.jayway.jsonpath.JsonPath;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class contains functionality concerning searching and retrieving Venues
 */
public class VenueSearch implements IVenueSearch {

    private JSONArray _venues;

    public VenueSearch(JSONArray venues){
        _venues = venues;
    }

    public JSONObject getVenue(String id){
        var data = JsonPath.parse(_venues.toString()).read("$[?(@.id == '" + id +"')]");
        try {
            var arr = new JSONArray(data.toString());
            return (JSONObject)arr.get(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getVenueCity(String id){
        var data = JsonPath.parse(_venues.toString()).read("$[?(@.id == '" + id +"')]");
        try {
            var arr = new JSONArray(data.toString());
            if (arr.length() < 1){
                return "no city";
            }
            return ((JSONObject)arr.get(0)).get("city").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}