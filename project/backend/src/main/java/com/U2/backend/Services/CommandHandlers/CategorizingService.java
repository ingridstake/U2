package com.U2.backend.Services.CommandHandlers;

import com.U2.backend.Data.DataObjectContracts.IEvent;
import com.U2.backend.Data.DataObjectFactory;
import com.U2.backend.Services.Contracts.ICategorizingService;
import org.json.JSONException;

import java.util.HashMap;
import java.util.List;

public class CategorizingService implements ICategorizingService {

    private List<IEvent> events;

    public CategorizingService(List<IEvent> events){
        this.events = events;
    }

    /**
     * Categorizes a list of IEvents into vowels and consonants.
     * @return a String of a JSONArray containing categories with events.
     */
    public String getCategories(){
        var categoryMap = new HashMap<String,List<IEvent>>();
        categoryMap.put("vokaler", getVowelEvents());
        categoryMap.put("konsonanter", getConsonantsEvents());
        categoryMap.put("teater", getTheatreEvents());
        categoryMap.put("sport", getSportEvents());
        categoryMap.put("konsert", getConsertEvents());
        categoryMap.put("mat", getFoodEvents());
        categoryMap.put("utställningar", getExhibitionEvents());
        categoryMap.put("presentkort", getGiftCardEvents());
        categoryMap.put("vin", getWineEvents());
        categoryMap.put("slott", getCastleEvents());


        String s = null;

        s = DataObjectFactory.populatedCategoriesToJSONString(categoryMap);

        return s;
    }

    /**
     * Filters a List of IEvents by the first letter of their name (only vowels).
     * @return a Lists of IEvents which name starts with a vowels
     */
    private List<IEvent> getVowelEvents() {
        String vowels = "aeiouåäö";
        var vowel = events.stream().filter(e -> vowels.contains(String.valueOf(e.getName().charAt(0)).toLowerCase())).toList();

        return vowel;
    }

    /**
     * Filters a List of IEvents by the first letter of their name (only consonants).
     * @return a Lists of IEvents which name starts with a consonants
     */
    private List<IEvent> getConsonantsEvents() {
        String consonants = "qwrtpsdfghjklzxcvbnm";
        var t = events.stream().filter(e -> consonants.contains(String.valueOf(e.getName().charAt(0)).toLowerCase())).toList();

        return t;
    }

    private List<IEvent> getTheatreEvents() {
        String searchString = "teat";
        var t = events.stream().filter(e -> e.getDescription().toLowerCase().contains(searchString)).toList() ;

        return t;
    }

    private List<IEvent> getSportEvents() {
        String searchString = "sport";
        var t = events.stream().filter(e -> e.getDescription().toLowerCase().contains(searchString)).toList() ;

        return t;
    }
    private List<IEvent> getConsertEvents() {
        String searchString = "konsert";
        var t = events.stream().filter(e -> e.getDescription().toLowerCase().contains(searchString)).toList() ;

        return t;
    }

    private List<IEvent> getFoodEvents() {
        String searchString = "mat";
        var t = events.stream().filter(e -> e.getDescription().toLowerCase().contains(searchString)).toList() ;

        return t;
    }

    private List<IEvent> getExhibitionEvents() {
        String searchString = "utst";
        var t = events.stream().filter(e -> e.getDescription().toLowerCase().contains(searchString)).toList() ;

        return t;
    }

    private List<IEvent> getGiftCardEvents() {
        String searchString = "presentkort";
        var t = events.stream().filter(e -> e.getDescription().toLowerCase().contains(searchString)).toList() ;

        return t;
    }

    private List<IEvent> getWineEvents() {
        System.out.println("Viiiiin");
        String searchString = " vin";
        var t = events.stream().filter(e -> e.getDescription().toLowerCase().contains(searchString)).toList() ;

        return t;
    }

    private List<IEvent> getCastleEvents() {
        String searchString = "slott";
        var t = events.stream().filter(e -> e.getDescription().toLowerCase().contains(searchString)).toList() ;

        return t;
    }




}
