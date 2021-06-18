package application;

import java.time.LocalDate;
import java.util.ArrayList;

import id318449782_id209544642.PoliticalParty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class ViewAddCitizen {
	private MainMenuView mainView;
	
	private TextField tfName;
	private TextField tfId;
	private TextField tfBirthYear;
	private CheckBox sickBox;
	private TextField tfSickDays;
	private CheckBox isSoldierBox;
	private CheckBox isCarryWeaponBox;
	private ComboBox<String> ballotCombo;
	
	private ArrayList<String> ballotList;
	
	public ViewAddCitizen(Stage primeryStage, MainMenuView mainView) {
		this.mainView = mainView; 
		
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
		
		Label sickLbl = new Label("Is Seak? ");
		sickBox = new CheckBox();
		tfSickDays = new TextField();
		tfSickDays.setVisible(false);
		tfSickDays.setPromptText("How many days are you sick");
		tfSickDays.setPrefWidth(200);
		setTextFieldNumbersOnly(tfSickDays);
		sickBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				tfSickDays.setVisible(newValue);
				tfSickDays.setText("");
				setBallotComboList();
			}
		});
		
		Label isSoldierLbl = new Label("Is soldier? ");
		isSoldierBox = new CheckBox();
		isCarryWeaponBox = new CheckBox();
		isCarryWeaponBox.setVisible(false);
		Label carryLbl = new Label("Are you carry weapon?");
		carryLbl.setVisible(false);
		isSoldierBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				isCarryWeaponBox.setVisible(newValue);
				carryLbl.setVisible(newValue);
				isCarryWeaponBox.setSelected(false);
				setBallotComboList();
			}
		});
		
		Label ballotLbl = new Label("Select the ballot box:");
		ballotCombo = new ComboBox<String>();
		ballotCombo.setMaxWidth(150);
		setBallotComboList();
		
		Button btnAdd = new Button("Add");
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				addCitizen();
				
				primeryStage.close();
			}
		});

		
		gbRoot.add(nameLbl, 0, 0);
		gbRoot.add(tfName, 1, 0);
		gbRoot.add(idLbl, 0, 1);
		gbRoot.add(tfId, 1, 1);
		gbRoot.add(birthLbl,0,2);
		gbRoot.add(tfBirthYear,1,2);
		gbRoot.add(sickLbl, 0, 3);
		gbRoot.add(sickBox, 1, 3);
		gbRoot.add(tfSickDays, 2, 3);
		gbRoot.add(isSoldierLbl, 0, 4);
		gbRoot.add(isSoldierBox, 1, 4);
		gbRoot.add(carryLbl, 2, 4);
		gbRoot.add(isCarryWeaponBox, 3, 4);
		gbRoot.add(ballotLbl, 0, 5);
		gbRoot.add(ballotCombo, 1, 5);
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
		boolean isSick = sickBox.isSelected();
		boolean isSoldier = isSoldierBox.isSelected();
		
		ballotList = mainView.getBallotBoxes(mainView.getBallotType(isSoldier, isSick));
		
		ballotCombo.getItems().clear();
		
		for (String string : ballotList) {
			ballotCombo.getItems().add(string);
		}
		ballotCombo.getSelectionModel().selectFirst();

	}
	
	private void addCitizen() {
		String name = tfName.getText();
		String id = tfId.getText();
		int birth = -1;
		if(!tfBirthYear.getText().isEmpty()) {
			birth = Integer.parseInt(tfBirthYear.getText());			
		}
		boolean isSick = sickBox.isSelected();
		int sickDays = 0;
		if(isSick && !tfSickDays.getText().isEmpty()) {
			sickDays = Integer.parseInt(tfSickDays.getText());
		}
		
		boolean isSoldier = isSoldierBox.isSelected();
		boolean carryWeapon = isCarryWeaponBox.isSelected();
		
		int ballotIndex = ballotList.indexOf(ballotCombo.getValue());
		
		mainView.addCitizen(name, id, birth, isSick, sickDays, isSoldier, carryWeapon, ballotIndex);
	}
}
