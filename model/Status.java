package model;

public enum Status {

	Backlog(0), SelectedForDevelopment (1), InProgress (2), DevelopmentDone (3), PeerReview (4), Finished (5);

	private int status;
	
	Status(int i) {
		this.status = i;
	}
	
	public int getStatus() {
        return status;
    }
}
