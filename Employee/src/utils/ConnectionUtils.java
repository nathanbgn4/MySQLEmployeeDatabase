package utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtils {
	//global declaration of database information variables
		static String database_name = "fireman_employee_database";
		static String database_url = "jdbc:mysql://192.185.83.158/" + database_name;
		static String database_user = "fireman_nbougeno";
		static String database_password = "Somethingsimple1";
		
		//connection method
		public static Connection getConnection() throws Exception {
			
			//accesses the class drivers for MySQL.
			Class.forName("com.mysql.jdbc.Driver");
			
			//passes in the global variables to the database to authorize the connection.
			Connection connection = DriverManager.getConnection(database_url, database_user, database_password);
			
			//returns connection information.
			return connection;
		}

		public static ResultSet runProcedureWithResults(String procedureName) throws Exception {
			
			Connection connection = getConnection();
			
			CallableStatement preparedCall = connection.prepareCall(procedureName);
			
			return preparedCall.executeQuery();
		}
		
		public static ResultSet runProcedureWithResults(CallableStatement preparedCall ) throws Exception {
			
			return preparedCall.executeQuery();
		}
		
		public static void executeProcedure(String sql_query) throws Exception
        {
            Statement adhoc_statement = getConnection().createStatement(); //will open a connection if the connection obj is null        
            adhoc_statement.execute(sql_query);
        }
		
		public static CallableStatement getCallableStatement(String procedureName) throws SQLException, Exception
		{
			return getConnection().prepareCall(procedureName);
		}
		
		public static PreparedStatement getPreparedStatement(String statementName) throws Exception
		{
			PreparedStatement statement = getConnection().prepareStatement(statementName);
			statement.executeUpdate();
			return statement;
		}
}
