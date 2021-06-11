package id318449782_id209544642;

import java.util.Date;

import id318449782_id209544642.BallotBox.BallotType;
import id318449782_id209544642.PoliticalParty.ePoliticalStand;
import listeners.ModelListener;
import listeners.ViewAddListener;
import listeners.ViewElectionsListenar;
import listeners.ViewSaveListener;
import listeners.ViewShowListeners;

public class Controller
		implements ModelListener, ViewAddListener, ViewElectionsListenar, ViewSaveListener, ViewShowListeners {

	@Override
	public void askBallotBoxList(BallotType ballotBoxType) {
		// TODO Auto-generated method stub

	}

	@Override
	public void askPoliticalPartyList() {
		// TODO Auto-generated method stub

	}

	@Override
	public void askCitizenList() {
		// TODO Auto-generated method stub

	}

	@Override
	public void askElectionResult() {
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
	public void vote(int politicalPartyIndex) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addBallotBox(String street, BallotType type) {
		// TODO Auto-generated method stub

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
	public String modelUpdateAddSuccessfuly(String msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String nextVoter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void viewStartElections() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modelStartElection() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modelFinishElections() {
		// TODO Auto-generated method stub
		
	}

}
