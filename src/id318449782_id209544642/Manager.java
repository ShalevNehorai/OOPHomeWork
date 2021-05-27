package id318449782_id209544642;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Vector;

import id318449782_id209544642.BallotBox.BallotType;
import id318449782_id209544642.PoliticalParty.ePoliticalStand;

public class Manager{
	Vector<Elections> elections;

	public Manager() {
		elections = new Vector<Elections>();
	}

	public void createNewElections(LocalDate date) {        
        Elections newElection = new Elections(date);
        
		try {
			newElection.addBallotBox("27 Habarzel St. 69710, Tel Aviv", BallotType.RegularCitizen);
			newElection.addBallotBox("Isakov 5 Lod", BallotType.RegularCitizen);
			newElection.addBallotBox("9, Galis St. , Petah Tikva", BallotType.SickCitizen);
			newElection.addBallotBox("Rajer Kereat Shmona", BallotType.Soldier);
			newElection.addBallotBox("Michad Ani 12", BallotType.SickSoldier);

			newElection.addPoliticalParty("White ticket", ePoliticalStand.CENTER, LocalDate.now());
			newElection.addPoliticalParty("Smart Wanderers", ePoliticalStand.LEFT, LocalDate.now());
			newElection.addPoliticalParty("Daring Pigeons", ePoliticalStand.RIGHT, LocalDate.now());
			newElection.addPoliticalParty("DisneyForEver", ePoliticalStand.LEFT, LocalDate.now());
			
			newElection.addCanadid("Noach Tovia", "153724950", 1950, 0, 1, 0);
			newElection.addCanadid("Achinoam Noya", "498573741", 1980, 0, 1, 1);

			newElection.addCanadid("Amram Devorah", "439218756", 1964, 1, 2, 0);//////
			newElection.addCanadid("Nessa Alma", "210635714", 1972, 0, 2, 1);

			newElection.addCanadid("Uria Chaya", "983248513", 1949, 0, 3, 0);
			newElection.addCanadid("Beracha Dalya", "028519635", 1956, 1, 3, 1);
			
			newElection.addCitizen("ilan", "421635247", 1997, false, 0, 0);
			newElection.addCitizen("Shalev", "102642685", 1980, false, 0, 1);
			newElection.addCitizen("Shaked", "107548653", 2001, false, 0, 0);
			newElection.addCitizen("Moshe", "206354915", 2002, true, 15,  0);
			newElection.addCitizen("Shlomo", "132646789", 1990, true, 1, 0);
			
			elections.add(newElection);

		} catch (Exception e) {
			System.out.println();
			System.out.println(e.getMessage());
			System.out.println();
		}
	}

	public void createNewElections() {
		createNewElections(LocalDate.now());
	}

	public void addParty(String partyName, ePoliticalStand partyStand, LocalDate creationDate)
			throws AlreadyExistException {
		elections.lastElement().addPoliticalParty(partyName, partyStand, creationDate);
	}

	public void addBallotBox(String ballotBoxAddress, BallotType type) throws AlreadyExistException, InputMismatchException {
		elections.lastElement().addBallotBox(ballotBoxAddress, type);
	}

	public void addCitizen(String name, String id, int birthYear, boolean isSick, int sickDays, int ballotBoxIndex)
			throws InvalidIdException, NullPointerException, CantVoteException, AlreadyExistException {
		elections.lastElement().addCitizen(name, id, birthYear, isSick, sickDays, ballotBoxIndex);
	}

	public void addCandid(String name, String id, int birthYear, boolean isQurentined, int ballotBoxIndex,
			int politicalPartyIndex, int primeriesPosition) throws InvalidIdException, NullPointerException,
			AlreadyExistException, CantVoteException, NotAdultException {
		elections.lastElement().addCanadid(name, id, birthYear, ballotBoxIndex, politicalPartyIndex, primeriesPosition);
	}
	
	public int getNumOfParties() {
		return elections.lastElement().getNumOfParties();
	}

	public String showAllParties() {
		StringBuffer str = new StringBuffer();
		int numOfParties = elections.lastElement().getNumOfParties();
		for (int i = 0; i < numOfParties; i++) {
			str.append(i).append(") ").append(elections.lastElement().getParties().get(i).getName()).append("\n");
		}
		return str.toString();
	}

	public String showAllTypeBallotBoxes(BallotType type) {
		StringBuffer str = new StringBuffer();
		int numOfBallotBoxes = elections.lastElement().getNumOfTypeBallotBox(type);
		for (int i = 0; i < numOfBallotBoxes; i++) {
			str.append(type.toString()).append("BallotBox - ").append(i).append(") ");
			str.append(elections.lastElement().getBallotBoxes(type).get(i).toString()).append("\n");
		}
		return str.toString();
	}
	public Set<BallotBox<?>> getBallotBoxesByType(BallotType type){
		return elections.lastElement().getBallotBoxes(type);
		
	}
	public String showAllBallotBoxes(){
		StringBuffer output = new StringBuffer();
		for (BallotType type : BallotType.values()) {
			output.append(showAllTypeBallotBoxes(type));
		}
		return output.toString();
	}

	public String showAllCitizens() {
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < elections.lastElement().getNumOfCitizens(); i++) {
			str.append(i).append(") ").append(elections.lastElement().getVoters().get(i).toString()).append("\n");
		}
		return str.toString();
	}
	
	public BallotBox<? extends Citizen> getBallotBox(int index, BallotType type) {
	 	return elections.lastElement().getBallotBox(index, type);
	}

	public int getNumOfBallotBoxes(BallotType type) {
		return elections.lastElement().getNumOfTypeBallotBox(type);
	}

	public String getVotesInAllBallotBoxes(int index) {
        if (index < 0 || index > elections.size()) {
			throw new IndexOutOfBoundsException("the index " + index + "is not in range of " + elections.size());
		}
		return elections.get(index).votesInEveryBallotBox();
	}

	public String getElectionsResults() {
		return getElectionsResults(elections.size());
	}

	public String getElectionsResults(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > elections.size()) {
			throw new IndexOutOfBoundsException();
		}
		return "Election at " + elections.get(index).getDateOfElection().toString() + " Results:\n" + elections.get(index).countAllVotes();
	}

	public Set<Citizen> getCitizens() {
		return elections.lastElement().getVoters();
	}

	public Elections getElections(int electionIndex) {
		return elections.get(electionIndex);
	}

	public Elections getCurrentElection() {
		return elections.lastElement();
	}

	public void endElections() {
		elections.lastElement().setVoteOccurred(true);
	}

    public String getLastVotedElectionsResults() throws IndexOutOfBoundsException {
        return getElectionsResults(getLastVotedElectionsIndex());
    }

    public String getLastVotesInAllBallotBoxes() throws IndexOutOfBoundsException{
        return getVotesInAllBallotBoxes(getLastVotedElectionsIndex());
    }

	public int getLastVotedElectionsIndex() {
		for (int i = elections.size()-1; i >= 0; i--) {
			if(elections.get(i).isVoteOccurred()) {
				return i;
			}
		}
		return -1;
	}

	public Vector<Elections> getVotedElections() {
		return elections;
	}
	
	public String showBallotBoxType(){
		int bBoxType = BallotType.values().length;
		StringBuffer output = new StringBuffer();
		for (int i = 0; i < bBoxType; i++) {
			output.append(i).append(" - ").append(BallotType.values()[i]).append("\n");
		}
		return output.toString();
	}
	
	public BallotType getBallotType(int birthYear, boolean isSick) {
		if(Soldier.isSoldierAge(birthYear)) {
			if(isSick) {
				return BallotType.SickSoldier;
			}
			else {
				return BallotType.Soldier;
			}
		}
		else {
			if(isSick) {
				return BallotType.SickCitizen;
			}
			else {
				return BallotType.RegularCitizen;
			}
		}
	}
	
	public void saveAsBinaryFile(String fileAddress) throws FileNotFoundException, IOException {
		ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream(fileAddress));
		outFile.writeObject(elections);
		outFile.close();
	}
	
	public void readBinaryFile(String fileAddress) throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream inputFile = new ObjectInputStream(new FileInputStream(fileAddress));
		elections = (Vector<Elections>)inputFile.readObject();
		inputFile.close();
	}
	public boolean isEmpty(){
		return elections.isEmpty();
	}
}
