package com.U2.backend;

import com.U2.backend.DataObjectContracts.IEvent;
import com.U2.backend.DataObjectContracts.IVenue;
import com.U2.backend.DataObjectFactories.DataObjectFactory;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class APIDataService {

    public APIDataService() {
        try {
            // Retrieve the url to the API dump
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

            // Retrieve data from the api url
            HttpRequest apiRequest = HttpRequest.newBuilder()
                    .uri(new URI(apiUrl))
                    .GET()
                    .build();

            HttpResponse<InputStream> apiResponse = client.send(apiRequest, HttpResponse.BodyHandlers.ofInputStream());
            GZIPInputStream gzipInputStream = new GZIPInputStream(apiResponse.body());

            //Create venues and events from the api data
            DataObjectFactory.getEvents(new String(gzipInputStream.readAllBytes(), StandardCharsets.UTF_8));

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public List<IEvent> getEvents(){
        return DataObjectFactory.getEvents();
    }

    public List<IVenue> getVenues(){
        return DataObjectFactory.getVenues();
    }
}
