/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyecto_discretas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author JAVIER
 */
public class PrimaryController implements Initializable {

    @FXML
    private VBox opcionEcuaciones;
    @FXML
    private Button buttonEcuaciones;
    @FXML
    private Button buttonJuego;
    @FXML
    private Button buttonCalcular;
    @FXML
    private VBox opcionjuego;
    @FXML
    private VBox opcionCalcular;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        opcionEcuaciones.setOnMouseEntered(event -> buttonEcuaciones.setStyle("-fx-background-color:  #1f2a4f; -fx-text-fill: white;"));
        opcionEcuaciones.setOnMouseExited(event -> buttonEcuaciones.setStyle("-fx-background-color: white;-fx-text-fill: black;"));
        opcionjuego.setOnMouseEntered(event -> buttonJuego.setStyle("-fx-background-color:  #1f2a4f; -fx-text-fill: white;"));
        opcionjuego.setOnMouseExited(event -> buttonJuego.setStyle("-fx-background-color: white;-fx-text-fill: black;"));        
        opcionCalcular.setOnMouseEntered(event -> buttonCalcular.setStyle("-fx-background-color:  #1f2a4f; -fx-text-fill: white;"));
        opcionCalcular.setOnMouseExited(event -> buttonCalcular.setStyle("-fx-background-color: white;-fx-text-fill: black;"));

    }    
    
    @FXML
    private void goToEcuaciones(MouseEvent event) throws IOException {
        App.cambiarPantalla("ecuaciones", 1000, 600,event);
  
    }


    @FXML
    private void goToGame(MouseEvent event) throws IOException {
        App.cambiarPantalla("juego", 1000, 600,event);
 
    }


    @FXML
    private void goToCalculate(MouseEvent event) throws IOException {
        App.cambiarPantalla("calcular", 1000, 600,event);

    }


}
