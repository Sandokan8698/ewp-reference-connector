/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.erasmuswithoutpaper.boundary;

import eu.erasmuswithoutpaper.api.echo.Response;
import java.util.List;
import java.util.Map;
import javax.ejb.embeddable.EJBContainer;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.DeploymentContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.glassfish.jersey.test.JerseyTest;
import static org.junit.Assert.*;

public class ConnectorResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(ConnectorResource.class);
    }

    @Test
    public void testPost() {
        MultivaluedMap<String, String> data = new MultivaluedHashMap<String, String>();
        data.add("echo", "Test1");
        data.add("echo", "Test2");

        final Response echoResponse = target("echo")
            .request()
            .post(Entity.form(data), Response.class);

        assertNotNull(echoResponse);
        assertEquals(2, echoResponse.getEcho().size());
        assertEquals("Test1", echoResponse.getEcho().get(0));
        assertEquals("Test2", echoResponse.getEcho().get(1));
    }
    
    @Test
    public void testGet() {
        final Response echoResponse = target("echo").queryParam("echo", "Test1").queryParam("echo", "Test2")
            .request()
            .get(Response.class);

        assertNotNull(echoResponse);
        assertEquals(2, echoResponse.getEcho().size());
        assertEquals("Test1", echoResponse.getEcho().get(0));
        assertEquals("Test2", echoResponse.getEcho().get(1));
    }
    
}