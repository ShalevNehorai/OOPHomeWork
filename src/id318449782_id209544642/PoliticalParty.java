package id318449782_id209544642;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;

public class PoliticalParty implements Serializable{

	public static enum ePoliticalStand {
		RIGHT, CENTER, LEFT
	};

	private String name;
	private LocalDate partyCreation;
	private Set<Candid> canidates;
	private ePoliticalStand partyStand;

	public PoliticalParty(String partyName, ePoliticalStand partyStand, LocalDate creationDate, Set<Candid> canidates) throws InputMismatchException {
		this.name = partyName;
		this.partyStand = partyStand;
		if(partyName.isEmpty()) {
			throw new InputMismatchException("party name cant be empty");
		}
		
		if(canidates != null){
			this.canidates = canidates;
		}
		else{
			this.canidates = new Set<Candid>();
		}
		this.partyCreation = creationDate;
	}
	public PoliticalParty(String partyName, ePoliticalStand partyStand, LocalDate creationDate) throws InputMismatchException {
		this(partyName, partyStand, creationDate, null);
	}
	public PoliticalParty(String partyName, ePoliticalStand partyStand) throws InputMismatchException {
		this(partyName, partyStand, LocalDate.now(), null);
	}
	
	public Set<Candid> getCanidates() {
		return canidates;
	}	

	public String getName() {
		return name;
	}

	public ePoliticalStand getPartyStand() {
		return partyStand;
	}

	public String getPartyCreation() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
		return formatter.format(this.partyCreation);
	}

	public void addCanidate(Candid canidate, int primeriesLocation) throws NotAdultException, AlreadyExistException, NullPointerException, ArrayIndexOutOfBoundsException {
		if(canidate == null)
			throw new NullPointerException();
		if (!canidate.isAbove18()) {
			throw new NotAdultException();
		}
		if (isCanidateExist(canidate)) {
			throw new AlreadyExistException();
		}
		if(primeriesLocation < 0){
			throw new ArrayIndexOutOfBoundsException("Primeries Location can not be negetaive.");
		}
		canidates.add(canidate, primeriesLocation);
	}

	public boolean isCanidateExist(Candid canidate) {
		return canidates.contains(canidate);
	}
	
	@Override
	public String toString() {
		StringBuffer output = new StringBuffer("Political Party ");
		output.append(name).append(" is standing for the ").append(partyStand.name()).append(", and have ");
		output.append(canidates.size()).append(" canidates.");
		return output.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PoliticalParty)) {
			return false;
		}
		PoliticalParty temp = (PoliticalParty) obj;
		if (!temp.getName().equals(name)) {
			return false;
		}
		if (!temp.getPartyStand().equals(partyStand)) {
			return false;
		}
		if (!temp.getPartyCreation().equals(this.getPartyCreation())) {
			return false;
		}
		if (!temp.getCanidates().equals(this.getCanidates())) {
			return false;
		}
		return true;
	}
}
