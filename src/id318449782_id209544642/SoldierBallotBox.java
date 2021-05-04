package id318449782_id209544642;

public class SoldierBallotBox extends BallotBox{

	public SoldierBallotBox(String address, int numOfParties) {
		super(address, numOfParties);
	}
	
	@Override
	protected void canVote(Citizen citizen) throws CantVoteException {
		super.canVote(citizen);

		if(!(citizen instanceof Soldier)){
			throw new CantVoteException("The citizen is not a soldier");
		}
	}
	
	@Override
	public String toString() {
		return "Soldier " + super.toString();
	}
	
	@Override
	public boolean equals(Object other) {
		return super.equals(other);
	}
}
