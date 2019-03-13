package org.alanvilla.projects.disneyRestJava;

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
	
	public static final String DISNEY_RESULT_JSON = "Disney result JSON";
	public static final String CALIFORNIA_ADVENTURE_RESULT_JSON = "California Adventure result JSON";
	public static final String TITLE_TEXT = "titleText";
	public static final String MAIN_TEXT = "mainText";
	public static final String RIDERECT_URL = "redirectionUrl";
	public static final String URL = "https://alanvilla.com";




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
    
    JsonObjectBuilder buildAlexaObject (JsonObjectBuilder builder, ThemePark park) { 	
    	addUTCData(builder);
    	if (park == ThemePark.DISNEYLAND) {
        	builder.add(TITLE_TEXT, DISNEY_RESULT_JSON);
        	if (BlackoutDates.isDisneyBlackoutDate()) {
        		builder.add(MAIN_TEXT, "Disneyland is not available today.");
        	} else {
        		builder.add(MAIN_TEXT, "Disneyland is available today.");
        	}
        	
    	} else if (park == ThemePark.CALIFORNIA_ADVENTURE){
        	builder.add(TITLE_TEXT, CALIFORNIA_ADVENTURE_RESULT_JSON);  
    	}	
    	builder.add(RIDERECT_URL, URL);
		return builder;   	
    }
    
    private void addUTCData (JsonObjectBuilder builder) {
    	builder.add("uid", UUID.randomUUID().toString());
    	builder.add("updateDate", Instant.now().toString());
    }
}