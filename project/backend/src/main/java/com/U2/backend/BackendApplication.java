package com.U2.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@SpringBootApplication
@RestController
public class BackendApplication {

	public static void main(String[] args) {
		printJSON();
		SpringApplication.run(BackendApplication.class, args);
	}

	@CrossOrigin
	@GetMapping(path = "/hello")
	public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	public static void printJSON() {
		var parser = new JSONParser();
		try (var fileReader = new BufferedReader(new FileReader("src\\main\\resources\\ten_events.json"))){
			var obj = parser.parse(fileReader);
			var eventList = (JSONArray)((JSONObject) obj).get("events");

			eventList.forEach(ev -> System.out.println(((JSONObject) ev ).get("name")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}


}
