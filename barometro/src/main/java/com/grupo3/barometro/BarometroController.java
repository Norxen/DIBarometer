/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.grupo3.barometro;

import domain.ModeloBarometro;
import java.net.URL;
import java.time.format.DateTimeFormatter;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
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
    private ImageView image;

    @FXML
    private GridPane panel;
    @FXML
    private ProgressBar progresBar;
    @FXML
    private Label labelProgreso;

    ModeloBarometro modelo;
    int iterations;

    ObservableList<String> items = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modelo = new ModeloBarometro();

        progresBar.progressProperty().bind(task.progressProperty());
        new Thread(task).start();
        //boton desactivado mientras no tengan valores los componentes
        anadir.disableProperty().bind(texPrecision.textProperty().isEqualTo("")
                .or(hora.valueProperty().isNull()).or(fecha.valueProperty().isNull()));

        // todo desactivado hasta estar cargado el programa (barra de proceso cargada)
        fecha.disableProperty().bind(progresBar.progressProperty().isNotEqualTo(1, 0));
        hora.disableProperty().bind(progresBar.progressProperty().isNotEqualTo(1, 0));
        texPrecision.disableProperty().bind(progresBar.progressProperty().isNotEqualTo(1, 0));
        sliderAltura.disableProperty().bind(progresBar.progressProperty().isNotEqualTo(1, 0));

        // para quitar barra de proceso una vez cargado
        labelProgreso.visibleProperty().bind(progresBar.progressProperty().isNotEqualTo(1, 0));
        progresBar.visibleProperty().bind(progresBar.progressProperty().isNotEqualTo(1, 0));

        //}
        //acutalizar el valor en labelAltura con sliderAltura, iniciamos el label en (0)
        labelAltura.setText("0");
        sliderAltura.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {

                int value = (int) sliderAltura.getValue();
                labelAltura.setText(value + "");
            }

        });

        textfieldNumeros(texPrecision);

        // boton para añadir
        EventHandler<ActionEvent> onClick = new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                modelo.setFecha(fecha.getValue().format(DateTimeFormatter.ISO_DATE));
                modelo.setHora(hora.getValue() + "");
                modelo.setPresion(Double.parseDouble(texPrecision.getText()));
                modelo.setAltura(sliderAltura.getValue());
                String element = "";
                element += "Fecha: " + modelo.getFecha() + "     "
                        + "Hora: " + modelo.getHora() + "\n" + "Presión: " + modelo.getPresion() + "     "
                        + "Altura: " + labelAltura.getText();

//                String element = "";
//                element += "Fecha: " + fecha.getValue().format(DateTimeFormatter.ISO_DATE) + "     "
//                        + "Hora: "  + hora.getValue() + "\n" + "Presión: " + texPrecision.getText() + "     "
//                        + "Altura: " + labelAltura.getText();
                addElement(element);
                event.consume();

            }
        };

        anadir.setOnAction(onClick);
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
                    Thread.sleep(2000);
                } catch (InterruptedException interrupted) {
                }
            }
            return iterations;
        }
    };

}
