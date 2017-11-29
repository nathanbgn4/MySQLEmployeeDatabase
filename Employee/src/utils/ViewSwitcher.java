package utils;

import java.io.IOException;
import java.lang.reflect.Method;

import application.EmployeeDatabaseTableController;
import application.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Control;
import javafx.scene.control.MenuItem;

public class ViewSwitcher {

	
	//make new method - showPopUp
	//should be modal
	
	/**
	 * Switches the current stage to the given scene name.
	 * @param owner The runtime class calling this method.
	 * @param ui_control The ui control switching the screen.
	 * @param screen_name The name of the UI scene to load.
	 * @param screen_title The title of the window.
	 */
	public static void switchScreen(Object owner, String screen_name, String title)
	{
		try 
		{
			SceneManager.getCurrentStage().getScene().getWindow().hide();
			
			Parent root = FXMLLoader.load(owner.getClass().getResource(screen_name));
			
			Stage stage = new Stage();
		    stage.setTitle(title);
		    stage.setScene(new Scene(root));
		    SceneManager.setCurrentStage(stage);
		    stage.show();
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
	}
	
	public static void showModalStage(Object owner, String screen_name, String title, Object transferObject,Object callbackObject, Method callbackMethod) {

		try {
			SceneManager.setCurrentTransferObject(transferObject);
			SceneManager.setCurrentCallbackMethod(callbackMethod);
			SceneManager.setCurrentCallbackObject(callbackObject);
			
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(owner.getClass().getResource(screen_name));
			SceneManager.setCurrentStage(stage);
			
			stage.setScene(new Scene(root));
			stage.setTitle(title);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
}
