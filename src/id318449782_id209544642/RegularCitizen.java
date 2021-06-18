package id318449782_id209544642;

public class RegularCitizen extends Citizen {
	
	public RegularCitizen(String name, String id, int birthYear, BallotBox<RegularCitizen> ballotBox)
			throws InvalidIdException, NullPointerException, CantVoteException, NotAdultException {
		super(name, id, birthYear);
		
		if (ballotBox != null)
			this.ballotBox = ballotBox;
		else
			throw new NullPointerException("Ballot box is null!");
		
		ballotBox.addCitizen(this);
	}

}
