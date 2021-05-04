package id318449782_id209544642;

import java.util.Arrays;

public class BallotBox {
	private static int globalId = 1;
	private int id;

	private String address;
	private Citizen[] voters;

	private int[] votes;

	public final static int MIN_TO_VOTE = 18;
	
	public BallotBox(String address, int numOfParties, Citizen[] voters) {
		this.id = globalId++;
		this.address = address;
		if (voters == null) {
			this.voters = new Citizen[1];
		} else {
			this.voters = voters;
		}

		if (numOfParties > 0) {
			votes = new int[numOfParties];
		} else {
			votes = new int[0];
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

	public int[] getVotes() {
		return Arrays.copyOf(votes, votes.length);
	}

	public int getNumOfCitizens() {
		for(int i = 0; i < voters.length; i++){
			if(voters[i] == null){
				return i;
			}
		}
		return voters.length;
	}

	public void addParty() {
		votes = Arrays.copyOf(votes, votes.length + 1);
	}

	public void addCitizen(Citizen citizen) throws CantVoteException {
		try{
			canVote(citizen);
		}
		catch(CantVoteException e){
			throw e;
		}

		voters = (Citizen[]) Util.addToLast(voters, citizen);
	}

	protected void canVote(Citizen citizen) throws CantVoteException {
		if (citizen.isQurentined()) {
			throw new CantVoteException("The person is qurentined and this ballot box is not for qurentined citizen");
		} 
		else if (citizen.getAge() < MIN_TO_VOTE){
			throw new CantVoteException("The citizen is below valid voting age");
		}
	}

	public void vote(int partyPostion) throws CantVoteException {
		if(partyPostion >= 0 && partyPostion < votes.length) {
			this.votes[partyPostion]++;
		}
		else {
			throw new CantVoteException("Party does not exists");
		}
	}

	public double votersPresentage() {
		int voteCount = 0;
		for (int i = 0; i < votes.length; i++) {
			voteCount += votes[i];
		}

		return (double)voteCount / getNumOfCitizens() * 100;
	}
	
	public void resetVotes(){
		for (int i = 0; i < votes.length; i++) {
			votes[i] = 0;
		}
	}

	@Override
	public String toString() {
		return "Ballotbox id: " + this.id + ", in address " + this.address;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof BallotBox)) {
			return false;
		}
		BallotBox temp = (BallotBox) obj;
		if (!temp.getAddress().equals(this.getAddress())) {
			return false;
		}
		return this.id == ((BallotBox) (obj)).id;
	}
}
