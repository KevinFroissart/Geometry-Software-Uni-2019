package ihm;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AfficherAide {
		
public static void popUp() {
		
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Aide");
		window.getIcons().add(new Image("File:ressources/aide.png"));
		window.setMinWidth(350);
		window.setMinHeight(150);
		
		Label aide = new Label();
		aide.setText("Guide d'utilisation :\n\n"
				+ "- Pour commencer une composition, il faut ajouter un motif grâce au bouton prévu à cet effet.\n\n"
				+ "- Vous pouvez ajouter une ou plusieurs transformations qui apraîtrons dans l'ordre dans la liste"
				+ " de droite.\n\n"
				+ "- Vous pouvez également les modifier en cliquant sur le bouton du même nom, ou bien les supprimer \n"
				+ "  ou encore changer leur ordre d'animation à l'aide des flèches 'haut' et 'bas'. \n\n"
				+ "- Pour animer votre composition cliquez sur 'Lancer'. \n\n"
				+ "- Le bouton 'Reset' permet de réinitialiser votre composition.\n\n"
				+ "- Vous avez la possibilité de vous déplacer sur la grille avec un drag de la souris (clic gauche)\n"
				+ "  et de zoomer/dézoomer"
				+ " grâce à la molette ou aux boutons en bas à gauche de la fenêtre");
		
		
		
		Button fermer = new Button("Fermer");
		fermer.setOnAction(e -> window.close());
		
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(15,50,20,50));
		VBox.setMargin(aide, new Insets(10,10,10,10));
		layout.getChildren().addAll(aide, fermer);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
}