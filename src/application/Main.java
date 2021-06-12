package application;
	
import id318449782_id209544642.CantVoteException;
import id318449782_id209544642.Controller;
import id318449782_id209544642.Manager;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		try {
			Manager mange = new Manager();
			mange.createNewElections();
			MainMenuView mainView = new MainMenuView(primaryStage);
			Controller controller = new Controller(mange, mainView);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
