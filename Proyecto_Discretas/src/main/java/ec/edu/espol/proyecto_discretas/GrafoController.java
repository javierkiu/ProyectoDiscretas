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
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
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
    private Pane paneGrafo;
    @FXML
    private Pane panePrincipal;
    @FXML
    private Rectangle recG1;
    @FXML
    private Rectangle recG2;
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
    private void prim(MouseEvent event) throws IOException {
        Stage stage = (Stage) lineChiArg.getScene().getWindow();
        stage.setWidth(1300);
        paneGrafo.getChildren().clear();
        if(panePrincipal.getChildren().contains(recG1)){
            panePrincipal.getChildren().remove(recG1);
        }    
        LCOL.setText("");LVEN.setText("");LECU.setText("");LPER.setText("");LCHI.setText("");LARG.setText("");
        LBOL.setText("");LARG.setText("");LPAR.setText("");LURU.setText("");
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
        Circle ven = new Circle(175, 35, 15);ven.setFill(Color.LIGHTCORAL);ven.setStroke(Color.BLACK);
        Circle bog = new Circle(175, 100, 15);bog.setFill(Color.LIGHTCORAL);bog.setStroke(Color.BLACK);
        Circle ecu = new Circle(175, 175, 15);ecu.setFill(Color.LIGHTCORAL);ecu.setStroke(Color.BLACK);
        Circle per = new Circle(175, 250, 15);per.setFill(Color.LIGHTCORAL);per.setStroke(Color.BLACK);
        Circle bol = new Circle(175, 325, 15);bol.setFill(Color.LIGHTCORAL);bol.setStroke(Color.BLACK);
        Circle par = new Circle(175, 400, 15);par.setFill(Color.LIGHTCORAL);par.setStroke(Color.BLACK);
        Circle bra = new Circle(75, 475, 15);bra.setFill(Color.WHITE);bra.setStroke(Color.BLACK);
        Circle arg = new Circle(275, 475, 15);arg.setFill(Color.LIGHTCORAL);arg.setStroke(Color.BLACK);
        Circle chi = new Circle(175, 550, 15);chi.setFill(Color.WHITE);chi.setStroke(Color.BLACK); 
        Circle uru = new Circle(375, 550, 15);uru.setFill(Color.LIGHTCORAL);uru.setStroke(Color.BLACK);
        Line venBog = new Line(ven.getCenterX(), ven.getCenterY()+ 15, bog.getCenterX(), bog.getCenterY()- 15);
        Line bogEcu = new Line(bog.getCenterX(), bog.getCenterY()+ 15, ecu.getCenterX(), ecu.getCenterY()- 15);
        Line bogPer = new Line(ecu.getCenterX(), ecu.getCenterY()+ 15, per.getCenterX(), per.getCenterY()- 15);
        Line perBol = new Line(per.getCenterX(), per.getCenterY()+ 15, bol.getCenterX(), bol.getCenterY()- 15);
        Line braPar = new Line(bol.getCenterX(), bol.getCenterY()+ 15, par.getCenterX(), par.getCenterY()- 15);
        Line venBra = new Line(par.getCenterX(), par.getCenterY()+ 15, bra.getCenterX(), bra.getCenterY()- 15);
        Line braArg = new Line(par.getCenterX(), par.getCenterY()+ 15, arg.getCenterX(), arg.getCenterY()- 15);
        Line perChi = new Line(arg.getCenterX(), arg.getCenterY()+ 15, chi.getCenterX(), chi.getCenterY()- 15);
        Line argUru = new Line(arg.getCenterX(), arg.getCenterY()+ 15, uru.getCenterX(), uru.getCenterY()- 15);
        Text venText = new Text(ven.getCenterX() - 10, ven.getCenterY() - 20, "VEN");
        Text bogText = new Text(bog.getCenterX() - 10, bog.getCenterY() - 25, "BOG");
        Text braText = new Text(bra.getCenterX() - 10, bra.getCenterY() - 25, "BRA");
        Text ecuText = new Text(ecu.getCenterX() - 10, ecu.getCenterY() - 25, "ECU");
        Text perText = new Text(per.getCenterX() - 10, per.getCenterY() - 25, "PER");
        Text chiText = new Text(chi.getCenterX() - 10, chi.getCenterY() - 25, "CHI");
        Text bolText = new Text(bol.getCenterX() - 10, bol.getCenterY() - 25, "BOL");
        Text parText = new Text(par.getCenterX() - 10, par.getCenterY() - 25, "PAR");
        Text argText = new Text(arg.getCenterX() - 10, arg.getCenterY() - 25, "ARG");
        Text uruText = new Text(uru.getCenterX() - 10, uru.getCenterY() - 25, "URU");
        String rutaImagen = "/img/flag.png";
        Image flag = new Image(getClass().getResourceAsStream(rutaImagen));
        //Image flag = new Image("f:src/main/resources/img/flag.png");
        ImageView imageV1 = new ImageView(flag);imageV1.setLayoutX(bog.getCenterX()+20);imageV1.setLayoutY(bog.getCenterY());
        ImageView imageV2 = new ImageView(flag);imageV2.setLayoutX(ecu.getCenterX()+20);imageV2.setLayoutY(ecu.getCenterY());
        ImageView imageV3 = new ImageView(flag);imageV3.setLayoutX(per.getCenterX()+20);imageV3.setLayoutY(per.getCenterY());        
        ImageView imageV4 = new ImageView(flag);imageV4.setLayoutX(bol.getCenterX()+20);imageV4.setLayoutY(bol.getCenterY());        
        ImageView imageV5 = new ImageView(flag);imageV5.setLayoutX(par.getCenterX()+20);imageV5.setLayoutY(par.getCenterY());       
        ImageView imageV6 = new ImageView(flag);imageV6.setLayoutX(arg.getCenterX()+20);imageV6.setLayoutY(arg.getCenterY());      
        ImageView imageV7 = new ImageView(flag);imageV7.setLayoutX(uru.getCenterX()+20);imageV7.setLayoutY(uru.getCenterY());
        PrimAlgorithm minD = new PrimAlgorithm(distancias);
        minD.construirMST(0);
        String mensaje="La distancia recorrida fue de: "+String.valueOf(minD.calcularCaminoEnMST(0, 8)+" km");
        Text distMin = new Text(uru.getCenterX() - 250, uru.getCenterY() + 30, mensaje);
        paneGrafo.getChildren().addAll(ven, bog, bra, ecu, per, chi, bol, par, arg, uru);
        paneGrafo.getChildren().addAll(venBog, venBra, bogEcu, bogPer, perChi, perBol, braPar, braArg, argUru);
        paneGrafo.getChildren().addAll(venText, bogText, braText, ecuText, perText, chiText, bolText, parText, argText, uruText,distMin);
        //paneGrafo.getChildren().addAll(imageV1, imageV2, imageV3, imageV4, imageV5, imageV6, imageV7);
        if(!(panePrincipal.getChildren().contains(recG2) || panePrincipal.getChildren().contains(recG1))){
            App.mostrarPopup("ganador",event);
        }
    }
    
    @FXML
    private void dijkstra(MouseEvent event) throws IOException{
        Stage stage = (Stage) lineChiArg.getScene().getWindow();
        stage.setWidth(1300);
        paneGrafo.getChildren().clear();
        if(panePrincipal.getChildren().contains(recG2)){
            panePrincipal.getChildren().remove(recG2);
        }   
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
        lineBraBol.setStroke(Color.LIGHTGRAY);lineColBra.setStroke(Color.LIGHTGRAY);lineUruBra.setStroke(Color.LIGHTGRAY);
        linePerBra.setStroke(Color.LIGHTGRAY);lineChiBol.setStroke(Color.LIGHTGRAY);lineArgBol.setStroke(Color.LIGHTGRAY);
        lineChiArg.setStroke(Color.LIGHTGRAY);lineParArg.setStroke(Color.LIGHTGRAY);lineEcuPer.setStroke(Color.LIGHTGRAY);lineBolPar.setStroke(Color.LIGHTGRAY);
        Circle ven = new Circle(250, 50, 15);ven.setFill(Color.LIGHTCORAL);ven.setStroke(Color.BLACK);
        Circle bog = new Circle(150, 150, 15);bog.setFill(Color.WHITE);bog.setStroke(Color.BLACK);
        Circle bra = new Circle(350, 150, 15);bra.setFill(Color.LIGHTCORAL);bra.setStroke(Color.BLACK);
        Circle ecu = new Circle(100, 250, 15);ecu.setFill(Color.WHITE);ecu.setStroke(Color.BLACK);
        Circle per = new Circle(200, 250, 15);per.setFill(Color.WHITE);per.setStroke(Color.BLACK);
        Circle chi = new Circle(150, 350, 15);chi.setFill(Color.WHITE);chi.setStroke(Color.BLACK);
        Circle bol = new Circle(250, 350, 15);bol.setFill(Color.WHITE);bol.setStroke(Color.BLACK);
        Circle par = new Circle(300, 250, 15);par.setFill(Color.WHITE);par.setStroke(Color.BLACK);
        Circle arg = new Circle(400, 250, 15);arg.setFill(Color.LIGHTCORAL);arg.setStroke(Color.BLACK);
        Circle uru = new Circle(400, 350, 15);uru.setFill(Color.LIGHTCORAL);uru.setStroke(Color.BLACK);
        Line venBog = new Line(ven.getCenterX(), ven.getCenterY()+ 15, bog.getCenterX(), bog.getCenterY()- 15);
        Line venBra = new Line(ven.getCenterX(), ven.getCenterY()+ 15, bra.getCenterX(), bra.getCenterY()- 15);
        Line bogEcu = new Line(bog.getCenterX(), bog.getCenterY()+ 15, ecu.getCenterX(), ecu.getCenterY()- 15);
        Line bogPer = new Line(bog.getCenterX(), bog.getCenterY()+ 15, per.getCenterX(), per.getCenterY()- 15);
        Line perChi = new Line(per.getCenterX(), per.getCenterY()+ 15, chi.getCenterX(), chi.getCenterY()- 15);
        Line perBol = new Line(per.getCenterX(), per.getCenterY()+ 15, bol.getCenterX(), bol.getCenterY()- 15);
        Line braPar = new Line(bra.getCenterX(), bra.getCenterY()+ 15, par.getCenterX(), par.getCenterY()- 15);
        Line braArg = new Line(bra.getCenterX(), bra.getCenterY()+ 15, arg.getCenterX(), arg.getCenterY()- 15);
        Line argUru = new Line(arg.getCenterX(), arg.getCenterY()+ 15, uru.getCenterX(), uru.getCenterY()- 15);
        Text venText = new Text(ven.getCenterX() - 10, ven.getCenterY() - 25, "VEN");
        Text bogText = new Text(bog.getCenterX() - 10, bog.getCenterY() - 25, "BOG");
        Text braText = new Text(bra.getCenterX() - 10, bra.getCenterY() - 25, "BRA");
        Text ecuText = new Text(ecu.getCenterX() - 10, ecu.getCenterY() - 25, "ECU");
        Text perText = new Text(per.getCenterX() - 10, per.getCenterY() - 25, "PER");
        Text chiText = new Text(chi.getCenterX() - 10, chi.getCenterY() - 25, "CHI");
        Text bolText = new Text(bol.getCenterX() - 10, bol.getCenterY() - 25, "BOL");
        Text parText = new Text(par.getCenterX() - 10, par.getCenterY() - 25, "PAR");
        Text argText = new Text(arg.getCenterX() - 10, arg.getCenterY() - 25, "ARG");
        Text uruText = new Text(uru.getCenterX() - 10, uru.getCenterY() - 25, "URU"); 
        DijkstraAlgorithm minD = new DijkstraAlgorithm(distancias);
        minD.construirArbol(0);
        String mensaje="La distancia recorrida fue de: "+String.valueOf(minD.calcularSumaDePesos(0,8)+" km");
        Text distMin = new Text(uru.getCenterX() - 300, uru.getCenterY() + 40, mensaje);
        paneGrafo.getChildren().addAll(ven, bog, bra, ecu, per, chi, bol, par, arg, uru);
        paneGrafo.getChildren().addAll(venBog, venBra, bogEcu, bogPer, perChi, perBol, braPar, braArg, argUru);
        paneGrafo.getChildren().addAll(venText, bogText, braText, ecuText, perText, chiText, bolText, parText, argText, uruText,distMin);
        
        if(!(panePrincipal.getChildren().contains(recG2) || panePrincipal.getChildren().contains(recG1))){
            App.mostrarPopup("ganador",event);
        }
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
