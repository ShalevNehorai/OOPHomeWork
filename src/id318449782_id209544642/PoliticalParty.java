package id318449782_id209544642;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class PoliticalParty {

	public static enum ePoliticalStand {
		RIGHT, CENTER, LEFT
	};

	private String name;
	private LocalDate partyCreation;
	private Candid[] canidates;
	private ePoliticalStand partyStand;

	public PoliticalParty(String partyName, ePoliticalStand partyStand, LocalDate creationDate, Candid[] canidates) {
		this.name = partyName;
		this.partyStand = partyStand;
		if(canidates != null){
			this.canidates = canidates;
		}
		else{
			this.canidates = new Candid[1];
		}
		this.partyCreation = creationDate;
	}
	public PoliticalParty(String partyName, ePoliticalStand partyStand, LocalDate creationDate) {
		this(partyName, partyStand, creationDate, null);
	}
	public PoliticalParty(String partyName, ePoliticalStand partyStand) {
		this(partyName, partyStand, LocalDate.now(), null);
	}
	
	public Candid[] getCanidates() {
		return Arrays.copyOf(canidates, canidates.length);
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

	public void addCanidate(Candid canidate, int primeriesLocation) throws NotAdultException, AlreadyExistException, NullPointerException {
		if(canidate == null)
			throw new NullPointerException();
		if (!canidate.isAbove18()) {
			throw new NotAdultException();
		}
		if (isCanidateExist(canidate)) {
			throw new AlreadyExistException();
		}
		canidates = (Candid[]) Util.addCellInArray(canidates, canidate, primeriesLocation);
	}

	public boolean isCanidateExist(Candid canidate) {
		return Util.indexOf(canidates, canidate) >= 0;
	}
	
	@Override
	public String toString() {
		StringBuffer output = new StringBuffer("Political Party ");
		output.append(name).append(" is standing for the ").append(partyStand.name()).append(", and have ");
		output.append(canidates.length).append(" canidates.");
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
