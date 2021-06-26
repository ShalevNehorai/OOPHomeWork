package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;

import javax.swing.JOptionPane;

import id318449782_id209544642.AlreadyExistException;
import id318449782_id209544642.CantVoteException;
import id318449782_id209544642.BallotBox.BallotType;
import id318449782_id209544642.Controller;
import id318449782_id209544642.InvalidIdException;
import id318449782_id209544642.PoliticalParty.ePoliticalStand;
import id318449782_id209544642.UIAbstractView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.print.Collation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import listeners.ViewListener;

public class MainMenuView implements UIAbstractView {
	private ArrayList<ViewListener> allListeners;

	private Stage showStage;

	private MainMenuView mainView;

	private GridPane gpRoot;

	private GridPane addGrid;
	private ScrollPane scroll;
	private Text resultText;

	private Button btnStartElections;
	private Button btnElectionResult;
	private Button btnCreateNewElections;

	public MainMenuView(Stage primaryStage) {
		mainView = this;

		allListeners = new ArrayList<ViewListener>();

		gpRoot = new GridPane();
		gpRoot.setPadding(new Insets(10));
		gpRoot.setHgap(10);
		gpRoot.setVgap(10);

		initAddingGrid();
		initEleResultsPane();

		hideSideElements();

		Button btnAdding = new Button("Adding Options");
		btnAdding.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				hideSideElements();
				addGrid.setVisible(true);
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

		btnStartElections = new Button("Start Elections");
		btnStartElections.setStyle("-fx-background-color: #2F8CD7; ");
		btnStartElections.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				viewAskToStartElection();
			}
		});

		btnElectionResult = new Button("Show Elections Results");
		btnElectionResult.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				showElectionResult();
			}
		});

		btnCreateNewElections = new Button("create new elections");
		btnCreateNewElections.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				// TODO create new elections
				
			}
		});

		Button btnQuit = new Button("Save and quit");
		btnQuit.setStyle("-fx-background-color: #e4685d; ");
		btnQuit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				exitMenu();
				primaryStage.close();
			}
		});


		gpRoot.add(btnAdding, 0, 2);
		gpRoot.add(btnShow, 1, 2);
		gpRoot.add(btnStartElections, 0, 3);
		gpRoot.add(btnElectionResult, 1, 3);
		gpRoot.add(btnCreateNewElections, 0, 4);

		gpRoot.add(btnQuit, 0, 5);

		HBox layout = new HBox();
		layout.getChildren().addAll(gpRoot, addGrid, scroll);
		scroll.setVisible(true);

		Scene scene = new Scene(layout, 700, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void initAddingGrid() {
		addGrid = new GridPane();
		addGrid.setPadding(new Insets(10));
		addGrid.setHgap(10);
		addGrid.setVgap(10);

		Button btnAddCitizen = new Button("Add Citizen");
		btnAddCitizen.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				ViewAddCitizen add = new ViewAddCitizen(new Stage(), mainView);
			}
		});

		Button btnAddCandid = new Button("Add Candid");
		btnAddCandid.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				ViewAddCanadid add = new ViewAddCanadid(new Stage(), mainView);
			}
		});

		Button btnAddBallotBox = new Button("Add Ballot Box");
		btnAddBallotBox.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				ViewAddBallotBox add = new ViewAddBallotBox(new Stage(), mainView);
			}
		});

		Button btnAddParty = new Button("Add Party");
		btnAddParty.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				ViewAddPoliticalParty add = new ViewAddPoliticalParty(new Stage(), mainView);
			}
		});

		addGrid.add(btnAddCitizen, 0, 0);
		addGrid.add(btnAddCandid, 0, 1);
		addGrid.add(btnAddBallotBox, 0, 2);
		addGrid.add(btnAddParty, 0, 3);
	}

	private void initEleResultsPane() {
		scroll = new ScrollPane();
		resultText = new Text();
		resultText.wrappingWidthProperty().bind(scroll.widthProperty());
		scroll.setMinWidth(300);
		scroll.setContent(resultText);
	}

	private void hideSideElements() {
		addGrid.setVisible(false);
		scroll.setVisible(false);
	}

	/*private void afterElectionButtons() {
		btnStartElections.setVisible(false);
		btnElectionResult.setVisible(true);
		btnCreateNewElections.setVisible(true);

//		 gpRoot.add(btnElectionResult, 0, 3);
	}*/

	/*private void beforeElectionButtons() {
		btnStartElections.setVisible(true);
		btnElectionResult.setVisible(false);
		btnCreateNewElections.setVisible(false);

//		 gpRoot.add(btnElectionResult, 0, 3);
	}*/

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
	public void addCitizen(String name, String id, int birthYear, boolean isSick, int sickDays, boolean isSoldier,
			boolean carryWeapon, int ballotBoxIndex) {
		for (ViewListener viewListener : allListeners) {
			viewListener.addCitizen(name, id, birthYear, isSick, sickDays, isSoldier, carryWeapon, ballotBoxIndex);
		}

	}

	@Override
	public void addPoliticalParty(String name, ePoliticalStand stand, LocalDate creationDate) {
		for (ViewListener viewListener : allListeners) {
			try {
				viewListener.addPoliticalParty(name, stand, creationDate);
			} catch (InputMismatchException | AlreadyExistException e) {
				showMsg(e.getMessage());
			}
		}

	}

	@Override
	public void addCandid(String name, String id, int birthYear, int ballotBoxIndex, int partyIndex,
			int primeriesPosition) {
		for (ViewListener viewListener : allListeners) {
			viewListener.addCanadid(name, id, birthYear, ballotBoxIndex, partyIndex, primeriesPosition);
		}
	}

	@Override
	public String showAllBallotBox() {
		try {
			ArrayList<String> ballotBoxes = allListeners.get(0).askAllBallotBoxes();
			StringBuilder sb = new StringBuilder();
			for (String string : ballotBoxes) {
				sb.append(string).append("\n");
			}
			JOptionPane.showMessageDialog(null, sb);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("no listeners register");
		}
		return null;
	}

	@Override
	public String showAllPoliticalParty() {
		try {
			ArrayList<String> parties = allListeners.get(0).askPoliticalPartyList();
			StringBuilder sb = new StringBuilder();
			for (String string : parties) {
				sb.append(string).append("\n");
			}
			JOptionPane.showMessageDialog(null, sb);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("no listeners register");
		}
		return null;
	}

	@Override
	public String showAllCitizen() {
		try {
			ArrayList<String> citizens = allListeners.get(0).askCitizenList();
			StringBuilder sb = new StringBuilder();
			for (String string : citizens) {
				sb.append(string).append("\n");
			}
			JOptionPane.showMessageDialog(null, sb);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("no listeners register");
		}

		return null;
	}

	@Override
	public void viewAskToStartElection() {
		for (ViewListener viewListener : allListeners) {
			viewListener.viewStartElections();
		}
	}

	@Override
	public void nextVoter(String voterName) {
		ViewVote viewVote = new ViewVote(new Stage(), mainView, voterName);
	}

	@Override
	public void viewVote(String voteParty) {
		for (ViewListener viewListener : allListeners) {
			viewListener.vote(voteParty);
		}
	}

	@Override
	public String showElectionResult() {
		try {
			String results = allListeners.get(0).askElectionResult();
			resultText.setText(results);
			resultText.wrappingWidthProperty().bind(scroll.widthProperty());
			scroll.setContent(resultText);

			hideSideElements();
			scroll.setVisible(true);
		} catch (Exception e) {

		}
		return null;
	}

	public ArrayList<String> getBallotBoxes(BallotType type) {
		return allListeners.get(0).askBallotBoxList(type);
	}

	public ArrayList<String> getParties() {
		return allListeners.get(0).askPoliticalPartyList();
	}

	@Override
	public BallotType getBallotType(boolean isSoldier, boolean isSick) {
		return allListeners.get(0).askBallotType(isSoldier, isSick);
	}

	@Override
	public void showMsg(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	@Override
	public void exitMenu() {
		try {
			for (ViewListener viewListener : allListeners) {
				viewListener.save();
			}
		} catch (Exception e) {

		}

	}

	public ArrayList<String> getPartyNameList() {
		return allListeners.get(0).askPartyNames();
	}

	@Override
	public void loadData() {
		for (ViewListener viewListener : allListeners) {
			viewListener.load();
		}

	}

	@Override
	public void viewAskToCreateElection(LocalDate date) {
		for (ViewListener viewListener : allListeners) {
			viewListener.viewCreateElection(date);
		}
	}

	@Override
	public void electionCreated() {
		hideSideElements();
	}

	@Override
	public void endElection() {
		showMsg("Finish the ELECTIONS");
	}

}
