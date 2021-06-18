package id318449782_id209544642;

public class CoronaSickCitizen extends Citizen implements Sickable {
	
	private int numOfSickDays;

	public CoronaSickCitizen(String name, String id, int birthYear, int numOfSickDays, BallotBox<CoronaSickCitizen> ballotBox)
			throws InvalidIdException, NullPointerException, CantVoteException, NotAdultException {
		super(name, id, birthYear);
		
		if (ballotBox != null)
			this.ballotBox = ballotBox;
		else
			throw new NullPointerException("Ballot box is null!");
		
		ballotBox.addCitizen(this);
		
		this.numOfSickDays = numOfSickDays;
	}
	
	public void setNumOfSickDays(int numOfSickDays) {
		this.numOfSickDays = numOfSickDays;
	}

	@Override
	public int getSickDays() {
		return numOfSickDays;
	}
	@Override
	public boolean isSick() {
		return getSickDays() != 0;
	}
}
