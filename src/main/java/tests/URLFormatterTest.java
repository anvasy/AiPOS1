package tests;

import org.junit.Test;
import structure.ResponseStatus;
import util.URLFormatter;

import static org.junit.Assert.*;

public class URLFormatterTest {

    @Test
    public void checkValidateHost() {
        assertTrue(URLFormatter.validateHost("http://localhost:8080"));
        assertTrue(URLFormatter.validateHost("http://seasonvar.ru"));
        assertTrue(URLFormatter.validateHost("http://68.183.98.116:8080/dasfg/afsdgf"));
        assertTrue(URLFormatter.validateHost("http://seasonvar.ru:22"));
        assertFalse(URLFormatter.validateHost("adsfghjkl"));
    }

    @Test
    public void checkResponseStatus() {
        assertNull(URLFormatter.getResponseStatus(""));
        String response = "HTTP/1.1 200 OK\n" +
                "Date: Thu, 07 Feb 2019 18:13:12 GMT";
        assertEquals(ResponseStatus.OK, URLFormatter.getResponseStatus(response));
    }

    @Test
    public void checkPrepareHost() {
        assertEquals("seasonvar.ru", URLFormatter.prepareHost("http://seasonvar.ru"));
        assertEquals("seasonvar.ru", URLFormatter.prepareHost("seasonvar.ru"));
    }
}
