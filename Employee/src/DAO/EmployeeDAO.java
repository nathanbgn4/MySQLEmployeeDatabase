package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import DO.Employee;
import javafx.fxml.FXML;
import utils.ConnectionUtils;

public class EmployeeDAO{
	
	
	public static LinkedList<Employee> getEmployeeGridData() {
		
		LinkedList<Employee> employeesList = new LinkedList<Employee>();
		try {
			ResultSet employees_reader = ConnectionUtils.runProcedureWithResults("{call usp_get_employees()}");
			
			while (employees_reader.next())
			{
				//make employee
				Employee newEmployee = new Employee();
				
				newEmployee.setEmployeeID(employees_reader.getInt("EmployeeID"));
				newEmployee.setLastname(employees_reader.getString("LastName"));
				newEmployee.setFirstname(employees_reader.getString("FirstName"));
				newEmployee.setPosition(employees_reader.getString("Position"));
				newEmployee.setAddress(employees_reader.getString("Address"));
				newEmployee.setCity(employees_reader.getString("City"));
				newEmployee.setState(employees_reader.getString("State"));
				newEmployee.setZipCode(employees_reader.getString("Zipcode"));
				newEmployee.setEmail(employees_reader.getString("Email"));
				newEmployee.setPhoneNumber(employees_reader.getString("Phone"));
				newEmployee.setPayrate(employees_reader.getString("PayRate"));
				newEmployee.setUsername(employees_reader.getString("Username"));
				newEmployee.setPassword(employees_reader.getString("Password"));
				newEmployee.setAccessTypeID(employees_reader.getInt("AccessTypeID"));
				
				
				//add to list
				employeesList.addLast(newEmployee);
			
			}
			
			employees_reader.close();
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return employeesList;
	}

	public static Employee getEmployee(int loginID){
		
		Employee employee = new Employee();
		
		try {
			ResultSet employees_reader = ConnectionUtils.runProcedureWithResults("{call usp_get_employee(" + loginID + ")}");
			
			while(employees_reader.next())
			{
				
				employee.setEmployeeID(employees_reader.getInt("EmployeeID"));
				employee.setLastname(employees_reader.getString("LastName"));
				employee.setFirstname(employees_reader.getString("FirstName"));
				employee.setPosition(employees_reader.getString("Position"));
				employee.setAddress(employees_reader.getString("Address"));
				employee.setCity(employees_reader.getString("City"));
				employee.setState(employees_reader.getString("State"));
				employee.setZipCode(employees_reader.getString("Zipcode"));
				employee.setEmail(employees_reader.getString("Email"));
				employee.setPhoneNumber(employees_reader.getString("Phone"));
				employee.setPayrate(employees_reader.getString("PayRate"));
				employee.setAccessTypeID(employees_reader.getInt("AccessTypeID"));
			}
			
			employees_reader.close();
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return employee;
	}
	
	//TODO make upsert employee method
	//Takes employee objects, the sql proc should take in all parameters
	//		The stored procedure should check if the eployee id exists already
			//  IF NOT EXISTS select id from employees where id = @employeeid
			//		BEGIN	
			//		Insert into employees(col...coln) values (@employeeparams)
		//			END
			//	else
			//		BEGIN
			//			update sql
			//		end
	public static void insertEmployeeToTable(String firstName, String lastName, String position, String address, String city,
			String state, String zipCode, String email, String phone, String payRate,Integer accessInt, String username, String password) {
		
		
		try {
			CallableStatement employee_creator = ConnectionUtils.getCallableStatement("{call usp_create_employee(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
			
			employee_creator.setString(1, firstName);
			employee_creator.setString(2, lastName);
			employee_creator.setString(3, position);
			employee_creator.setString(4, address);
			employee_creator.setString(5, city);
			employee_creator.setString(6, state);
			employee_creator.setString(7, zipCode);
			employee_creator.setString(8, email);
			employee_creator.setString(9, phone);
			employee_creator.setString(10, payRate);
			employee_creator.setInt(11, accessInt);
			employee_creator.setString(12, username);
			employee_creator.setString(13, password);
			
			employee_creator.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void updateEmployeeInfo(Employee employee) {
		
		String update_employeesql = "UPDATE Employees SET FirstName = '" + employee.getFirstname() + "', LastName = '" + employee.getLastname() + 
				"', Position = '" + employee.getPosition() + "', PayRate = '" + employee.getPayrate() + "', AccessTypeID = " + employee.getAccessTypeID() +
				" WHERE EmployeeID = " + employee.getEmployeeID() + ";";
		String update_addresssql = "UPDATE Location_info SET Address = '" + employee.getAddress() + "', City = '" + employee.getCity() +
				"', State = '" + employee.getState() + "', Zipcode = '" + employee.getZipCode() + "' WHERE LocationID = " + employee.getEmployeeID() + ";"; 
		String update_contactsql = "UPDATE Contact_Info SET Email = '" + employee.getEmail() + "', Phone = '" + employee.getPhoneNumber() + 
				"' WHERE ContactID = " + employee.getEmployeeID() + ";"; 
		String update_loginsql = "UPDATE login_info SET Username = '" + employee.getUsername() + "', Password = '" + employee.getPassword() + "' WHERE LoginID = "
				+ employee.getEmployeeID() + ";";
		
		try 
		{
			ConnectionUtils.executeProcedure(update_employeesql);
			ConnectionUtils.executeProcedure(update_addresssql);
			ConnectionUtils.executeProcedure(update_contactsql);
			ConnectionUtils.executeProcedure(update_loginsql);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void removeEmployeeFromDatabase(int employee_ID) {

		try {
			CallableStatement delete_employee = ConnectionUtils.getCallableStatement("{call usp_delete_employee(?)}");
			
			delete_employee.setInt(1, employee_ID);
			
			delete_employee.executeQuery();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
