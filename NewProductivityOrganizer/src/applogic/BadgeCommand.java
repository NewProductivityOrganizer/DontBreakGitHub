package applogic;
/**
 * Interface of BadgeCommand that has methods executeCommand() and undoCommand() 
 * do and undo are actions related to a particular badge
 **/
public interface BadgeCommand {
	
	public void undo();

	public void execute();

	//void executeCommand(Badge badge);


}
