package id318449782_id209544642;

import java.util.ArrayList;
import java.util.Date;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPoliticalParty(String name, ePoliticalStand stand, Date creationDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCanadid(String name, String id, int birthYear, int partyIndex, int primeriesPosition) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String askAllBallotBoxes() {
		return theModel.showAllBallotBoxes();
	}

	@Override
	public String askBallotBoxList(BallotType ballotBoxType) {
		 theModel.getBallotBoxesByType(ballotBoxType);
		 return null;
	}

	@Override
	public String askPoliticalPartyList() {
		return theModel.showAllParties();
	}

	@Override
	public String askCitizenList() {
		return theModel.showAllCitizens();
	}

	@Override
	public String askElectionResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void viewStartElections() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vote(int politicalPartyIndex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewNewElection() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(String filePath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load(String filePath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modelUpdateAddSuccessfuly(String msg) {
		theView.showMsg(msg);
	}

	@Override
	public void modelStartElection() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modelFinishElections() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int nextVoter(String citizen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void newElection() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modelShowSuccessfully(String data) {
		// TODO Auto-generated method stub
		
	}

	

}
