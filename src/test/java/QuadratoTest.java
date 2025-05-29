import it.intesys.codylab.Quadrato;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuadratoTest {

    @Test
    void testArea() {
        Quadrato q = new Quadrato(5);
        assertEquals(25.0f, q.area());
    }
}
