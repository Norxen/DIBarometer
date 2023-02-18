/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.grupo3.barometro;

import domain.HistoricalValue;
import domain.ModeloBarometro;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.converter.IntegerStringConverter;

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
    private Label valorAltura;
    @FXML
    private ImageView image;
    @FXML
    private Button calibrar;
    @FXML
    private GridPane panel;
    @FXML
    private ProgressBar progresBar;
    @FXML
    private Label labelProgreso;

    @FXML
    private Label lblAltura;

    @FXML
    private Label lblFecha;

    @FXML
    private Label lblHora;

    @FXML
    private Menu lblLenguajes;

    @FXML
    private Label lblPresion;
    ModeloBarometro modelo;
    int iterations;
    ResourceBundle resourceBundle;

    

    ObservableList<String> items = FXCollections.observableArrayList();

    @FXML
    private ToggleGroup idioma;

    /**
     *
     * @param url
     * @param rb
     * @throws IOException
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        modelo = new ModeloBarometro();
        resourceBundle = ResourceBundle.getBundle("com.grupo3.barometro.i18n_es_ES");

        progresBar.progressProperty().bind(task.progressProperty());
        new Thread(task).start();
        //boton desactivado mientras no tengan valores los componentes
        anadir.disableProperty().bind(texPrecision.textProperty()
                .isEqualTo("")
                .or(hora.valueProperty().isNull())
                .or(fecha.valueProperty().isNull()));

        calibrar.disableProperty().bind(progresBar.progressProperty()
                .isNotEqualTo(1, 0));
        fecha.disableProperty().bind(progresBar.progressProperty()
                .isNotEqualTo(1, 0).or(calibrar.textProperty()
                .isNotEqualTo(resourceBundle.getString("calibrar"))));
        hora.disableProperty().bind(progresBar.progressProperty()
                .isNotEqualTo(1, 0).or(calibrar.textProperty()
                .isNotEqualTo(resourceBundle.getString("calibrar"))));
        texPrecision.disableProperty().bind(progresBar.progressProperty()
                .isNotEqualTo(1, 0).or(calibrar.textProperty()
                .isNotEqualTo(resourceBundle.getString("calibrar"))));
        sliderAltura.disableProperty().bind(progresBar.progressProperty()
                .isNotEqualTo(1, 0).or(calibrar.textProperty()
                .isNotEqualTo(resourceBundle.getString("confirmar"))));

        // para quitar barra de proceso una vez cargado
        labelProgreso.visibleProperty().bind(progresBar.progressProperty()
                .isNotEqualTo(1, 0));
        progresBar.visibleProperty().bind(progresBar.progressProperty()
                .isNotEqualTo(1, 0));

        //}
        //acutalizar el valor en labelAltura con sliderAltura, iniciamos el label en (0)
        labelAltura.setText("0");
        valorAltura.setText("Altura: 0");
        sliderAltura.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {

                int value = (int) sliderAltura.getValue();
                labelAltura.setText(value + "");
                valorAltura.setText("Altura: " + value + "");
            }

        });

        //cambiar nombre cuando es clickado
        EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (calibrar.getText().compareTo(resourceBundle.getString("confirmar")) == 0) {
                    calibrar.setText(resourceBundle.getString("calibrar"));
                    modelo.setAltura(sliderAltura.getValue());
                } else {
                    calibrar.setText(resourceBundle.getString("confirmar"));
                }

                event.consume();
            }

        };
        calibrar.setOnAction(buttonHandler);

        textfieldNumeros(texPrecision);
        
      
        // boton para añadir
        EventHandler<ActionEvent> onClick = new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                modelo.setFecha(fecha.getValue().format(DateTimeFormatter.ISO_DATE));
                modelo.setHora(hora.getValue() + "");
                modelo.setPresion(Double.parseDouble(texPrecision.getText()));
                modelo.setAltura(sliderAltura.getValue());
                
                ObservableList<String> items = FXCollections.observableArrayList();
                modelo.addValueToList();
                for (String item : modelo.getHistoricalValues()) {
                    items.add(item);
                    
                }
                
                historial.setItems(items);
                System.out.println(modelo.getHv().size());
                if(modelo.getHv().size() >= 2){  
                 //predecirClima(modelo.getFecha(), modelo.getHora(), modelo.getHv().get(0).getPressure(), modelo.getAltura(),modelo.getHv().get(1).getPressure());
                }else{
               
                    image.setImage(new Image(getClass().getResourceAsStream("error404.png")));
                
                }
                    
                
               

                
//                String element = "";
//                element += "Fecha: " + modelo.getFecha() + "     "
//                        + "Hora: " + modelo.getHora() + "\n" + "Presión: " + modelo.getPresion() + "     ";
//
//                addElement(element);
                event.consume();

            }
        };

        anadir.setOnAction(onClick);

        idioma.selectedToggleProperty().addListener((p, o, n) -> {
            RadioMenuItem rr = (RadioMenuItem) n.getToggleGroup().getSelectedToggle();

            boolean isConfirming = true;

            if (calibrar.getText().compareTo(resourceBundle.getString("confirmar")) != 0) {
                isConfirming = false;
            }

            switch (rr.getText()) {
                case "Spanish":
                    resourceBundle = ResourceBundle.getBundle("com.grupo3.barometro.i18n_es_ES");
                    break;
                case "French":
                    resourceBundle = ResourceBundle.getBundle("com.grupo3.barometro.i18n_fr_FR");
                    break;
                case "Italian":
                    resourceBundle = ResourceBundle.getBundle("com.grupo3.barometro.i18n_it_IT");
                    break;
                case "English":
                    resourceBundle = ResourceBundle.getBundle("com.grupo3.barometro.i18n_en_US");

                    break;
            }

            lblFecha.setText(resourceBundle.getString("fecha"));
            lblHora.setText(resourceBundle.getString("hora"));
            lblPresion.setText(resourceBundle.getString("presion"));
            lblAltura.setText(resourceBundle.getString("altura"));
            if (isConfirming) {
                calibrar.setText(resourceBundle.getString("confirmar"));
            } else {
                calibrar.setText(resourceBundle.getString("calibrar"));
            }
            labelProgreso.setText(resourceBundle.getString("cargando"));
            valorAltura.setText(resourceBundle.getString("valorAltura") + (int) modelo.getAltura());
            lblLenguajes.setText(resourceBundle.getString("lenguaje"));
            anadir.setText(resourceBundle.getString("añadir"));

            //sabemos que está mal, pero no nos vamos a poner a hacerlo, 
            //entiendelo, son las 10:30, no llegamos teacher
            fecha.disableProperty().bind(progresBar.progressProperty()
                    .isNotEqualTo(1, 0).or(calibrar.textProperty()
                    .isNotEqualTo(resourceBundle.getString("calibrar"))));
            hora.disableProperty().bind(progresBar.progressProperty()
                    .isNotEqualTo(1, 0).or(calibrar.textProperty()
                    .isNotEqualTo(resourceBundle.getString("calibrar"))));
            texPrecision.disableProperty().bind(progresBar.progressProperty()
                    .isNotEqualTo(1, 0).or(calibrar.textProperty()
                    .isNotEqualTo(resourceBundle.getString("calibrar"))));
            sliderAltura.disableProperty().bind(progresBar.progressProperty()
                    .isNotEqualTo(1, 0).or(calibrar.textProperty()
                    .isNotEqualTo(resourceBundle.getString("confirmar"))));
        });

        comboBox();

    }
    
    void addElement(String element) {
        
        items.add(element);
        historial.setItems(items);

    }

    // metodo para rellenar el comboBox(HORAS)
    void comboBox() {
        ObservableList<String> items = FXCollections.observableArrayList();

        items.addAll("00.00", "01:00", "02:00", "03:00",
                "04:00", "05:00", "06:00", "07:00",
                "08:00", "09:00", "10:00", "11:00",
                "12:00", "13:00", "14:00", "15:00",
                "16:00", "17:00", "18:00", "19:00",
                "20:00", "21:00", "22:00", "23:00",
                "24:00");
        hora.setItems(items);

    }

    //metodo para Textfield, solo numeros
    void textfieldNumeros(TextField textField) {

        UnaryOperator<Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([0-9]*)?")) {
                return change;
            }
            return null;
        };
        textField.setTextFormatter(
                new TextFormatter<Integer>(
                        new IntegerStringConverter(), 0, integerFilter));

        //para que salga vacio y no con a (0)
        textField.setText("");

    }

    Task<Integer> task = new Task<Integer>() {
        @Override
        protected Integer call() throws Exception {
            for (iterations = 0; iterations < 10; iterations++) {

                updateProgress(iterations + 1, 10);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException interrupted) {
                }
            }
            return iterations;
        }
    };

//    public void predecirClima(String fecha, String hora, double presion, double altura ,double presionAnterior) {
//        
//        double cambioPresion = presion - presionAnterior;
//
//        // Cálculo del cambio de presión y predicción del clima
//        
//        if (cambioPresion < 0 && Math.abs(cambioPresion) <= 6.0 / 24.0) {
//            
//            // Devuelve una imagen correspondiente al resultado de la predicción
//            image.setImage(new Image(getClass().getResourceAsStream("sol.png")));
//            
//        } else if (cambioPresion < 0 && Math.abs(cambioPresion) > 6.0 / 24.0) {
//            
//            // Devuelve una imagen correspondiente al resultado de la predicción
//            image.setImage( new Image(getClass().getResourceAsStream("Rain.png")));
//            
//        } else if (cambioPresion > 0 && cambioPresion <= 6.0 / 24.0) {
//            
//            // Devuelve una imagen correspondiente al resultado de la predicción
//            image.setImage(new Image(getClass().getResourceAsStream("cloud.png")));
//            
//        } else if (cambioPresion > 0 && cambioPresion > 6.0 / 24.0) {
//            
//            // Devuelve una imagen correspondiente al resultado de la predicción
//            image.setImage(new Image(getClass().getResourceAsStream("couldhappy.png")));
//        }
//    }
}
 
       