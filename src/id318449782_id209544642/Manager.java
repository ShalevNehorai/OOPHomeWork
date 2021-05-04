package id318449782_id209544642;

import java.time.LocalDate;
import java.util.Arrays;

import id318449782_id209544642.PoliticalParty.ePoliticalStand;

public class Manager {
	Elections[] elections;
	int curretnElection = -1;

	public Manager() {
		elections = new Elections[1];
	}

	public void createNewElections(LocalDate date) {
        curretnElection++;
		elections = (Elections[]) Util.addToLast(elections, new Elections(date));

		try {
			elections[curretnElection].addBallotBox("27 Habarzel St. 69710, Tel Aviv", 'r');
			elections[curretnElection].addBallotBox("Isakov 5 Lod", 'r');
			elections[curretnElection].addBallotBox("9, Galis St. , Petah Tikva", 'c');
			elections[curretnElection].addBallotBox("Rajer Kereat Shmona", 's');

			elections[curretnElection].addPoliticalParty("White ticket", ePoliticalStand.CENTER, LocalDate.now());
			elections[curretnElection].addPoliticalParty("Smart Wanderers", ePoliticalStand.LEFT, LocalDate.now());
			elections[curretnElection].addPoliticalParty("Daring Pigeons", ePoliticalStand.RIGHT, LocalDate.now());
			elections[curretnElection].addPoliticalParty("DisneyForEver", ePoliticalStand.LEFT, LocalDate.now());

			elections[curretnElection].addCanadid("Noach Tovia", "153724950", 1950, false, 0, 1, 0);
			elections[curretnElection].addCanadid("Achinoam Noya", "498573741", 1980, false, 0, 1, 1);

			elections[curretnElection].addCanadid("Amram Devorah", "439218756", 1964, true, 2, 2, 0);
			elections[curretnElection].addCanadid("Nessa Alma", "210635714", 1972, false, 0, 2, 1);

			elections[curretnElection].addCanadid("Uria Chaya", "983248513", 1949, false, 0, 3, 0);
			elections[curretnElection].addCanadid("Beracha Dalya", "028519635", 1956, true, 2, 3, 1);

			elections[curretnElection].addCitizen("ilan", "421635247", 1997, true, 2);
			elections[curretnElection].addCitizen("Shalev", "102642685", 1980, false, 1);
			elections[curretnElection].addCitizen("Shaked", "107548653", 2001, false, 3);
			elections[curretnElection].addCitizen("Moshe", "206354915", 2002, true, 2);
			elections[curretnElection].addCitizen("Shlomo", "132646789", 1990, false, 0);

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
		elections[curretnElection].addPoliticalParty(partyName, partyStand, creationDate);
	}

	public void addBallotBox(String ballotBoxAddress, char type) throws AlreadyExistException {
		elections[curretnElection].addBallotBox(ballotBoxAddress, type);
	}

	public void addCitizen(String name, String id, int birthYear, boolean isQurentined, int ballotBoxIndex)
			throws InvalidIdException, NullPointerException, CantVoteException, AlreadyExistException {
		elections[curretnElection].addCitizen(name, id, birthYear, isQurentined, ballotBoxIndex);
	}

	public void addCandid(String name, String id, int birthYear, boolean isQurentined, int ballotBoxIndex,
			int politicalPartyIndex, int primeriesPosition) throws InvalidIdException, NullPointerException,
			AlreadyExistException, CantVoteException, NotAdultException {
		elections[curretnElection].addCanadid(name, id, birthYear, isQurentined, ballotBoxIndex, politicalPartyIndex,
				primeriesPosition);
	}

	public String showAllParties() {
		StringBuffer str = new StringBuffer();
		int numOfParties = elections[curretnElection].getNumOfParties();
		for (int i = 0; i < numOfParties; i++) {
			str.append(i).append(") ").append(elections[curretnElection].getParties()[i].getName()).append("\n");
		}
		return str.toString();
	}

	public String showAllBallotBoxes() {
		StringBuffer str = new StringBuffer();
		int numOfBallotBoxes = elections[curretnElection].getNumOfBallotBox();
		for (int i = 0; i < numOfBallotBoxes; i++) {
			str.append(i).append(") ").append(elections[curretnElection].getBallotBoxes()[i].toString()).append("\n");
		}
		return str.toString();
	}

	public String showAllCitizens() {
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < elections[curretnElection].getNumOfCitizens(); i++) {
			str.append(i).append(") ").append(elections[curretnElection].getVoters()[i].toString()).append("\n");
		}
		return str.toString();
	}

	public BallotBox getBallotBox(int index) {
		return elections[curretnElection].getBallotBoxes()[index];
	}

	public int getNumOfBallotBoxes() {
		return elections[curretnElection].getNumOfBallotBox();
	}

	public String getVotesInAllBallotBoxes(int index) {
        if (index < 0 || index > curretnElection) {
			throw new IndexOutOfBoundsException();
		}
		return elections[index].votesInEveryBallotBox();
	}

	public String getElectionsResults() {
		return getElectionsResults(curretnElection);
	}

	public String getElectionsResults(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > curretnElection) {
			throw new IndexOutOfBoundsException();
		}
		return elections[index].getDateOfElection().toString() + " : " + elections[index].countAllVotes();
	}

	public Citizen[] getCitizens() {
		return Arrays.copyOf(elections[curretnElection].getVoters(), elections[curretnElection].getNumOfCitizens());
	}

	public void resetVotes() {
		elections[curretnElection].resetAllVotes();
	}

	public Elections getElections(int electionIndex) {
		return elections[electionIndex];
	}

	public Elections getCurrentElection() {
		return getElections(curretnElection);
	}

	public void endElections() {
		elections[curretnElection].setVoteOccurred(true);
	}

    public String getLastVotedElectionsResults() throws IndexOutOfBoundsException {
        return getElectionsResults(getLastVotedElectionsIndex());
    }

    public String getLastVotesInAllBallotBoxes() throws IndexOutOfBoundsException{
        return getVotesInAllBallotBoxes(getLastVotedElectionsIndex());
    }

	public int getLastVotedElectionsIndex() {
		for (int i = curretnElection; i >= 0; i--) {
			if (elections[i].isVoteOccurred()) {
				return i;
			}
		}
		return -1;
	}

	public Elections[] getVotedElections() {
		Elections[] votedElections = new Elections[elections.length];
		int i = 0, j = 0;
		while (elections[i] != null) {
			if (elections[i].isVoteOccurred()) {
				votedElections[j] = elections[i];
				j++;
			}
			i++;
		}
		return votedElections;
	}

}
