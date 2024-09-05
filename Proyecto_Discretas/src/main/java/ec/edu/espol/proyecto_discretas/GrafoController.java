package ec.edu.espol.proyecto_discretas;

import Grafos.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.util.Duration; 
import java.util.HashMap;
import java.util.List;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Steven Mirabá
 */
public class GrafoController implements Initializable {
    
    @FXML
    private Line lineVenCol,lineVenBra,lineEcuCol,lineBolPar,linePerChi,lineChiArg,lineUruArg,lineUruBra,lineColBra;
    @FXML
    private Line lineColPer,linePerBra,linePerBol,lineChiBol,lineArgBol,lineParBra,lineArgBra,lineEcuPer,lineParArg,lineBraBol;
    @FXML
    private Circle vecVEN,vecPAR,vecCO,vecECU,vecPER,vecCHI,vecAR,vecBOL,vecBRA,vecURU;
    @FXML
    private Label LVEN,LCOL,LECU,LPER,LCHI,LARG,LBOL,LPAR,LURU,LBRA;
    @FXML
    private ImageView imgAlg;
    @FXML
    private Pane panePrincipal;
    @FXML 
    private Rectangle recDis;
    int[][] distancias = {
            {0, 1023, 0, 0, 0, 0, 0, 0, 0, 3597},
            {1023, 0, 734, 1885, 0, 0, 0, 0, 0, 2666},
            {0, 734, 0, 1324, 0, 0, 0, 0, 0, 0},
            {0, 1885, 1324, 0, 2466, 0, 1077, 0, 0, 3171},
            {0, 0, 0, 2466, 0, 1138, 1900, 0, 0, 0},
            {0, 0, 0, 0, 1138, 0, 2237, 1040, 202, 2340},
            {0, 0, 0, 1077, 1900, 2237, 0, 1464, 0, 2165},
            {0, 0, 0, 0, 0, 1040, 1464, 0, 0, 1463},
            {0, 0, 0, 0, 0, 202, 0, 0, 0, 2770},
            {3597, 2666, 0, 3171, 0, 2340, 2165, 1463, 2770, 0}
        };
    Map<String,Line> aristas = new HashMap<String,Line>(){{
        put("01",lineVenCol);put("09",lineVenBra);put("12",lineEcuCol);put("13",lineColPer);put("19",lineColBra);
        put("23",lineEcuPer);put("39",linePerBra);put("36",linePerBol);put("34",linePerChi);put("45",lineChiArg);
        put("46",lineChiBol);put("59",lineArgBra);put("56",lineArgBol);put("57",lineParArg);put("58",lineUruArg);
        put("89",lineUruBra);put("67",lineBolPar);put("79",lineParBra);put("69",lineBraBol);
        
    }};
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
    private void reset(MouseEvent event) throws IOException {
        App.cambiarPantalla("grafo", 1000, 600, event);
    }
    
    @FXML
    private void prim(MouseEvent event) throws IOException {
        vecVEN.setFill(Color.LIGHTCORAL);
        lineVenCol.setStroke(Color.RED);vecCO.setFill(Color.LIGHTCORAL);
        lineEcuCol.setStroke(Color.RED);vecECU.setFill(Color.LIGHTCORAL);
        lineEcuPer.setStroke(Color.RED);vecPER.setFill(Color.LIGHTCORAL);
        linePerBol.setStroke(Color.RED);vecBOL.setFill(Color.LIGHTCORAL);
        lineBolPar.setStroke(Color.RED);vecPAR.setFill(Color.LIGHTCORAL);
        lineParBra.setStroke(Color.RED);vecBRA.setFill(Color.LIGHTCORAL);
        lineParArg.setStroke(Color.RED);vecAR.setFill(Color.LIGHTCORAL);
        lineChiArg.setStroke(Color.RED);vecCHI.setFill(Color.LIGHTCORAL);
        lineUruArg.setStroke(Color.RED);vecURU.setFill(Color.LIGHTCORAL);
        lineVenBra.setStroke(Color.LIGHTGRAY);lineBraBol.setStroke(Color.LIGHTGRAY);lineColBra.setStroke(Color.LIGHTGRAY);lineUruBra.setStroke(Color.LIGHTGRAY);
        linePerBra.setStroke(Color.LIGHTGRAY);lineArgBra.setStroke(Color.LIGHTGRAY);lineColPer.setStroke(Color.LIGHTGRAY);linePerChi.setStroke(Color.LIGHTGRAY);
        lineChiBol.setStroke(Color.LIGHTGRAY);lineArgBol.setStroke(Color.LIGHTGRAY);
    }
    
    @FXML
    private void dijkstra(MouseEvent event) throws IOException{
        Stage stage = (Stage) lineChiArg.getScene().getWindow();
        stage.setWidth(1300);
        recDis.setFill(Color.web("#1f93ff00"));
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
    
    @FXML
    private void bruteForce(MouseEvent event) throws IOException{
        vecVEN.setFill(Color.LIGHTCORAL);vecCO.setFill(Color.LIGHTCORAL);vecECU.setFill(Color.LIGHTCORAL);vecPER.setFill(Color.LIGHTCORAL);vecBOL.setFill(Color.LIGHTCORAL);
        vecPAR.setFill(Color.LIGHTCORAL);vecURU.setFill(Color.LIGHTCORAL);vecAR.setFill(Color.LIGHTCORAL);vecCHI.setFill(Color.LIGHTCORAL);vecBRA.setFill(Color.LIGHTCORAL);
        BruteForceTSPAlgorithm ciclomin=new BruteForceTSPAlgorithm(distancias);
        List<Integer> listVertices = ciclomin.hamiltonGraph();
        List<String> conexiones = new ArrayList<>();
        for (int i = 0; i < listVertices.size() - 1; i++) {
            String combinacion = listVertices.get(i) + "" + listVertices.get(i + 1);
            conexiones.add(combinacion);
        conexiones.add(listVertices.get(listVertices.size() - 1) + "0");
        for (String c:conexiones){
            Line con=aristas.get(c);
            con.setFill(Color.RED);
        
        }
        }
    }
}
