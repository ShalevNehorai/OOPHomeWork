package application;
	
import java.util.Vector;

import javax.swing.JOptionPane;

import id318449782_id209544642.CantVoteException;
import id318449782_id209544642.Controller;
import id318449782_id209544642.Manager;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
//		Stage loadStage = new Stage();
//		
//		FileChooser fileChooser = new FileChooser();
//		VBox vbox = new VBox();
//		
//		vbox.getChildren().add(fileChooser);
//		
//		Scene scene = new Scene(vbox, 500, 300);
//		loadStage.setScene(scene);
//		loadStage.show();
		
		
		try {
			Manager mange = new Manager();
			int dialogButton = JOptionPane.showConfirmDialog(null, "Do you want to load data from saved file?","",JOptionPane.YES_NO_OPTION);
			if(dialogButton == JOptionPane.YES_OPTION) {
				mange.readBinaryFile();
			}
			else {				
				mange.createNewElections();
			}
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
