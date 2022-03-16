package view;

import java.awt.Component;

public interface TaskCardListener{
	public void cardRemoved(int paneId, int cardNo, Component card);
	public void editCard(int paneId, int cardNo);
}
