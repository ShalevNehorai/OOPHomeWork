package application;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.concurrent.Callable;

import javax.swing.JOptionPane;

import id318449782_id209544642.AlreadyExistException;
import id318449782_id209544642.BallotBox.BallotType;
import id318449782_id209544642.Controller;
import id318449782_id209544642.PoliticalParty.ePoliticalStand;
import id318449782_id209544642.UIAbstractView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import listeners.ViewListener;

public class MainMenuView implements UIAbstractView {
	
	private ArrayList<ViewListener> allListeners;
	
	private Stage showStage;
	
	private MainMenuView mainView;

	
	public MainMenuView(Stage primaryStage) {
		mainView = this;
		
		allListeners = new ArrayList<ViewListener>();
		
		GridPane gpRoot = new GridPane();
		gpRoot.setPadding(new Insets(10));
		gpRoot.setHgap(10);
		gpRoot.setVgap(10);
		
		Button btnAdding = new Button("Adding Options");
		btnAdding.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				//TODO open add view
				
				ViewAddBallotBox add = new ViewAddBallotBox(new Stage(), mainView);
				
			}
		});
		
		Button btnShow = new Button("Show Options");
		btnShow.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				
				Label lbl = new Label("What do you want to see?");
				
				Button btnShowCitizens = new Button("Show all Citizens");
				btnShowCitizens.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent action) {
						showAllCitizen();
						showStage.close();
					}
				});
				
				Button btnShowParty = new Button("Show all Parties");
				btnShowParty.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent action) {
						showAllPoliticalParty();
						showStage.close();
					}
				});
				
				Button btnShowBallot = new Button("Show all Ballot boxes");
				btnShowBallot.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent action) {
						showAllBallotBox();
						showStage.close();
					}
				});		
				
				GridPane showGrid = new GridPane();
				showGrid.setPadding(new Insets(10));
				showGrid.setHgap(10);
				showGrid.setVgap(10);
				
				showGrid.add(lbl, 0, 0);
				showGrid.add(btnShowCitizens, 0, 1);
				showGrid.add(btnShowParty, 0, 2);
				showGrid.add(btnShowBallot, 0, 3);
				
				
				Scene showScene = new Scene(showGrid, 300, 200);
				showStage = new Stage(); 
				showStage.setScene(showScene);
				showStage.show();
			}
		});
		
		Button btnStartElections = new Button("Start Elections");
		btnStartElections.setStyle("-fx-background-color: #2F8CD7; ");
		btnStartElections.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				//TODO start ele
			}
		});
		
		Button btnElectionResult = new Button("Show Elections Results");
		btnElectionResult.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				//TODO show ele resutls
			}
		});
		
		Button btnQuit = new Button("Save and quit");
		btnQuit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				//TODO save and quit
			}
		});
		
		gpRoot.add(btnAdding, 0, 2);
		gpRoot.add(btnShow, 1, 2);
		gpRoot.add(btnStartElections, 0, 3);
		gpRoot.add(btnElectionResult, 1, 3);

		gpRoot.add(btnQuit, 1, 4);
		
		Scene scene = new Scene(gpRoot, 500, 300);
		primaryStage.setScene(scene);
		primaryStage.show();
	}


	@Override
	public void registerListener(ViewListener listener) {
		allListeners.add(listener);
	}


	@Override
	public void addBallotBox(String streer, BallotType type) {
		for (ViewListener viewListener : allListeners) {
			try {
				viewListener.addBallotBox(streer, type);
			} catch (InputMismatchException | AlreadyExistException e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public void addCitizen() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void addPoliticalParty() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void addCandid() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String showAllBallotBox() {
		try {
			String citizen = allListeners.get(0).askAllBallotBoxes();
			JOptionPane.showMessageDialog(null, citizen);
		}catch (IndexOutOfBoundsException e) {
			System.out.println("no listeners register");
		}
		return null;
	}


	@Override
	public String showAllPoliticalParty() {
		try {
			String citizen = allListeners.get(0).askPoliticalPartyList();
			
			JOptionPane.showMessageDialog(null, citizen);
		}catch (IndexOutOfBoundsException e) {
			System.out.println("no listeners register");
		}
		return null;
	}


	@Override
	public String showAllCitizen() {
		try {
			String citizen = allListeners.get(0).askCitizenList();
			
			JOptionPane.showMessageDialog(null, citizen);
		}catch (IndexOutOfBoundsException e) {
			System.out.println("no listeners register");
		}
		
		return null;
	}


	@Override
	public void startElection() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String showElectionResult() {
		try{
		String results = allListeners.get(0).askElectionResult();
		
		JOptionPane.showMessageDialog(null, results);
		
		}catch(Exception e){
				
		}
		return null;
	}
	
	@Override
	public void showMsg(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}


	@Override
	public void exitMenu() {
		try{
			//TODO Need to add the Save Path
			allListeners.get(0).save("Hello");;
		}
		catch(Exception e){
			
		}
		
	}

}
