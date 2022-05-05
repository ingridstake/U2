package com.U2.backend.EventFiltering;

import com.U2.backend.DataObjectContracts.IEvent;
import com.U2.backend.DataObjectFactories.DataObjectFactory;
import org.json.JSONException;

import java.util.HashMap;
import java.util.List;

public class EasyCategorization {

    /**
     * Categorizes a list of IEvents into vowels and consonants.
     * @param events is a list of IEvents.
     * @return a String of a JSONArray containing categories with events.
     */
    public static String getCategorization(List<IEvent> events){
        var categoryMap = new HashMap<String,List<IEvent>>();
        categoryMap.put("vowels", getVowelEvents(events));
        categoryMap.put("consonants", getConsonantsEvents(events));
        categoryMap.put("theatres", getTheatreEvents(events));
        categoryMap.put("sports", getSportEvents(events));

        String s = null;
        try {
            s = DataObjectFactory.populatedCategoriesToJSONString(categoryMap);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * Filters a List of IEvents by the first letter of their name (only vowels).
     * @param events is the List of IEvents to be filtered
     * @return a Lists of IEvents which name starts with a vowels
     */
    private static List<IEvent> getVowelEvents(List<IEvent> events) {
        String vowels = "aeiouåäö";
        var t = events.stream().filter(e -> vowels.contains(String.valueOf(e.getName().charAt(0)).toLowerCase())).toList();

        return t;
    }

    /**
     * Filters a List of IEvents by the first letter of their name (only consonants).
     * @param events is the List of IEvents to be filtered
     * @return a Lists of IEvents which name starts with a consonants
     */
    private static List<IEvent> getConsonantsEvents(List<IEvent> events) {
        String consonants = "qwrtpsdfghjklzxcvbnm";
        var t = events.stream().filter(e -> consonants.contains(String.valueOf(e.getName().charAt(0)).toLowerCase())).toList();

        return t;
    }

    private static List<IEvent> getTheatreEvents(List<IEvent> events) {
        System.out.println("test1");
        String searchString = "teat";
        var t = events.stream().filter(e -> e.getDescription().toLowerCase().contains(searchString)).toList() ;

        return t;
    }

    private static List<IEvent> getSportEvents(List<IEvent> events) {
        System.out.println("test2");
        String searchString = "sport";
        var t = events.stream().filter(e -> e.getDescription().toLowerCase().contains(searchString)).toList() ;

        return t;
    }

}
