package com.U2.backend.Data;

import com.U2.backend.Data.DataObjectContracts.*;
import com.U2.backend.Data.DataObjects.CategoryTag;
import com.U2.backend.Data.DataObjects.Event;
import com.U2.backend.Data.DataObjects.Venue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataObjectFactory {

    private static List<IEvent> events;
    private static List<IVenue> venues;

    //region Public Properties
    public static List<IEvent> getEvents(){
        return events;
    }

    public static List<IVenue> getVenues(){
        return venues;
    }
    //endregion

    //region Public Methods for creating dataObjects

    public static ICategoryTag createCategoryTag(String tagName){
        return new CategoryTag(tagName);
    }

    //endregion

    //region Public methods for reading JSON
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

            return instant.events;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    //endregion

    //region Public methods for writing JSON
    public static String convertToJSONString(List<IEvent> events){
        try {
            return getEventJSON(events).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String populatedCategoriesToJSONString(HashMap<String, List<IEvent>> categories){
        try {
            return populatedCategoriesToJSON(categories).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String categoriesToJSONString(List<ICategory> categories){
        try {
            return categoriesToJSON(categories).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    //endregion

    //region Private methods for reading JSON
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

        DataObjectFactory.venues = venueList;
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

        DataObjectFactory.events = eventList;
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
        var venue = venues.stream().filter(v -> v.getId().equals(venueId)).findFirst().orElse(null);

        var temp = event.getJSONArray("tags");
        var tags = new ArrayList<String>();
        for (int i = 0; i < temp.length(); i++) {
            tags.add(temp.getString(i));
        }

        return new Event(event.getString("id"), event.getBoolean("published"), event.getString("start"),
                event.getString("end"), event.getString("doorsOpen"), event.getString("infoUri"),
                event.getString("description"), event.getString("productionParentId"),
                HierarchyType.readHierarchyType(event.getString("hierarchyType")), venue,
                event.getString("name"), event.getString("imageUrl"), tags);
    }
    //endregion

    //region Private methods for writing JSON
    /**
     * Converts a list of Events to a json array that is then returned as a string.
     * @param events The list of events to be converted
     * @return a string that contains the json array representation of the input list
     * @throws JSONException
     */
    private static JSONArray getEventJSON(List<IEvent> events) throws JSONException {

        JSONArray jsonArray = new JSONArray();
        for (var event : events) {
            var temp = new JSONObject();
            temp.put("name", event.getName());
            temp.put("id", event.getId());
            temp.put("city", event.getVenue().getCity());
            temp.put("imageUrl", event.getImageUrl());
            temp.put("date", event.getStart());
            temp.put("description", event.getDescription());
            jsonArray.put(temp);
        }
        return jsonArray;
    }

    /**
     * Converts a HashMap of categories with Lists of IEvents to a json array that is returned as a string.
     * @param categories is the HashMap to be converted.
     * @return a String that contains the json array
     * @throws JSONException
     */
    private static JSONArray populatedCategoriesToJSON(HashMap<String, List<IEvent>> categories) throws JSONException {

        JSONArray jsonArray = new JSONArray();

        for (var category : categories.keySet()) {
            var cat = new JSONObject();
            cat.put("category", category);

            cat.put("events", getEventJSON(categories.get(category)));
            jsonArray.put(cat);
        }
        return jsonArray;
    }

    private static JSONArray categoriesToJSON(List<ICategory> categories) throws JSONException {
        var categoryArray = new JSONArray();

        for (var category : categories) {
            var categoryObject = new JSONObject();
            categoryObject.put("name", category.getName());
            categoryObject.put("events", getEventJSON(category.getEvents()));
            categoryObject.put("tags", listOfStringsToJSON(category.getTags()));
            categoryArray.put(categoryObject);
        }
        return categoryArray;
    }

    private static JSONArray listOfStringsToJSON(List<String> list){
        var arr = new JSONArray();
        for (var string : list) {
            arr.put(string);
        }

        return arr;
    }
    //endregion
}
