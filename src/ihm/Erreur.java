package ihm;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Erreur {

	public static void popUp(String titre, String msg) {
		Stage error = new Stage();
		BorderPane root = new BorderPane();
		Label msgLabel = new Label(msg);
		Button ok = new Button("OK");
		ok.setOnMouseClicked(e -> {
			error.close();
		});
		BorderPane.setAlignment(msgLabel, Pos.CENTER);
		BorderPane.setAlignment(ok, Pos.CENTER);
		root.setCenter(msgLabel);
		root.setBottom(ok);

		Scene errorScene = new Scene(root);
		error.setScene(errorScene);
		error.initModality(Modality.APPLICATION_MODAL);
		error.setHeight(100);
		error.setWidth(400);
		error.setTitle(titre);
		error.getIcons().add(new Image("File:ressources/error.png"));
		error.setResizable(false);
		error.show();
	}
}