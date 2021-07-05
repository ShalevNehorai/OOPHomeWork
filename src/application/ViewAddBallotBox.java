package application;

import id318449782_id209544642.BallotBox.BallotType;
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

public class ViewAddBallotBox {
		
	private MainMenuView mainView;
	
	TextField tfStreen;
	ComboBox<String> typeCmb;
	
	public ViewAddBallotBox(Stage primeryStage, MainMenuView mainView) {
		this.mainView = mainView; 
		
		GridPane gbRoot = new GridPane();
		gbRoot.setPadding(new Insets(10));
		gbRoot.setHgap(10);
		gbRoot.setVgap(10);
		
		Label streetLbl = new Label("Street: ");
		tfStreen = new TextField();
		
		Label typeLbl = new Label("Type: ");
		typeCmb = new ComboBox<String>();		
		for (BallotType type : BallotType.values()) {
			typeCmb.getItems().add(type.toString());
		}
		typeCmb.getSelectionModel().selectFirst();
		
		Button btnAdd = new Button("Add");
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				addBallotBox();
				
				primeryStage.close();
			}
		});
		
		gbRoot.add(streetLbl, 0, 0);
		gbRoot.add(tfStreen, 1, 0);
		gbRoot.add(typeLbl, 0, 1);
		gbRoot.add(typeCmb, 1, 1);
		gbRoot.add(btnAdd, 1, 2);
		
		Scene scene = new Scene(gbRoot, 300, 200);
		primeryStage.setScene(scene);
		primeryStage.show();
			
	}

	
	public void addBallotBox() {
		String street = tfStreen.getText();
		BallotType type = BallotType.valueOf(typeCmb.getValue());
		
		mainView.addBallotBox(street, type);
	}

}
