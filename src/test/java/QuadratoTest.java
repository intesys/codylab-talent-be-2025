import it.intesys.codylab.Quadrato;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuadratoTest {

    @Test
    void testArea() {
        Quadrato q = new Quadrato(5);
        assertEquals(25.0f, q.area(), 0.0001);
    }

    @Test
    void testToString() {
        Quadrato q = new Quadrato(3);
        assertEquals("Quadrato con lato 3.0", q.toString());
    }
}
