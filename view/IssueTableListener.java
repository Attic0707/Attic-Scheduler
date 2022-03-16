package view;

public interface IssueTableListener {
	public void rowDeleted(int row);
	public void cellEdited(int row, int col, Object editedValue);
}
