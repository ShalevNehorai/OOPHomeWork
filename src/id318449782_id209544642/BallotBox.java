package id318449782_id209544642;

import java.util.Arrays;

public class BallotBox <T extends Citizen>{
	private static int globalId = 1;
	private int id;

	private String address;
	private Set<T> voters;

	private int[] votes;

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
		return voters.size();
	}

	public void addParty() {
		votes = Arrays.copyOf(votes, votes.length + 1);
	}

	public void addCitizen(T citizen) {
		/*try{
			canVote(citizen);
		}
		catch(CantVoteException e){
			throw e;
		}*/
		
		voters.add(citizen);// = (Citizen[]) Util.addToLast(voters, citizen);
	}

	/*protected void canVote(T citizen) throws CantVoteException {
		if (citizen.isQurentined()) {
			throw new CantVoteException("The person is qurentined and this ballot box is not for qurentined citizen");
		} 
		else if (citizen.getAge() < MIN_TO_VOTE){
			throw new CantVoteException("The citizen is below valid voting age");
		}
	}*/
	public Set<T> getVoters() {
		return voters;
	}

	public void vote(T citizen, int partyPostion) throws CantVoteException {
		if(!voters.contains(citizen))
			throw new CantVoteException("Citizen cannot vote in this ballot box");
			
		if(partyPostion >= 0 && partyPostion < votes.length) {
			this.votes[partyPostion]++;
		}
		else {
			throw new CantVoteException("Party does not exists");
		}
	}

	public double votersPrecentage() {
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
