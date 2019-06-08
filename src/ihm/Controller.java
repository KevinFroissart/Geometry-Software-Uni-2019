package ihm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javafx.animation.Timeline;
import javafx.beans.binding.SetBinding;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
	ScrollPane scrollPaneTransfo;

	@FXML
	ToolBar toolBarHaut,toolBarBas;

	@FXML
	Button boutonLancer,boutonReset,boutonMotif,boutonTranslation,boutonRotation,boutonHomothetie,boutonPlus,boutonMoins,boutonModifier,boutonSupprimer,boutonHaut,boutonBas;

	@FXML
	Label zoomLabel,labelMatrice;

	@FXML
	Accordion accordeon;

	@FXML
	TitledPane transfoList,matriceList;

	@FXML
	ListView<Transformation> matriceA;

	@FXML
	VBox vBoxDroite,vBoxMatrice,vBoxListTransfo,vBoxBoutons;

	@FXML
	HBox hBoxBoutons;

	public static Composition composition;
	private List<Node> allNodes;
	private ArrayList<Boolean> display = new ArrayList<>(Arrays.asList(true));
	public static ArrayList<Transformation> transfo = new ArrayList<>();
	public static ArrayList<Maison> motifList = new ArrayList<>();
	private Motif motif;
	private Color couleur;
	private static double zoom = 30.0;
	private static GrilleAdaptable grille;
	public static int nbTransfo = 0;
	private static Button editer;
	private static Button supprimer;
	private static Transformation selection;

	public void initialize() {
		pane.prefHeight(320.0);
		pane.prefWidth(398.0);
		composition = new Composition();
		composition.setZoom(zoom, 400.0, 342.5);
		grille = new GrilleAdaptable(composition, pane, 1, 1);
		pane.getChildren().add(composition.getGrille(pane));
		vBoxDroite.setStyle("-fx-border-width: 0.5; -fx-border-color: LIGHTGREY");
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
		matriceA.getItems().clear();
		display.clear();
		display.add(true);
		boutonLancer.setDisable(false);
		composition.setZoom(30.0, 400.0, 342.5);
		nbTransfo = 0;
	}

	public void doAjouterMotif(ActionEvent actionEvent) {
		//editer.setGraphic(new ImageView("File:ressources/editer.png"));
		//supprimer.setGraphic(new ImageView("File:ressources/delete.png"));
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
		pane.getChildren().removeAll(allNodes);
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

	public void doModifier(ActionEvent actionEvent) {
		if(!matriceA.getSelectionModel().isEmpty()) {

		} else Erreur.popUp("Aucune transformation", "Selectionnez une transformation pour pouvoir la modifier");
	}

	public void doSupprimer(ActionEvent actionEvent) {
		if(!matriceA.getSelectionModel().isEmpty()) {
			int index = matriceA.getSelectionModel().getSelectedIndex();
			matriceA.getItems().remove(index);
			composition.getSequence().remove(index);
			display.remove(index);
			pane.getChildren().removeAll(allNodes);
			doTransformation();
		} else Erreur.popUp("Aucune transformation", "Selectionnez une transformation pour pouvoir la modifier");
	}

	public void doMonter(ActionEvent actionEvent) {
		if(!matriceA.getSelectionModel().isEmpty()) {
			if(matriceA.getSelectionModel().getSelectedIndex() != 0) {
				int index = matriceA.getSelectionModel().getSelectedIndex();
				pane.getChildren().removeAll(allNodes);
				doModif(index + 1, index - 1, index - 1);
				doTransformation();
			}
		} else Erreur.popUp("Aucune transformation", "Selectionnez une transformation pour pouvoir la modifier");
	}

	public void doDescendre(ActionEvent actionEvent) {
		if(!matriceA.getSelectionModel().isEmpty()) {
			if(matriceA.getSelectionModel().getSelectedIndex() + 1 != nbTransfo) {
				int index = matriceA.getSelectionModel().getSelectedIndex();
				pane.getChildren().removeAll(allNodes);
				doModif(index, index + 1, index + 2);
				doTransformation();
			}
		} else Erreur.popUp("Aucune transformation", "Selectionnez une transformation pour pouvoir la modifier");
	}
	
	public void doModif(int index, int add, int remove) {
		final ArrayList<Transformation> tmpTra = transfo;
		final ArrayList<Boolean> tmpDis = display;
		final ObservableList<Transformation> tmpMa = matriceA.getItems();
		final ObservableList<Transformation> tmpCom = composition.getSequence();
		
		matriceA.getItems().add(index, tmpMa.get(add));
		composition.getSequence().add(index, tmpCom.get(add));
		display.add(index, tmpDis.get(add));
		//transfo.add(index, tmpTra.get(add));
		matriceA.getItems().remove(remove);
		composition.getSequence().remove(remove);
		display.remove(remove);
		//transfo.remove(remove);
	}
}