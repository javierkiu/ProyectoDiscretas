package ec.edu.espol.proyecto_discretas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

import javafx.scene.input.MouseEvent;

import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Steven Mirabá
 */
public class GrafoController implements Initializable {

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
    
}
