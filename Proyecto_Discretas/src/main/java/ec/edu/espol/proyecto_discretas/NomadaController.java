package ec.edu.espol.proyecto_discretas;

import Grafos.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.input.MouseEvent;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author JAVIER
 */
public class NomadaController implements Initializable {



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void volver(MouseEvent event) throws IOException {
        App.cambiarPantalla("juego", 1000, 600, event);
    }
    
    @FXML
    private void cambiarGrafo(MouseEvent event) throws IOException {
        App.cambiarPantalla("grafo", 1000, 600, event);
    }



}
