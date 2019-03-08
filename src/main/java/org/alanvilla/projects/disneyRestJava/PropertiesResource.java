package org.alanvilla.projects.disneyRestJava;

import java.util.UUID;
import java.time.Instant;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("properties")
public class PropertiesResource {
	
	public static final String DISNEY_RESULT_JSON = "Disney result JSON";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getProperties() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        JsonObjectBuilder builder = Json.createObjectBuilder();

        System.getProperties().entrySet().stream()
                .forEach(entry -> builder.add((String) entry.getKey(), (String) entry.getValue()));
        //buildAlexaObject(builder);
        return builder.build();
    }
    
    JsonObjectBuilder buildAlexaObject (JsonObjectBuilder builder) {
    	builder.add("uid", UUID.randomUUID().toString());
    	builder.add("updateDate", Instant.now().toString());
    	builder.add("titleText", DISNEY_RESULT_JSON);
    	
		return builder;   	
    }
}