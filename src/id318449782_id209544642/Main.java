package id318449782_id209544642;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import id318449782_id209544642.PoliticalParty.ePoliticalStand;

public class Main {

	public static void main(String[] args) {
		Manager manage = new Manager();
		Scanner scan = new Scanner(System.in);
		boolean validAnswer = false;

		LocalDate dateNewElections = startNewElections(scan);
		if (dateNewElections != null) {
			manage.createNewElections(dateNewElections);
		} else {
			System.out.println("New elections didn't happen, have a great day!");
			scan.close();
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
				System.out.println("Enter the new Ballot box address");
				scan.skip("[\r\n]+");
				String ballotBoxAddress = scan.nextLine();
				validAnswer = false;
				while (!validAnswer) {
					try {
						System.out.println("What is the BallotBox type: \nc - Corona\ns - Soldier\nr - Regular\n");
						char type = scan.next().charAt(0);
						manage.addBallotBox(ballotBoxAddress, type);
						validAnswer = true;
					} catch (AlreadyExistException e) {
						System.out.println(e.getMessage());
					} catch (InputMismatchException e) {
						System.out.println("invalid type, try again");
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
				break;
			case 2:
				System.out.println("What is your name?");
				scan.skip("[\r\n]+");
				String name = scan.nextLine();
				System.out.println("Enter your ID ");
				String id = scan.next();
				System.out.println("In what year were you born?");
				int birthYear = scan.nextInt();

				System.out.println("In what BallotBox are you registered? type the number of the BallotBox");
				System.out.println(manage.showAllBallotBoxes());
				int ballotBox = -1;
				validAnswer = false;
				while (!validAnswer) {
					ballotBox = scan.nextInt();
					if (ballotBox >= 0 && ballotBox < manage.getNumOfBallotBoxes())
						validAnswer = true;
					else
						System.out.println("Answer is not valid, try again");
				}

				boolean qurentined = false;

				validAnswer = false;
				while (!validAnswer) {
					System.out.println("Are you qurentined? \n type Y or y for YES \n type n or N for NO");
					char isQurentined = scan.next().charAt(0);
					if (isQurentined == 'y' || isQurentined == 'Y') {
						validAnswer = true;
						qurentined = true;
					} else if (isQurentined == 'N' || isQurentined == 'n') {
						validAnswer = true;
						qurentined = false;
					} else
						System.out.println("Answer is not valid, try again");
				}
				try {
					manage.addCitizen(name, id, birthYear, qurentined, ballotBox);
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
				System.out.println("What is the name of the Political Party?");
				scan.skip("[\r\n]+");
				name = scan.nextLine();

				validAnswer = false;

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
				break;
			case 4:
				System.out.println("What is your name?");
				scan.skip("[\r\n]+");
				name = scan.nextLine();
				System.out.println("Enter your ID ");
				id = scan.next();
				System.out.println("In what year were you born?");
				birthYear = scan.nextInt();
				System.out.println("In what BallotBox are you registered? type the number of the BallotBox");
				System.out.println(manage.showAllBallotBoxes());
				ballotBox = scan.nextInt();

				qurentined = false;

				validAnswer = false;
				while (!validAnswer) {
					System.out.println("Are you qurentined? \n type Y or y for YES \n type n or N for NO");
					char isQurentined = scan.next().charAt(0);
					if (isQurentined == 'y' || isQurentined == 'Y') {
						validAnswer = true;
						qurentined = true;
					} else if (isQurentined == 'N' || isQurentined == 'n') {
						validAnswer = true;
						qurentined = false;
					} else
						System.out.println("Answer is not valid, try again");
				}
				try {
					System.out.println("In what party does the canidate run for? type the number of the party");
					System.out.println(manage.showAllParties());
					int party = scan.nextInt();
					System.out.println("Where is the canidate in the priemeries?");
					int spot = scan.nextInt() - 1;
					manage.addCandid(name, id, birthYear, qurentined, ballotBox, party, spot);
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
				}

				break;
			case 5:
				System.out.println(manage.showAllBallotBoxes());
				break;
			case 6:
				System.out.println(manage.showAllCitizens());
				break;
			case 7:
				System.out.println(manage.showAllParties());
				break;
			case 8:
				if(manage.getCurrentElection().isVoteOccurred()){
					System.out.println("voting already occured, if you want to vote again you need to create new elections");
					dateNewElections = startNewElections(scan);
					if (dateNewElections != null) {
						manage.createNewElections(dateNewElections);
					}
					break;
				}
				System.out.println("The election is starting!");
				for (Citizen voter : manage.getCitizens()) {
					System.out.println(voter.toString());
//					if (voter.isQurentined()) {
//						System.out.println("You are qurentined, are you wearing a mask?");
//						char isProtected = scan.next().charAt(0);
//						if (isProtected != 'y' && isProtected != 'Y') {
//							System.out.println("Come again with a mask to vote!");
//							continue;
//						}
//					}

					System.out.println(
							"To what Political Party are you voting? type the party's number\ntype -1 to not vote");
					System.out.println(manage.showAllParties());

					validAnswer = false;
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
				break;
			case 9:
				try {
					System.out.println("The result of the last elections in each BallotBox:");
					System.out.println(manage.getLastVotesInAllBallotBoxes());
					System.out.println("The results of the last election:");
					System.out.println(manage.getLastVotedElectionsResults());
				}
				catch(IndexOutOfBoundsException e){
					System.out.println("Elections didn't happen");
				}
				break;
			case 10:
				System.out.println("The program is over, thank you");
				break;
			}

		} while (choise != 10);

		scan.close();
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

	public static LocalDate startNewElections(Scanner scan) {
		System.out.println("Start a new election? Type y or Y to start");
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
			return date;
		default:
			return null;
		}
	}

}
