import it.intesys.codylab.Quadrato;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuadratoTest {

    Logger logger = LoggerFactory.getLogger(QuadratoTest.class);


    @Test
    void testArea() {
        Quadrato q = new Quadrato(5);
        try {
            assertEquals(25.0f, q.area());
            logger.info("Test Area superato correttamente.");
        }
        catch(AssertionError e){
            logger.error("Errore durante il test dell'area.");
            throw e;
        }
    }

    @Test
    void testPerimetro() {
        Quadrato q = new Quadrato(5);
        try {
            assertEquals(20.0f, q.perimetro());
            logger.info("Test Perimetro superato correttamente.");
        }
        catch(AssertionError e){
            logger.error("Errore durante il test del perimetro.");
            throw e;
        }
    }
}
