package id318449782_id209544642;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Vector;

import id318449782_id209544642.BallotBox.BallotType;
import id318449782_id209544642.PoliticalParty.ePoliticalStand;
import listeners.ModelListener;

public class Manager{
	Vector<Elections> elections;
	private Vector<ModelListener> listeners;
	
	private int currentVoter = 0;
	
	public Manager() {
		elections = new Vector<Elections>();
		listeners = new Vector<ModelListener>();
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

			newElection.addCanadid("Amram Devorah", "439218756", 1964, 1, 2, 0);
			newElection.addCanadid("Nessa Alma", "210635714", 1972, 0, 2, 1);

			newElection.addCanadid("Uria Chaya", "983248513", 1949, 0, 3, 0);
			newElection.addCanadid("Beracha Dalya", "028519635", 1956, 1, 3, 1);
			
			newElection.addCitizen("ilan", "421635247", 1997, false, 0, false, false, 0);
			newElection.addCitizen("Shalev", "102642685", 1980, false, 0, false, false, 1);
			newElection.addCitizen("Shaked", "107548653", 2001, false, 0, true, true, 0);
			newElection.addCitizen("Moshe", "206354915", 2002, true, 15,  true, false, 0);
			newElection.addCitizen("Shlomo", "132646789", 1990, true, 1, false, false, 0);
			
			elections.add(newElection);

		} catch (Exception e) {
			System.out.println();
			System.out.println(e.getMessage());
			System.out.println();
		}
		fireNewElection();
	}
	
	public void registerListener(ModelListener listener){
		listeners.add(listener);
	}
	
	public void createNewElections() {
		createNewElections(LocalDate.now());
		fireNewElection();
	}
	
	private void fireNewElection(){
		for (ModelListener listener : listeners) {
			listener.newElection();
		}
		currentVoter = 0;
	}
	
	public void startElection() throws CantVoteException{
		Set<Citizen> voters =  getCitizens();
		Citizen currentCitizen = voters.get(currentVoter);
		if(currentCitizen != null){
			fireStartElection();
		}
			
		while(voters.get(currentVoter) != null){
			currentCitizen = voters.get(currentVoter);
			int voted = fireNextVoter(currentCitizen.toString());
			currentCitizen.vote(voted);	
			currentVoter++;
		}
		fireFinishElection();
	}
	private void fireStartElection(){
		for (ModelListener listener : listeners) {
			listener.modelStartElection();
		}
	}
	
	private int fireNextVoter(String citizen){
		int partyVoted = 0;
		for (ModelListener listener : listeners) {
			partyVoted = listener.nextVoter(citizen);
		}
		return partyVoted;
	}
	
	public void addParty(String partyName, ePoliticalStand partyStand, LocalDate creationDate)
			throws AlreadyExistException, InputMismatchException {
		elections.lastElement().addPoliticalParty(partyName, partyStand, creationDate);
		fireAdditionData("Political Party added successfuly");
	}
	
	public void addBallotBox(String ballotBoxAddress, BallotType type) {
		String endMsg = "Error ocured";
		try {
			elections.lastElement().addBallotBox(ballotBoxAddress, type);
			endMsg = "BallotBox added successfuly";
		}
		catch (InputMismatchException e ) {
			endMsg = "Invalid input enterd, didnt add the ballot box";
		}
		catch (AlreadyExistException e) {
			endMsg = "this ballot box allready exists, cant add another one";
		}
		catch (Exception e) {
			endMsg = "some errors ocured, didnt add the ballot box";
		}
		finally {
			fireAdditionData(endMsg);
		}
	}

	public void addCitizen(String name, String id, int birthYear, boolean isSick, int sickDays, boolean isSoldier, boolean isCarryWeapon, int ballotBoxIndex) {
		String msg = "";
		try {
			elections.lastElement().addCitizen(name, id, birthYear, isSick, sickDays, isSoldier, isCarryWeapon, ballotBoxIndex);
			msg = "Citizen added successfuly";
		} catch (NullPointerException e) {
			msg = e.getMessage();
		} catch (InvalidIdException e) {
			msg = "Id must be 9 digits long";
		} catch (CantVoteException e) {
			msg = "the citizen cant vote in the selcted ballot box, person not added";
		} catch (AlreadyExistException e) {
			msg = e.getMessage() + " new citizen didnt added";
		} catch (NotAdultException e) {
			msg = e.getMessage() + " new citizen didnt added";
		} catch(InputMismatchException e) {
			msg = e.getMessage() + " new citizen didnt added";
		}
		finally {
			fireAdditionData(msg);
		}
	}

	public void addCandid(String name, String id, int birthYear, int ballotBoxIndex,
			int politicalPartyIndex, int primeriesPosition) {
		String msg = "";
		try {
			elections.lastElement().addCanadid(name, id, birthYear, ballotBoxIndex, politicalPartyIndex, primeriesPosition);
			msg = "Candid added successfuly";
		} catch (NullPointerException e) {
			msg = e.getMessage();
		} catch (InvalidIdException e) {
			msg = "Id must be 9 digits long";
		} catch (NotAdultException e) {
			msg = e.getMessage() + " new candid didnt added";
		} catch (AlreadyExistException e) {
			msg = e.getMessage() + " new candid didnt added";
		} catch (CantVoteException e) {
			msg = "the citizen cant vote in the selcted ballot box, candid not added";
		} catch(ArrayIndexOutOfBoundsException e) {
			msg = "primaris position cant be negative or empty, didnt add candid";
		} catch(InputMismatchException e) {
			msg = e.getMessage();
		}
		finally {
			fireAdditionData(msg);
		}
	}
	
	private void fireAdditionData(String msg){
		for (ModelListener listener : listeners) {
			listener.modelUpdateAddSuccessfuly(msg);
		}
	} 
	
	public int getNumOfParties() {
		return elections.lastElement().getNumOfParties();
	}

	/*public String showAllParties() {
		StringBuffer str = new StringBuffer();
		int numOfParties = elections.lastElement().getNumOfParties();
		for (int i = 0; i < numOfParties; i++) {
			str.append(i).append(") ").append(elections.lastElement().getParties().get(i).getName()).append("\n");
		}
		String output = str.toString();
		fireShowData(output);
		return output;
	}*/
	
	public ArrayList<String> getAllParties(){
		ArrayList<String> list = new ArrayList<String>();
		Set<PoliticalParty> parties = elections.lastElement().getParties();
		for (PoliticalParty party : parties) {
			list.add(party.toString());
		}
		return list;
	}
	
	public ArrayList<String> getPartyNames(){
		ArrayList<String> list = new ArrayList<String>();
		Set<PoliticalParty> parties = elections.lastElement().getParties();
		for (PoliticalParty party : parties) {
			list.add(party.getName());
		}
		return list;
	}
	/*public String showAllTypeBallotBoxes(BallotType type) {
		StringBuffer str = new StringBuffer();
		int numOfBallotBoxes = elections.lastElement().getNumOfTypeBallotBox(type);
		for (int i = 0; i < numOfBallotBoxes; i++) {
			str.append(type.toString()).append("BallotBox - ").append(i).append(") ");
			str.append(elections.lastElement().getBallotBoxes(type).get(i).toString()).append("\n");
		}
		String output = str.toString();
		fireShowData(output);
		return output;
	}*/
	public ArrayList<String> getBallotBoxes(BallotType type){
		ArrayList<String> list = new ArrayList<String>();
		Set<BallotBox<?>> ballots = elections.lastElement().getBallotBoxes(type);
		for (BallotBox<?> ballotBox : ballots) {
			list.add(type.toString() + " " + ballotBox.toString());
		}
		return list;
	}
	public Set<BallotBox<?>> getBallotBoxesByType(BallotType type){
		return elections.lastElement().getBallotBoxes(type);
		
	}
	/*public String showAllBallotBoxes(){
		StringBuffer str = new StringBuffer();
		for (BallotType type : BallotType.values()) {
			str.append(showAllTypeBallotBoxes(type));
		}
		String output = str.toString();
		fireShowData(output);
		return output;
	}*/	
	
	public ArrayList<String> getAllBallotBoxes(){
		ArrayList<String> list = new ArrayList<String>();
		for (BallotType type : BallotType.values()) {
			for(String ballot : getBallotBoxes(type)) {
				list.add(ballot);
			}
		}
		return list;
	}

	/*public String showAllCitizens() {
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < elections.lastElement().getNumOfCitizens(); i++) {
			str.append(i).append(") ").append(elections.lastElement().getVoters().get(i).toString()).append("\n");
		}
		String output = str.toString();
		fireShowData(output);
		return output;
	}*/
	
	public ArrayList<String> getAllCitizens(){
		ArrayList<String> list = new ArrayList<String>();
		Set<Citizen> citizens = elections.lastElement().getVoters();
		for (Citizen citizen : citizens) {
			list.add(citizen.toString());
		}
		return list;
	}
	
	private void fireShowData(String data){
		for (ModelListener listener : listeners) {
			listener.modelShowSuccessfully(data);
		}
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
		StringBuffer str = new StringBuffer(elections.get(index).votesInEveryBallotBox());
		String output = str.toString();
		fireShowData(output);
		return output;
	}

	public String getElectionsResults() {
		StringBuffer str = new StringBuffer(getElectionsResults(elections.size()));
		String output = str.toString();
		fireShowData(output);
		return output;
	}

	public String getElectionsResults(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > elections.size()) {
			throw new IndexOutOfBoundsException();
		}
		
		StringBuffer str = new StringBuffer();
		str.append("Election at ").append(elections.get(index).getDateOfElection().toString());
		str.append(" Results:\n").append(elections.get(index).countAllVotes());
		String output = str.toString();
		fireShowData(output);
		return output;
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
		fireFinishElection();
	}
	
	private void fireFinishElection(){
		for (ModelListener listener : listeners) {
			listener.modelFinishElections();
		}
	}
    public String getLastVotedElectionsResults() throws IndexOutOfBoundsException {
        String output = getElectionsResults(getLastVotedElectionsIndex());
		fireShowData(output);
		return output;
    }

    public String getLastVotesInAllBallotBoxes() throws IndexOutOfBoundsException{
        String output = getVotesInAllBallotBoxes(getLastVotedElectionsIndex());
		fireShowData(output);
		return output;
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
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < bBoxType; i++) {
			str.append(i).append(" - ").append(BallotType.values()[i]).append("\n");
		}
		String output = str.toString();
		fireShowData(output);
		return output;
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
	
	public BallotType getBallotType(boolean isSoldier, boolean isSick) {
		if(isSoldier) {
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
