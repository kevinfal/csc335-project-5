import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
/**
 * This class implements a JavaFX GUI. It consists of a visual representation
 * of a "Connect4" game. 
 * 
 * @author Alexander Buell
 *
 */
public class Connect4View extends Application implements Observer{

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Purpose: This method is the main entry point for all JavaFX applications.
	 *          The start method is called after the init method has returned,
	 *          and after the system is ready for the application to begin running.
	 *           
	 * @param primaryStage, which represents the primary stage for this application.
	 *      
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		BorderPane pane = new BorderPane();
		GridPane gPane = new GridPane();
		Menu menu = new Menu("File");
		MenuBar menuBar = new MenuBar(menu);
		MenuItem newGame = new MenuItem("New Game");
		 
		Dialog<String> dialog = new Dialog<String>();
		dialog.setTitle("Network Setup");
		
		
		
		newGame.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				 //Stage other = new Stage();
				DialogPane dPane = new DialogPane() {
	                @Override
	                public Node createButtonBar() {
	                    HBox box = new HBox(5);
	                    Label label = new Label("Create:");
	                    RadioButton server = new RadioButton("Server");
	                    RadioButton client = new RadioButton("Client");
	                    
	                    
	                    box.getChildren().add(super.createButtonBar());
	                    box.getChildren().addAll(label,server, client);
	                    return box ;
	                }
	            };
	             dialog.setDialogPane(dPane);
				 dialog.initModality(Modality.APPLICATION_MODAL);
				 dialog.show();
				
			}
			
		});
		
		menu.getItems().add(newGame);
		
		
		
		gPane.setBackground(new Background(new BackgroundFill(Color.BLUE, new CornerRadii(0), Insets.EMPTY)));
		gPane.setVgap(8);
		gPane.setHgap(8);
		gPane.setPadding(new Insets(8));
	
		for(int i = 0; i < 7 ; i++) {
			for(int j = 0; j < 6; j++) {
				Circle circle = new Circle();
				circle.setFill(Color.WHITE);
				circle.setRadius(20);
				
				gPane.add(circle, i , j);
			}
		}
		pane.setTop(menuBar);
		pane.setCenter(gPane);
		Scene scene = new Scene(pane, 344, 324);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Connect 4");
		
	    //primaryStage.initOwner(primaryStage);
		
		primaryStage.show();
		
	}

}
