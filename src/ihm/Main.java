package ihm;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import transforms.mobile.GrilleAdaptable;

public class Main extends Application{


	public static Stage stage;
	public static Scene scene;
	public static BorderPane root;
	public static Pane espaceTravail;
	private static GrilleAdaptable grille;
	private static CompositionPlus composition;


	public void start(Stage stage) {

		stage = new Stage();
		root = new BorderPane();

		initialisationMenuBoutons();
		initialisationParametres();
		initialisationMatrice();
		initialisationEspaceDeTravail();


		scene = new Scene(root,1050,850);
		stage.setScene(scene);
		stage.getIcons().add(new Image("file:ressources/logo.png"));
		stage.setTitle("Projet IHM2 FROISSART/BOURDIN 2019");
		stage.show();
	}

	public static void initialisationMenuBoutons() {
		VBox actions = new VBox();
		HBox actionTool = new HBox();


		Button lancer = new Button("Lancer");
		Button nouveau = new Button("Nouveau");
		Button translation = new Button("Translation");
		Button rotation = new Button("Rotation");
		Button homothetie = new Button("Homothetie");
		Button aide = new Button("aide");

		//VBox.setMargin(actionTool, new Insets(0,10,10,10));

		actionTool.setStyle("-fx-background-color: white; -fx-border-width: 0 0 2 0; -fx-border-color: lightgrey");
		actionTool.getChildren().addAll(lancer,nouveau,translation,rotation,homothetie,aide);

		actions.getChildren().add(actionTool);
		root.setTop(actions);
	}

	public static void initialisationParametres() {
		VBox parametres = new VBox();
		
		parametres.setStyle("-fx-background-color: white; -fx-border-width: 0 2 0 0; -fx-border-color: lightgrey");
		parametres.prefWidthProperty().bind(root.widthProperty().divide(6));
		
		root.setLeft(parametres);
	}

	public static void initialisationMatrice() {
		VBox matricesContainer = new VBox();
		
		matricesContainer.setStyle("-fx-background-color: white; -fx-border-width: 0 0 0 2; -fx-border-color: lightgrey");
		matricesContainer.prefWidthProperty().bind(root.widthProperty().divide(6));

		root.setRight(matricesContainer);
	}
	
	public static void initialisationEspaceDeTravail() {
		espaceTravail = new Pane();
		espaceTravail.setStyle("-fx-background-color: AliceBlue; -fx-border-width: 2");

		composition = new CompositionPlus();
		grille = new GrilleAdaptable(composition, espaceTravail, 1, 1);

		
		espaceTravail.getChildren().add(grille);
		
		root.setCenter(espaceTravail);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
