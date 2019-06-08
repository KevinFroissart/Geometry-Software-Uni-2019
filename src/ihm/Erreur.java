package ihm;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Erreur {

	public static void popUp(String titre, String msg) {
		Stage layout = new Stage();
		VBox root = new VBox();
		Label msgLabel = new Label(msg);
		Button ok = new Button("OK");
		
		ok.setOnMouseClicked(e -> {
			layout.close();
		});
		
		ok.setOnKeyPressed(e -> { 
			if(e.getCode().equals(KeyCode.ENTER)) {
				RotationParam.res = false;
				layout.close();
			}
		});
		
		root.getChildren().addAll(msgLabel, ok);
		root.setAlignment(Pos.CENTER);
		Scene stageScene = new Scene(root);
		VBox.setMargin(ok, new Insets(10,0,0,0));
		layout.setScene(stageScene);
		layout.initModality(Modality.APPLICATION_MODAL);
		layout.setHeight(100);
		layout.setWidth(400);
		layout.setTitle(titre);
		layout.getIcons().add(new Image("File:ressources/error.png"));
		layout.setResizable(false);
		layout.show();
	}
}