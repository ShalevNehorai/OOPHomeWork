package id318449782_id209544642;

import java.time.LocalDate;

public class Soldier extends Citizen {

	public final static int MIN_SOLDIER_AGE = 18;
	public final static int MAX_SOLDIER_AGE = 21;
	private boolean isCarryWeapon;
	public Soldier(String name, String id, int birthYear, BallotBox<Soldier> ballotBox, boolean isCarryWeapon)
			throws InvalidIdException, CantVoteException, NullPointerException {		
		super(name, id, birthYear);
		this.isCarryWeapon = isCarryWeapon;
		
		if (ballotBox != null)
			this.ballotBox = ballotBox;
		else
			throw new NullPointerException("Ballot box is null!");
		
		ballotBox.addCitizen(this);
	}
	
	public static boolean isSoldierAge(int birthYear){
		 int age = LocalDate.now().getYear() - birthYear;
		 return ((age >= MIN_SOLDIER_AGE) && (age <= MAX_SOLDIER_AGE));
	}
	@Override
	public String toString() {
		StringBuffer output = new StringBuffer().append(super.toString());
		if(this.carryWeapon())
			output.append(" is carrying a weapon ");
		output.append("Soldier");
		return output.toString();
	}
	
	public boolean carryWeapon(){
		return isCarryWeapon;
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Soldier))
			return false;
		Soldier temp = (Soldier)obj;
		if(temp.carryWeapon() != this.carryWeapon())
			return false;
		return super.equals(obj);
	}
}
