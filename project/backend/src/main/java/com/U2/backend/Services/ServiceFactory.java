package com.U2.backend.Services;

import com.U2.backend.Data.DataObjectContracts.IEvent;
import com.U2.backend.Services.CommandHandlers.APIDataService;
import com.U2.backend.Services.CommandHandlers.CategorizingService;
import com.U2.backend.Services.CommandHandlers.SearchService;
import com.U2.backend.Services.Contracts.IAPIDataService;
import com.U2.backend.Services.Contracts.ICategorizingService;
import com.U2.backend.Services.Contracts.ISearchService;

import java.util.List;

public class ServiceFactory {

    public static ICategorizingService createCategorizingService(List<IEvent> events){
        return new CategorizingService(events);
    }

    public static ISearchService createSearchService(List<IEvent> events){
        return new SearchService(events);
    }

    public static IAPIDataService createAPIDataService(){
        return new APIDataService();
    }
}
