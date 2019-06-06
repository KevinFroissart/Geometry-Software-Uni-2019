package ihm;

import javafx.scene.layout.Pane;
import transforms.Composition;
import transforms.mobile.GrilleAdaptable;

public class CompositionPlus extends Composition{

	public GrilleAdaptable getGrille(Composition composition, Pane pane) {
		GrilleAdaptable grille = new GrilleAdaptable(composition, pane, 1, 1);	
		return grille;
	}	
}<>