package tests;

import controller.Controller;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class ControllerTest {

    private Controller controller;

    @Before
    public void init() {
        controller = new Controller();
    }

    @Test
    public void checkSendRequest() {
        assertEquals("INCORRECT HOST", controller.sendRequest("", 0, ""));
        assertNotNull(controller.sendRequest("www.martinbroadhurst.com",80, ""));
    }

    @After
    public void onEnd() {
        controller = null;
    }
}
