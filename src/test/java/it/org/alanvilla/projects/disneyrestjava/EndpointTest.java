package it.org.alanvilla.projects.disneyrestjava;

import static org.alanvilla.projects.disneyrestjava.Constants.CALIFORNIA_ADVENTURE_AVAILABLE;
import static org.alanvilla.projects.disneyrestjava.Constants.CALIFORNIA_ADVENTURE_NOT_AVAILABLE;
import static org.alanvilla.projects.disneyrestjava.Constants.DISNEYLAND_AVAILABLE;
import static org.alanvilla.projects.disneyrestjava.Constants.DISNEYLAND_NOT_AVAILABLE;
import static org.alanvilla.projects.disneyrestjava.Constants.MAIN_TEXT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.provider.jsrjsonp.JsrJsonpProvider;
import org.junit.Test;

public class EndpointTest {

    @SuppressWarnings("unlikely-arg-type")
	@Test
    public void testResults() {
        String port = System.getProperty("liberty.test.port");
        String war = System.getProperty("war.name");
        String url = "http://localhost:" + port + "/" + war + "/";

        Client client = ClientBuilder.newClient();
        client.register(JsrJsonpProvider.class);

        WebTarget target = client.target(url + "results");
        Response response = target.request().get();

        assertEquals("Incorrect response code from " + url, Response.Status.OK.getStatusCode(), response.getStatus());

        JsonArray jsArray = response.readEntity(JsonArray.class);
        
        JsonObject disneyObject = jsArray.getJsonObject(0);
        JsonObject californiaObject = jsArray.getJsonObject(1);
        
        assertTrue("The main text should be one of two things", 
        		removeAllquotes(disneyObject.get(MAIN_TEXT).toString()).equals(DISNEYLAND_AVAILABLE) 
        		|| removeAllquotes(disneyObject.get(MAIN_TEXT).toString()).equals(DISNEYLAND_NOT_AVAILABLE));
        
        assertTrue("The main text should be one of two things", 
        		removeAllquotes(californiaObject.get(MAIN_TEXT).toString()).equals(CALIFORNIA_ADVENTURE_AVAILABLE) 
        		|| removeAllquotes(californiaObject.get(MAIN_TEXT).toString()).equals(CALIFORNIA_ADVENTURE_NOT_AVAILABLE));
        response.close();
    }
    
    private String removeAllquotes(String str) {   	
    	return str.replaceAll("\"", ""); 
    }
}