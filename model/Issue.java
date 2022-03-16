package model;

import java.io.Serializable;

public class Issue implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int count = 0;
	private int id;
	private String issueName;
	private Status status;
	private String desc;
	private String date;
	private Assignee assignee;
	private Type type;
	private Priority priority;
	private int difficulty;
	private String subTaskId;

	public Issue(String issueName, Status status, String desc, String date, Assignee assignee, Type type, Priority priority,
			int difficulty, String subTaskId) {
		this.issueName = issueName;
		this.status = status;
		this.desc = desc;
		this.date = date;
		this.assignee = assignee;
		this.type = type;
		this.priority = priority;
		this.difficulty = difficulty;
		this.subTaskId = subTaskId;
		this.id = count;
		count++;
	}

	public int getId() {
		return id;
	}

	public String getIssueName() {
		return issueName;
	}

	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setDate(String date) {
		this.date = date;
	}
	public String getDate() {
		return date;
	}
	
	public Assignee getAssignee() {
		return assignee;
	}

	public void setAssignee(Assignee assignee) {
		this.assignee = assignee;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public void setDifficulty(int diff) {
		this.difficulty = diff;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public String getSubTaskId() {
		return subTaskId;
	}

	public void setSubTaskId(String subTaskId) {
		this.subTaskId = subTaskId;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("Name : ").append(issueName + "\n").append("Description: ")
				.append(desc + "\n").append("Due Date: ").append(date + "\n").append("Assignee: ").append(assignee + "\n").append("Priority: ").append(priority + "\n")
				.append("Difficulty: ").append(difficulty + "\n").append("Subtask (if any) :").append(subTaskId + "\n");
		return sb.toString();

	}

}
