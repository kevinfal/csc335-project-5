import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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

	private Connect4Model model;
	private Connect4Controller controller;
	private Circle[][] circles;

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		Connect4MoveMessage lastMove = (Connect4MoveMessage) arg;
		int color = lastMove.getColor();   //1 for Yellow, 2 for Red
		int rowNum = lastMove.getRow();
		int colNum = lastMove.getColumn();
		System.out.println("Hi!!");
		if(color == 1) {
			//Set color to yellow
			circles[rowNum][colNum].setFill(Color.YELLOW);
			
						
	    }else if (color == 2) {
	    	//Set color to red
			circles[rowNum][colNum].setFill(Color.RED);
						
		}else {
			//Not valid
						
	    }
					
				

	}
		
		

	

	

	/**
	 * Takes a BorderPane object as an argument and adds a Menu and
	 * MenuItem of “New Game” to build a menu bar.
	 * 
	 * @param bp, which represents a borderPane object.
	 *
	 * @return menuBar, which represents the built MenuBar object.
	 */
	private MenuBar buildMenuBar(BorderPane bp) {
		Menu menu = new Menu("File");
		MenuBar menuBar = new MenuBar(menu);
		MenuItem newGame = new MenuItem("New Game");
		menu.getItems().add(newGame);
		
		newGame.setOnAction((event -> {
			Connect4Controller controller = new Connect4Controller(model);
			buildBoard(controller, bp);
			
		}));
		
		newGame.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				 //Stage other = new Stage();
				gameDialog gd = new gameDialog();
				gd.show();
			}
		});
		return menuBar;
	}
	
	/**
	 * Takes a Connect4Controller and a BorderPane object as arguments
	 * and builds the main board, which contains the game board and
	 * the menu bar.
	 * 
	 * @param c, which represents the controller.
	 * 
	 * @param bp, which represents a borderPane object.
	 */
	private void buildBoard(Connect4Controller c, BorderPane bp) {
		// TODO Auto-generated method stub
		circles = new Circle[7][6];
		MenuBar menuBar = buildMenuBar(bp);
		GridPane gPane = new GridPane();
		bp.setTop(menuBar);
		
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
				
				
				circles[i][j] = circle;
				
				//circles[j][i] = circle;
			}
		}
		System.out.println(Arrays.deepToString(circles));
		bp.setTop(menuBar);
		bp.setCenter(gPane);
		
		
		gPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			int n = (int) event.getX();
			checkPos(c,bp,n);
		});
		
	}
	
	/**
	 * Takes a Connect4Controller, a BorderPane, and an integer as arguments
	 * to determine which column was clicked to add a color token.
	 * 
	 * @param c, which represents the controller.
	 * 
	 * @param bp, which represents a borderPane object.
	 * 
	 * @param n, which represents the X coordinate of the board.
	 */
	private void checkPos(Connect4Controller c, BorderPane bp, int n) {
		int column = 6;
		if(n <= 52) {
			column = 0;
			
		}else if (n <=100) {
			column = 1;
			
		}else if (n <= 148) {
			column = 2;
			
		}else if (n <= 196) {
			column = 3;
			
		}else if (n <= 244) {
			column = 4;
			
		}else if (n <= 292) {
			column = 5;
		}else {
			column = 6;
		}
		
		c.humanTurn(column);
	
		
		
		c.computerTurn();
	
			
	}
	
	
	
	
	
	
	/**
	 * Takes a Connect4Controller as an argument and checks
	 * if the game has been won to display an alert.
	 * 
	 * @param c, which represents the controller.
	 */
	private void checkIfOver(Connect4Controller c) {
		if(c.isGameOver()> 0) {
			Alert a = new Alert(Alert.AlertType.INFORMATION);
			a.setTitle("Message");
			a.setContentText("You won!");
			a.setHeaderText("Message");
			a.showAndWait();	
		}	
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
		this.model = new Connect4Model();
		this.controller = new Connect4Controller(model);
		
		BorderPane pane = new BorderPane();
		
		buildBoard(controller, pane);
		
		
		Scene scene = new Scene(pane, 344, 324);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Connect 4");
		primaryStage.show();
		
	}
	
	private class gameDialog extends Stage {
		public gameDialog() {
			//Initialize HBoxes for all needed elements for dialog
			HBox createElements = new HBox();
			HBox playElements = new HBox();
			HBox serverPortElements = new HBox();
			HBox dialogButtons = new HBox();
			VBox allElements = new VBox();
			
			fillCreateElements(createElements);
			fillPlayElements(playElements);
			fillServerPortElements(serverPortElements);
			fillDialogButtons(dialogButtons);
			
			allElements.getChildren().addAll(createElements,playElements,
            		serverPortElements);
            
            BorderPane bp = new BorderPane();
            bp.setBottom(dialogButtons);
            bp.setCenter(allElements);
            
            initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(bp, 450, 200);
            setScene(scene);
            setTitle("Network Setup");
            		
		}

		private void fillDialogButtons(HBox dialogButtons) {
			//OK and cancel buttons
            Button ok = new Button("OK");
            Button cancel = new Button("Cancel");
            
            ok.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					hide();		
				}
            	
            });
            
            cancel.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					hide();		
				}
            	
            });
            
            dialogButtons.getChildren().addAll(ok,cancel);
            dialogButtons.setPadding(new Insets(10));
            dialogButtons.setSpacing(10);
			
		}

		private void fillServerPortElements(HBox serverPortElements) {
			//Server/Port section of dialog
            Label serverL = new Label("Server");
            TextField serverT = new TextField("localhost");
            Label port = new Label("Port");
            TextField portT = new TextField("4000");
            serverPortElements.getChildren().addAll(serverL, serverT, port, portT);
            serverPortElements.setPadding(new Insets(10));
            serverPortElements.setSpacing(10);
			
		}

		private void fillPlayElements(HBox playElements) {
			//Play section of dialog
            Label play = new Label("Play as:    ");
            RadioButton human = new RadioButton("Human   ");
            RadioButton computer = new RadioButton("Computer");
            playElements.getChildren().addAll(play, human, computer);
            playElements.setPadding(new Insets(10));
			
		}

		private void fillCreateElements(HBox createElements) {
			//Create section of dialog
            Label create = new Label("Create:    ");
            RadioButton server = new RadioButton("Server   ");
            RadioButton client= new RadioButton("Client");
            createElements.getChildren().addAll(create,server, client);
            createElements.setPadding(new Insets(10));
			
		}
	}

}
