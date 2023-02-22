package testing;

import static org.junit.Assert.assertEquals;
import domain.ModeloBarometro;
import domain.HistoricalValue;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import org.junit.Before;

/**
 *
 * @author norxe
 */
public class WhiteBoxTest {
    private ModeloBarometro model;
    
    @Before
    public void setUp() {
        model = new ModeloBarometro();
    }
    
    @Test
    public void test13578F() {
        assertEquals(4, model.calcularClima(0, 0));
    }
    
    @Test
    public void test1357F() {
        assertEquals(3, model.calcularClima(0, -7));
    }
    
    @Test
    public void test1356F() {
        assertEquals(2, model.calcularClima(0, 7));
    }
    @Test
    public void test134F() {
        assertEquals(1, model.calcularClima(2, 0));
    }
    
    @Test
    public void test12F() {
        assertEquals(0, model.calcularClima(-2, 0));
    }
    
}
