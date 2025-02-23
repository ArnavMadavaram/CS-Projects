package lab;

/**
 * Name: Rishigesh Rajendrakumar
 * Username: rajer03
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/lab/main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(App.class.getResource("/lab/style.css").toExternalForm());
        stage.setTitle("Book Store");
        stage.setScene(scene);
        stage.show();
    }

    // Main method to launch the application
    public static void main(String[] args) {
        launch(args);
    }
}
