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
	private static String APIDump;
	private static JSONArray jsonArray;

	public static void main(String[] args) throws JSONException {
		 APIDump = APIDataService.getData();
		 events = DataObjectFactory.getEvents(APIDump);
		 jsonArray = DataObjectFactory.convertToJSONArray();

		SpringApplication.run(BackendApplication.class, args);

		System.out.println(jsonArray);
	}

	@CrossOrigin
	@GetMapping(path = "/hello")
	public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@CrossOrigin
	@GetMapping(path = "/ten_event_names")
	public JSONObject tenEventNames() {
		JSONObject tenEventNames = null;
		try {
			var events = (JSONArray)(new JSONObject(APIDump)).get("events");
			tenEventNames = Basics.getTenEventNames(events);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return tenEventNames;
	}

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

	@CrossOrigin
	@GetMapping(path = "/ten_events_string")
	public String tenEventsString() {
		JSONArray tenEvents = null;
		try {
			var events = (JSONArray)(new JSONObject(APIDump)).get("events");
			var venues = (JSONArray)(new JSONObject(APIDump)).get("venues");
			tenEvents = Basics.getTenEvents(events,venues);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return tenEvents.toString();
	}

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
}