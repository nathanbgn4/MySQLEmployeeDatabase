package application;

import java.io.IOException;
import java.lang.reflect.Method;

import DAO.EmployeeDAO;
import DO.Employee;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.AlertUtils;
import utils.ConnectionUtils;
import utils.ModifyEntryUtils;
import utils.SceneManager;
import utils.ViewSwitcher;

public class EmployeeDatabaseTableController extends Application{

	//FXML menu declarations
	@FXML private MenuItem menuClose;
	@FXML private MenuItem menuSaveEmployee;
	@FXML private MenuItem viewMenuReport;
	@FXML private MenuItem viewMenuTimeClock;
	@FXML private MenuItem editMenuItem;
	
	//FXML table declarations
	@FXML private TableView<Employee> employeeTableView; //employessTableView
	@FXML private TableColumn<Employee, Integer> employeeNumberColumn; //employeeNumberColumn
	@FXML private TableColumn<Employee, String> firstNameColumn;
	@FXML private TableColumn<Employee, String> lastNameColumn;
	@FXML private TableColumn<Employee, String> employeePositionColumn;
	@FXML private TableColumn<Employee, String> addressColumn;
	@FXML private TableColumn<Employee, String> cityColumn;
	@FXML private TableColumn<Employee, String> stateColumn;
	@FXML private TableColumn<Employee, String> zipCodeColumn;
	@FXML private TableColumn<Employee, String> phoneNumberColumn;
	@FXML private TableColumn<Employee, String> emailColumn;
	@FXML private TableColumn<Employee, String> payRateColumn;
	@FXML private TableColumn<Employee, String> usernameColumn;
	@FXML private TableColumn<Employee, String> passwordColumn;
	@FXML private TableColumn<Employee, Integer> accessColumn;
	
	private final ObservableList<Employee> employeeData = FXCollections.observableArrayList(EmployeeDAO.getEmployeeGridData());
	
	//unused method that exists as a necessity to javafx
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
	}
	
	
	@FXML
	public void initialize() {
		
		populateColumns();
		
		employeeTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		employeeTableView.setEditable(true);
		employeeNumberColumn.setEditable(true);
		firstNameColumn.setEditable(true);
		
		employeeTableView.setItems(employeeData);
		
	}
	
	@FXML
	public void editTableRow(ActionEvent action) throws Exception {
	
		Employee selected_employee = employeeTableView.getSelectionModel().getSelectedItem();
		
		if(selected_employee == null)
		{
			AlertUtils.showAlert(this, "No Selection", "No employee selected!", "Please select employee.", AlertType.ERROR);
		}
		else 
		{
			ViewSwitcher.showModalStage(this, "ModifyEmployee.fxml", "Modify Employee", selected_employee, this, this.getClass().getMethod("refreshTable", (Class<?>[]) null));

		}
	}
	
	@FXML
	public void deleteTableRow(ActionEvent action) throws IOException {
		
		if(employeeTableView.getSelectionModel().getSelectedItem() == null)
		{
			AlertUtils.showAlert(this, "No Selection", "No employee selected!", "Please select employee.", AlertType.ERROR);
		}
		else {
			EmployeeDAO.removeEmployeeFromDatabase(employeeTableView.getSelectionModel().getSelectedItem().getEmployeeID());
		}
		refreshTable();
	}

	@FXML
	public void newTableRow(ActionEvent action) throws Exception {
		
		Method[] methods = this.getClass().getMethods();
		
		for(Method method : methods)
		{
			System.out.println(method.getName());
		}
		
		
		ViewSwitcher.showModalStage(this, "AddEmployee.fxml", "Add Employee", null, this, this.getClass().getMethod("refreshTable", (Class<?>[])null));
	}
	
	@FXML
	public void viewMenuReportSwitch(ActionEvent action) throws IOException{

		ViewSwitcher.switchScreen(this, "Report_Filing.fxml", "Employee Database: Time Clock Report");
	
	}
	
	@FXML
	public void viewMenuTimeClockClicked(ActionEvent action) throws IOException{
		
		ViewSwitcher.showModalStage(this, "Time_clock.fxml", "Employee Database: Time Clock", null, null, null);
		
	}
	
	public void populateColumns() {
		employeeNumberColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("employeeID"));		
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstname"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastname"));
		employeePositionColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("position"));
		addressColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("address"));
		cityColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("city"));
		stateColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("state"));
		zipCodeColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("zipCode"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
		phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("phoneNumber"));
		payRateColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("payrate"));
		usernameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("username"));
		passwordColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("password"));
		accessColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("accessTypeID"));
	}
	
	public void refreshTable() {
		employeeData.removeAll();
		employeeTableView.getItems().clear();
		employeeData.addAll(EmployeeDAO.getEmployeeGridData());
		
		populateColumns();
		
		employeeTableView.setItems(employeeData);
		employeeTableView.refresh();
	}
	
	
	
	
	
}
