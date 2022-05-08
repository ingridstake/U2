package com.U2.backend.Services.CommandHandlers;

import com.U2.backend.Services.ServiceFactory;
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
}