package ihm;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import transforms.mobile.Maison;
import transforms.mobile.MotifConcret;
import transforms.Composition;

public class MaisonMotif {

	private static int id = 0;
	private Label label;
	private Color couleurInitiale;
	private MotifConcret motif;

	
	public MaisonMotif(Composition composition) {
		id++;
		label = new Label("Maison " + id);
		couleurInitiale = Color.BLACK;
		motif = new Maison(composition);
		
	}
	
}
