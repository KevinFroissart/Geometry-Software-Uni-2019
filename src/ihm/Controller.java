package ihm;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import sun.security.krb5.internal.PAEncTSEnc;
import transforms.Composition;
import transforms.LibraryException;
import transforms.elementaires.Transformation;
import transforms.mobile.GrilleAdaptable;
import transforms.mobile.Maison;
import transforms.mobile.Motif;

public class Controller  {

	@FXML
	static
	BorderPane broderPane;

	@FXML
	static
	Pane pane;

	@FXML
	ToolBar toolBarHaut,toolBarBas;

	@FXML
	Button bouttonLancer,bouttonReset,bouttonMotif,bouttonTranslation,bouttonRotation,bouttonHomothetie,bouttonPlus,bouttonMoins;

	@FXML
	Label zoomLabel;

	@FXML
	Accordion accordeon;

	@FXML
	TitledPane transfoList,matriceList;

	@FXML
	VBox vBoxDroite;

	private static Composition composition;
	/*private ArrayList<Boolean> display = new ArrayList<>(Arrays.asList(true));
	private ArrayList<Transformation> transfo = new ArrayList<>();
	private Motif motif;
	private Color couleur;
	private static int zoom;
	private static GrilleAdaptable grille;*/



	public void initialize() {
	/*	pane = new Pane();
		pane.prefHeight(320.0);
		pane.prefWidth(398.0);*/
		//BorderPane.setAlignment(pane, new Insets(10,10,10,10));
		composition = new Composition();
		composition.setZoom(30.0, 200.0, 200.0);
		//grille = new GrilleAdaptable(composition, pane, 1, 1);
		pane.getChildren().add(composition.getGrille(pane));
	}


	public void doLancer(ActionEvent actionEvent) {
		/*final int firstStep = 0;
		final int lastStep = display.size()-1;
		try {
			motif = composition.getStep(firstStep);
			motif.setStroke(couleur);
			pane.getChildren().add(motif.toGroup());
			Timeline tl = composition.animate(
					motif.toGroup(),
					firstStep,
					lastStep,
					e -> pane.getChildren().remove(motif.toGroup())
					);
			tl.play();
			tl.setOnFinished(e-> {
				bouttonLancer.setDisable(false);
			});    
		} catch (LibraryException e) {
			e.printStackTrace();
		}*/
	}

	public void doReset(ActionEvent actionEvent) {		    	
		/*composition.getSequence().clear();
		pane.getChildren().clear();
		pane.getChildren().add(composition.getGrille(pane));
		display.clear();
		display.add(true);*/
	}

	public void doAjouterMotif(ActionEvent actionEvent) {
		/*Motif maison = new Maison(composition);
		composition.setMotif(maison);*/
	}

	public void doTranslation(ActionEvent actionEvent) {
		//TranslationParam.display();
	}

	public void doRotation(ActionEvent actionEvent) {
		//RotationParam.display();
	}

	public void doHomothetie(ActionEvent actionEvent) {
		//HomothetieParam.display();
	}

	public void doZoomPlus(ActionEvent actionEvent) {
		/*if(zoom < 90) zoom += 5 + zoom/25.25;
		composition.setZoom(zoom, composition.getOffsetX(), composition.getOffsetY());*/
	}

	public void doZoomMoins(ActionEvent actionEvent) {
		/*if(zoom > 15) zoom -= 5 + zoom/15.25;
		composition.setZoom(zoom, composition.getOffsetX(), composition.getOffsetY());*/
	}

}