package id318449782_id209544642;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Vector;

import id318449782_id209544642.BallotBox.BallotType;
import id318449782_id209544642.PoliticalParty.ePoliticalStand;

public class Elections implements Serializable{
	private Set<Citizen> voters;
	private Set<PoliticalParty> parties;
	
	private Set<BallotBox<RegularCitizen>> regCitizenBallotBoxes;
	private Set<BallotBox<Soldier>> soldierBallotBoxes;
	private Set<BallotBox<CoronaSickCitizen>> sickBallotBoxes;
	private Set<BallotBox<SickSoldier>> sickSoldierBallotBoxes;
	
	private LocalDate dateOfElection;
	private boolean voteOccurred = false;

	public Elections() {
		this(new Set<Citizen>(), new Set<PoliticalParty>(), LocalDate.now());
	}

	public Elections(LocalDate date){
		this(new Set<Citizen>(), new Set<PoliticalParty>(), date);
	}

	public Elections(Set<Citizen> voters, Set<PoliticalParty> parties, LocalDate date) {
		this.parties = parties;
		this.voters = voters;
		this.dateOfElection = date;
		voteOccurred = false;
		
		
		this.regCitizenBallotBoxes = new Set<BallotBox<RegularCitizen>>();
		this.soldierBallotBoxes = new Set<BallotBox<Soldier>>();
		this.sickBallotBoxes = new Set<BallotBox<CoronaSickCitizen>>();
		this.sickSoldierBallotBoxes = new Set<BallotBox<SickSoldier>>();
	}

	public void addPoliticalParty(PoliticalParty party) throws AlreadyExistException{
		boolean success = parties.add(party);
					
		if(!success) {
			throw new AlreadyExistException();
		}
		addPartyToAllBoxes();
	}

	public void addPoliticalParty(String name, ePoliticalStand stand, LocalDate creationDate) throws AlreadyExistException{
		addPoliticalParty(new PoliticalParty(name, stand, creationDate));
	}
	
	public <T extends Citizen> void addBallotBox(BallotBox<T> box, BallotType type) throws AlreadyExistException{
		boolean success = true;
		switch (type) {
		case RegularCitizen:
			success = regCitizenBallotBoxes.add((BallotBox<RegularCitizen>)box);
			break;
		case SickCitizen:
			success = sickBallotBoxes.add((BallotBox<CoronaSickCitizen>)box);
			break;
		case SickSoldier:
			success =  sickSoldierBallotBoxes.add((BallotBox<SickSoldier>)box);
			break;
		case Soldier:
			success = soldierBallotBoxes.add((BallotBox<Soldier>)box);
			break;
		} 
		
		if(!success) {
			throw new AlreadyExistException();
		}
	}
	
	public void addBallotBox(String address, BallotType type) throws AlreadyExistException, InputMismatchException{
		BallotBox ballotBox = null;
		switch(type){
			case SickCitizen:
				ballotBox = new BallotBox<CoronaSickCitizen>(address, getNumOfParties());
				break;
			case Soldier:
				ballotBox = new BallotBox<Soldier>(address, getNumOfParties());
				break;
			case SickSoldier:
				ballotBox = new BallotBox<SickSoldier>(address, getNumOfParties());
				break;
			case RegularCitizen:
				ballotBox = new BallotBox<RegularCitizen>(address, getNumOfParties());
				break;
				default:
				ballotBox = null;
				break;
		}
		
		addBallotBox(ballotBox, type);
	}

	public void addCitizen(Citizen citizen) throws AlreadyExistException{
	  	boolean success = voters.add(citizen);;
		
		if(!success) {
			throw new AlreadyExistException();
		}
	}
	public void addCitizen(String name, String id, int birthYear, boolean isSick, int sickDays, int ballotBoxIndex)
	  throws InvalidIdException, NullPointerException, CantVoteException, AlreadyExistException{
		Citizen citizen;
		if(Soldier.isSoldierAge(birthYear)) {
			if(isSick) {
				citizen = new SickSoldier(name, id, birthYear, sickSoldierBallotBoxes.get(ballotBoxIndex), false, sickDays);//TODO change the carry weapon
			}
			else {				
				citizen = new Soldier(name, id, birthYear, soldierBallotBoxes.get(ballotBoxIndex), false);//TODO change the carry weapon
			}
		}
		else {
			if(isSick) {
				citizen = new CoronaSickCitizen(name, id, birthYear, sickDays, sickBallotBoxes.get(ballotBoxIndex));
			}
			else {				
				citizen = new RegularCitizen(name, id, birthYear, regCitizenBallotBoxes.get(ballotBoxIndex));
			}
		}
		
		addCitizen(citizen);
	}
	
	public void addCanadid(String name, String id, int birthYear, int ballotBoxIndex, int partyIndex, int primeriesPosition)
	  throws InvalidIdException, NotAdultException, AlreadyExistException, NullPointerException, CantVoteException{
		if(!Soldier.isSoldierAge(birthYear)) {
			addCitizen(new Candid(name, id, birthYear, regCitizenBallotBoxes.get(ballotBoxIndex), parties.get(partyIndex), primeriesPosition));
		}else {
			throw new NotAdultException("A soldier can't be a canidate for a Political Party");
		}
	}

	public Set<PoliticalParty> getParties() {
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

	/*public boolean checkPartyExist(PoliticalParty party) {
		return getPartyIndex(party) >= 0;
	}*/

	public int getNumOfParties() {
		return parties.size();
	}

	public int getNumOfTypeBallotBox(BallotType type){
		return getBallotBoxes(type).size();
	}

	public int getNumOfCitizens(){
		return voters.size();
	}
	public LocalDate getDateOfElection() {
		return dateOfElection;
	}
	
	public int getPartyIndex(PoliticalParty party) {
		return parties.indexOf(party);
	}
	
	public int getBallotBoxIndex(BallotBox box, BallotType type) {
		return getBallotBoxes(type).indexOf(box);
	}
	
	public BallotBox<?> getBallotBox(int index, BallotType type){
		return getBallotBoxes(type).get(index);
	}
	/*public boolean checkBallotBoxExist(BallotBox box) {
		return getBallotBoxIndex(box) >= 0;
	}*/
	
	/*public boolean checkCitizenExists(Citizen citizen){
		return Util.indexOf(voters, citizen) >= 0;
	}*/
	
	public Set<Citizen> getVoters() {
		return voters;
	}
	
	public Set<BallotBox<?>> getBallotBoxes(BallotType type) {
		Set<BallotBox<?>> set = new Set<BallotBox<?>>();
		
		switch (type) {
		case RegularCitizen:
			set.addAll(regCitizenBallotBoxes);
			/*for (BallotBox<RegularCitizen> box : regCitizenBallotBoxes) {
				set.add(box);
			}*/
			break;
		case SickCitizen:
			set.addAll(sickBallotBoxes);
			break;
		case SickSoldier:
			set.addAll(sickSoldierBallotBoxes);
			break;
		case Soldier:
			set.addAll(soldierBallotBoxes);
			break;
		default:
			break;
		}
		
		return set;
	}
	public Set<BallotBox> getAllBallotBoxes(){
		Set<BallotBox> output = new Set<BallotBox>();
		output.addAll(getBallotBoxes(BallotType.RegularCitizen));
		output.addAll(getBallotBoxes(BallotType.SickCitizen));
		output.addAll(getBallotBoxes(BallotType.SickSoldier));
		output.addAll(getBallotBoxes(BallotType.Soldier));	
		return output;
	}

	public String countVotesInBallotBox(BallotBox ballotBox){
		StringBuffer str = new StringBuffer("In Ballot box with id " + ballotBox.getId() + ", " + ballotBox.votersPrecentage() + "% of " + ballotBox.getNumOfCitizens() + " voters voted as followed: \n");
		for(int i=0; i < getNumOfParties(); i++){
			str.append("for the party: ").append(parties.get(i).getName()).append(" voted ").append(ballotBox.getVotes().get(i)).append("\n");
		}
		return str.toString();
	}
	public <T extends Citizen> String countVotesInBallotType(Set<BallotBox<T>> boxes) {
		StringBuffer str = new StringBuffer();
		
		Iterator<BallotBox<T>> iter = boxes.iterator();
		
		while(iter.hasNext()) {
			str.append(countVotesInBallotBox(iter.next())).append("\n");
		}
		
		return str.toString();
	}
	
	public String votesInEveryBallotBox(){
		StringBuffer str = new StringBuffer();
		
		str.append(countVotesInBallotType(regCitizenBallotBoxes));
		str.append(countVotesInBallotType(sickBallotBoxes));
		str.append(countVotesInBallotType(soldierBallotBoxes));
		str.append(countVotesInBallotType(sickSoldierBallotBoxes));
		
		return str.toString();
	}
	
	public <T extends Citizen> void countAllVotesInType(Set<BallotBox<T>> boxes, int[] votes) {	
		for (BallotBox<T> box : boxes) {
			for (int i = 0; i < votes.length; i++) {
				votes[i] += box.getVotes().get(i);
			}
		}
	}

	public String countAllVotes(){
		int[] votes = new int[getNumOfParties()];
		
		countAllVotesInType(regCitizenBallotBoxes, votes);
		countAllVotesInType(soldierBallotBoxes, votes);
		countAllVotesInType(sickBallotBoxes, votes);
		countAllVotesInType(sickSoldierBallotBoxes, votes);

		StringBuffer str = new StringBuffer("In the ELECTIONS the votes were as following: \n");
		for(int i=0; i < getNumOfParties(); i++){
			str.append(i).append(") for the party: ").append(parties.get(i).getName()).append(" voted ").append(votes[i]).append("\n");
		}

		return str.toString();
	}
	
	private void addPartyToAllBoxes() {
		Set<BallotBox> boxes = getAllBallotBoxes();
		for (BallotBox box : boxes) {
			box.addParty();
		}
	}
	
	/*public void resetAllVotes(){
		int numOfBallotBox = getNumOfBallotBox();
		for (int i = 0; i < numOfBallotBox; i++) {
			ballotBoxes[i].resetVotes();
		}
		
		for (BallotBox<RegularCitizen> box : regCitizenBallotBoxes) {
			box.resetVotes();
		}
	}*/

	public void setVoteOccurred(boolean voteOccurred) {
		this.voteOccurred = voteOccurred;
	}

	public boolean isVoteOccurred() {
		return voteOccurred;
	}
	
}
