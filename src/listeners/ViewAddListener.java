package listeners;

import java.util.Date;

import id318449782_id209544642.BallotBox.BallotType;
import id318449782_id209544642.PoliticalParty.ePoliticalStand;

public interface ViewAddListener {
	void addBallotBox(String street, BallotType type);
	void addCitizen(String name, String id, int birthYear, boolean isSick, int sickDays, boolean isSoldier, boolean carryWeapon, int ballotBoxIndex);
	void addPoliticalParty(String name, ePoliticalStand stand, Date creationDate);
	void addCanadid(String name, String id, int birthYear, int partyIndex, int primeriesPosition);
}
