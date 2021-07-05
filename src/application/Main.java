package application;

import javax.swing.JOptionPane;

import id318449782_id209544642.Controller;
import id318449782_id209544642.Manager;
import javafx.application.Application;
import javafx.stage.Stage;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {		
		
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
