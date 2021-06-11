package listeners;

public interface ModelListener {
	String modelUpdateAddSuccessfuly(String msg);
	void modelStartElection();
	void modelFinishElections();
	String nextVoter();
}
