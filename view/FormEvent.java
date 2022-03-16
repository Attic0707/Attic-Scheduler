package view;

import java.util.UUID;

public class FormEvent {
	private String issueId;
	private String issueName;
	private String status;
	private String desc;
	private String dueDate;
	private String assignee;
	private String type;
	private String priority;
	private int difficulty;
	private String subTaskId;

	public FormEvent(Object source, String issueName, String status, String desc, String date, String assignee, String type,
			String priority, int difficulty, String stId) {
		this.issueName = issueName;
		this.status = status;
		this.desc = desc;
		this.dueDate = date;
		this.assignee = assignee;
		this.type = type;
		this.priority = priority;
		this.difficulty = difficulty;
		this.subTaskId = stId;
		this.issueId = UUID.randomUUID().toString();
	}

	public String getIssueId() {
		return issueId;
	}
	public String getIssueName() {
		return issueName;
	}

	public String getStatus() {
		return status;
	}

	public String getDesc() {
		return desc;
	}
	
	public String getDate() {
		return dueDate;
	}

	public String getAssignee() {
		return assignee;
	}

	public String getType() {
		return type;
	}

	public String getPriority() {
		return priority;
	}
	
	public int getDifficulty() {
		return difficulty;
	}

	public String getSubTaskId() {
		return subTaskId;
	}

}
