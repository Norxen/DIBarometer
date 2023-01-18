/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.grupo3.barometro;

import domain.ModeloBarometro;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import static java.util.Collections.list;

import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import javafx.scene.image.ImageView;


import javafx.scene.layout.GridPane;



/**
 * FXML Controller class
 *
 * @author calde
 */
public class BarometroController implements Initializable {

    @FXML
    private ListView<String> historial;
    @FXML
    private TextField texPrecision;
    @FXML
    private DatePicker fecha;
    @FXML
    private ComboBox hora;
    @FXML
    private Button anadir;
    @FXML
    private Slider sliderAltura;
    @FXML
    private Label labelAltura;
    @FXML
    private ImageView image;

    @FXML
    private GridPane panel;

    ModeloBarometro modelo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modelo = new ModeloBarometro();

        labelAltura.setText("0");

        //acutalizar el valor en labelAltura con sliderAltura 
        sliderAltura.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {

                int value = (int) sliderAltura.getValue();
                labelAltura.setText(value + "");
            }

        });
        
        // boton para y añadiendo 
        EventHandler<ActionEvent> onClick = new EventHandler<ActionEvent>() {
           
            @Override
            public void handle(ActionEvent event) {
                String element = "";
                element += "Fecha: " + fecha.getValue().format(DateTimeFormatter.ISO_DATE) 
                        +"     "+ "Hora: "+ hora.getValue() +"\n"+  "Presión: " + texPrecision.getText()  +"     "+ "Altura: " + labelAltura.getText();
                          
               
                addElement(element);
                
                event.consume();
      
            }
        };
        anadir.setOnAction(onClick);
       
        comboBox();
    }
    
    
 
    void addElement(String element) {
        
        ObservableList<String> items = FXCollections.observableArrayList();
        items.add(element);
        historial.setItems(items);
        
    }
    // metodo para rellenar el comboBox(HORAS)
    void comboBox() {
        ObservableList<String> items = FXCollections.observableArrayList();
        
        String nada = "Nada"+"";
        items.addAll(nada,"00.00", "01:00", "02:00", "03:00", "04:00","05:00","06:00","07:00","08:00","09:00","10:00","11:00","12:00"
                    ,"13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00","24:00");
                   hora.setItems(items);
       

    }
}
