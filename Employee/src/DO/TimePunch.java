package DO;

import java.sql.Date;
import java.time.LocalDateTime;

public class TimePunch {
	private int employeeID;
	private Date clockin;
	private Date clockout;
	private int clockID;
	
	public int getEmployeeID() {
		return employeeID;
	}
	
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	
	public Date getClockIn() {
		return clockin;
	}
	
	public void setClockIn(Date clockin) {
		this.clockin = clockin;
	}
	
	public Date getClockOut() {
		return clockout;
	}
	
	public void setClockOut(Date clockout) {
		this.clockout = clockout;
	}
	
	public int getClockID() {
		return clockID;
	}
	
	public void setClockID(int clockID) {
		this.clockID = clockID;
	}
}
