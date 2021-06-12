package listeners;

public interface ModelListener {
	void modelUpdateAddSuccessfuly(String msg);
	void modelStartElection();
	void modelFinishElections();
	int nextVoter(String citizen);
	void newElection();
	void modelShowSuccessfully(String data);
}
