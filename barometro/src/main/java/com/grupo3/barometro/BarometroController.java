/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.grupo3.barometro;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

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

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author calde
 */
public class BarometroController implements Initializable {

    @FXML
    private ListView<?> historial;
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
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        labelAltura.setText("0");
        
        //acutalizar el valor en labelAltura con sliderAltura 
        sliderAltura.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
              
                int value = (int) sliderAltura.getValue();
                labelAltura.setText(value + "");
            }

        });

    
        
    }

}
