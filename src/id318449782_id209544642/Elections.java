package id318449782_id209544642;

import java.time.LocalDate;
import java.util.InputMismatchException;

import id318449782_id209544642.PoliticalParty.ePoliticalStand;

public class Elections {
	private Citizen[] voters;
	private PoliticalParty[] parties;
	private BallotBox[] ballotBoxes;
	private LocalDate dateOfElection;
	private boolean voteOccurred = false;

	public Elections() {
		this(new Citizen[1], new PoliticalParty[1], new BallotBox[1], LocalDate.now());
	}

	public Elections(LocalDate date){
		this(new Citizen[1], new PoliticalParty[1], new BallotBox[1], date);
	}

	public Elections(Citizen[] voters, PoliticalParty[] parties, BallotBox[] boxes, LocalDate date) {
		this.parties = parties;
		this.ballotBoxes = boxes;
		this.voters = voters;
		this.dateOfElection = date;
		voteOccurred = false;
	}

	public void addPoliticalParty(PoliticalParty party) throws AlreadyExistException{
		if (!checkPartyExist(party) ) {
			parties = (PoliticalParty[]) Util.addToLast(parties, party);
			for (int i = 0; i < ballotBoxes.length; i++) {
				if(ballotBoxes[i] != null) {
					ballotBoxes[i].addParty();
				}
			}
		}else {
			throw new AlreadyExistException();
		}
	}

	public void addPoliticalParty(String name, ePoliticalStand stand, LocalDate creationDate) throws AlreadyExistException{
		addPoliticalParty(new PoliticalParty(name, stand, creationDate));
	}
	public void addBallotBox(BallotBox box) throws AlreadyExistException{
		if(checkBallotBoxExist(box)) {
			throw new AlreadyExistException();
		}
		else {
			ballotBoxes = (BallotBox[])Util.addToLast(ballotBoxes, box);
		}
	}
	public void addBallotBox(String address, char type) throws AlreadyExistException, InputMismatchException{
		BallotBox ballotBox;
		switch(type){
			case 'c':
				ballotBox = new CoronaBallotBox(address, getNumOfParties());
				break;
			case 's':
				ballotBox = new SoldierBallotBox(address, getNumOfParties());
				break;
			case 'r':
				ballotBox = new BallotBox(address, getNumOfParties());
				break;
			default:
				throw new InputMismatchException("the type " + type + "is not valid");

		}
		
		addBallotBox(ballotBox);
	}

	public void addCitizen(Citizen citizen) throws AlreadyExistException{
		if(checkCitizenExists(citizen)){
			throw new AlreadyExistException();
		}

	  	voters = (Citizen[]) Util.addToLast(voters, citizen);
	}
	public void addCitizen(String name, String id, int birthYear, boolean isQurentined, int ballotBoxIndex)
	  throws InvalidIdException, NullPointerException, CantVoteException, AlreadyExistException{
		Citizen citizen;
		if(Soldier.isSoldierAge(birthYear)) {
			citizen = new Soldier(name, id, birthYear, isQurentined, ballotBoxes[ballotBoxIndex]);
		}
		else {
			citizen = new Citizen(name, id, birthYear, isQurentined, ballotBoxes[ballotBoxIndex]);
		}
		
		addCitizen(citizen);
	}
	public void addCanadid(String name, String id, int birthYear, boolean isQurentined, int ballotBoxIndex, int partyIndex, int primeriesPosition)
	  throws InvalidIdException, NotAdultException, AlreadyExistException, NullPointerException, CantVoteException{
		if(!Soldier.isSoldierAge(birthYear)) {
			addCitizen(new Candid(name, id, birthYear, isQurentined, ballotBoxes[ballotBoxIndex], parties[partyIndex], primeriesPosition));
		}else {
			throw new NotAdultException("A soldier can't be a canidate for a Political Party");
		}
	}

	public PoliticalParty[] getParties() {
		return parties;
	}

	@Override
	public String toString() {
		StringBuffer output = new StringBuffer();
		output.append("Elections at ").append(dateOfElection.toString()).append(":\n");
//		output.append(b)

		return output.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Elections)) {
			return false;
		}
		return super.equals(obj);
	}

	public boolean checkPartyExist(PoliticalParty party) {
		return getPartyIndex(party) >= 0;
	}

	public int getNumOfParties() {
		for (int i = 0; i < parties.length; i++) {
			if (parties[i] == null) {
				return i;
			}
		}
		return parties.length;
	}

	public int getNumOfBallotBox(){
		for (int i = 0; i < ballotBoxes.length; i++) {
			if (ballotBoxes[i] == null) {
				return i;
			}
		}
		return ballotBoxes.length;
	}

	public int getNumOfCitizens(){
		for (int i = 0; i < voters.length; i++) {
			if (voters[i] == null) {
				return i;
			}
		}
		return voters.length;
	}
	public LocalDate getDateOfElection() {
		return dateOfElection;
	}
	
	public int getPartyIndex(PoliticalParty party) {
		return Util.indexOf(parties, party);
	}
	public int getBallotBoxIndex(BallotBox box) {
		return Util.indexOf(ballotBoxes, box);
	}
	public boolean checkBallotBoxExist(BallotBox box) {
		return getBallotBoxIndex(box) >= 0;
	}
	public boolean checkCitizenExists(Citizen citizen){
		return Util.indexOf(voters, citizen) >= 0;
	}
	public Citizen[] getVoters() {
		return voters;
	}
	public BallotBox[] getBallotBoxes() {
		return ballotBoxes;
	}

	public String countVotesInBallotBox(BallotBox ballotBox){
		StringBuffer str = new StringBuffer("In Ballot box with id " + ballotBox.getId() + ", " + ballotBox.votersPrecentage() + "% of " + ballotBox.getNumOfCitizens() + " voters voted as followed: \n");
		for(int i=0; i < getNumOfParties(); i++){
			str.append("for the party: ").append(parties[i].getName()).append(" voted ").append(ballotBox.getVotes()[i]).append("\n");
		}
		return str.toString();
	}
	public String votesInEveryBallotBox(){
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < getNumOfBallotBox(); i++) {
			str.append(countVotesInBallotBox(ballotBoxes[i])).append("\n");
		}
		return str.toString();
	}

	public String countAllVotes(){
		int[] votes = new int[getNumOfParties()];
		int numOfBallotBox = getNumOfBallotBox();
		for (int i = 0; i < numOfBallotBox; i++) {
			for(int j = 0; j < votes.length; j++){
				votes[j] += ballotBoxes[i].getVotes()[j];
			}
		}

		StringBuffer str = new StringBuffer("In the ELECTIONS the votes were as following: \n");
		for(int i=0; i < getNumOfParties(); i++){
			str.append(i).append(") for the party: ").append(parties[i].getName()).append(" voted ").append(votes[i]).append("\n");
		}

		return str.toString();
	}
	
	public void resetAllVotes(){
		int numOfBallotBox = getNumOfBallotBox();
		for (int i = 0; i < numOfBallotBox; i++) {
			ballotBoxes[i].resetVotes();
		}
	}

	public void setVoteOccurred(boolean voteOccurred) {
		this.voteOccurred = voteOccurred;
	}

	public boolean isVoteOccurred() {
		return voteOccurred;
	}
	
}
