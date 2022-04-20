package com.U2.backend.EventFiltering;

import com.U2.backend.EventFiltering.CommandHandlers.VenueSearch;
import com.U2.backend.EventFiltering.Contracts.IVenueSearch;
import org.json.JSONArray;

public class ServiceFactory {

    public static IVenueSearch CreateVenueSearchService(JSONArray venues){
       return new VenueSearch(venues);
    }
}
