/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.Objects;

/**
 *
 * @author norxe
 */
public class HistoricalValue implements Comparable<HistoricalValue> {
    private String date;
    private String hour;
    private double pressure;

    public String getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }

    public double getPressure() {
        return pressure;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    @Override
    public String toString() {
        return date + " " + hour + " " + pressure;
    }

    @Override
    public int compareTo(HistoricalValue o) {
        if(date.compareTo(o.date) != 0) {
            return date.compareTo(o.date);
        }
        
        return hour.compareTo(o.hour);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.date);
        hash = 73 * hash + Objects.hashCode(this.hour);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HistoricalValue other = (HistoricalValue) obj;
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return Objects.equals(this.hour, other.hour);
    }
    
    
    
}
