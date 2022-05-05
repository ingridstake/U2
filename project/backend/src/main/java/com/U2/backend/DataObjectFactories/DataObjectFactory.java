package com.U2.backend.DataObjectFactories;

import com.U2.backend.DataObjectContracts.HierarchyType;
import com.U2.backend.DataObjectContracts.IEvent;
import com.U2.backend.DataObjectContracts.IVenue;
import com.U2.backend.DataObjects.Event;
import com.U2.backend.DataObjects.Venue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataObjectFactory {

    private static List<IEvent> _events;
    private static List<IVenue> _venues;

    public static List<IEvent> getEvents(String APIData){
        var instant = new DataObjectFactory();

        try {
            var eventJSON = (JSONArray)(new JSONObject(APIData)).get("events");
            var venuesJSON = (JSONArray)(new JSONObject(APIData)).get("venues");

            instant.buildVenues(venuesJSON);
            instant.buildEvents(eventJSON);

            return instant._events;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<IEvent> getEvents(){
        return _events;
    }

    public static List<IVenue> getVenues(){
        return _venues;
    }


    private DataObjectFactory(){}

    private void buildVenues(JSONArray venues) throws JSONException {

        var venueList = new ArrayList<IVenue>();

        for (int i = 0; i < venues.length(); i++) {
            var venue = (JSONObject) venues.get(i);
            venueList.add(new Venue(venue.get("id").toString(), venue.get("city").toString(),
                    venue.get("address").toString(), venue.get("name").toString()));
        }

        _venues = venueList;
    }

    private void buildEvents(JSONArray events) throws JSONException {
        var eventList = new ArrayList<IEvent>();
        for (int i = 0; i < events.length(); i++) {
            var JSONevent = (JSONObject) events.get(i);
            var venueId = JSONevent.getString("venueId");

            var event = readEvent(JSONevent);

            if (event.isPublished()){
                eventList.add(event);
            }
        }

        _events = eventList;
    }

    private IEvent readEvent(JSONObject event) throws JSONException {
        var venueId = event.get("venueId").toString();
        var venue = _venues.stream().filter(v -> v.getId().equals(venueId)).findFirst().orElse(null);

        return new Event(event.getString("id"), event.getBoolean("published"), event.getString("start"),
                event.getString("end"), event.getString("doorsOpen"), event.getString("infoUri"),
                event.getString("description"), event.getString("productionParentId"),
                HierarchyType.readHierarchyType(event.getString("hierarchyType")), venue,
                event.getString("name"), event.getString("imageUrl"));
    }

    public static String convertToJSONArrayString() throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (IEvent event : _events) {
            jsonArray.put(event.getId());
            jsonArray.put(event.getName());
            jsonArray.put(event.getVenue().getCity());
        }
        return jsonArray.toString();
    }

    /**
     * Converts a list of Events to a json array that is then returned as a string.
     * @param events The list of events to be converted
     * @return a string that contains the json array
     * @throws JSONException
     */
    public static String convertToJSONString(List<IEvent> events) throws JSONException {

        JSONArray jsonArray = new JSONArray();
        for (var event : events) {
            var temp = new JSONObject();
            temp.put("name", event.getName());
            temp.put("id", event.getId());
            temp.put("city", event.getVenue().getCity());
            temp.put("imageUrl", event.getImageUrl());
            temp.put("date", event.getStart());
            jsonArray.put(temp);
        }
        return jsonArray.toString();
    }

    /**
     * Converts a HashMap of categories with Lists of IEvents to a json array that is returned as a string.
     * @param categories is the HashMap to be converted.
     * @return a String that contains the json array
     * @throws JSONException
     */
    public static String populatedCategoriesToJSONString(HashMap<String, List<IEvent>> categories) throws JSONException {

        JSONArray jsonArray = new JSONArray();

        for (var category : categories.keySet()) {
            var cat = new JSONObject();
            cat.put("category", category);
            JSONArray eventArray = new JSONArray();

            for (var event : categories.get(category)) {
                var temp = new JSONObject();
                temp.put("name", event.getName());
                temp.put("id", event.getId());
                temp.put("city", event.getVenue().getCity());
                temp.put("imageUrl", event.getImageUrl());
                temp.put("date", event.getStart());
                temp.put("description", event.getDescription());
                eventArray.put(temp);
            }
            cat.put("events", eventArray);
            jsonArray.put(cat);
        }
        return jsonArray.toString();
    }
}
