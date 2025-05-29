import it.intesys.codylab.Quadrato;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuadratoTest {

    @Test
    void testArea() {
        Quadrato quadrato = new Quadrato(5);
        assertEquals(25, quadrato.area(), "L'area del quadrato con lato 5 dovrebbe essere 25");
    }

    @Test
    void testNumberEquals() {
        Long l1 = new Long("123455667");
        Long l2 = new Long("123455667");
//        Long l1 = 3L;
//        Long l2 = 3L;
        assertTrue(l1.equals(l2));
    }

}