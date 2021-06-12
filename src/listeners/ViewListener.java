package listeners;

import java.util.Date;
import java.util.InputMismatchException;

import id318449782_id209544642.AlreadyExistException;
import id318449782_id209544642.BallotBox.BallotType;
import id318449782_id209544642.PoliticalParty.ePoliticalStand;

public interface ViewListener {

	//Add
	void addBallotBox(String street, BallotType type) throws InputMismatchException, AlreadyExistException ;
	void addCitizen(String name, String id, int birthYear, boolean isSick, int sickDays, boolean isSoldier, boolean carryWeapon, int ballotBoxIndex);
	void addPoliticalParty(String name, ePoliticalStand stand, Date creationDate);
	void addCanadid(String name, String id, int birthYear, int partyIndex, int primeriesPosition);
	
	//Show
	String askAllBallotBoxes();
	String askBallotBoxList(BallotType ballotBoxType);
	String askPoliticalPartyList();
	String askCitizenList();
	String askElectionResult();
	
	//Elections
	void viewStartElections();
	void vote(int politicalPartyIndex);
	void viewNewElection();
	
	//Save
	void save(String filePath);
	void load(String filePath);
}
