package id318449782_id209544642;

public class NotAdultException extends Exception {
	public NotAdultException(String msg) {
		super(msg);
	}
	public NotAdultException() {
		super("Not an adult, to vote - citizen must be of age 18 and above!");
	}
}
