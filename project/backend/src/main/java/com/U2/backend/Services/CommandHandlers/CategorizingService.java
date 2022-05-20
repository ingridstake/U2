package com.U2.backend.Services.CommandHandlers;

import com.U2.backend.Data.DataObjectContracts.ICategory;
import com.U2.backend.Data.DataObjectContracts.IEvent;
import com.U2.backend.Data.DataObjectFactory;
import com.U2.backend.Services.Contracts.ICategorizingService;

import java.util.ArrayList;
import java.util.List;

public class CategorizingService implements ICategorizingService {

    private List<IEvent> events;

    public CategorizingService(List<IEvent> events){
        this.events = events;
    }

    /**
     * Categorizes a list of IEvents.
     * @return a String of a JSONArray containing categories with events.
     */
    public String getCategories(){
        
        var categories = new ArrayList<ICategory>();

        /* Create categories */
        var giftCardEvents = DataObjectFactory.createCategory("Presentkort");
        var theatreEvents = DataObjectFactory.createCategory("Teater & Scen");
        var foodWineEvents = DataObjectFactory.createCategory("Mat & Dryck");
        var sportEvents = DataObjectFactory.createCategory("Sport");
        var concertEvents = DataObjectFactory.createCategory("Konserter & Klubb");
        var artExhibitionEvents = DataObjectFactory.createCategory("Konst & Utställningar");
        var showEvents = DataObjectFactory.createCategory("Show");
        var educationalEvents = DataObjectFactory.createCategory("Utbildningar, Konferens & Föreläsningar");
        var leftOverEvents = DataObjectFactory.createCategory("Övrigt");

        /* Add events to their correct category */
        for (var ev : events) {
            var categoryCount = 0;
            var categoryCountLimit = 2;

            if(isNotSellable(ev)) {
                continue;
            }

            if(isGiftCardEvent(ev)) {
                giftCardEvents.addEvent(ev);
                continue;
            }

            if(isTheatreEvent(ev)) {
                theatreEvents.addEvent(ev);
                categoryCount++;
            }

            if(isSportEvent(ev)) {
                sportEvents.addEvent(ev);
                categoryCount++;
                if(categoryCount >= categoryCountLimit)
                    continue;
            }

            if(isConcertEvent(ev)) {
                concertEvents.addEvent(ev);
                categoryCount++;
                if(categoryCount >= categoryCountLimit)
                    continue;
            }

            if(isArtExhibitionEvent(ev)) {
                artExhibitionEvents.addEvent(ev);
                categoryCount++;
                if(categoryCount >= categoryCountLimit)
                    continue;
            }

            if(isFoodAndWineEvent(ev)) {
                foodWineEvents.addEvent(ev);
                categoryCount++;
                if(categoryCount >= categoryCountLimit)
                    continue;
            }

            if(isShowEvent(ev)) {
                showEvents.addEvent(ev);
                categoryCount++;
                if(categoryCount >= categoryCountLimit)
                    continue;
            }

            if(isEducationalEvent(ev)) {
                educationalEvents.addEvent(ev);
                categoryCount++;
                if (categoryCount >= categoryCountLimit)
                    continue;
            }

            if(categoryCount == 0) {
                leftOverEvents.addEvent(ev);
            }
        }

        categories.add(giftCardEvents);
        categories.add(theatreEvents);
        categories.add(foodWineEvents);
        categories.add(sportEvents);
        categories.add(concertEvents);
        categories.add(artExhibitionEvents);
        categories.add(showEvents);
        categories.add(educationalEvents);
        categories.add(leftOverEvents);

        return DataObjectFactory.categoriesToJSONString(categories);
    }

    //region Event evaluators
    // evaluates events from their descriptions/names/organizer's names
    private boolean isNotSellable(IEvent e) {
        return e.getName().toLowerCase().contains("slutsål") ||
                e.getName().toLowerCase().contains("mall");
    }

    private boolean isGiftCardEvent(IEvent e){
        return ((e.getDescription().toLowerCase().contains("presentkort") ||
                e.getName().toLowerCase().contains("presentkort") ||
                e.getDescription().toLowerCase().contains("gavekort") ||
                e.getName().toLowerCase().contains("gavekort")) && !isShowEvent(e));
    }

    private boolean isEducationalEvent(IEvent e) {
        return ((e.getName().toLowerCase().contains("utbildning") ||
                e.getDescription().toLowerCase().contains("föreläsning") ||
                e.getName().toLowerCase().contains("föreläsning") ||
                e.getName().toLowerCase().contains("konferens") ||
                e.getName().toLowerCase().contains("kurs") ||
                e.getDescription().toLowerCase().contains("utbildning") ||
                e.getDescription().toLowerCase().contains("conference") ||
                e.getDescription().toLowerCase().contains(" kurs ") ||
                e.getDescription().toLowerCase().contains("konferens")) &&
                !e.getName().toLowerCase().contains("handel"));
    }

    private boolean isArtExhibitionEvent(IEvent e){
        return (e.getDescription().toLowerCase().contains("utställning") ||
                e.getName().toLowerCase().contains("utställning") ||
                e.getDescription().toLowerCase().contains("museum") ||
                e.getDescription().toLowerCase().contains("visning") ||
                e.getDescription().toLowerCase().contains(" art ") ||
                e.getName().toLowerCase().contains(" art ") ||
                e.getDescription().toLowerCase().contains("konst ") ||
                e.getName().toLowerCase().contains("konst")) &&
                !e.getName().toLowerCase().contains("vandring") &&
                !e.getName().toLowerCase().contains("trädgård");
    }

    private boolean isSportEvent(IEvent e) {
        return (e.getDescription().toLowerCase().contains("sport") ||
                e.getDescription().toLowerCase().contains(" race") ||
                e.getDescription().toLowerCase().contains("trav") ||
                e.getName().toLowerCase().contains("trav") ||
                e.getVenue().getName().toLowerCase().contains("solvalla") ||
                e.getName().toLowerCase().contains("ssl") ||
                e.getName().toLowerCase().contains("ibf") ||
                e.getName().toLowerCase().contains("ibk") ||
                e.getName().toLowerCase().contains("hockey")) && !isConcertEvent(e);
    }

    private boolean isShowEvent(IEvent e) {
        return (e.getName().toLowerCase().contains("wallmans") ||
                e.getDescription().toLowerCase().contains("dinnershow") ||
                e.getName().toLowerCase().contains("show ") ||
                e.getDescription().toLowerCase().contains("show ")) &&
                !isConcertEvent(e) &&
                !isTheatreEvent(e);
    }

    private boolean isTheatreEvent(IEvent e) {
        return ((e.getDescription().toLowerCase().contains("teat") ||
                e.getName().toLowerCase().contains("teat") ||
                e.getDescription().toLowerCase().contains("musikal ") ||
                e.getName().toLowerCase().contains("musikal ") ||
                e.getDescription().toLowerCase().contains("musikalen") ||
                e.getName().toLowerCase().contains("musikalen") ||
                e.getDescription().toLowerCase().contains("standup") ||
                e.getName().toLowerCase().contains("standup") ||
                e.getDescription().toLowerCase().contains("föreställning") ||
                e.getName().toLowerCase().contains("broadway") ||
                e.getName().toLowerCase().contains(" arts")) &&
                !e.getName().toLowerCase().contains("pausbeställning") &&
                !isConcertEvent(e));
    }

    private boolean isConcertEvent(IEvent e){
        return (e.getDescription().toLowerCase().contains("konsert") ||
                e.getName().toLowerCase().contains("konsert") ||
                e.getName().toLowerCase().contains("nattklubb") ||
                e.getDescription().toLowerCase().contains("komposition ") ||
                e.getDescription().toLowerCase().contains("band ") ||
                e.getDescription().toLowerCase().contains("liveband") ||
                e.getDescription().toLowerCase().contains("album") ||
                e.getDescription().toLowerCase().contains("låtar") ||
                e.getDescription().toLowerCase().contains(" lp") ||
                e.getName().toLowerCase().contains("nattklubb") ||
                e.getName().toLowerCase().contains("nightclub") ||
                e.getName().toLowerCase().contains("jazz") ||
                e.getName().toLowerCase().contains("medley")) &&
                !e.getDescription().toLowerCase().contains("historieland");
    }

    private boolean isFoodAndWineEvent(IEvent e){
        return ((e.getDescription().toLowerCase().contains(" mat ") ||
                e.getDescription().toLowerCase().contains("meny") ||
                e.getDescription().toLowerCase().contains("vin ") ||
                e.getDescription().toLowerCase().contains("öl") ||
                e.getDescription().toLowerCase().contains("dryck ") ||
                e.getDescription().toLowerCase().contains("bistro ") ||
                e.getDescription().toLowerCase().contains("whiskey") ||
                e.getName().toLowerCase().contains("pausbeställning")) &&
                !e.getName().toLowerCase().contains("standup") &&
                !e.getDescription().toLowerCase().contains("standup") &&
                !e.getDescription().toLowerCase().contains("musikalen") &&
                !e.getName().toLowerCase().contains("loppis") &&
                !e.getDescription().toLowerCase().contains("loppis") &&
                !e.getDescription().toLowerCase().contains("folkets park") &&
                !e.getDescription().toLowerCase().contains("medley") &&
                !isArtExhibitionEvent(e) &&
                !isTheatreEvent(e) &&
                !isConcertEvent(e) &&
                !isSportEvent(e) && 
                !isShowEvent(e));
    }
    //endregion
}
