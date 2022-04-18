package com.U2.backend;

import com.U2.backend.EventFiltering.Basics;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import org.json.JSONObject;

@SpringBootApplication
@RestController
public class BackendApplication {

	private static String APIDump;

	public static void main(String[] args) {
		APIDump = APIDataService.getData();

		SpringApplication.run(BackendApplication.class, args);
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
}
