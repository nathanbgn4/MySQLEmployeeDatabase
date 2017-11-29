package application;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.CallableStatement;

import DAO.EmployeeDAO;
import DO.Employee;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.ConnectionUtils;
import utils.Globals;
import utils.SceneManager;

public class ModifyEmployeeController extends Application{

	static ObservableList<String> accessLevelList = FXCollections.observableArrayList("Employee", "Admin");
	
	@FXML
	private TextField employeeIDField;
	@FXML
	private TextField firstNameField;
	@FXML 
	private TextField lastNameField;
	@FXML 
	private TextField addressField;
	@FXML 
	private TextField cityField;
	@FXML 
	private TextField stateField;
	@FXML 
	private TextField zipCodeField;
	@FXML 
	private TextField emailField;
	@FXML 
	private TextField phoneField;
	@FXML 
	private TextField jobTitleField;
	@FXML 
	private TextField payRateField;
	@FXML 
	private TextField usernameField;
	@FXML 
	private TextField passwordField;
	@FXML 
	private ChoiceBox<String> accessLevelChoiceBox;
	@FXML 
	private Button saveButton;
	@FXML 
	private Button cancelButton;
	
	@FXML
	public void initialize() {
		
		accessLevelChoiceBox.setItems(accessLevelList);
		
		Employee selected_employee = (Employee) SceneManager.getCurrentTransferObject();
	
		if (selected_employee.getAccessTypeID() == 1) 
		{
			accessLevelChoiceBox.setValue("Employee");
		}
		else
		{
			accessLevelChoiceBox.setValue("Admin");
		}
		accessLevelChoiceBox.setItems(accessLevelList);
		
		employeeIDField.setText(String.valueOf(selected_employee.getEmployeeID()));
		firstNameField.setText(selected_employee.getFirstname());
		lastNameField.setText(selected_employee.getLastname());
		addressField.setText(selected_employee.getAddress());
		cityField.setText(selected_employee.getCity());
		zipCodeField.setText(selected_employee.getZipCode());
		stateField.setText(selected_employee.getState());
		emailField.setText(selected_employee.getEmail());
		phoneField.setText(selected_employee.getPhoneNumber());
		jobTitleField.setText(selected_employee.getPosition());
		payRateField.setText(selected_employee.getPayrate());
		usernameField.setText(selected_employee.getUsername());
		passwordField.setText(selected_employee.getPassword());
	}
	
	public void updateEmployee(ActionEvent action) throws Exception {
		
		Employee selected_employee = (Employee) SceneManager.getCurrentTransferObject();
		
		int accessLevelChoice = accessLevelChoiceBox.getValue().equals("Employee") ? Globals.ACCESS_TYPE_NORMAL_ID : Globals.ACCESS_TYPE_ADMIN_ID;  //add globals for this
		
		selected_employee.setEmployeeID(Integer.parseInt(employeeIDField.getText()));
		selected_employee.setFirstname(firstNameField.getText());
		selected_employee.setLastname(lastNameField.getText());
		selected_employee.setAddress(addressField.getText());
		selected_employee.setCity(cityField.getText());
		selected_employee.setState(stateField.getText());
		selected_employee.setZipCode(zipCodeField.getText());
		selected_employee.setEmail(emailField.getText());
		selected_employee.setPhoneNumber(phoneField.getText());
		selected_employee.setPayrate(payRateField.getText());
		selected_employee.setPosition(jobTitleField.getText());
		selected_employee.setUsername(usernameField.getText());
		selected_employee.setPassword(passwordField.getText());
		selected_employee.setAccessTypeID(accessLevelChoice);
		
		EmployeeDAO.updateEmployeeInfo(selected_employee);	
		
		SceneManager.getCurrentCallbackMethod().invoke(SceneManager.getCurrentCallbackObject(), (Object[]) null);
		
		SceneManager.getCurrentStage().close();
	}
		
	public void cancelButtonClicked(ActionEvent action) throws IOException {

		SceneManager.getCurrentStage().close();
	}
	
	
	@Override
	public void start(Stage arg0) throws Exception {
		
	}
	
	
}
