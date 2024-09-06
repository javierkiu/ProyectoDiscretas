package ec.edu.espol.proyecto_discretas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Steven Mirabá
 */
public class JuegoController implements Initializable {

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
    private void nomada(MouseEvent event) throws IOException {
        App.cambiarPantalla("nomada", 1000, 600, event);
    }
    
    @FXML
    private void gira(MouseEvent event) throws IOException {
        App.cambiarPantalla("gira", 1000, 600, event);
    }
    
}
