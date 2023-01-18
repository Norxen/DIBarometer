/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.Calendar;

import javafx.collections.ObservableList;

public class ModeloBarometro {

    String fecha;
    Calendar hora;
    double presion;
    double altura;
    ObservableList lista;
  
    
    public ModeloBarometro() {
    }

    public ModeloBarometro(String fecha, Calendar hora, double presion, double añtura, ObservableList lista) {
        this.fecha = fecha;
        this.hora = hora;
        this.presion = presion;
        this.altura = añtura;
    }

    public ObservableList getLista() {
       
        return lista;
    }

    public void setLista(ObservableList lista) {
        this.lista = lista;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Calendar getHora() {
        return hora;
    }

    public void setHora(Calendar hora) {
        this.hora = hora;
    }

    public double getPresion() {
        return presion;
    }

    public void setPresion(double presion) {
        this.presion = presion;
    }

    public double getAñtura() {
        return altura;
    }

    public void setAñtura(double añtura) {
        this.altura = añtura;
    }

}
