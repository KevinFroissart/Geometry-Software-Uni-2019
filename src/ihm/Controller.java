package ihm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Controller extends Application {
	
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../ihm.fxml"));
        primaryStage.setTitle("IHM S2 FROISSART/BOURDIN");
        primaryStage.setScene(new Scene(root, 300, 600));
        primaryStage.getIcons().add(new Image("File:ressources/logo.png"));
        primaryStage.setWidth(1100.0);
        primaryStage.setHeight(800.0);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
}