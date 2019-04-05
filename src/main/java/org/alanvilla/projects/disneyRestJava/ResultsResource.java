package org.alanvilla.projects.disneyRestJava;

import static org.alanvilla.projects.disneyRestJava.Constants.CALIFORNIA_ADVENTURE_RESULT_JSON;
import static org.alanvilla.projects.disneyRestJava.Constants.CALIFORNIA_ADVENTURE_AVAILABLE;
import static org.alanvilla.projects.disneyRestJava.Constants.CALIFORNIA_ADVENTURE_NOT_AVAILABLE;
import static org.alanvilla.projects.disneyRestJava.Constants.DISNEYLAND_AVAILABLE;
import static org.alanvilla.projects.disneyRestJava.Constants.DISNEYLAND_NOT_AVAILABLE;
import static org.alanvilla.projects.disneyRestJava.Constants.DISNEY_RESULT_JSON;
import static org.alanvilla.projects.disneyRestJava.Constants.MAIN_TEXT;
import static org.alanvilla.projects.disneyRestJava.Constants.REDIRECT_URL;
import static org.alanvilla.projects.disneyRestJava.Constants.TITLE_TEXT;
import static org.alanvilla.projects.disneyRestJava.Constants.URL;

import java.time.Instant;
import java.util.UUID;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("results")
public class ResultsResource {
	
	enum ThemePark{
		DISNEYLAND, CALIFORNIA_ADVENTURE;
	}

	/**
	 * Get request for results endpoint that returns a json array of two 
	 * objects. First one is the disneyland object and second is
	 * California adventure object
	 * @return
	 */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray getProperties() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        JsonObjectBuilder builder = Json.createObjectBuilder();

        arrayBuilder.add(buildAlexaObject(builder, ThemePark.DISNEYLAND));
        arrayBuilder.add(buildAlexaObject(builder, ThemePark.CALIFORNIA_ADVENTURE)); 
        return arrayBuilder.build();
    }
    
    private JsonObjectBuilder buildAlexaObject (JsonObjectBuilder builder, ThemePark park) { 	
    	addUTCData(builder);
    	if (park == ThemePark.DISNEYLAND) {
        	builder.add(TITLE_TEXT, DISNEY_RESULT_JSON);
        	if (BlackoutDates.isDisneyBlackoutDate()) {
        		builder.add(MAIN_TEXT, DISNEYLAND_NOT_AVAILABLE);
        	} else {
        		builder.add(MAIN_TEXT, DISNEYLAND_AVAILABLE);
        	}
        	
    	} else if (park == ThemePark.CALIFORNIA_ADVENTURE){
        	builder.add(TITLE_TEXT, CALIFORNIA_ADVENTURE_RESULT_JSON);
        	if (BlackoutDates.isCaliforniaAdventureBlackoutDate()) {
        		builder.add(MAIN_TEXT, CALIFORNIA_ADVENTURE_NOT_AVAILABLE);
        	} else {
        		builder.add(MAIN_TEXT, CALIFORNIA_ADVENTURE_AVAILABLE);
        	}
    	}	
    	builder.add(REDIRECT_URL, URL);
		return builder;   	
    }
    
    private void addUTCData (JsonObjectBuilder builder) {
    	builder.add("uid", UUID.randomUUID().toString());
    	builder.add("updateDate", Instant.now().toString());
    }
}