package id318449782_id209544642;

import id318449782_id209544642.BallotBox.BallotType;
import id318449782_id209544642.PoliticalParty.ePoliticalStand;
import listeners.ViewListener;

public interface UIAbstractView {
	void registerListener(ViewListener listener);
	public void addBallotBox(String streer, BallotType type);
	public void addCitizen(/*String name, String id, int birthYear*/) /*throws InvalidIdException, NullPointerException, CantVoteException*/;
	public void addPoliticalParty(/*String name, ePoliticalStand stand*/);
	public void addCandid(/*PoliticalParty party, int primerPosition*/) /*throws InvalidIdException, NotAdultException, AlreadyExistException, NullPointerException, CantVoteException*/;
	public String showAllBallotBox();
	public String showAllPoliticalParty();
	public String showAllCitizen();
	public void startElection();
	public String showElectionResult();
	public void showMsg(String msg);
	public void exitMenu();	
}
