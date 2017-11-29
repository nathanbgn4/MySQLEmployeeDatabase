package application;

import java.io.IOException;
import java.sql.SQLException;

import DAO.EmployeeDAO;
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
import utils.EmployeeManager;
import utils.Globals;
import utils.SceneManager;

public class AddEmployeeController extends Application{

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
	
	public void initialize() {
		accessLevelChoiceBox.setValue("Employee");
		accessLevelChoiceBox.setItems(accessLevelList);
	}
	
	
	public void addEmployee(ActionEvent action) throws Exception {
	
		int accessLevelChoice = accessLevelChoiceBox.getValue().equals("Employee") ? Globals.ACCESS_TYPE_NORMAL_ID : Globals.ACCESS_TYPE_ADMIN_ID;  //add globals for this
		
		try {
			EmployeeDAO.insertEmployeeToTable(firstNameField.getText(), lastNameField.getText(), 
					jobTitleField.getText(), addressField.getText(), cityField.getText(), stateField.getText(), 
					zipCodeField.getText(), emailField.getText(), phoneField.getText(), payRateField.getText(), 
					accessLevelChoice, usernameField.getText(), passwordField.getText());
		

			SceneManager.getCurrentCallbackMethod().invoke(SceneManager.getCurrentCallbackObject(), (Object[]) null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		SceneManager.getCurrentStage().close();
	}
	
	@FXML
	public void cancelButtonClicked(ActionEvent action) throws IOException {
		SceneManager.getCurrentStage().close();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
