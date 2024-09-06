package ec.edu.espol.proyecto_discretas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setResizable(false);
        scene = new Scene(loadFXML("primary"), 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    
    public static Stage cambiarPantalla(String fxml, double ancho, double alto, Event event) throws IOException {
        Stage newScreen = new Stage();
        Parent root = loadFXML(fxml);
        Scene newScene = new Scene(root, ancho , alto);
        newScreen.setScene(newScene);
        newScreen.setResizable(false);
        newScreen.show();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        return newScreen;
    }
    public static void mostrarPopup(String fxml, Event event) throws IOException {
        Parent popupRoot = loadFXML(fxml);
        Stage popupStage = new Stage();
        popupStage.setTitle("Ganador");
        Scene popupScene = new Scene(popupRoot);
        popupStage.setScene(popupScene);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        popupStage.initOwner(mainStage);
        popupStage.show();
    }
    
    public static void actualizarEscena(String fxml) throws IOException {
        Parent root = loadFXML(fxml);
        Scene currentScene = scene;
        currentScene.setRoot(root);
    }
}