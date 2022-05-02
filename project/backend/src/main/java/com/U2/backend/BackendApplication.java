package com.U2.backend;

import com.U2.backend.DataObjectContracts.IEvent;
import com.U2.backend.DataObjectContracts.IVenue;
import com.U2.backend.DataObjectFactories.DataObjectFactory;
import com.U2.backend.EventFiltering.Basics;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import org.json.JSONObject;

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
/*
	@CrossOrigin
	@GetMapping(path = "/ten_event_names")
	public JSONObject tenEventNames() {
		JSONObject tenEventNames = null;
		try {
			var ev = events.subList(0,10);
			var events = (JSONArray)(new JSONObject(APIDump)).get("events");
			tenEventNames = Basics.getTenEventNames(events);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return tenEventNames;
	}

 */
/*
	@CrossOrigin
	@GetMapping(path = "/ten_event_names_string")
	public String tenEventNamesString() {
		JSONObject tenEventNames = null;
		try {
			var events = (JSONArray)(new JSONObject(APIDump)).get("events");
			tenEventNames = Basics.getTenEventNames(events);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return tenEventNames.toString();
	}
*/
	@CrossOrigin
	@GetMapping(path = "/ten_events_string")
	public String tenEventsString() {
		try {
			var temp = events.subList(0,100);
			 return DataObjectFactory.convertToJSONString(temp);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
/*
	@CrossOrigin
	@GetMapping(path = "/ten_events")
	public JSONArray tenEvents() {
		JSONArray tenEvents = null;
		try {
			var events = (JSONArray)(new JSONObject(APIDump)).get("events");
			var venues = (JSONArray)(new JSONObject(APIDump)).get("venues");
			tenEvents = Basics.getTenEvents(events,venues);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return tenEvents;
	}
	
 */
}