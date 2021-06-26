package id318449782_id209544642;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;

import id318449782_id209544642.BallotBox.BallotType;
import id318449782_id209544642.PoliticalParty.ePoliticalStand;
import listeners.ModelListener;
import listeners.ViewListener;

public class Controller
		implements ModelListener,  ViewListener{
	
	private Manager theModel;
	private UIAbstractView theView;
	
	public Controller(Manager m, UIAbstractView v) {
		theModel = m;
		theView = v;
		
		theModel.registerListener(this);
		theView.registerListener(this);
	}

	@Override
	public void addBallotBox(String street, BallotType type) throws InputMismatchException, AlreadyExistException {
		theModel.addBallotBox(street, type);
	}

	@Override
	public void addCitizen(String name, String id, int birthYear, boolean isSick, int sickDays, boolean isSoldier,
			boolean carryWeapon, int ballotBoxIndex) {
		theModel.addCitizen(name, id, birthYear, isSick, sickDays, isSoldier, carryWeapon, ballotBoxIndex);
		
	}

	@Override
	public void addPoliticalParty(String name, ePoliticalStand stand, LocalDate creationDate) throws AlreadyExistException, InputMismatchException {
		theModel.addParty(name, stand, creationDate);
	}

	@Override
	public void addCanadid(String name, String id, int birthYear, int ballotBoxIndex, int partyIndex, int primeriesPosition) {
		theModel.addCandid(name, id, birthYear, ballotBoxIndex, partyIndex, primeriesPosition);
	}
	
	@Override
	public ArrayList<String> askAllBallotBoxes() {
		return theModel.getAllBallotBoxes();
	}

	@Override
	public ArrayList<String> askBallotBoxList(BallotType ballotBoxType) {
		 return theModel.getBallotBoxes(ballotBoxType);
	}

	@Override
	public ArrayList<String> askPoliticalPartyList() {
		return theModel.getAllParties();
	}

	@Override
	public ArrayList<String> askCitizenList() {
		return theModel.getAllCitizens();
	}

	@Override
	public String askElectionResult() {
		return theModel.getElectionsResults();
	}

	@Override
	public void viewStartElections() {
		theModel.startElection();
	}

	@Override
	public void vote(String politicalParty) {
		theModel.nextVoter(politicalParty);
	}

	@Override
	public void viewCreateElection(LocalDate date) {
		theModel.createNewElections(date);
	}

	@Override
	public void save() {
		theModel.saveAsBinaryFile();
	}

	@Override
	public void load() {
		theModel.readBinaryFile();
	}
	
	@Override
	public void modelUpdateAddSuccessfuly(String msg) {
		theView.showMsg(msg);
	}

	@Override
	public void modelStartElection(String firstVoter) {
		theView.nextVoter(firstVoter);	
	}

	@Override
	public void modelFinishElections() {
		// TODO Auto-generated method stub
		theView.endElection();
	}

	@Override
	public void modelNextVoter(String citizen) {
		theView.nextVoter(citizen);
	}

	@Override
	public void newElectionCreated() {
		// TODO Auto-generated method stub
		theView.electionCreated();
	}

	@Override
	public void modelShowSuccessfully(String data) {
		// TODO Auto-generated method stub
		theView.showMsg(data);
	}

	@Override
	public BallotType askBallotType(boolean isSoldier, boolean isSick) {
		return theModel.getBallotType(isSoldier, isSick);
	}

	@Override
	public ArrayList<String> askPartyNames() {
		return theModel.getPartyNames();
	}
	
	@Override
	public boolean viewAskElectionOccured() {
		// TODO Auto-generated method stub
		return theModel.isElectionOccured();
	}

}
