package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ModeloBarometro {

    String fecha;
    String hora;
    double presion;
    double altura;

    ArrayList<HistoricalValue> listaDeDatos;

   
    public ModeloBarometro() {
        listaDeDatos = new ArrayList<>();
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
        if (index >= 0) {
            HistoricalValue valor = listaDeDatos.get(index);
            valor.setPressure(value.getPressure());
        } else {
            listaDeDatos.add(value);
        }

        Collections.sort(listaDeDatos);
    }

    public List<String> getHistoricalValues() {
        ArrayList<String> lista = new ArrayList<>();

        for (HistoricalValue s : listaDeDatos) {
            lista.add(s.toString());
        }

        return lista;
    }

    public List<HistoricalValue> getHv (){
     return listaDeDatos;
    }
    
    /**
     * 0 = Sol
     * 1 = Lluvia
     * 2 = Nube Feliz
     * 3 = Nube Enfadada
     * @return 
     */
    public int actualizar() {
        int result = 0;
        //1
        if(listaDeDatos.size() < 2) {
            //F
            return -1;
        }
        
        //3
        HistoricalValue a = listaDeDatos.get(0);
        HistoricalValue b = listaDeDatos.get(1);
        
        //4
        if(isPreviousHour(b.getHour(), a.getHour())){
            System.out.println(b.getPressure() - a.getPressure());
            //4.a
            if(b.getPressure() - a.getPressure() > 1) 
                result = 1;
            //4.b
            else if(b.getPressure() - a.getPressure() < -1) 
                result = 0;
            //4.c
            else 
                result = 4;
        }//5
        else if(isPreviousDay(a.getDate())) {
            //5.a
            if(b.getPressure() - a.getPressure() >= 6) 
                result = 2;
            //5.b
            else if(b.getPressure() - a.getPressure() <= -6) 
                result = 3;
            //5.c
            else 
                result = 4;
        }
        //F
        return result;
    }
    
    public boolean isPreviousHour(String a, String b) {
        // Parse the strings into LocalTime objects
        LocalTime localTime1 = LocalTime.parse(a, DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime localTime2 = LocalTime.parse(b, DateTimeFormatter.ofPattern("HH:mm"));

        // Check if localTime1 is one hour greater than localTime2
        return localTime1.getHour() == localTime2.getHour() + 1;
    }
    
    public boolean isPreviousDay(String a) {
        //5
        long encontrado = 0;
        int i;
        int index = 0;
        //6
        for(i = 0; i < listaDeDatos.size() && encontrado <= 0; i++){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date1 = LocalDate.parse(a, formatter);
            LocalDate date2 = LocalDate.parse(listaDeDatos.get(i).getDate(), formatter);

            long daysBetween = ChronoUnit.DAYS.between(date1, date2);
            System.out.println(daysBetween);
            if(Math.abs(daysBetween) > 0) {
                index = i;
                encontrado = Math.abs(daysBetween);
            }
        }
        
        return encontrado == 1;
    }
    
    /**
     * Metodo falseado unicamente para hacer la caja negra, no funciona con la logica de nuestra aplicacion
     * 
     * 0 = Se mantiene
     * 1 = Lluvia
     * 2 = Mejora Pasajera
     * 3 = Anticiclon
     * 4 = borrasca pasa lejos
     * 
     * @param pdHour
     * @param pdDay
     * @return 
     */
    
    public int calcularClima(double pdHour, double pdDay) {
        int value = 0;
        
        if(pdHour < -1) value = 1;
        else if(pdHour > 1) value = 2;
        else if(pdDay > 6) value = 3;
        else if(pdDay < -6) value = 4;
        
        return value;
    }
    
}
