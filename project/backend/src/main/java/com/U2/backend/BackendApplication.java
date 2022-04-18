package com.U2.backend;

import org.json.JSONException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import org.json.JSONObject;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;

@SpringBootApplication
@RestController
public class BackendApplication {

	private static String events;

	public static void main(String[] args) {
		events = getData();

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
		return getTenEventNames();
	}
	@CrossOrigin
	@GetMapping(path = "/ten_event_names_string")
	public String tenEventNamesString() {
		return getTenEventNames().toString();
	}

	private static String getData(){
		try {

			var client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI("https://api.tickster.com/sv/api/0.4/events/dump/upcoming?key=38a79e4a7f000b4f"))
					.setHeader("Accept", "application/json")
					.setHeader("Content-type", "application/json")
					.setHeader("Accept-Charset", "utf-8")
					.GET()
					.build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			var urlObj = new JSONObject( org.apache.commons.text.StringEscapeUtils.unescapeJava(response.body()));
			var apiUrl = urlObj.get("uri").toString();

			HttpRequest apiRequest = HttpRequest.newBuilder()
					.uri(new URI(apiUrl))
					.GET()
					.build();

			HttpResponse<InputStream> apiResponse = client.send(apiRequest, HttpResponse.BodyHandlers.ofInputStream());
			GZIPInputStream gzipInputStream = new GZIPInputStream(apiResponse.body());
			return new String(gzipInputStream.readAllBytes(), StandardCharsets.UTF_8);

		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static JSONObject getTenEventNames(){
		try {
			var jsonEvents = (org.json.JSONArray) (new JSONObject(events)).get("events");

			var eventObject = new JSONObject();
			var eventArray = new org.json.JSONArray();
			for (int i = 0; i < 10; i++) {
				eventArray.put(new JSONObject().put("name", ((JSONObject)jsonEvents.get(i)).get("name")));
			}
			eventObject.put("events", eventArray);
			return eventObject;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return  null;
	}
}
