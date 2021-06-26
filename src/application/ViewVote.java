package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ViewVote {
	private MainMenuView mainView;

	private Label nameLbl;
	private ComboBox<String> partyCombo;
	private ArrayList<String> partyList;

	
	public ViewVote(Stage primeryStage, MainMenuView mainView, String voterName) {
		this.mainView = mainView;
		
		partyList = mainView.getPartyNameList();
		
		GridPane gbRoot = new GridPane();
		gbRoot.setPadding(new Insets(10));
		gbRoot.setHgap(10);
		gbRoot.setVgap(10);
		
		Label lbl = new Label("Corrently voting is: ");
		nameLbl = new Label(voterName);
		
		Label partyLbl = new Label("Selet the party you want to vote to: ");
		partyCombo = new ComboBox<String>();
		partyCombo.getItems().add("Don't vote for anyone");
		for (String string : partyList) {
			partyCombo.getItems().add(string);
		}
		partyCombo.getSelectionModel().selectFirst();
		
		Button btnVote = new Button("vote");
		btnVote.setOnAction(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent arg0) {
				String voteParty = partyCombo.getValue();
				mainView.viewVote(voteParty);
				primeryStage.close();
			}
		});
		
		gbRoot.add(lbl, 0, 0);
		gbRoot.add(nameLbl, 1, 0);
		gbRoot.add(partyLbl, 0, 1);
		gbRoot.add(partyCombo, 1, 1);
		gbRoot.add(btnVote, 1, 2);
		
		
		Scene scene = new Scene(gbRoot, 400, 150);
		primeryStage.setScene(scene);
		primeryStage.show();
	}
}
