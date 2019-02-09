import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import view.Window;

import static org.junit.Assert.*;

public class WindowTest {
    private Window window;

    @Before
    public void init() throws InterruptedException {
        Thread thread = new Thread(() -> {
            new JFXPanel();
            Platform.runLater(() -> window = new Window());
        });
        thread.start();
        Thread.sleep(10000);
    }

    @Test
    public void testWindow() {
        assertEquals("Not found", window.checkResponseStatus("dfghj"));
        assertEquals("HTTP/1.1 200 OK\n Date: Sat, 09 Feb 2019", window.checkResponseStatus("HTTP/1.1 200 OK\n Date: Sat, 09 Feb 2019"));
        assertEquals("404 - The requested resource could not be found", window.checkResponseStatus("HTTP/1.1 404 NOT FOUND\n Date: Sat, 09 Feb 2019"));
    }

    @After
    public void onEnd() {
        window = null;
    }
}
