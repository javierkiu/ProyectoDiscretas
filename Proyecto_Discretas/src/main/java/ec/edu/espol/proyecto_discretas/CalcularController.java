/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyecto_discretas;

import Grafos.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

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
    @FXML
    private Pane drawPane;

    
    private Map<String, Circle> vertices = new HashMap<>(); // Mapa para almacenar los vértices por nombre
    private final double vertexRadius = 15; // Radio de los vértices
    private final double spacing = 50; // Espaciado entre los vértices
    
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
                    addVertex(verticeIda);
                    addVertex(verticeLlegada);
                    addEdge(verticeIda, verticeLlegada, costoValue);
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
    private void addVertex(String vertexName) {
        if (!vertices.containsKey(vertexName)) {
            double x = Math.random() * (drawPane.getWidth() - 2 * vertexRadius) + vertexRadius;
            double y = Math.random() * (drawPane.getHeight() - 2 * vertexRadius) + vertexRadius;

            Circle vertex = new Circle(x, y, vertexRadius);
            vertex.setFill(Color.LIGHTBLUE);
            Text vertexLabel = new Text(x - vertexRadius / 2, y + vertexRadius / 2, vertexName);

            vertices.put(vertexName, vertex);
            drawPane.getChildren().addAll(vertex, vertexLabel);
        }
    }

    // Método para añadir una arista entre dos vértices
    private void addEdge(String vertex1, String vertex2, double cost) {
        Circle v1 = vertices.get(vertex1);
        Circle v2 = vertices.get(vertex2);

        if (v1 != null && v2 != null) {
            Line edge = new Line(v1.getCenterX()+vertexRadius, v1.getCenterY(), v2.getCenterX()+vertexRadius, v2.getCenterY());
            Text costLabel = new Text((v1.getCenterX() + v2.getCenterX()) / 2, (v1.getCenterY() + v2.getCenterY()) / 2, String.valueOf(cost));
            drawPane.getChildren().addAll(edge, costLabel);
        }
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
