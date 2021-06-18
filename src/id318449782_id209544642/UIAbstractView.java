package id318449782_id209544642;

import java.time.LocalDate;

import id318449782_id209544642.BallotBox.BallotType;
import id318449782_id209544642.PoliticalParty.ePoliticalStand;
import listeners.ViewListener;

public interface UIAbstractView {
	void registerListener(ViewListener listener);
	public void addBallotBox(String streer, BallotType type);
	public void addCitizen(String name, String id, int birthYear, boolean isSick, int sickDays, boolean isSoldier, boolean carryWeapon, int ballotBoxIndex);
	public void addPoliticalParty(String name, ePoliticalStand stand, LocalDate creationDate);
	public void addCandid(String name, String id, int birthYear, int ballotBoxIndex, int partyIndex, int primeriesPosition);
	public String showAllBallotBox();
	public String showAllPoliticalParty();
	public String showAllCitizen();
	public void startElection();
	public String showElectionResult();
	public BallotType getBallotType(boolean isSoldier, boolean isSick);
	public void showMsg(String msg);
	public void exitMenu();	
}
