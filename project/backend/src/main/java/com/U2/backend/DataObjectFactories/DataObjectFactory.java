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
import java.util.List;

public class DataObjectFactory {

    private static List<IEvent> _events;
    private static List<IVenue> _venues;

    /**
     *Takes in API data and coordinates procedures to create a representation of the data using IEvent and IVenue
     * @param APIData is the JSONObject string containing all data from the API
     * @return
     */
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

    /**
     * Iterates all venues from the parameter JSONArray, interprets them as IVenue and puts them in a list that is assigned to the instance variable _venues of the DataObjectFactory
     * @param venues is the JSONArray of venues to be interpreted.
     * @throws JSONException
     */
    private void buildVenues(JSONArray venues) throws JSONException {

        var venueList = new ArrayList<IVenue>();

        for (int i = 0; i < venues.length(); i++) {
            var venue = (JSONObject) venues.get(i);
            venueList.add(new Venue(venue.get("id").toString(), venue.get("city").toString(),
                    venue.get("address").toString(), venue.get("name").toString()));
        }

        _venues = venueList;
    }

    /**
     * Iterates all events from the parameter JSONArray, interprets them as IEvents and puts them in a list that is assigned to the instance variable _events of the DataObjectFactory
     * @param events is the JSONArray of events to be interpreted.
     * @throws JSONException
     */
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

    /**
     * Interprets a json object as an Event and returns it.
     * @param event is a JSON object representation of an event. The object should contain following:
     *              "venueId", "id", "published", "start", "description", "productionParentId","hierarchyType",
     *              "name", "infoUri" and "imageUrl"
     * @return returns an instance of IEvent that stores all data form the JSON object
     * @throws JSONException
     */
    private IEvent readEvent(JSONObject event) throws JSONException {
        var venueId = event.get("venueId").toString();
        var venue = _venues.stream().filter(v -> v.getId().equals(venueId)).findFirst().orElse(null);

        return new Event(event.getString("id"), event.getBoolean("published"), event.getString("start"),
                event.getString("end"), event.getString("doorsOpen"), event.getString("infoUri"),
                event.getString("description"), event.getString("productionParentId"),
                HierarchyType.readHierarchyType(event.getString("hierarchyType")), venue,
                event.getString("name"), event.getString("imageUrl"));
    }

    /**
     * Converts all event attributes to a json array that is returned as a string
     * @return s string representation of the json array of all event attributes
     * @throws JSONException
     */
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
}
