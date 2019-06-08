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
	static boolean res = false;

	public static boolean display() {
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
			if(!coordX.getText().isEmpty() && !coordY.getText().isEmpty() && !scale.getText().isEmpty() && doubleInt(coordX, coordY, scale)) {
				try {
					double x =Double.parseDouble(coordX.getText());
					double y =Double.parseDouble(coordY.getText());
					double setScale = Double.parseDouble(scale.getText());
					Controller.transfo.add(new Homothetie(setScale, x, y));
					stage.close();
					HomothetieParam.res = true;
				}catch(NumberFormatException error) {
					System.out.println("Saisie incorrecte, veuillez entrer des valeurs numériques");
				}
			} else Erreur.popUp("Aucune saisie", "Veuillez saisir des valeurs numériques");
		});

		retour.setOnAction(e -> { 
			RotationParam.res = false;
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

		return HomothetieParam.res;
	}

	public static boolean doubleInt(TextField coordX, TextField coordY, TextField scale) {
		boolean res = false;
		if(coordX.getText().matches("([0-9]*)\\.([0-9]*)") || coordX.getText().matches("[0-9]*")) {
		if(coordY.getText().matches("([0-9]*)\\.([0-9]*)") || coordY.getText().matches("[0-9]*")) {
		if(scale.getText().matches("([0-9]*)\\.([0-9]*)") || scale.getText().matches("[0-9]*")) {	res = true; }}}
		return res;
	}
}