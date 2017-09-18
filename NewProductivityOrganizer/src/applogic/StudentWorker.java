package applogic;
public class StudentWorker {
	private int userID;
	private String actualName;
	private int point;
	
	public StudentWorker(int userID, String actualName, int point) {
		this.userID = userID;
		this.actualName = actualName;
		this.point = point;
	}
	
	public int getUserID() {
		return this.userID;
	}
	public String toString() {
		
		return "\nStudent ID: " +userID + "\nStudent Name: " + actualName + "\nPoints Earned: " + point;
		
	}
}