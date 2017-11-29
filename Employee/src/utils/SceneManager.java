package utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import DO.Employee;
import javafx.scene.control.TableView;
import application.EmployeeDatabaseTableController;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class SceneManager {

	//Singleton object pattern
	private static Stage CURRENT_STAGE = null;
	private static Object CURRENT_TRANSFER_OBJECT = null; 
	private static Method CURRENT_CALLBACK_METHOD = null;
	private static Object CURRENT_CALLBACK_OBJECT = null;
	
	
	
	public static void setCurrentStage(Stage new_stage)	
	{
		CURRENT_STAGE = new_stage;
	}
	
	public static Stage getCurrentStage()	
	{
		return CURRENT_STAGE;		
	}

	public static Object getCurrentTransferObject() {
		return CURRENT_TRANSFER_OBJECT;
	}

	public static void setCurrentTransferObject(Object transferobject) {
		CURRENT_TRANSFER_OBJECT = transferobject;
	}

	public static Method getCurrentCallbackMethod() {
		return CURRENT_CALLBACK_METHOD;
	}

	public static void setCurrentCallbackMethod(Method cURRENT_CALLBACK_METHOD) {
		CURRENT_CALLBACK_METHOD = cURRENT_CALLBACK_METHOD;
	}

	public static Object getCurrentCallbackObject() {
		return CURRENT_CALLBACK_OBJECT;
	}

	public static void setCurrentCallbackObject(Object cURRENT_CALLBACK_OBJECT) {
		CURRENT_CALLBACK_OBJECT = cURRENT_CALLBACK_OBJECT;
	}
	
	
}
