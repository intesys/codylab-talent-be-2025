import it.intesys.codylab.model.Quadrato;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.logging.Logger;

public class QuadratoTest {

    private static final Logger logger = Logger.getLogger(QuadratoTest.class.getName());

    @Test
    public void testPerimetro(){
        logger.info("Inizio test perimetro");
        Quadrato quadrato = new Quadrato(5);
        float risultato = quadrato.perimetro();
        assertEquals(20,risultato);
        logger.info("Il quadrato ha un perimetro di " + risultato + " Il risultato è corretto");

    }

    @Test
    public void testArea(){
        logger.info("Inizio test area");
        Quadrato quadrato = new Quadrato(5);
        float risultato = quadrato.area();
        assertEquals(25,risultato);
        logger.info("Il quadrato ha un'area di " + risultato + " Il risultato è corretto");
    }
}
