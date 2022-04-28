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

    private List<IEvent> _events;
    private List<IVenue> _venues;

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


    private DataObjectFactory(){}

    private void buildVenues(JSONArray venues) throws JSONException {

        var venueList = new ArrayList<IVenue>();

        for (int i = 0; i < venues.length(); i++) {
            var venue = (JSONObject) venues.get(i);
            venueList.add(new Venue(venue.get("id").toString(), venue.get("city").toString(),
                    venue.get("address").toString(), venue.get("name").toString()));
        }
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
}