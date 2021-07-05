package application;

import java.time.LocalDate;

import id318449782_id209544642.PoliticalParty;
import id318449782_id209544642.PoliticalParty.ePoliticalStand;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ViewAddPoliticalParty {
	private MainMenuView mainView;
	
    TextField tfName;
    ComboBox<PoliticalParty.ePoliticalStand> standCmb; 
    DatePicker datePicker;
    
	public ViewAddPoliticalParty(Stage primeryStage, MainMenuView mainView) {
		this.mainView = mainView; 
		
		GridPane gbRoot = new GridPane();
		gbRoot.setPadding(new Insets(10));
		gbRoot.setHgap(10);
		gbRoot.setVgap(10);
		
		Label nameLbl = new Label("Name: ");
		tfName = new TextField();
		
		Label standLbl = new Label("Type: ");
		standCmb = new ComboBox<PoliticalParty.ePoliticalStand>();		
		for (PoliticalParty.ePoliticalStand stand : PoliticalParty.ePoliticalStand.values()) {
			standCmb.getItems().add(stand);
		}
		standCmb.getSelectionModel().selectFirst();
		
		Button btnAdd = new Button("Add");
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				addParty();
				
				primeryStage.close();
			}
		});
		
		Label dateLbl = new Label("Creation date");
		datePicker = new DatePicker(LocalDate.now());
		
		gbRoot.add(nameLbl, 0, 0);
		gbRoot.add(tfName, 1, 0);
		gbRoot.add(standLbl, 0, 1);
		gbRoot.add(standCmb, 1, 1);
		gbRoot.add(dateLbl, 0, 2);
		gbRoot.add(datePicker, 1, 2);
		gbRoot.add(btnAdd, 1, 3);
		
		Scene scene = new Scene(gbRoot, 300, 200);
		primeryStage.setScene(scene);
		primeryStage.show();	
	}
	
	private void addParty() {
		String name = tfName.getText();
		ePoliticalStand stand = standCmb.getValue();
		LocalDate date = datePicker.getValue();
		
		mainView.addPoliticalParty(name, stand, date);
	}
}
