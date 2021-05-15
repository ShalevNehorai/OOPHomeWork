package id318449782_id209544642;

public class Candid extends RegularCitizen{
	private PoliticalParty party;

	public Candid(String name, String id, int birthYear, BallotBox<RegularCitizen> ballotBox,
			PoliticalParty party, int primeriesPosition) throws InvalidIdException, NotAdultException,
			AlreadyExistException, NullPointerException, CantVoteException {
		super(name, id, birthYear, ballotBox);

		this.party = party;
		if (this.party != null)
			this.party.addCanidate(this, primeriesPosition);

	}

	public Candid(Citizen citizen, PoliticalParty party, int primeriesPosition) throws InvalidIdException,
			NotAdultException, AlreadyExistException, NullPointerException, CantVoteException {
		this(citizen.getName(), citizen.getId(), citizen.getBirthYear(), citizen.getBallotBox(),
				party, primeriesPosition);
	}

	@Override
	public String toString() {
		StringBuffer output = new StringBuffer();
		output.append(super.toString()).append(" he is a candid in ").append(party.getName());
		int spot = party.getCanidates().indexOf(this); //Util.indexOf(party.getCanidates(), this);
		if (spot >= 0) {
			output.append(", he is number ").append(spot + 1).append(" in the party ").append(party.getName());
		}
		return output.toString();
	}

	public String getParty() {
		return party.getName();
	}

	public boolean equals(Object obj) {
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Candid)) {
			return false;
		}

		Candid temp = (Candid) obj;
		if (!party.getName().equals(temp.getParty())) {
			return false;
		}
		return true;

	}
}
