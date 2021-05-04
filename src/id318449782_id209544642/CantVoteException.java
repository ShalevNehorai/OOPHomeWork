package id318449782_id209544642;

public class CantVoteException extends Exception {
	public CantVoteException() {
		this("Can't vote here");
	}
	public CantVoteException(String msg){
		super(msg);
	}
}
