package application;

import javafx.event.EventHandler;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class EditingCell extends TableCell<XYChart.Data, Number> {
	
	private TextField textField;
	
	public EditingCell() {}
	
	@Override
	public void startEdit() {
		
		super.startEdit();
		
		if(textField == null){
			createTextField();
		}
		
		setGraphic(textField);
		setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
	}
	
	@Override
	public void cancelEdit() {
		super.cancelEdit();
		
		setText(String.valueOf(getItem()));
		setContentDisplay(ContentDisplay.TEXT_ONLY);
	}
	
	public void updateItem(Number item, Boolean empty) {
		super.updateItem(item, empty);
		if(empty) {
			setText(null);
			setGraphic(null);
		}
		else {
			if(isEditing()) {
				if(textField != null) {
					textField.setText(getString());
				}
				setGraphic(textField);
				setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			}
			else {
				setText(getString());
				setContentDisplay(ContentDisplay.TEXT_ONLY);
			}
		}
	}
	
	private String getString() {return getItem() == null ? "" : getItem().toString();}
	
	private void createTextField() {
		textField = new TextField(getString());
		textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()*2);
		textField.setOnKeyPressed(new EventHandler<KeyEvent>()
				{	
					@Override
					public void handle(KeyEvent event)
					{
						if(event.getCode() == KeyCode.ENTER) 
						{
							commitEdit(Integer.parseInt(textField.getText()));
						}
						else if(event.getCode() == KeyCode.ESCAPE) {
							cancelEdit();
						}
	
					}	
				});
	}

	
}

