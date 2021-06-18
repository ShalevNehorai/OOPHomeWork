package id318449782_id209544642;

public class SickSoldier extends Soldier implements Sickable {
	
	private int sickDays;

	public SickSoldier(String name, String id, int birthYear, BallotBox<SickSoldier> ballotBox, boolean isCarryWeapon, int sickDays)
			throws InvalidIdException, CantVoteException, NullPointerException, NotAdultException {
		super(name, id, birthYear, isCarryWeapon);
		
		if (ballotBox != null)
			this.ballotBox = ballotBox;
		else
			throw new NullPointerException("Ballot box is null!");
		
		ballotBox.addCitizen(this);
		
		this.sickDays = sickDays;
	}

	@Override
	public int getSickDays() {
		return sickDays;
	}

	@Override
	public boolean isSick() {
		return getSickDays() != 0;
	}

}
