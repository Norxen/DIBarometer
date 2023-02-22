/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testing;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import domain.ModeloBarometro;
import domain.HistoricalValue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import org.junit.Before;
/**
 *
 * @author norxe
 */
public class BlackBoxTest {
    
    private ModeloBarometro model;
    
    @Before
    public void setUp() {
        model = new ModeloBarometro();
    }
    
    @Test
    public void test1 () {
        assertEquals(0, model.calcularClima(-2, 52478358));
    }
    
    @Test
    public void test2 () {
        assertEquals(1, model.calcularClima(2, 52478358));
    }
    @Test
    public void test3 () {
        assertEquals(4, model.calcularClima(0, 5));
    }
    @Test
    public void test4 () {
        assertEquals(2, model.calcularClima(0, 7));
    }
    @Test
    public void test5 () {
        assertEquals(3, model.calcularClima(0, -7));
    }
    @Test
    public void test6 () {
        assertEquals(4, model.calcularClima(0, 0));
    }

    
}
