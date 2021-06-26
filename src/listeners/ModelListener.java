package listeners;

public interface ModelListener {
	void modelUpdateAddSuccessfuly(String msg);
	void modelStartElection(String firstVoter);
	void modelFinishElections();
	void modelNextVoter(String citizen);
	void newElectionCreated();
	void modelShowSuccessfully(String data);
}
