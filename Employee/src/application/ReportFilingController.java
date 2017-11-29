package application;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

import DAO.TimePunchDAO;
import DO.TimePunch;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import utils.ViewSwitcher;

public class ReportFilingController extends Application{

	@FXML MenuItem menuDatabaseSwitch;
	@FXML MenuItem menuTimeClockSwitch;
	@FXML TableView<TimePunch> reportTableView;
	@FXML TableColumn<TimePunch, Date> firstDayColumn;
	@FXML TableColumn<TimePunch, Date> clockInOne;
	@FXML TableColumn<TimePunch, Date> clockOutOne;
	@FXML TableColumn<TimePunch, Date> secondDayColumn;
	@FXML TableColumn<TimePunch, Date> clockInTwo;
	@FXML TableColumn<TimePunch, Date> clockOutTwo;
	@FXML TableColumn<TimePunch, Date> thirdDayColumn;
	@FXML TableColumn<TimePunch, Date> clockInThree;
	@FXML TableColumn<TimePunch, Date> clockOutThree;
	@FXML TableColumn<TimePunch, Date> fourthDayColumn;
	@FXML TableColumn<TimePunch, Date> clockInFour;
	@FXML TableColumn<TimePunch, Date> clockOutFour;
	@FXML TableColumn<TimePunch, Date> fifthDayColumn;
	@FXML TableColumn<TimePunch, Date> clockInFive;
	@FXML TableColumn<TimePunch, Date> clockOutFive;
	@FXML TableColumn<TimePunch, Integer> totalHoursColumn;
	@FXML TableColumn<TimePunch, String> employeeNameColumn;
	
	private final ObservableList<TimePunch> timePunchList = FXCollections.observableArrayList(TimePunchDAO.getTimePunchGridData());
	
	public void initialize() {
		
	}
	
	private void populateGridData() {
		
	}
	
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void viewMenuDatabaseSwitch(ActionEvent action) throws IOException {
		
		ViewSwitcher.switchScreen(this, "Database_table.fxml", "Employee Database: Database");
		
	}
	
	public void viewMenuTimeClockSwitch() {
		
		ViewSwitcher.switchScreen(this, "Time_clock.fxml", "Employee Database: Time Clock");
		
	}
	
	
}
