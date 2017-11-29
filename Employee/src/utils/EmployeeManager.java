package utils;

import java.sql.ResultSet;

import DO.Employee;

public class EmployeeManager {

	static Employee currentEmployee = null;
	static boolean isClockedIn = false;
	
	public static Employee getCurrentEmployee() {
		return currentEmployee;
	}

	public static void setCurrentEmployee(Employee employee) {
		currentEmployee = employee;
	}
	
	public static boolean isClockedIn() {
		return isClockedIn;
	}
	
	public static void setClockInStatus(boolean clockInBoolean) {
		isClockedIn = clockInBoolean;
	}
}
