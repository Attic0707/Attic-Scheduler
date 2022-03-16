package view;

public class TalkerEvent {
	private String message;
	public TalkerEvent(Object source, String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}

}
