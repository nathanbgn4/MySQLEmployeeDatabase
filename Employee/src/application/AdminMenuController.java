package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import utils.ViewSwitcher;

public class AdminMenuController extends Application{

	@FXML private Button timeClockButton;
	@FXML private Button weeklyReportButton;
	@FXML private Button databaseButton;
	@FXML private Button cancelButton;
	
	//private Stage main_stage = null; 
	
	//unused method that exists as a necessity to javafx
	@Override
	public void start(Stage primaryStage) throws Exception {
	
	}
	
	@FXML
	public void employeeDatabaseButtonClicked(ActionEvent event) throws IOException {
		ViewSwitcher.switchScreen(this, "Database_Table.fxml", "Employee Database: Employees");
	}
	
	@FXML
	public void weeklyReportButtonClicked(ActionEvent event) throws IOException {
		ViewSwitcher.switchScreen(this, "Report_Filing.fxml", "Employee Database: Hourly Report");
	}
	
	
	@FXML
	public void timeClockButtonClicked(ActionEvent event) throws IOException {
		ViewSwitcher.showModalStage(this, "Time_clock.fxml", "Employee Database: Time Clock", null, null, null);
		
	}
	
	@FXML
	public void cancelButtonclicked(ActionEvent event) throws IOException {
		ViewSwitcher.switchScreen(this, "Login_screen.fxml", "Employee Database: Login");
	}
	
	
	
	
}
