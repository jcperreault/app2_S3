package hellofx;
   
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
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
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.prism.paint.Color;

import enums.eshape;
import formes.*;
import javafx.scene.shape.*;

public class FactoryController {
	 
	
	//code de base --pas touche--
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    //--pas touche--
     
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
  
    //bouton 


    @FXML
    private Button bouton1;
    
    @FXML
    private Button bouton2;
    
    @FXML
    private Button bouton3;
    
    @FXML
    private Button bouton4;
    
    @FXML
    private Button bouton5;
    
    @FXML
    private Button bouton6;
    
    @FXML
    private Button bouton7;
    
    @FXML
    private Button bouton8;
    
    @FXML
    private Button bouton9;

    @FXML
    private MenuItem menuItem1;

    @FXML
    private MenuItem menuItem2;
    
    @FXML
    private Button boutonAgrandir;    

    @FXML
    private Ellipse formeElipse;

    @FXML
    private Rectangle formeRectangle;

    @FXML
    private Rectangle formeCarre;
    
    @FXML
    void boutonHandler(ActionEvent event) {
    	System.out.println(event.getSource().toString()); 
    }
    
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
    
    private ArrayList<Canvas> listFormes = new ArrayList<Canvas>();
 
    private int nbrAnchor = 0;
    private double [] anchorX = {0,0,0};
    private double [] anchorY = {0,0,0};  
    private Canvas tempCanvas;
    private Line curLine;
    
    private Canvas gestionFlechesSurComposantes(Canvas can)
    {
    	//on fait la gestion des fleches
    	can.setOnMouseReleased(new EventHandler<javafx.scene.input.MouseEvent>() { 
            @Override 
            public void handle(javafx.scene.input.MouseEvent e) {    
            	if(e.getClickCount() > 1)
            	{
            		anchorX[nbrAnchor] = can.getTranslateY();
                    anchorY[nbrAnchor] = can.getTranslateX();  
                    
                    if(nbrAnchor < 1)
                    {
                 	   nbrAnchor++; 
                 	   tempCanvas = can;
                    }
                    else
                    {
                 	   nbrAnchor = 0;                   	   
                 	   
                 	   //on cree la nouvelle ligne 
                        curLine = new Line(
                     		   tempCanvas.getTranslateX() + tempCanvas.getWidth()/2, tempCanvas.getTranslateY() + tempCanvas.getHeight()/2,
                     		   can.getTranslateX() + can.getWidth()/2, can.getTranslateY() + can.getHeight()/2
                            );  
                         
                        tableauTravail.getChildren().remove(tableauTravail.getChildren().indexOf(tempCanvas));
                        tableauTravail.getChildren().remove(tableauTravail.getChildren().indexOf(can));
                                                
                        tableauTravail.getChildren().add(curLine);
                        
                        tableauTravail.getChildren().add(tempCanvas);
                        tableauTravail.getChildren().add(can); 
                         
                    }    
            	}                              
            } 
         });  
    	return can;
    } 
    
    @FXML
    void mouseClickedElipse(MouseEvent event) {
    	//on ajoute un canvas dans le pane
    	Canvas can = ShapeFactory.createShape(eshape.ELIPSE); 
    	tableauTravail.getChildren().add(can);
    	listFormes.add(can); 
    	
    	//la fonction controle les connection entre les elements
    	gestionFlechesSurComposantes(can);
    }  

    @FXML
    void mouseClickedCarre(MouseEvent event) {
    	//on ajoute un canvas dans le pane
    	Canvas can = ShapeFactory.createShape(eshape.CARRE); 
    	tableauTravail.getChildren().add(can);
    	listFormes.add(can); 
    	
    	//la fonction controle les connection entre les elements 
    	gestionFlechesSurComposantes(can);
    }
    
    @FXML
    void mouseClickedRectangle(MouseEvent event) {
    	//on ajoute un canvas dans le pane
    	Canvas can = ShapeFactory.createShape(eshape.RECTANGLE); 
    	tableauTravail.getChildren().add(can);
    	listFormes.add(can); 
    	
    	//la fonction controle les connection entre les elements
    	gestionFlechesSurComposantes(can);
    }
    
    @FXML
    void mouseClickedCercle(MouseEvent event) {
    	//on ajoute un canvas dans le pane
    	Canvas can = ShapeFactory.createShape(eshape.CERCLE); 
    	tableauTravail.getChildren().add(can);
    	listFormes.add(can); 
    	
    	//la fonction controle les connection entre les elements
    	gestionFlechesSurComposantes(can);
    } 

    @FXML
    void paneDrageDropped(DragEvent event) {  
        System.out.println("onDragDropped"); 
        /* if there is a string data on dragboard, read it and use it */
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasString()) { 
        	System.out.println("true");
            success = true;
        }
        /* let the source know whether the string was successfully 
         * transferred and used */
        event.setDropCompleted(success);
        
        event.consume();
    	
    }
}
