package ihm;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import transforms.Composition;
import transforms.LibraryException;
import transforms.elementaires.Transformation;
import transforms.mobile.GrilleAdaptable;
import transforms.mobile.Maison;
import transforms.mobile.Motif;

public class Controller  {

	@FXML
	static
	BorderPane borderPane;

	@FXML
	static
	Pane paneGrid;

	@FXML
	ToolBar upToolBar,downToolBar;

	@FXML
	Button lancer,reset,addMotif,translation,rotation,homothetie,aide;

	@FXML
	static Button zoomPlus;

	@FXML
	static
	Button zoomMoins;

	@FXML
	Label zoomLabel,labelA,labelC;

	@FXML
	Accordion accordion;

	@FXML
	TitledPane compoPane,matrixPane;

	@FXML
	ListView listCompo,matrixA,matrixC;

	private static Composition composition;
	private ArrayList<Boolean> display = new ArrayList<>(Arrays.asList(true));
	private ArrayList<Transformation> transfo = new ArrayList<>();
	private Motif motif;
	private Color couleur;
	private static int zoom;
	private static GrilleAdaptable grille;

	public void doAjouterMotif(ActionEvent actionEvent) {
		Motif maison = new Maison(composition);
		composition.setMotif(maison);
	}

	public void doLancer(ActionEvent actionEvent) {
		final int firstStep = 0;
		final int lastStep = display.size()-1;
		try {
			motif = composition.getStep(firstStep);
			motif.setStroke(couleur);
			paneGrid.getChildren().add(motif.toGroup());
			Timeline tl = composition.animate(
					motif.toGroup(),
					firstStep,
					lastStep,
					e -> paneGrid.getChildren().remove(motif.toGroup())
					);
			tl.play();
			tl.setOnFinished(e-> {
				lancer.setDisable(false);
			});    
		} catch (LibraryException e) {
			e.printStackTrace();
		}
	}

	public void doReset(ActionEvent actionEvent) {		    	
		composition.getSequence().clear();
		paneGrid.getChildren().clear();
		paneGrid.getChildren().add(composition.getGrille(paneGrid));
		matrixA.getItems().clear();
		matrixC.getItems().clear();
		display.clear();
		display.add(true);
	}

	public void doTranslation(ActionEvent actionEvent) {

	}

	public void doRotation(ActionEvent actionEvent) {

	}

	public void doHomothetie(ActionEvent actionEvent) {

	}

	public void doZoomPlus(ActionEvent actionEvent) {
		if(zoom < 90) zoom += 5 + zoom/25.25;
		composition.setZoom(zoom, composition.getOffsetX(), composition.getOffsetY());
	}

	public void doZoomMoins(ActionEvent actionEvent) {
		if(zoom > 15) zoom -= 5 + zoom/15.25;
		composition.setZoom(zoom, composition.getOffsetX(), composition.getOffsetY());
	}

	public void initialize() {
		paneGrid.setStyle("-fx-background-color: AliceBlue; -fx-border-width: 2");
		grille = new GrilleAdaptable(composition, paneGrid, 1, 1);
		paneGrid.getChildren().add(grille);
	}

}