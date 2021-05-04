package id318449782_id209544642;

public class CoronaBallotBox extends BallotBox {

	public CoronaBallotBox(String address, int numOfParties) {
		super(address, numOfParties);
	}
	
	@Override
	protected void canVote(Citizen citizen) throws CantVoteException {
		if(!citizen.isQurentined()){
			throw new CantVoteException("The citizen is not in qurentine");
		}
	}
	
	@Override
	public String toString() {
		return "Corona " + super.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
}
