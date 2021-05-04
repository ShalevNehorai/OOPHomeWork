package id318449782_id209544642;

public class CoronaSickCitizen extends Citizen {
	
	private int numOfSickDays;

	public CoronaSickCitizen(String name, String id, int birthYear, int numOfSickDays, BallotBox<CoronaSickCitizen> ballotBox)
			throws InvalidIdException, NullPointerException, CantVoteException {
		super(name, id, birthYear);
		
		if (ballotBox != null)
			this.ballotBox = ballotBox;
		else
			throw new NullPointerException("Ballot box is null!");
		
		ballotBox.addCitizen(this);
		
		this.numOfSickDays = numOfSickDays;
	}
	
	public int getNumOfSickDays() {
		return numOfSickDays;
	}
	
	public void setNumOfSickDays(int numOfSickDays) {
		this.numOfSickDays = numOfSickDays;
	}
}
