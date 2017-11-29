package application;

import java.io.IOException;

import DAO.EmployeeDAO;
import DAO.TimePunchDAO;
import DO.Employee;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import utils.AlertUtils;
import utils.EmployeeManager;
import utils.Globals;
import utils.LoginUtils;
import utils.SceneManager;
import utils.TimeClockUtils;
import utils.ViewSwitcher;

public class LoginController extends Application{

	//FXML ID declarations grouped by type
	@FXML private Button loginButton;
	@FXML private Button loginCancel;
	
	@FXML private TextField usernameField;
	@FXML private PasswordField passwordField;
	
	//global declaration of the main stage
	private Stage main_stage = null; 
	
	//JavaFX main launch class
	public static void main(String[] args) {
		
		launch(args);
		
	}
	
	//Start method to initiate the login stage
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//Sets the parent stage and loads the .fxml file.
		Parent root = FXMLLoader.load(getClass().getResource("Login_screen.fxml"));
		main_stage = primaryStage;
		
		//Sets the title and scene for the main_stage
		main_stage.setTitle("Employee Database: Login");
		Scene scene = new Scene(root);
		SceneManager.setCurrentStage(main_stage);
		main_stage.setScene(scene);
		main_stage.show();
		
	}
	
	//Logs in user to either time clock or admin menu
	@FXML
    private void loginButtonClick(ActionEvent event) throws IOException {       
		
		//variables containing strings pulled from text fields in the login form
		final String user = usernameField.getText();
		final String pass = passwordField.getText();
		final int loginID = LoginUtils.getLoginID(user, pass);
		
		//ViewSwitcher.switchScreen(this, "Admin_Menu.fxml", "Employee Database: Admin Menu");
		//compares password and username against database for a match.
		
		if(user.isEmpty() || pass.isEmpty()) {
			AlertUtils.showAlert(this, "Incorrect Login", "You have left one or more fields empty", "Please enter username and login to continue", AlertType.WARNING);
		}
		else {
			if(loginID != Globals.NOT_FOUND_EMPLOYEE_ID) {
				Employee employee = EmployeeDAO.getEmployee(loginID);
				EmployeeManager.setCurrentEmployee(employee);
				//checks the users admin status and determines the menu.
				if(employee.getAccessTypeID() == Globals.ACCESS_TYPE_ADMIN_ID) {
					ViewSwitcher.switchScreen(this, "Admin_Menu.fxml", "Employee Database: Admin Menu");
				
				}
				else {
					ViewSwitcher.switchScreen(this, "Time_Clock.fxml", "Employee Database: Time Clock");
				}
			}
			else {
				AlertUtils.showAlert(this, "Incorrect Login", "Username or Password incorrect", "Please try again or contact admin", AlertType.ERROR);
			}
			EmployeeManager.setClockInStatus(TimePunchDAO.isClockedIn(EmployeeManager.getCurrentEmployee().getEmployeeID()));
		}
	}
	
	//closes window when cancel button is clicked
	@FXML
	public void cancelButtonClicked(ActionEvent action) throws IOException {
		Platform.exit();
	}
	
}
