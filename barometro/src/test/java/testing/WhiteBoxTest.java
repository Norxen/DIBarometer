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
    private List<HistoricalValue> listaDeDatos;
    
    @Before
    public void setUp() {
        model = new ModeloBarometro();
        listaDeDatos = model.getHv();
    }
    
    //Nodo 2 del Grafo
    @Test
    public void testActualizar1() {
        int result = model.actualizar();
        assertEquals(-1, result);
    }
    
    //Nodo 4 del grafo
    //Estrategia: Hora anterior y Presion B < Presion A - 1
    @Test
    public void testActualizar2() {
        HistoricalValue a = new HistoricalValue();
        HistoricalValue b = new HistoricalValue();
        
        a.setDate("2000-02-14");
        a.setHour("01:00");
        a.setPressure(150);
        listaDeDatos.add(a);
        
        b.setDate("2000-02-14");
        b.setHour("00:00");
        b.setPressure(140);
        listaDeDatos.add(b);
        
        Collections.sort(listaDeDatos);
        int result = model.actualizar();
        
        assertEquals(1, result);
    }
    
    //Nodo 5 del grafo
    //Estrategia: Hora anterior y Presion B > Presion A + 1
    @Test
    public void testActualizar3() {
        HistoricalValue a = new HistoricalValue();
        HistoricalValue b = new HistoricalValue();
        
        a.setDate("2000-02-14");
        a.setHour("01:00");
        a.setPressure(150);
        listaDeDatos.add(a);
        
        b.setDate("2000-02-14");
        b.setHour("00:00");
        b.setPressure(160);
        listaDeDatos.add(b);
        
        Collections.sort(listaDeDatos);
        int result = model.actualizar();
        
        assertEquals(0, result);
    }
    
    //Nodo 6 del grafo
    //Estrategia:   Hora anterior y caso default, cuando no se cumple ninguna 
    //              de las condiciones anteriores.
    @Test
    public void testActualizar4() {
        HistoricalValue a = new HistoricalValue();
        HistoricalValue b = new HistoricalValue();
        
        a.setDate("2000-02-14");
        a.setHour("01:00");
        a.setPressure(150);
        listaDeDatos.add(a);
        
        b.setDate("2000-02-14");
        b.setHour("00:00");
        b.setPressure(151);
        listaDeDatos.add(b);
        
        Collections.sort(listaDeDatos);
        int result = model.actualizar();
        
        assertEquals(4, result);
    }
    
    //Nodo 8 del grafo
    //Estrategia:   Dia anterior y Presion A <= Presion B - 6
    @Test
    public void testActualizar5() {
        HistoricalValue a = new HistoricalValue();
        HistoricalValue b = new HistoricalValue();
        
        a.setDate("2000-02-15");
        a.setHour("00:00");
        a.setPressure(150);
        listaDeDatos.add(a);
        
        b.setDate("2000-02-14");
        b.setHour("00:00");
        b.setPressure(157);
        listaDeDatos.add(b);
        
        Collections.sort(listaDeDatos);
        int result = model.actualizar();
        
        assertEquals(2, result);
    }

    //Nodo 9 del grafo
    //Estrategia:   Dia anterior y Presion A >= Presion B + 6
    @Test
    public void testActualizar6() {
        HistoricalValue a = new HistoricalValue();
        HistoricalValue b = new HistoricalValue();
        
        a.setDate("2000-02-15");
        a.setHour("00:00");
        a.setPressure(157);
        listaDeDatos.add(a);
        
        b.setDate("2000-02-14");
        b.setHour("00:00");
        b.setPressure(150);
        listaDeDatos.add(b);
        
        Collections.sort(listaDeDatos);
        int result = model.actualizar();
        
        assertEquals(3, result);
    }
    
    //Nodo 10 del grafo
    //Estrategia:   Dia y caso default, cuando no se cumple ninguna 
    //              de las condiciones anteriores.
    @Test
    public void testActualizar7() {
        HistoricalValue a = new HistoricalValue();
        HistoricalValue b = new HistoricalValue();
        
        a.setDate("2000-02-15");
        a.setHour("00:00");
        a.setPressure(150);
        listaDeDatos.add(a);
        
        b.setDate("2000-02-14");
        b.setHour("00:00");
        b.setPressure(153);
        listaDeDatos.add(b);
        
        Collections.sort(listaDeDatos);
        int result = model.actualizar();
        
        assertEquals(4, result);
    }
    
}
