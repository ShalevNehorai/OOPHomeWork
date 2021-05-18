package id318449782_id209544642;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import id318449782_id209544642.BallotBox.BallotType;
import id318449782_id209544642.PoliticalParty.ePoliticalStand;

public class Main {
	public static void main(String[] args) {
		Manager manage = new Manager();
		Scanner scan = new Scanner(System.in);
		
		try{
			getElectionsFromData(manage, scan);
		}
		catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
		catch(ClassNotFoundException e){
			System.out.println(e.getMessage());
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		

		if(startNewElections(manage, scan))
			System.out.println("New elections started.");
		else{
			if(manage.isEmpty()){
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
					+ "9 - ELECTIONS RESULTS\n" + "10 - exit\n");
			choise = scan.nextInt();

			switch (choise) {
			case 1:
				try {
					addBallotBox(manage, scan);
				} catch (AlreadyExistException e) {
					System.out.println(e.getMessage());
				} catch (InputMismatchException e) {
					System.out.println("invalid type, try again");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				try {
					addCitizen(manage, scan);
				} catch (CantVoteException e) {
					System.out.println("cant vote in the given ballot box because " + e.getMessage());
				} catch (NullPointerException e) {
					System.out.println("One of the inputs is null");
				} catch (InvalidIdException e) {
					System.out.println(e.getMessage());
				} catch (AlreadyExistException e) {
					System.out.println("Citizen already exist.");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				addPoliticalParty(manage, scan);
				break;
			case 4:
				try {
					addCandid(manage, scan);
				} catch (NotAdultException e) {
					System.out.println("Canidate have to be an adult!");
				} catch (CantVoteException e) {
					System.out.println("cant vote in the given ballot box because " + e.getMessage());
				} catch (NullPointerException e) {
					System.out.println("One of the inputs is null");
				} catch (InvalidIdException e) {
					System.out.println(e.getMessage());
				} catch (AlreadyExistException e) {
					System.out.println("Citizen already exist.");
				} catch(ArrayIndexOutOfBoundsException e){
					System.out.println(e.getMessage());
				}

				break;
			case 5:
				System.out.println(showAllBallotBox(manage));
				break;
			case 6:
				System.out.println(showAllCitizen(manage));
				break;
			case 7:
				System.out.println(showAllPoliticalParty(manage));
				break;
			case 8:
				startElection(manage, scan);
				break;
			case 9:
				System.out.println(showElection(manage));
				break;
			case 10:
				try{
				System.out.println(exitMenu(manage, scan));
				}
				catch(FileNotFoundException e){
					System.out.println(e.getMessage());
				}
				catch(IOException e){
					System.out.println(e.getMessage());
				}
				catch(Exception e){
					System.out.println(e.getMessage());
				}
				break;
			}

		} while (choise != 10);

		scan.close();

	}

	public static void addPoliticalParty(Manager manage, Scanner scan) {
		System.out.println("What is the name of the Political Party?");
		scan.skip("[\r\n]+");
		String name = scan.nextLine();

		boolean validAnswer = false;

		ePoliticalStand stand = ePoliticalStand.CENTER;
		while (!validAnswer) {
			System.out.println(
					"What is the Political Stand of the party? \n type the first letter: Left , Right, Center ");
			char politicalStand = scan.next().charAt(0);
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
				LocalDate date = getDate(scan);
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
			}
		}
	}

	public static void addBallotBox(Manager manage, Scanner scan) throws AlreadyExistException, InputMismatchException {
		System.out.println("Enter the new Ballot box address");
		scan.skip("[\r\n]+");
		String ballotBoxAddress = scan.nextLine();
		System.out.println("What is the BallotBox? Type the number for the type");
		System.out.println(manage.showBallotBoxType());
		int typeIndex = scan.nextInt();
		
		BallotType type = BallotType.values()[typeIndex];
		manage.addBallotBox(ballotBoxAddress, type);
	}

	public static void addCandid(Manager manage, Scanner scan) throws InvalidIdException, NotAdultException,
			AlreadyExistException, NullPointerException, CantVoteException {
		System.out.println("What is your name?");
		scan.skip("[\r\n]+");
		String name = scan.nextLine();
		System.out.println("Enter your ID ");
		String id = scan.next();
		System.out.println("In what year were you born?");
		int birthYear = scan.nextInt();
		
		System.out.println("In what BallotBox are you registered? type the number of the BallotBox");
		System.out.println(manage.showAllTypeBallotBoxes(BallotType.RegularCitizen));
		int ballotBox = scan.nextInt();
		
		while (ballotBox < 0 || ballotBox >= manage.getNumOfBallotBoxes(BallotType.RegularCitizen)) {
			System.out.println("Ballot box number invalid, please enter another");
			ballotBox = scan.nextInt();	
		}

		System.out.println("In what party does the canidate run for? type the number of the party");
		System.out.println(showAllPoliticalParty(manage));
		int party = scan.nextInt();
		while (party < 0 ||  party >= manage.getNumOfParties()){
			System.out.println("Party number invalid, please enter another");
			party = scan.nextInt();
		}
		
		
		System.out.println("Where is the canidate in the priemeries?");
		int spot = scan.nextInt() - 1;
		while (spot < 0) {
			System.out.println("priemeries location is not valid, must be 1 or grater");
			spot = scan.nextInt() - 1;
		}
		manage.addCandid(name, id, birthYear, false, ballotBox, party, spot);

	}

	public static void addCitizen(Manager manage, Scanner scan)
			throws InvalidIdException, NullPointerException, CantVoteException, AlreadyExistException {
		System.out.println("What is your name?");
		scan.skip("[\r\n]+");
		String name = scan.nextLine();
		System.out.println("Enter your ID ");
		String id = scan.next();
		System.out.println("In what year were you born?");
		int birthYear = scan.nextInt();

		boolean qurentined = false;
		int numOfSickDays = 0;
		boolean validAnswer = false;
		while (!validAnswer) {
			System.out.println("Are you qurentined? \n type Y or y for YES \n type n or N for NO");
			char isQurentined = scan.next().charAt(0);
			if (isQurentined == 'y' || isQurentined == 'Y') {
				qurentined = true;
				System.out.println("How many days were you sick?");
				numOfSickDays = scan.nextInt();
				if(numOfSickDays >= 0)
					validAnswer = true;
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
			ballotBox = scan.nextInt();
			if (ballotBox >= 0 && ballotBox < manage.getNumOfBallotBoxes(type))
				validAnswer = true;
			else
				System.out.println("Answer is not valid, try again");
		}
		
		manage.addCitizen(name, id, birthYear, qurentined, numOfSickDays, ballotBox);

	}
	
	public static String showAllBallotBox(Manager manage) {
		return manage.showAllBallotBoxes();
	}

	public static String showAllCitizen(Manager manage) {
		return manage.showAllCitizens();
	}

	public static String showAllPoliticalParty(Manager manage) {
		return manage.showAllParties();
	}

	public static void startElection(Manager manage, Scanner scan) {
		if (manage.getCurrentElection().isVoteOccurred()) {
			System.out.println("voting already occured, if you want to vote again you need to create new elections");
			startNewElections(manage, scan);
			return;
		}

		System.out.println("The election is starting!");
		for (Citizen voter : manage.getCitizens()) {
			System.out.println(voter.toString());

			System.out.println("To what Political Party are you voting? type the party's number\ntype -1 to not vote");
			System.out.println(manage.showAllParties());

			boolean validAnswer = false;
			while (!validAnswer) {
				int party = scan.nextInt();
				try {
					voter.vote(party);
					validAnswer = true;
				} catch (CantVoteException e) {
					System.out.println("Given party is not valid, choose a valid party");
				}
			}
		}
		manage.endElections();
		System.out.println("The election is over!");

	}

	public static String showElection(Manager manage) {
		StringBuffer output = new StringBuffer();
		try {
			output.append("The result of the last elections in each BallotBox:");
			output.append(manage.getLastVotesInAllBallotBoxes());
			output.append("The results of the last election:");
			output.append(manage.getLastVotedElectionsResults());
		} catch (IndexOutOfBoundsException e) {
			output.delete(0, output.length());
			output.append("The election didn't happen\n");
		}
		return output.toString();
	}

	public static String exitMenu(Manager manage, Scanner scan) throws FileNotFoundException, IOException {
		System.out.println("What is the address to save the data of the elections?");
		scan.skip("[\r\n]+");
		String address = scan.nextLine();
		
		manage.saveAsBinaryFile(address);
		
		return "The program is over, thank you";
	}

	public static LocalDate getDate(Scanner scan) throws DateTimeException, NumberFormatException {
		System.out.println("What year? (Type NOW in capital letters for today\\now)");
		String input = scan.next();
		LocalDate date = null;
		if (input.equals("NOW")) {
			date = LocalDate.now();
		} else {
			int year = Integer.parseInt(input);
			System.out.println("What month number? ");
			int month = scan.nextInt();
			System.out.println("In what day of the month? ");
			int day = scan.nextInt();
			date = LocalDate.of(year, month, day);
		}
		return date;
	}

	public static boolean startNewElections(Manager manage, Scanner scan) {
		System.out.println("Start a new election? Type y or Y to start");
//		scan.skip("[\r\n]+");
		boolean validAnswer = false;
		char start = scan.next().charAt(0);
		switch (start) {
		case 'y':
		case 'Y':
			System.out.println("When is the elections?");
			LocalDate date = null;
			while (!validAnswer) {
				try {
					date = getDate(scan);
					validAnswer = true;
				} catch (DateTimeException e) {
					System.out.println("Invalid date inputs, try again");
				} catch (NumberFormatException e) {
					System.out.println("Invalid input - input a number or NOW only");
				}
			}
			manage.createNewElections(date);
			return true;
		default:
			return false;
		}
	}
	public static boolean getElectionsFromData(Manager manage, Scanner scan) throws IOException, FileNotFoundException, ClassNotFoundException{
		System.out.println("Do you want to get elections data from a file? Type y or Y to get data");
		char start = scan.next().charAt(0);
		switch (start) {
		case 'y':
		case 'Y':
			System.out.println("What is the address of the file?");
			scan.skip("[\r\n]+");
			String address = scan.nextLine();
			
			manage.readBinaryFile(address);	
			return true;
		default:
			return false;
		}
	}

}
