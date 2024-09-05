/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyecto_discretas;

import Grafos.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author JAVIER
 */
public class CalcularController implements Initializable {

    @FXML
    private TextField valorIda;
    @FXML
    private TextField valorLlegada;
    @FXML
    private TextField valorCosto;
    @FXML
    private Label resultLabel;
    private GraphAL<String, Integer> graph = new GraphAL<>(false, (a,b) -> a.compareTo(b));
    @FXML
    private Label errorLabel;
    @FXML
    private ComboBox<String> cbIda;
    @FXML
    private ComboBox<String> cbLlegada;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void volver(MouseEvent event) throws IOException {
        App.cambiarPantalla("primary", 1000, 600, event);
    }

    @FXML
    private void agregarGrafo(ActionEvent event) {
        String verticeIda = valorIda.getText();
        String verticeLlegada = valorLlegada.getText();
        String costo = valorCosto.getText();
        Integer costoValue = 0;

        if(verticeIda.isBlank() || verticeLlegada.isBlank() || costo.isBlank()){
            errorLabel.setText("Por favor llenar todos los campos");
        }
        else{
            try{
                costoValue = Integer.valueOf(costo);
                if(costoValue < 0) {
                    errorLabel.setText("El costo solo pueden ser enteros no negativos");
                }
                else{
                    graph.addVertex(verticeIda);
                    graph.addVertex(verticeLlegada);
                    graph.connect(verticeIda, verticeLlegada, costoValue, 1);
                    if (!cbIda.getItems().contains(verticeIda)) { // Evitar duplicados
                        cbIda.getItems().add(verticeIda);
                        cbLlegada.getItems().add(verticeIda);
                    }
                    if (!cbLlegada.getItems().contains(verticeLlegada)) { // Evitar duplicados
                        cbLlegada.getItems().add(verticeLlegada);
                        cbIda.getItems().add(verticeLlegada);
                    } 
                    System.out.println("Se ha anadido con exito");
                    errorLabel.setText("Se ha añadido con éxito la conexión");
                }
            }
            catch(NumberFormatException ex){
                errorLabel.setText("El costo solo pueden ser enteros no negativos");
            }
        }
        valorIda.setText("");
        valorLlegada.setText("");
        valorCosto.setText("");
    }

    @FXML
    private void calcularRuta(ActionEvent event) {
        String partida = cbIda.getValue();
        String destino = cbLlegada.getValue();
        if(partida == null || destino == null){
            resultLabel.setText("Por favor seleccione ambas opciones!");
        }
        else{
            DijkstraAlgorithm da = new DijkstraAlgorithm(graph);
            String result = da.findShortestPath(partida, destino);
            resultLabel.setText(result);
        }
    }
    
}
