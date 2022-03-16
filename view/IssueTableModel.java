package view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Issue;

public class IssueTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1513454598985852542L;

	private List<Issue> db;
	private String[] colNames = {"Id", "Task Name", "Status", "Description", "Due Date", "Assignee", "Type", "Priority", "Difficulty", "Subtask Name"

	};

	public IssueTableModel() {
	}


	public void setData(List<Issue> db) {
		this.db = db;
	}

	@Override
	public int getRowCount() {
		return db.size();
	}

	@Override
	public int getColumnCount() {
		return 10;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Issue issue = db.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return issue.getId();
		case 1:
			return issue.getIssueName();
		case 2:
			return issue.getStatus();
		case 3:
			return issue.getDesc();
		case 4: 
			return issue.getDate();
		case 5:	
			return issue.getAssignee();
		case 6:
			return issue.getType();
		case 7:
			return issue.getPriority();
		case 8:
			return issue.getDifficulty();
		case 9:
			return issue.getSubTaskId();
		}
		return null;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

//	@Override 
//	public void fireTableCellUpdated(int rowIndex, int columnIndex) {
//	}
	
	@Override
	public String getColumnName(int column) {
		return colNames[column];
	}

}
