package listeners;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;

import id318449782_id209544642.AlreadyExistException;
import id318449782_id209544642.BallotBox.BallotType;
import id318449782_id209544642.PoliticalParty.ePoliticalStand;

public interface ViewListener {

	//Add
	void addBallotBox(String street, BallotType type) throws InputMismatchException, AlreadyExistException ;
	void addCitizen(String name, String id, int birthYear, boolean isSick, int sickDays, boolean isSoldier, boolean carryWeapon, int ballotBoxIndex);
	void addPoliticalParty(String name, ePoliticalStand stand, LocalDate creationDate) throws AlreadyExistException, InputMismatchException;
	void addCanadid(String name, String id, int birthYear, int ballotBoxIndex, int partyIndex, int primeriesPosition);
	
	//Show
	ArrayList<String> askAllBallotBoxes();
	ArrayList<String> askBallotBoxList(BallotType ballotBoxType);
	ArrayList<String> askPoliticalPartyList();
	ArrayList<String> askCitizenList();
	String askElectionResult();
	
	//Elections
	void viewStartElections();
	void vote(String politicalParty);
	void viewCreateElection(LocalDate date);
	boolean viewAskElectionOccured();
	
	//Save
	void save();
	void load();
	
	BallotType askBallotType(boolean isSoldier, boolean isSick);
	ArrayList<String> askPartyNames();
}
