package com.U2.backend;

import com.U2.backend.Data.DataObjectContracts.IEvent;
import com.U2.backend.Data.DataObjectContracts.IVenue;
import com.U2.backend.Data.DataObjectFactory;
import com.U2.backend.Services.CommandHandlers.APIDataService;
import com.U2.backend.Services.ServiceFactory;
import org.json.JSONException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
public class BackendApplication {

	private static List<IEvent> events;
	private static List<IVenue> venues;

	public static void main(String[] args) throws JSONException {

		 var dataService = new APIDataService();
		 events = dataService.getEvents();
		 venues = dataService.getVenues();

		SpringApplication.run(BackendApplication.class, args);

	}

	@CrossOrigin
	@GetMapping(path = "/hello")
	public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@CrossOrigin
	@GetMapping(path = "/search")
	public String search(@RequestParam(value = "param", defaultValue = "") String param) {
		var service = ServiceFactory.createSearchService(events);
		return service.performSearch("p");
	}

	@CrossOrigin
	@GetMapping(path = "/ten_events_string")
	public String tenEventsString() {
		try {
			// takes the 100 first events in the events
			var temp = events.subList(0,100);
			//sends the list to the DataObjectFactory, which converts it to a json array string
			 return DataObjectFactory.convertToJSONString(temp);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	@CrossOrigin
	@GetMapping(path = "/vowel_events")
	public String vowelEventsString() {
		var temp = events.subList(0,100);
		var service = ServiceFactory.createCategorizingService(temp);
		return service.getCategories();
	}
}
