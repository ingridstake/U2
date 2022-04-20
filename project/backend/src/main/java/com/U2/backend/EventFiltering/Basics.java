package com.U2.backend.EventFiltering;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Basics {

    public static JSONObject getTenEventNames(JSONArray events){
        try {
            var eventObject = new JSONObject();
            var eventArray = new org.json.JSONArray();
            for (int i = 0; i < 10; i++) {
                eventArray.put(new JSONObject().put("name", ((JSONObject)events.get(i)).get("name")));
            }
            eventObject.put("events", eventArray);
            return eventObject;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public static JSONObject getTenEvents(JSONArray events, JSONArray venues){
        try {
            var eventObject = new JSONObject();
            var eventArray = new org.json.JSONArray();
            var venueService = ServiceFactory.CreateVenueSearchService(venues);

            for (int i = 0; i < 100; i++) {
                var temp = new JSONObject();
                temp.put("name", ((JSONObject)events.get(i)).get("name"));
                temp.put("id", i+1);

                var venueId= ((JSONObject)events.get(i)).get("venueId").toString();
                temp.put("city", venueService.getVenueCity(venueId));
                eventArray.put(temp);
            }

            eventObject.put("events", eventArray);
            return eventObject;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  null;
    }
}