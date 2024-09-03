package ec.edu.espol.proyecto_discretas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
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
    
    public static Stage cambiarPantalla(String fxml, double n, double m, MouseEvent me) throws IOException {
        Stage newScreen = new Stage();
        Parent root = loadFXML(fxml);
        Scene nuevaScene = new Scene(root, n, m);
        newScreen.setScene(nuevaScene);
        newScreen.setResizable(false);
        newScreen.show();
        Stage stage = (Stage) ((Node) me.getSource()).getScene().getWindow();
        stage.close();
        return newScreen;
    }
}