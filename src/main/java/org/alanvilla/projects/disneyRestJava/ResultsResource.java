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
	
	public static final String DISNEY_RESULT_JSON = "Disney result JSON";
	public static final String CALIFORNIA_ADVENTURE_RESULT_JSON = "California Adventure result JSON";


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray getProperties() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        JsonObjectBuilder builder = Json.createObjectBuilder();

        arrayBuilder.add(buildAlexaObject(builder)); 
        return arrayBuilder.build();
    }
    
    JsonObjectBuilder buildAlexaObject (JsonObjectBuilder builder) { 	
    	addUTCData(builder);
    	builder.add("titleText", DISNEY_RESULT_JSON);  
    	if (BlackoutDates.isDisneyBlackoutDate()) {
    		
    	} else {
    		
    	}
		return builder;   	
    }
    
    private void addUTCData (JsonObjectBuilder builder) {
    	builder.add("uid", UUID.randomUUID().toString());
    	builder.add("updateDate", Instant.now().toString());
    }
}