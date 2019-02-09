import org.junit.Test;
import structure.ResponseStatus;

import static org.junit.Assert.*;

public class ResponseStatusTest {

    @Test
    public void checkGetStatus() {
        assertEquals(ResponseStatus.OK, ResponseStatus.getStatusByCode(200));
        assertEquals(ResponseStatus.NOT_FOUND, ResponseStatus.getStatusByCode(404));
        assertNull(ResponseStatus.getStatusByCode(10));
    }
}
