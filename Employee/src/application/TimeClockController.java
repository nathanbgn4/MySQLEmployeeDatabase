package application;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javax.swing.text.DateFormatter;

import DAO.TimePunchDAO;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import utils.ConnectionUtils;
import utils.EmployeeManager;
import utils.Globals;
import utils.SceneManager;

public class TimeClockController extends Application {
	
	@FXML private MenuItem menuClose;
	@FXML private Button clockInButton;
	@FXML private Button clockOutButton;
	@FXML private Label timeLabel;
	
	@FXML
	public void initialize() {
		
		setClockOutButtonStatus();
		
		if(EmployeeManager.isClockedIn()) {
			setClockInButtonStatus();
		}
	}
	
	@FXML
	public void clockInButtonClicked(ActionEvent action) throws Exception {
		try {
			TimePunchDAO.insertClockIn(EmployeeManager.getCurrentEmployee().getEmployeeID());
			EmployeeManager.setClockInStatus(true);
			setClockInButtonStatus();
			TimePunchDAO.isClockedIn(EmployeeManager.getCurrentEmployee().getEmployeeID());
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SceneManager.getCurrentStage().close();
	}
	
	@FXML
	public void clockOutButtonClicked(ActionEvent action) throws Exception {
		try {
			TimePunchDAO.insertClockOut(EmployeeManager.getCurrentEmployee().getEmployeeID());
			EmployeeManager.setClockInStatus(false);
			setClockOutButtonStatus();
			TimePunchDAO.isClockedIn(EmployeeManager.getCurrentEmployee().getEmployeeID());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		SceneManager.getCurrentStage().close();
	}
	
	@FXML
	public void menuItemCloseClicked(ActionEvent action) throws Exception {
		SceneManager.getCurrentStage().close();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
	}

	private void setClockInButtonStatus() {
		clockInButton.setDisable(true);
		clockOutButton.setDisable(false);
	}
	
	private void setClockOutButtonStatus() {
		clockOutButton.setDisable(true);
		clockInButton.setDisable(false);
	}
	
}

	
