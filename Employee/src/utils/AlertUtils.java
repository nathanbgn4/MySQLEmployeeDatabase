package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertUtils {
	public static void showAlert(Object owner, String alertTitle, String alertHeader, String alertContext, AlertType alertType) {
		Alert alert = new Alert(null);
		alert.setAlertType(alertType);
		alert.setTitle(alertTitle);
		alert.setHeaderText(alertHeader);
		alert.setContentText(alertContext);
		
		alert.showAndWait();
	}
}
