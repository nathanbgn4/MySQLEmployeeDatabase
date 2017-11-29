package utils;

import java.sql.*;

public class LoginUtils {

	
	//Utility method to check the login info against the database
	public static int getLoginID(String username, String password) {
		int outputValue = -1;  //add to globals
		//connects to database and pulls username and password info
		try {
			
			//pulls getConnection method from BaseDAO			
			CallableStatement loginCall = ConnectionUtils.getCallableStatement("{call usp_login_check(?, ?, ?)}");
			
			//set paremeters here
			loginCall.setString(1, username);
			loginCall.setString(2, password);
			loginCall.registerOutParameter(3, Types.INTEGER);
			
			//not sure if needs reader
			ConnectionUtils.runProcedureWithResults(loginCall);
			
			
			//Sets output results to a string
			outputValue = loginCall.getInt(3);
			
		}
		catch(Exception e) {
			System.out.println("Unable to connect to database");
			e.printStackTrace();
		}
		
		//returns whether user exists or not
		return outputValue;
	}
	
	
}
