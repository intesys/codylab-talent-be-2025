import it.intesys.codylab.model.Quadrato;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuadratoTest {

    @Test
    public void testPerimetro(){
        Quadrato quadrato = new Quadrato(5);
        float risultato = quadrato.perimetro();
        assertEquals(20,risultato);
    }

    @Test
    public void testArea(){
        Quadrato quadrato = new Quadrato(5);
        float risultato = quadrato.area();
        assertEquals(25,risultato);
    }
}
