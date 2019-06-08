package ihm;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AfficherAide {
		
public static void popUp(String title, String message) {
		
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(350);
		window.setMinHeight(150);
		
		Label label = new Label();
		label.setText(message);
		
		Label aideTransformations = new Label();
		aideTransformations.setText("Transformations : - Permet d'ajouter une ou plusieurs transformations à effectuer sur la figure actuellement selectionné.");
		
		Label aideFigures = new Label();
		aideFigures.setText("Créer une figure : - Possibilité de créer une figure en ajoutant des points à l'aide de la souris en cliquant sur la grille.");
		
		Label aideZoom = new Label();
		aideZoom.setText("Zoom : - Permet de zoomer/dézoomer la grille.");
		
		Label aideAfficher = new Label();
		aideAfficher.setText("Afficher : - Permet d'afficher sur la grille la figure actuellement selectionné ainsi que les différentes transformations qui lui sont actuellement associées.");
		
		Label aideAnimer = new Label();
		aideAnimer.setText("Animer : - Permet d'animer la figure qui effectue les différentes transformations qui lui sont associées.");
		
		Label aideReset = new Label();
		aideReset.setText("Réinitialiser : - Permet de rétablir la figure par défaut (Maison avec porte) et d'effacer toutes les transformations actuellement associées à la figure.");
		
		Label aideMatrices = new Label();
		aideMatrices.setText("Matrices : - Permet d'afficher les matrices associées aux transformations voulues (de la transformation ? à ?)");
		
		Label aideColor = new Label();
		aideColor.setText("Couleur des transformations / Couleur des animations : - Permet de définir la couleur d'affichage des transformations / la couleur d'affichage des animations associées aux transformations");
		
		Button closeButton = new Button("Fermer l'aide");
		closeButton.setOnAction(e -> window.close());
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, aideFigures, aideColor, aideAfficher, aideAnimer, aideTransformations, aideMatrices, aideZoom, aideReset, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
}
