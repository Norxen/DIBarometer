
package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.scene.image.Image;

public class ModeloBarometro {

    String fecha;
    String hora;
    double presion;
    double altura;
    
    ArrayList<HistoricalValue> listaDeDatos;
    
    Image sol, lluvia, nubeCabreada, nubeFeliz;
   
    public ModeloBarometro() {
        listaDeDatos = new ArrayList<HistoricalValue>();
        
//        sol = new Image(getClass().getResourceAsStream("sol.png"));
//        lluvia = new Image(getClass().getResourceAsStream("Rain.png"));
//        nubeCabreada = new Image(getClass().getResourceAsStream("cloud.png"));
//        nubeFeliz = new Image(getClass().getResourceAsStream("cloudhappy.png"));
    }

    public ModeloBarometro(String fecha, String hora, double presion, double añtura) {
        this.fecha = fecha;
        this.hora = hora;
        this.presion = presion;
        this.altura = añtura;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public double getPresion() {
        return presion;
    }

    public void setPresion(double presion) {
        this.presion = presion;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }
    
    public void addValueToList() {
        HistoricalValue value = new HistoricalValue();
        
        value.setDate(fecha);
        value.setHour(hora);
        value.setPressure(presion);
        
        int index = listaDeDatos.indexOf(value);
        if(index >= 0) {
            HistoricalValue valor = listaDeDatos.get(index);
            valor.setPressure(value.getPressure());
        }else{
            listaDeDatos.add(value);
        }
        
        Collections.sort(listaDeDatos);
    }
    
    public List<String> getHistoricalValues() {
        ArrayList<String> lista = new ArrayList<String>();
        
        for(HistoricalValue s : listaDeDatos) {
            lista.add(s.toString());
        }
        
        return lista;
    }
    
    public void actualizar() 
    {
        
    }

}
