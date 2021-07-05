package application;

import java.util.ArrayList;

import id318449782_id209544642.BallotBox.BallotType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ViewAddCanadid {
	private MainMenuView mainView;
	
	private TextField tfName;
	private TextField tfId;
	private TextField tfBirthYear;
	private TextField tfPrimerisPos;
	
	private ComboBox<String> ballotCombo;
	private ComboBox<String> partyCombo;
	
	private ArrayList<String> ballotList;
	private ArrayList<String> partyList;

	
	public ViewAddCanadid(Stage primeryStage, MainMenuView mainView) {
		this.mainView = mainView; 
		
		partyList = mainView.getPartyNameList();
		
		GridPane gbRoot = new GridPane();
		gbRoot.setPadding(new Insets(10));
		gbRoot.setHgap(10);
		gbRoot.setVgap(10);
		
		Label nameLbl = new Label("Name: ");
		tfName = new TextField();
		
		Label idLbl = new Label("Id: ");
		tfId = new TextField();
		setTextFieldNumbersOnly(tfId);

		Label birthLbl = new Label("Birth Year: ");
		tfBirthYear = new TextField();
		setTextFieldNumbersOnly(tfBirthYear);
				
		Label ballotLbl = new Label("Select the ballot box:");
		ballotCombo = new ComboBox<String>();
		ballotCombo.setMaxWidth(150);
		setBallotComboList();

		Label partyLbl = new Label("Selet the party you are in");
		partyCombo = new ComboBox<String>();
		for (String string : partyList) {
			partyCombo.getItems().add(string);
		}
		partyCombo.getSelectionModel().selectFirst();

		Label posLbl = new Label("enter your place in the primeries");
		tfPrimerisPos = new TextField();
		setTextFieldNumbersOnly(tfPrimerisPos);
		
		
		Button btnAdd = new Button("Add");
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				addCandid();
				
				primeryStage.close();
			}
		});

		
		gbRoot.add(nameLbl, 0, 0);
		gbRoot.add(tfName, 1, 0);
		gbRoot.add(idLbl, 0, 1);
		gbRoot.add(tfId, 1, 1);
		gbRoot.add(birthLbl,0,2);
		gbRoot.add(tfBirthYear,1,2);
		gbRoot.add(ballotLbl, 0, 3);
		gbRoot.add(ballotCombo, 1, 3);
		gbRoot.add(partyLbl, 0, 4);
		gbRoot.add(partyCombo, 1, 4);
		gbRoot.add(posLbl, 0, 5);
		gbRoot.add(tfPrimerisPos, 1, 5);
		gbRoot.add(btnAdd, 1, 6);
	
		
		Scene scene = new Scene(gbRoot, 700, 500);
		primeryStage.setScene(scene);
		primeryStage.show();	
	}
	
	private void setTextFieldNumbersOnly(TextField tf) {
		tf.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	tf.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
	}
	
	private void setBallotComboList() {
		ballotList = mainView.getBallotBoxes(BallotType.RegularCitizen);
		
		ballotCombo.getItems().clear();
		
		for (String string : ballotList) {
			ballotCombo.getItems().add(string);
		}
		ballotCombo.getSelectionModel().selectFirst();
	}
	
	private void addCandid() {
		String name = tfName.getText();
		String id = tfId.getText();
		int birth = -1;
		if(!tfBirthYear.getText().isEmpty()) {
			birth = Integer.parseInt(tfBirthYear.getText());			
		}
		int ballotIndex = ballotList.indexOf(ballotCombo.getValue());
		int partyIndex = partyList.indexOf(partyCombo.getValue());
		int primeriesPosition = -1;
		if(!tfPrimerisPos.getText().isEmpty()) {
			primeriesPosition = Integer.parseInt(tfPrimerisPos.getText());			
		}
		
		mainView.addCandid(name, id, birth, ballotIndex, partyIndex, primeriesPosition);
		
	}
}
