package id318449782_id209544642;

import java.time.LocalDate;

public abstract class Citizen {
	private String name;
	private String id;
	private int birthYear;
//	private boolean isQurentined;
	protected BallotBox ballotBox;

	public Citizen(String name, String id, int birthYear)
			throws InvalidIdException, NullPointerException, CantVoteException {
		this.name = name;
		setId(id);
		this.birthYear = birthYear;
//		this.isQurentined = isQurentined;
		/*if (ballotBox != null)
			this.ballotBox = ballotBox;
		else
			throw new NullPointerException("Ballot box is null!");
		this.ballotBox.addCitizen(this);*/
	}

	protected void setId(String id) throws InvalidIdException {
		if (id.length() != 9) {
			throw new InvalidIdException("Id length must be 9 digits");
		}
		for (int i = 0; i < id.length(); i++) {
			if (!Character.isDigit(id.charAt(i))) {
				throw new InvalidIdException("Id must contain only digits");
			}
		}
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public BallotBox getBallotBox() {
		return ballotBox;
	}

	/*public boolean isQurentined() {
		return isQurentined;
	}*/

	public final boolean isAbove18() {
		return LocalDate.now().getYear() - birthYear >= 18;
	}

	public final void vote(int partyToVote) throws CantVoteException, IndexOutOfBoundsException {
		if(partyToVote != -1)
			this.ballotBox.vote(this, partyToVote);
	}

	@Override
	public String toString() {
		return "Citizen: name = " + name + ", id = " + id + ", birthYear = " + birthYear;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Citizen)) {
			return false;
		}

		return this.id.equals(((Citizen) (obj)).id);
	}
	
	public int getAge() {
		return LocalDate.now().getYear() - birthYear;
	}
}
