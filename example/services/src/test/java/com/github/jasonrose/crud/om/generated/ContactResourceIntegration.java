package com.github.jasonrose.crud.om.generated;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@link com.github.jasonrose.crud.om.generated.ContactDefaultResource}
 * <p>
 * This is an integration test.
 * <p>
 * In order to pass these tests within an IDE, you have to first run Jetty. This is configured for you via maven, if you run the command:
 * 
 * <pre>
 * mvn jetty:run
 * </pre>
 * 
 * Alternatively, you can just run the maven command:
 * 
 * <pre>
 * mvn integration-test
 * </pre>
 * 
 * and maven will do everything for you (start jetty, run test, stop jetty).
 * <p>
 * Make sure the literal URL below matches that defined in pom.xml
 */
public class ContactResourceIntegration {
  /** URL */
  private static final String URL = "http://localhost:8080/mdd-test/Contact";

  private static final String ERR_MSG = "Unexpected exception in test. Is Jetty Running at " + URL + " ? ->";

  protected HttpClient client;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() {
    client = new DefaultHttpClient();
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testEmptyList() {
    try {
      final HttpResponse response = client.execute(new HttpGet(URL));
      assertEquals(200, response.getStatusLine().getStatusCode());

      final BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      assertEquals("[]", rd.readLine());
    } catch( final Exception e ) {
      fail(ERR_MSG + e);
    }
  }

  @Test
  public void testCreateOnce() {
    try {
      {
        final HttpPost request = new HttpPost(URL);
        final StringEntity input = new StringEntity("{\"firstName\":\"Bob\",\"lastName\":\"Bobber\"}");
        input.setContentType("application/json");
        request.setEntity(input);
        final HttpResponse response = client.execute(request);
        assertEquals(200, response.getStatusLine().getStatusCode());
      }
      {
        final HttpResponse response = new DefaultHttpClient().execute(new HttpGet(URL));
        assertEquals(200, response.getStatusLine().getStatusCode());

        final BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        assertEquals("[{\"lastName\":\"Bobber\",\"divisions\":[],\"firstName\":\"Bob\"}]", rd.readLine());
      }
    } catch( final Exception e ) {
      fail(ERR_MSG + e);
    }
  }
}