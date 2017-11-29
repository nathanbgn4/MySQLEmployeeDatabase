package application;

import java.io.IOException;
import DAO.EmployeeDAO;
import DO.Employee;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import utils.SceneManager;
import utils.ViewSwitcher;

public class TestController extends Application{

	@FXML TableView<Employee> testTableView;
	@FXML TableColumn<Employee, Integer> testColumnOne;
	@FXML TableColumn<Employee, String> testColumnTwo;
	
	private final ObservableList<Employee> employeeData = FXCollections.observableArrayList(EmployeeDAO.getEmployeeGridData());
	
	
			
	
	public void initialize() {
	
	}




	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	
}


