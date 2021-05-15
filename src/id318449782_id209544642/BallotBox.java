package id318449782_id209544642;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Vector;

public class BallotBox <T extends Citizen> implements Serializable{
	
	public enum BallotType{
		RegularCitizen, SickCitizen, Soldier, SickSoldier
	}
	
	private static int globalId = 1;
	private int id;

	private String address;
	private Set<T> voters;

//	private int[] votes;
	private Vector<Integer> votes;

	public final static int MIN_TO_VOTE = 18;
	
	public BallotBox (String address, int numOfParties, Set<T> voters) {
		this.id = globalId++;
		this.address = address;
		if (voters == null) {
			this.voters = new Set<T>();
		} else {
			this.voters = voters;
		}

		if (numOfParties > 0) {
			votes = new Vector<Integer>(numOfParties);
		} else {
			votes = new Vector<Integer>();
		}
	}

	public BallotBox(String address, int numOfParties) {
		this(address, numOfParties, null);
	}

	public int getId() {
		return id;
	}

	public String getAddress() {
		return address;
	}

	public Vector<Integer> getVotes() {
		return votes;
	}
	
	public int getNumOfCitizens() {
		return voters.size();
	}

	public void addParty() {
		votes.add(0);
	}

	public void addCitizen(T citizen) {
		voters.add(citizen);
	}
	public Set<T> getVoters() {
		return voters;
	}

	public void vote(T citizen, int partyPostion) throws CantVoteException {
		if(!voters.contains(citizen))
			throw new CantVoteException("Citizen cannot vote in this ballot box");
			
		if(partyPostion >= 0 && partyPostion < votes.size()) {
			votes.set(partyPostion, votes.get(partyPostion) + 1);
		}
		else {
			throw new CantVoteException("Party does not exists");
		}
	}

	public double votersPrecentage() {
		int voteCount = 0;
		for (int i = 0; i < votes.size(); i++) {
			voteCount += votes.get(i);
		}

		return (double)voteCount / getNumOfCitizens() * 100;
	}
	
	/*public void resetVotes(){
		for (int i = 0; i < votes.size(); i++) {
			votes.set(i, 0);
		}
	}*/

	@Override
	public String toString() {
		return "Ballotbox id: " + this.id + ", in address " + this.address;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof BallotBox<?>)) {
			return false;
		}
		BallotBox<?> temp = (BallotBox<?>) obj;			
		
		if (!temp.getAddress().equals(this.getAddress())) {
			return false;
		}
		return this.id == temp.id;
		
	}
}
