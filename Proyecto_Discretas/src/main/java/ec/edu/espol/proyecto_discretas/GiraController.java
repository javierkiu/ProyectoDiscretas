/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyecto_discretas;

import Grafos.BruteForceTSPAlgorithm;
import Grafos.NearestNeighborTSPAlgorithm;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 * FXML Controller class
 *
 * @author Steven Mirabá
 */
public class GiraController implements Initializable {
    @FXML
    private Line lineVenCol,lineVenBra,lineEcuCol,lineBolPar,linePerChi,lineChiArg,lineUruArg,lineUruBra,lineColBra;
    @FXML
    private Line lineColPer,linePerBra,linePerBol,lineChiBol,lineArgBol,lineParBra,lineArgBra,lineEcuPer,lineParArg,lineBraBol;
    @FXML
    private Circle vecVEN,vecPAR,vecCO,vecECU,vecPER,vecCHI,vecAR,vecBOL,vecBRA,vecURU;
    @FXML
    private Label orden,costo;
    int[][] costos = {
            {0, 450, 0, 0, 0, 0, 0, 0, 0, 1822},
            {450, 0, 405, 421, 0, 0, 0, 0, 0, 1957},
            {0, 405, 0, 526, 0, 0, 0, 0, 0, 0},
            {0, 421, 526, 0, 1380, 0, 1159, 0, 0, 1761},
            {0, 0, 0, 1380, 0, 1671, 1123, 0, 0, 0},
            {0, 0, 0, 0, 1671, 0, 1319, 1604, 1484, 2258},
            {0, 0, 0, 1159, 1123, 1319, 0, 968, 0, 1948},
            {0, 0, 0, 0, 0, 1604, 968, 0, 0, 1623},
            {0, 0, 0, 0, 0, 1484, 0, 0, 0, 1240},
            {1822, 1957, 0, 1761, 0, 2258, 1948, 1623, 1240, 0}
    };
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
    private void nearestNeighbor(MouseEvent event) throws IOException {
        App.actualizarEscena("gira");
        vecVEN.setFill(Color.LIGHTCORAL);vecCO.setFill(Color.LIGHTCORAL);vecECU.setFill(Color.LIGHTCORAL);vecPER.setFill(Color.LIGHTCORAL);
        vecBOL.setFill(Color.LIGHTCORAL);vecPAR.setFill(Color.LIGHTCORAL);vecURU.setFill(Color.LIGHTCORAL);vecAR.setFill(Color.LIGHTCORAL);
        vecCHI.setFill(Color.LIGHTCORAL);vecBRA.setFill(Color.LIGHTCORAL);
        NearestNeighborTSPAlgorithm nearestNeighbor = new NearestNeighborTSPAlgorithm(costos);
        List<Integer> listVertices = nearestNeighbor.hamiltonGraph();
        listVertices.add(0);
        System.out.println(listVertices);
        String ordenV = "orden:    VEN -> COL -> ECU -> PER -> BOL -> PAR -> ARG -> URU -> BRA";
        Integer costoTotal = nearestNeighbor.calcularPeso(listVertices);
        String mensaje = "El costo total es: $"+String.valueOf(costoTotal);
        orden.setText(ordenV);
        costo.setText(mensaje);
        lineVenCol.setStroke(Color.RED);lineEcuCol.setStroke(Color.RED);lineEcuPer.setStroke(Color.RED);linePerBol.setStroke(Color.RED);
        lineBolPar.setStroke(Color.RED);lineParArg.setStroke(Color.RED);lineUruArg.setStroke(Color.RED);
        lineUruBra.setStroke(Color.RED);lineVenBra.setStroke(Color.RED);
        lineColPer.setStroke(Color.web("#B3B3B3"));lineColBra.setStroke(Color.web("#B3B3B3"));lineBraBol.setStroke(Color.web("#B3B3B3"));
        linePerBra.setStroke(Color.web("#B3B3B3"));linePerChi.setStroke(Color.web("#B3B3B3"));lineChiArg.setStroke(Color.web("#B3B3B3"));
        lineArgBra.setStroke(Color.web("#B3B3B3"));lineArgBol.setStroke(Color.web("#B3B3B3"));lineParBra.setStroke(Color.web("#B3B3B3"));lineChiBol.setStroke(Color.web("#B3B3B3"));
    }
    
    @FXML
    private void bruteForce(MouseEvent event) throws IOException {
        App.actualizarEscena("gira");
        vecVEN.setFill(Color.LIGHTCORAL);vecCO.setFill(Color.LIGHTCORAL);vecECU.setFill(Color.LIGHTCORAL);vecPER.setFill(Color.LIGHTCORAL);
        vecBOL.setFill(Color.LIGHTCORAL);vecPAR.setFill(Color.LIGHTCORAL);vecURU.setFill(Color.LIGHTCORAL);vecAR.setFill(Color.LIGHTCORAL);
        vecCHI.setFill(Color.LIGHTCORAL);vecBRA.setFill(Color.LIGHTCORAL);
        BruteForceTSPAlgorithm bruteForc = new BruteForceTSPAlgorithm(costos);
        List<Integer> listVertices = bruteForc.hamiltonGraph();
        //listVertices.add(0);
        System.out.println(listVertices);
        String ordenV = "orden:    VEN -> COL -> ECU -> PER -> CHI -> BOL -> PAR -> ARG -> URU -> BRA";
        Integer costoTotal = bruteForc.calcularPeso(listVertices);
        String mensaje = "El costo total es: $"+String.valueOf(costoTotal);
        orden.setText(ordenV);
        costo.setText(mensaje);
        lineVenCol.setStroke(Color.RED);lineEcuCol.setStroke(Color.RED);lineEcuPer.setStroke(Color.RED);lineChiBol.setStroke(Color.RED);
        lineBolPar.setStroke(Color.RED);lineParArg.setStroke(Color.RED);lineUruArg.setStroke(Color.RED);
        lineUruBra.setStroke(Color.RED);lineVenBra.setStroke(Color.RED);linePerChi.setStroke(Color.RED);
        lineColPer.setStroke(Color.web("#B3B3B3"));lineColBra.setStroke(Color.web("#B3B3B3"));lineBraBol.setStroke(Color.web("#B3B3B3"));
        linePerBra.setStroke(Color.web("#B3B3B3"));lineChiArg.setStroke(Color.web("#B3B3B3"));linePerBol.setStroke(Color.web("#B3B3B3"));
        lineArgBra.setStroke(Color.web("#B3B3B3"));lineArgBol.setStroke(Color.web("#B3B3B3"));lineParBra.setStroke(Color.web("#B3B3B3"));
    }
    
}
