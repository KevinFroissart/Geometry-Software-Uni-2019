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
import transforms.elementaires.Transformation;
import transforms.elementaires.Translation;

public class TranslationModif {

	static boolean res = false;
	public static Transformation modification;

	public static boolean display(Transformation transformation) {
		Stage stage = new Stage();

		Label label = new Label("Veuillez entrer les coordonées");

		Button valider = new Button("Valider");
		Button retour = new Button("Retour");

		TextField coordX = new TextField();
		TextField coordY = new TextField();

		coordX.setPromptText("Valeur de X :");
		coordY.setPromptText("Valeur de Y :");


		valider.setOnAction(e -> {
			if(!coordX.getText().isEmpty() && !coordY.getText().isEmpty() && doubleInt(coordX, coordY)) {
				try {
					double x =Double.parseDouble(coordX.getText());
					double y =Double.parseDouble(coordY.getText());
					modification = new Translation(x, y);
					stage.close();
					TranslationModif.res = true;
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
		layout.getChildren().addAll(label, coordX, coordY, valider, retour);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Translation");
		stage.getIcons().add(new Image("File:ressources/param.png"));
		stage.setMinWidth(350);
		stage.setMinHeight(150);
		stage.setScene(scene);
		stage.showAndWait();
		
		return res;
	}

	public static boolean doubleInt(TextField coordX, TextField coordY) {
		boolean res = false;
		if(coordX.getText().matches("([0-9]*)\\.([0-9]*)") || coordX.getText().matches("[0-9]*")) {
		if(coordY.getText().matches("([0-9]*)\\.([0-9]*)") || coordY.getText().matches("[0-9]*")) { res = true; }}
		return res;
	}
}