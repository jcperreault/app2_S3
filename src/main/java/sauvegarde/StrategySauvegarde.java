package sauvegarde;

import java.util.ArrayList;
import java.util.Vector;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

public interface StrategySauvegarde {
	public void execute(Pane tableauTravail, ArrayList<Canvas> listFormes);
}
