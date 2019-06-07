package ihm;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import transforms.elementaires.Homothetie;

public class HomothetieParam {

	static double X;
	static double Y;

	public static boolean invalidPoint(TextField input1, TextField input2) {

		try {
			X = Double.parseDouble(input1.getText());
			Y = Double.parseDouble(input2.getText());
			return true;
		}catch(NumberFormatException e) {
			return false;
		}

	}

	public static void display() {
		Stage stage = new Stage();

		Label label = new Label("Veuillez entrer les coordonées");

		Button valider = new Button("Valider");
		Button retour = new Button("Retour");

		TextField coordX = new TextField();
		TextField coordY = new TextField();
		TextField scale = new TextField();

		coordX.setPromptText("Valeur de X :");
		coordY.setPromptText("Valeur de Y :");
		scale.setPromptText("Valeur du facteur :");


		valider.setOnAction(e -> {
				try {
					invalidPoint(coordX, coordY);
					double setScale = Double.parseDouble(scale.getText());
					Controller.transfo.add(new Homothetie(setScale, X, Y));
					stage.close();
				}catch(NumberFormatException error) {
					System.out.println("Saisie incorrecte, veuillez entrer des valeurs numériques");
				}
		});

		retour.setOnAction(e -> { 
			stage.close();
		});

		VBox layout = new VBox();
		layout.getChildren().addAll(label, coordX, coordY, scale, valider, retour);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Homothétie");
		stage.getIcons().add(new Image("File:ressources/param.png"));
		stage.setMinWidth(350);
		stage.setMinHeight(150);
		stage.setScene(scene);
		stage.showAndWait();
		}
}
