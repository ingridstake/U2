package com.U2.backend.EventFiltering;

import com.U2.backend.APIDataService;
import com.U2.backend.DataObjectContracts.IEvent;
import com.U2.backend.DataObjectContracts.IVenue;
import com.U2.backend.DataObjectFactories.DataObjectFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EasyCategorization {

    public EasyCategorization() {

    }

    public static String getCategorization(List<IEvent> events){
        var categoryMap = new HashMap<String,List<IEvent>>();

        categoryMap.put("vowels", getVowelEvents(events));
        categoryMap.put("consonants", getConsonantsEvents(events));
        String s = null;
        try {
            s = DataObjectFactory.populatedCategoriesToJSONString(categoryMap);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return s;
    }

    private static List<IEvent> getVowelEvents(List<IEvent> events) {
        System.out.println("test");
        String vowels = "aeiouåäö";
        var t = events.stream().filter(e -> vowels.contains(String.valueOf(e.getName().charAt(0)).toLowerCase())).toList();

        return t;
    }

    private static List<IEvent> getConsonantsEvents(List<IEvent> events) {
        System.out.println("test");
        String consonants = "qwrtpsdfghjklzxcvbnm";
        var t = events.stream().filter(e -> consonants.contains(String.valueOf(e.getName().charAt(0)).toLowerCase())).toList();

        return t;
    }
}
