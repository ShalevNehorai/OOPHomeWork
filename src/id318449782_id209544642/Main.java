package id318449782_id209544642;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import id318449782_id209544642.BallotBox.BallotType;
import id318449782_id209544642.PoliticalParty.ePoliticalStand;
import listeners.ViewListener;

public class Main implements UIAbstractView {

	private static Scanner s = new Scanner(System.in);
	private static Manager manage = new Manager();

	public static void main(String[] args) {

		Main mainObject = new Main();

		try {
			getElectionsFromData(manage);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		if (startNewElections())
			System.out.println("New elections started.");
		else {
			if (manage.isEmpty()) {
				System.out.println("The program is over, thank you");
				return;
			}
			System.out.println("There is no new election");

		}

		int choise = -1;

		do {
			System.out.println("What do you want to do?\n" + "1 - add Ballot box\n" + "2 - add Citizen\n"
					+ "3 - add Political party\n" + "4 - add Canadite to a party\n" + "5 - show all Ballotboxes\n"
					+ "6 - show all Citizens\n" + "7 - show all Political parties\n" + "8 - Start ELECTIONS\n"
					+ "9 - ELECTIONS RESULTS\n" + "10 - save and exit\n");
			try {
				choise = s.nextInt();
			}
			catch(InputMismatchException e) {
				System.out.println("invalid input");
				choise = -1;
			}
			switch (choise) {
			case 1:
				mainObject.addBallotBox();
				break;
			case 2:
				mainObject.addCitizen();
				break;
			case 3:
				mainObject.addPoliticalParty();
				break;
			case 4:
				mainObject.addCandid();
				break;
			case 5:
				System.out.println(mainObject.showAllBallotBox());
				break;
			case 6:
				System.out.println(mainObject.showAllCitizen());
				break;
			case 7:
				System.out.println(mainObject.showAllPoliticalParty());
				break;
			case 8:
				mainObject.startElection();
				break;
			case 9:
				System.out.println(mainObject.showElectionResult());
				break;
			case 10:
				mainObject.exitMenu();
				return;
			}

			s.nextLine();
		} while (choise != 10);

		s.close();

	}

	public static LocalDate getDate() throws DateTimeException, NumberFormatException, InputMismatchException {
		System.out.println("What year? (Type NOW in capital letters for today\\now)");
		String input = s.next();
		LocalDate date = null;
		if (input.equals("NOW")) {
			date = LocalDate.now();
		} else {
			int year = Integer.parseInt(input);
			System.out.println("What month number? ");
			int month = s.nextInt();
			System.out.println("In what day of the month? ");
			int day = s.nextInt();
			date = LocalDate.of(year, month, day);
		}
		return date;
	}

	public static boolean startNewElections() {
		System.out.println("Start a new election? Type y or Y to start");
		boolean validAnswer = false;
		char start = s.next().charAt(0);
		switch (start) {
		case 'y':
		case 'Y':
			System.out.println("When is the elections?");
			LocalDate date = null;
			while (!validAnswer) {
				try {
					date = getDate();
					validAnswer = true;
				} catch (DateTimeException e) {
					System.out.println("Invalid date inputs, try again");
				} catch (NumberFormatException e) {
					System.out.println("Invalid input - input a number or NOW only");
				} catch (InputMismatchException e){
					System.out.println("Invalid input - enter a number");
				}
			}
			manage.createNewElections(date);
			return true;
		default:
			return false;
		}
	}

	public static boolean getElectionsFromData(Manager manage)
			throws IOException, FileNotFoundException, ClassNotFoundException {
		System.out.println("Do you want to get elections data from a file? Type y or Y to get data");
		char start = s.next().charAt(0);
		switch (start) {
		case 'y':
		case 'Y':
			System.out.println("What is the address of the file?");
			s.skip("[\r\n]+");
			String address = s.nextLine();

			manage.readBinaryFile(address);
			return true;
		default:
			return false;
		}
	}

	@Override
	public void addBallotBox(String streer, BallotType type) {
		// TODO Auto-generated method stub
		
	}
	public void addBallotBox() {
		try {
			System.out.println("Enter the new Ballot box address");
			s.skip("[\r\n]+");
			String ballotBoxAddress = s.nextLine();
			System.out.println("What is the BallotBox type? Type the number for the type");
			System.out.println(manage.showBallotBoxType());
			int typeIndex = s.nextInt();
	
			BallotType type = BallotType.values()[typeIndex];

			manage.addBallotBox(ballotBoxAddress, type);
			
		} catch (InputMismatchException e) {
			System.out.println("type value must be number");
		} catch (Exception e) {
			System.out.println("That ballot box is already exists");
		}

	}

	@Override
	public void addCitizen() {
		System.out.println("What is your name?");
		s.skip("[\r\n]+");
		String name = s.nextLine();
		System.out.println("Enter your ID ");
		String id = s.next();
		System.out.println("In what year were you born?");
		try{
			int birthYear = s.nextInt();
	
			boolean qurentined = false;
			int numOfSickDays = 0;
			boolean validAnswer = false;
			while (!validAnswer) {
				System.out.println("Are you qurentined? \n type Y or y for YES \n type n or N for NO");
				char isQurentined = s.next().charAt(0);
				if (isQurentined == 'y' || isQurentined == 'Y') {
					qurentined = true;
					System.out.println("How many days were you sick?");
					try {
						numOfSickDays = s.nextInt();
						if (numOfSickDays >= 0)
							validAnswer = true;
					} catch (InputMismatchException e) {
						System.out.println("Input is not valid, enter a positive number only");
					}
	
				} else if (isQurentined == 'N' || isQurentined == 'n') {
					validAnswer = true;
					qurentined = false;
				} else
					System.out.println("Answer is not valid, try again");
			}
	
			BallotType type = manage.getBallotType(birthYear, qurentined);
	
			System.out.println("In what BallotBox are you registered? type the number of the BallotBox");
			System.out.println(manage.showAllTypeBallotBoxes(type));
			int ballotBox = -1;
			validAnswer = false;
			while (!validAnswer) {
				ballotBox = s.nextInt();
				if (ballotBox >= 0 && ballotBox < manage.getNumOfBallotBoxes(type))
					validAnswer = true;
				else
					System.out.println("Answer is not valid, try again");
			}
			manage.addCitizen(name, id, birthYear, qurentined, numOfSickDays, ballotBox);
		} catch (AlreadyExistException e) {
			System.out.println("that ballot box is already exists");
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (InvalidIdException e) {
			e.printStackTrace();
		} catch (CantVoteException e) {
			e.printStackTrace();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input, failed to create citizen");
		} catch (NotAdultException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void addPoliticalParty() {
		System.out.println("What is the name of the Political Party?");
		s.skip("[\r\n]+");
		String name = s.nextLine();

		boolean validAnswer = false;

		ePoliticalStand stand = ePoliticalStand.CENTER;
		while (!validAnswer) {
			System.out.println(
					"What is the Political Stand of the party? \n type the first letter: Left , Right, Center ");
			char politicalStand = s.next().charAt(0);
			switch (politicalStand) {
			case 'L':
			case 'l':
				stand = ePoliticalStand.LEFT;
				validAnswer = true;
				break;
			case 'R':
			case 'r':
				stand = ePoliticalStand.RIGHT;
				validAnswer = true;
				break;
			case 'C':
			case 'c':
				stand = ePoliticalStand.CENTER;
				validAnswer = true;
				break;
			default:
				System.out.println("Answer is not valid, try again");
				break;
			}
		}

		validAnswer = false;
		while (!validAnswer) {
			try {
				System.out.println("When did the party formed?");
				LocalDate date = getDate();
				if (!date.isAfter(LocalDate.now())) {
					manage.addParty(name, stand, date);
					validAnswer = true;
				} else {
					System.out.println("Date is not valid, try again\n");
				}
			} catch (DateTimeException e) {
				System.out.println("Invalid date inputs, try again");
			} catch (AlreadyExistException e) {
				System.out.println("Political Party already exist");
			} catch (NumberFormatException e) {
				System.out.println("Invalid input - input a number or NOW only");
			} catch(InputMismatchException e){
				System.out.println("Invalid input - input a number only");
				s.nextLine();
			}
		}

	}

	@Override
	public void addCandid() {
		try {
			System.out.println("What is your name?");
			s.skip("[\r\n]+");
			String name = s.nextLine();
			System.out.println("Enter your ID ");
			String id = s.next();
			System.out.println("In what year were you born?");
			int birthYear = s.nextInt();

			System.out.println("In what BallotBox are you registered? type the number of the BallotBox");
			System.out.println(manage.showAllTypeBallotBoxes(BallotType.RegularCitizen));
			int ballotBox = s.nextInt();

			while (ballotBox < 0 || ballotBox >= manage.getNumOfBallotBoxes(BallotType.RegularCitizen)) {
				System.out.println("Ballot box number invalid, please enter another");
				ballotBox = s.nextInt();
			}

			System.out.println("In what party does the canidate run for? type the number of the party");
			System.out.println(showAllPoliticalParty());
			int party = s.nextInt();
			while (party < 0 || party >= manage.getNumOfParties()) {
				System.out.println("Party number invalid, please enter another");
				party = s.nextInt();
			}

			System.out.println("Where is the canidate in the priemeries?");
			int spot = s.nextInt() - 1;
			while (spot < 0) {
				System.out.println("priemeries location is not valid, must be 1 or grater");
				spot = s.nextInt() - 1;
			}

			manage.addCandid(name, id, birthYear, ballotBox, party, spot);
			
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (InvalidIdException e) {
			e.printStackTrace();
		} catch (AlreadyExistException e) {
			System.out.println(e.getMessage());
		} catch (CantVoteException e) {
			e.printStackTrace();
		} catch (NotAdultException e) {
			e.printStackTrace();
		} catch (InputMismatchException e) {
			System.out.println("Input invvalid, failed to create Candid");
		}
	}

	@Override
	public String showAllBallotBox() {
		return manage.showAllBallotBoxes();
	}

	@Override
	public String showAllPoliticalParty() {
		return manage.showAllParties();
	}

	@Override
	public String showAllCitizen() {
		return manage.showAllCitizens();
	}

	@Override
	public void startElection() {
		if (manage.getCurrentElection().isVoteOccurred()) {
			System.out.println("voting already occured, if you want to vote again you need to create new elections");
			startNewElections();
			return;
		}

		System.out.println("The election is starting!");
		for (Citizen voter : manage.getCitizens()) {
			System.out.println(voter.toString());

			System.out.println("To what Political Party are you voting? type the party's number\ntype -1 to not vote");
			System.out.println(showAllPoliticalParty());

			boolean validAnswer = false;
			while (!validAnswer) {	
				try {
					int party = s.nextInt();
					voter.vote(party);
					validAnswer = true;
				} catch (CantVoteException e) {
					System.out.println("Given party is not valid, choose a valid party");
				} catch(InputMismatchException e){
					System.out.println("Given party is not valid, choose a valid party");
					s.nextLine();
				}
			}
		}
		manage.endElections();
		System.out.println("The election is over!");
	}

	@Override
	public String showElectionResult() {
		StringBuffer output = new StringBuffer();
		try {
			output.append("The result of the last elections in each BallotBox:");
			output.append(manage.getLastVotesInAllBallotBoxes());
			output.append(manage.getLastVotedElectionsResults());
		} catch (IndexOutOfBoundsException e) {
			output.delete(0, output.length());
			output.append("The election didn't happen\n");
		}
		return output.toString();
	}

	@Override
	public void exitMenu() {
		System.out.println("What is the address to save the data of the eltctions?");
		s.skip("[\r\n]+");
		String aderessd = s.nextLine();
		try {
			manage.saveAsBinaryFile(aderessd);
			System.out.println("The program is over, thank you");
		} catch (FileNotFoundException e) {
			System.out.println("File wasn't found.");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	@Override
	public void registerListener(ViewListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showMsg(String msg) {
		// TODO Auto-generated method stub
		
	}
}
