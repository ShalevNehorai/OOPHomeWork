package id318449782_id209544642;

import java.time.LocalDate;

public class Soldier extends Citizen {

	public final static int MIN_SOLDIER_AGE = 18;
	public final static int MAX_SOLDIER_AGE = 21;
	public Soldier(String name, String id, int birthYear, boolean isQurentined, BallotBox ballotBox)
			throws InvalidIdException, CantVoteException, NullPointerException {		
		super(name, id, birthYear, isQurentined, ballotBox);
	}
	
	public static boolean isSoldierAge(int birthYear){
		 int age = LocalDate.now().getYear() - birthYear;
		 return ((age >= MIN_SOLDIER_AGE) && (age <= MAX_SOLDIER_AGE));
	}
	@Override
	public String toString() {
		return super.toString() + " (Soldier)";
	}
}
