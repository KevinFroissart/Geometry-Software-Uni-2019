package ihm;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class Controller  {
	
	@FXML
	BorderPane borderPane;
	
	@FXML
	Pane paneGrid;

	@FXML
	ToolBar upToolBar,downToolBar;
	
	@FXML
	Button lancer,reset,addMotif,translation,rotation,homothetie,aide,zoomPlus,zoomMoins;
	
	@FXML
	Label zoomLabel,labelA,labelC;
	
	@FXML
	Accordion accordion;
	
	@FXML
	TitledPane compoPane,matrixPane;
	
	@FXML
	ListView listCompo,matrixA,matrixC;
	
}