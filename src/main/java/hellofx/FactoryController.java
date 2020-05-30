package hellofx;
   
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.prism.paint.Color;

import formes.Rectangle;
import formes.Elipse;
import formes.Formes;

public class FactoryController {
	
	//code de base --pas touche--
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    //--pas touche--
    
    //liste de forme
    ArrayList<Formes> listeFormes = new ArrayList<Formes>();
    Formes selectedForme;

    //variable pour le MenuBar
    @FXML
    private Menu menuFile;
    @FXML
    private Menu menuEdit;
    @FXML
    private Menu menuMore;
    
    //label du bas de page
    @FXML
    private Label labelMode;
    
    //Menu des tiltedPane sur la gauche de la fenetre
    @FXML
    private TitledPane tiltedPaneEnergy;
    @FXML
    private TitledPane titledPaneInversion;
    @FXML
    private TitledPane titledPaneStrategy;
    @FXML
    private TitledPane titledPaneEstimator;
    
    //zone de travail principal
    @FXML
    private Pane tableauTravail;
     

    @FXML
    private Circle formeCercle;

    @FXML
    private Button boutonAjouter;
    
    @FXML
    private ToolBar toolBarEditor;
  
    
    @FXML
    void initialize() {
        assert toolBarEditor != null : "fx:id=\"toolBarEditor\" was not injected: check your FXML file 'UI.fxml'.";
        assert formeCercle != null : "fx:id=\"formeCercle\" was not injected: check your FXML file 'UI.fxml'.";
        assert labelMode != null : "fx:id=\"labelMode\" was not injected: check your FXML file 'UI.fxml'.";
        assert boutonAjouter != null : "fx:id=\"boutonAjouter\" was not injected: check your FXML file 'UI.fxml'.";
        assert titledPaneInversion != null : "fx:id=\"titledPaneInversion\" was not injected: check your FXML file 'UI.fxml'.";
        assert titledPaneStrategy != null : "fx:id=\"titledPaneStrategy\" was not injected: check your FXML file 'UI.fxml'.";
        assert menuEdit != null : "fx:id=\"menuEdit\" was not injected: check your FXML file 'UI.fxml'.";
        assert menuFile != null : "fx:id=\"menuFile\" was not injected: check your FXML file 'UI.fxml'.";
        assert titledPaneEstimator != null : "fx:id=\"titledPaneEstimator\" was not injected: check your FXML file 'UI.fxml'.";
        assert tableauTravail != null : "fx:id=\"tableauTravail\" was not injected: check your FXML file 'UI.fxml'.";
        assert tiltedPaneEnergy != null : "fx:id=\"tiltedPaneEnergy\" was not injected: check your FXML file 'UI.fxml'.";
        assert menuMore != null : "fx:id=\"menuMore\" was not injected: check your FXML file 'UI.fxml'.";

    } 
    
    @FXML
    void formeDragDetected(MouseEvent event) {
    	  /* drag was detected, start drag-and-drop gesture*/
        System.out.println("onDragDetected");
        
        /* allow any transfer mode */
        Dragboard db = formeCercle.startDragAndDrop(TransferMode.ANY);
        
        /* put a string on dragboard */
        ClipboardContent cb = new ClipboardContent();
        cb.putString("mamaCOCO");
        db.setContent(cb);
        
        event.consume();
    }

    @FXML
    void paneDrageDropped(DragEvent event) {  
    	 /* data dropped */
        System.out.println("onDragDropped"); 
        /* if there is a string data on dragboard, read it and use it */
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasString()) { 
        	System.out.println("true");
            success = true;
            
            Circle nouveauCercle = new Circle(event.getX(), event.getY(), formeCercle.getRadius());
            System.out.println(event.getX() + " " + event.getY() + " " + formeCercle.getRadius());

            tableauTravail.getChildren().add(nouveauCercle);
        }
        /* let the source know whether the string was successfully 
         * transferred and used */
        event.setDropCompleted(success);
        
        event.consume();
    	
    }

    @FXML
    void paneDrageOver(DragEvent  event) {
        /* data is dragged over the target */
        System.out.println("onDragOver");
        
        /* accept it only if it is  not dragged from the same node 
         * and if it has a string data */ 
            /* allow for both copying and moving, whatever user chooses */
        event.acceptTransferModes(TransferMode.COPY_OR_MOVE); 
        event.consume();
    }


    @FXML
    void paneDragEntered(DragEvent event) {
    	 /* the drag-and-drop gesture entered the target */
        System.out.println("onDragEntered");
        /* show to the user that it is an actual gesture target */
        if (event.getGestureSource() != tableauTravail &&
                event.getDragboard().hasString()) {
        	System.out.println("dropp succes");
        }
        
        event.consume();
    }
}
