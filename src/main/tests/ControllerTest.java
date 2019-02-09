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
        assertNotNull(controller.sendRequest("www.martinbroadhurst.com",80, "Accept: text/html,image/apng,*/*;q=0.8\n" +
                "Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64)"));
    }

    @After
    public void onEnd() {
        controller = null;
    }
}
