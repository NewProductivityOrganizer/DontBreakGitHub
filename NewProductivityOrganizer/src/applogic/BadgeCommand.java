package applogic;
/**
 * Interface of BadgeCommand that has methods executeCommand() and undoCommand() to: do and undo an action related to a particular badge,e.create  a bage or deleting one
 **/
public interface BadgeCommand {
	
	public void undo();

	public void execute();

	//void executeCommand(Badge badge);


}