
package domain;

public class ModeloBarometro {

    String fecha;
    String hora;
    double presion;
    double altura;
   
    public ModeloBarometro() {
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

}
