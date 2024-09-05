package ec.edu.espol.proyecto_discretas;

import Grafos.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.util.Duration; 
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
 * @author Steven Mirabá
 */
public class GrafoController implements Initializable {
    
    @FXML
    private Line lineVenCol,lineVenBra,lineEcuCol,lineBolPar,linePerChi,lineChiArg,lineUruArg,lineUruBra,lineColBra;
    @FXML
    private Line lineColPer,linePerBra,linePerBol,lineChiBol,lineArgBol,lineParBra,lineArgBra,lineEcuPer,lineParArg;
    @FXML
    private Circle vecVEN,vecPAR,vecCO,vecECU,vecPER,vecCHI,vecAR,vecBOL,vecBRA,vecURU;
    @FXML
    private Label LVEN,LCOL,LECU,LPER,LCHI,LARG,LBOL,LPAR,LURU,LBRA;
    
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
    private void prim(MouseEvent event) throws IOException {
        vecVEN.setFill(Color.LIGHTCORAL);
        lineVenCol.setStroke(Color.RED);vecCO.setFill(Color.LIGHTCORAL);
        lineEcuCol.setStroke(Color.RED);vecECU.setFill(Color.LIGHTCORAL);
        lineEcuPer.setStroke(Color.RED);vecPER.setFill(Color.LIGHTCORAL);
        linePerBol.setStroke(Color.RED);vecBOL.setFill(Color.LIGHTCORAL);
        lineChiBol.setStroke(Color.RED);vecCHI.setFill(Color.LIGHTCORAL);
        lineChiArg.setStroke(Color.RED);vecAR.setFill(Color.LIGHTCORAL);
        lineUruArg.setStroke(Color.RED);vecURU.setFill(Color.LIGHTCORAL);lineParArg.setStroke(Color.RED);vecPAR.setFill(Color.LIGHTCORAL);
        lineParBra.setStroke(Color.RED);vecBRA.setFill(Color.LIGHTCORAL);
    }
    
    @FXML
    private void dijkstra(MouseEvent event) throws IOException{
        vecVEN.setFill(Color.LIGHTCORAL);LVEN.setText("[0,-]");
        vecCO.setFill(Color.LIGHTCORAL);LCOL.setText("[1023,VEN](1)");lineVenCol.setStroke(Color.RED);
        vecECU.setFill(Color.LIGHTCORAL);LECU.setText("[1757,COL](2)");lineEcuCol.setStroke(Color.RED);
        vecPER.setFill(Color.LIGHTCORAL);LPER.setText("[2908,COL](2)");lineColPer.setStroke(Color.RED);
        vecBOL.setFill(Color.LIGHTCORAL);LBOL.setText("[3985,PER](3)");linePerBol.setStroke(Color.RED);
        vecPAR.setFill(Color.LIGHTCORAL);LPAR.setText("[5060,BRA](2)");lineParBra.setStroke(Color.RED);
        vecURU.setFill(Color.LIGHTCORAL);LURU.setText("[6139,ARG](3)");lineUruArg.setStroke(Color.RED);
        vecAR.setFill(Color.LIGHTCORAL);LARG.setText("[5937,BRA](2)");lineArgBra.setStroke(Color.RED);
        vecCHI.setFill(Color.LIGHTCORAL);LCHI.setText("[5374,PER](3)");linePerChi.setStroke(Color.RED);
        vecBRA.setFill(Color.LIGHTCORAL);LBRA.setText("[3597,VEN](1)");lineVenBra.setStroke(Color.RED);
    }
    
}
