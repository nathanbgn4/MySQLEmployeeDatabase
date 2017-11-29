package DAO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import DO.Employee;
import DO.TimePunch;
import utils.ConnectionUtils;

public class TimePunchDAO {

	public static boolean isClockedIn(int employeeID) {
		
		boolean clockedIn = false;
		
		try {
			ResultSet clockInReader = ConnectionUtils.runProcedureWithResults("{call usp_check_clock_in(" + employeeID +")}");
			
			if(clockInReader.isBeforeFirst()) {
				clockedIn = true;
			}
			clockInReader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return clockedIn;
	}
	
	public static void insertClockIn(int employeeID) {
		try {
			CallableStatement clockIn = ConnectionUtils.getCallableStatement("{call usp_clock_in(?)}");
			
			clockIn.setInt(1, employeeID);
			clockIn.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void insertClockOut(int employeeID) {
		try {
			CallableStatement clockOut = ConnectionUtils.getCallableStatement("{call usp_clock_out(?)}");
		
			clockOut.setInt(1, employeeID);
			clockOut.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static LinkedList<TimePunch> getTimePunchGridData(){
		
		LinkedList<TimePunch> timePunchList = new LinkedList<TimePunch>();
		
		try {
			ResultSet timePunchReader = ConnectionUtils.runProcedureWithResults("{call usp_get_time_punches()}");
			
			while(timePunchReader.next()) {
				if(timePunchList.size() > 5) {
				TimePunch newTimePunch = new TimePunch();
				
				newTimePunch.setClockID(timePunchReader.getInt("ClockID"));
				newTimePunch.setClockIn(timePunchReader.getDate("CLOCK_IN"));
				newTimePunch.setClockOut(timePunchReader.getDate("CLOCK_OUT"));
				newTimePunch.setEmployeeID(timePunchReader.getInt("EmployeeID"));
			
				timePunchList.addLast(newTimePunch);
				}
			}
			timePunchReader.close();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return timePunchList;
		
	}
	
	
}



/*


*/