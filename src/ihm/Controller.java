package ihm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
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
	Pane pane;

	@FXML
	ToolBar toolBarHaut,toolBarBas;

	@FXML
	Button boutonLancer,boutonReset,boutonMotif,boutonTranslation,boutonRotation,boutonHomothetie,boutonPlus,boutonMoins;

	@FXML
	Label zoomLabel,labelMatriceC,labelMatriceA;

	@FXML
	Accordion accordeon;

	@FXML
	TitledPane transfoList,matriceList;

	@FXML
	ListView matriceA,matriceC;
	
	@FXML
	VBox vBoxDroite,vBoxMatrice;

	public static Composition composition;
	private List<Node> allNodes;
	private ArrayList<Boolean> display = new ArrayList<>(Arrays.asList(true));
	public static ArrayList<Transformation> transfo = new ArrayList<>();
	public static ArrayList<Maison> motifList = new ArrayList<>();
	private Motif motif;
	private Color couleur;
	private static double zoom = 30.0;
	private static GrilleAdaptable grille;
	private static int nbTransfo = 0;

	public void initialize() {
		pane.prefHeight(320.0);
		pane.prefWidth(398.0);
		composition = new Composition();
		composition.setZoom(zoom, 400.0, 342.5);
		grille = new GrilleAdaptable(composition, pane, 1, 1);
		pane.getChildren().add(composition.getGrille(pane));
		dragGrille();
	}

	public void doLancer(ActionEvent actionEvent) {
		if(nbTransfo > 0) {
			try {
				boutonLancer.setDisable(true);
				motif = composition.getStep(0);
				motif.setStroke(Color.BLUE);
				pane.getChildren().add(motif.toGroup());
				Timeline tl = composition.animate(
						motif.toGroup(),
						0,
						display.size()-1,
						e -> pane.getChildren().remove(motif.toGroup())
						);
				tl.play();
				tl.setOnFinished(e-> {
					boutonLancer.setDisable(false);
				});    
			} catch (LibraryException e) {
				e.printStackTrace();
			}
		} else Erreur.popUp("Aucune tranformation", "Ajoutez des transformations pour pouvoir les animer");
	}

	public void doReset(ActionEvent actionEvent) {		    	
		composition.getSequence().clear();
		pane.getChildren().clear();
		pane.getChildren().add(composition.getGrille(pane));
		transfo.clear();
		motifList.clear();
		display.clear();
		display.add(true);
		boutonLancer.setDisable(false);
		composition.setZoom(30.0, 400.0, 342.5);
		nbTransfo = 0;
	}

	public void doAjouterMotif(ActionEvent actionEvent) {
		Motif maison = new Maison(composition);
		motifList.add((Maison) maison);
		composition.setMotif(maison);
		try {
			allNodes = composition.draw(display);
		} catch (LibraryException e) {
			e.printStackTrace();
		}
		pane.getChildren().addAll(allNodes);
	}

	public void doTranslation(ActionEvent actionEvent) {
		if(!motifList.isEmpty()) {if(TranslationParam.display()) {doTransformation();}}
		else Erreur.popUp("Aucun motif", "Veuillez selectionner un motif");
	}

	public void doRotation(ActionEvent actionEvent) {
		if(!motifList.isEmpty()) {if(RotationParam.display()) {doTransformation();}}
		else Erreur.popUp("Aucun motif", "Veuillez selectionner un motif");
	}	

	public void doHomothetie(ActionEvent actionEvent) {
		if(!motifList.isEmpty()) {if(HomothetieParam.display()) {doTransformation();}}
		else Erreur.popUp("Aucun motif", "Veuillez selectionner un motif");
	}	

	public void doTransformation() {
		for (Transformation transfo : transfo) {
			composition.add((Transformation) transfo.getTransform());
			display.add(true);
			nbTransfo++;
		}
		try {
			allNodes = composition.draw(display);
		} catch (LibraryException e) {
			e.printStackTrace();
		}
		transfo.clear();
		pane.getChildren().addAll(allNodes);
		matriceA.getItems().clear();
		matriceA.getItems().addAll(composition.getSequence());
	}

	public void doZoomPlus(ActionEvent actionEvent) {
		if(zoom < 90) zoom += 5 + zoom/25.25;
		composition.setZoom(zoom, composition.getOffsetX(), composition.getOffsetY());
	}

	public void doZoomMoins(ActionEvent actionEvent) {
		if(zoom > 15) zoom -= 5 + zoom/15.25;
		composition.setZoom(zoom, composition.getOffsetX(), composition.getOffsetY());
	}

	public void dragGrille() {
		pane.setOnMouseDragged( e-> {
			composition.offsetXProperty().set(e.getX());
			composition.offsetYProperty().set(e.getY());
		});
	}
}