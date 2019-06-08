package ihm;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import transforms.elementaires.Rotation;

public class RotationParam {

	public static boolean res = false;
	public static double x,y,doubleAngle;
	
	public static boolean display() {
		Stage stage = new Stage();

		Label label = new Label("Veuillez entrer les coordonnées");

		Button valider = new Button("Valider");
		Button retour = new Button("Retour");

		TextField coordX = new TextField();
		TextField coordY = new TextField();
		TextField angle = new TextField();

		coordX.setMaxWidth(150);
		coordY.setMaxWidth(150);
		angle.setMaxWidth(150);

		coordX.setPromptText("Valeur de X :");
		coordY.setPromptText("Valeur de Y :");
		angle.setPromptText("Valeur de l'angle :");


		valider.setOnAction(e -> {
			if(!coordX.getText().isEmpty() && !coordY.getText().isEmpty() && !angle.getText().isEmpty() && 	doubleInt(coordX, coordY, angle)) {
				try {
					Controller.transfo.add(new Rotation(doubleAngle, x, y));
					stage.close();
					RotationParam.res = true;
				}catch(NumberFormatException error) {
					System.out.println("Saisie incorrecte, veuillez entrer des valeurs numériques");
				}
			} else Erreur.popUp("Aucune saisie", "Veuillez saisir des valeurs numériques");
		});

		valider.setOnKeyPressed(e -> {
			if(e.getCode().equals(KeyCode.ENTER)) {
				if(!coordX.getText().isEmpty() && !coordY.getText().isEmpty() && !angle.getText().isEmpty() && 	doubleInt(coordX, coordY, angle)) {
					try {
						Controller.transfo.add(new Rotation(doubleAngle, x, y));
						stage.close();
						RotationParam.res = true;
					}catch(NumberFormatException error) {
						System.out.println("Saisie incorrecte, veuillez entrer des valeurs numériques");
					}
				} else Erreur.popUp("Aucune saisie", "Veuillez saisir des valeurs numériques");
			}
		});

		retour.setOnAction(e -> { 
			RotationParam.res = false;
			stage.close();
		});
		
		retour.setOnKeyPressed(e -> { 
			if(e.getCode().equals(KeyCode.ENTER)) {
				RotationParam.res = false;
				stage.close();
			}
		});

		VBox layout = new VBox();
		layout.getChildren().addAll(label, coordX, coordY, angle, valider, retour);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Rotation");
		stage.getIcons().add(new Image("File:ressources/param.png"));
		stage.setMinWidth(250);
		stage.setMinHeight(150);
		stage.setResizable(false);
		stage.setScene(scene);
		stage.showAndWait();

		return RotationParam.res;
	}

	public static boolean doubleInt(TextField coordX, TextField coordY, TextField angle) {
		try {
			x = Double.parseDouble(coordX.getText());
			y = Double.parseDouble(coordY.getText());
			doubleAngle = Double.parseDouble(angle.getText());
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
	}

	/*public static boolean doubleInt(TextField coordX, TextField coordY, TextField scale) {
		boolean res = false;
		if(coordX.getText().matches("([0-9]*)\\.([0-9]*)") || coordX.getText().matches("[0-9]*")) {
			if(coordY.getText().matches("([0-9]*)\\.([0-9]*)") || coordY.getText().matches("[0-9]*")) {
				if(scale.getText().matches("([0-9]*)\\.([0-9]*)") || scale.getText().matches("[0-9]*")) {	res = true; }}}
		return res;
	}*/
}