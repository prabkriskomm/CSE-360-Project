package application;
//team 9 is amazing
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

public class Diagnosis extends GridPane {
	private Patient patient;
	
    public Diagnosis(Patient patient) {
    	this.patient = patient;
        createRegistrationFormPane();
    }

    private void createRegistrationFormPane() {
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(40));
        this.setHgap(10);
        this.setVgap(10);

        // Add UI controls
        TextField nameField = new TextField();
        nameField.setPromptText("Patient Name");

        TextField physicianField = new TextField();
        physicianField.setPromptText("Primary Physician");

        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Visit Date");

        // Create an HBox to hold the name, physician, and date controls side by side
        HBox infoBox = new HBox(10); // Adjust spacing as needed
        infoBox.setAlignment(Pos.CENTER_LEFT);
        infoBox.getChildren().addAll(nameField, new Region(), physicianField, new Region(), datePicker);
        
        // Set HBox to evenly distribute space between nodes
        HBox.setHgrow(infoBox.getChildren().get(1), Priority.ALWAYS);
        HBox.setHgrow(infoBox.getChildren().get(3), Priority.ALWAYS);

        // TextAreas for diagnosis, course of action, and prescription
        TextArea diagnosisArea = new TextArea();
        diagnosisArea.setPromptText("Enter diagnosis...");

        TextArea courseOfActionArea = new TextArea();
        courseOfActionArea.setPromptText("Enter course of action...");

        TextArea prescriptionArea = new TextArea();
        prescriptionArea.setPromptText("Enter prescription details...");
        
        Button saveButton = new Button("Save");
        
        saveButton.setOnAction(event -> {
            // Get the text from the text areas
        	Visit visit = new Visit();
            visit.setDiagnosis(diagnosisArea.getText());
            visit.setCourseOfAction(courseOfActionArea.getText());
            visit.setPrescription(prescriptionArea.getText());
            patient.addVisit(visit);


            // Clear the text areas
            diagnosisArea.clear();
            courseOfActionArea.clear();
            prescriptionArea.clear();
        });

        // Add controls to the grid
        this.add(infoBox, 0, 0, 2, 1); // Span two columns for full width

        // Add Diagnosis, Course of Action, and Prescription in separate rows
        this.add(diagnosisArea, 0, 1);
        this.add(courseOfActionArea, 1, 1);
        this.add(prescriptionArea, 0, 2, 2, 1); // Span two columns for full width
        this.add(saveButton, 1, 3); // Add the "Save" button

        // Set column constraints to make the second column grow with the form
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS);
        this.getColumnConstraints().addAll(col1, col2);

        // Set row constraints for vertical growth if needed
        RowConstraints row1 = new RowConstraints();
        row1.setVgrow(Priority.ALWAYS);
        this.getRowConstraints().addAll(row1, new RowConstraints(), new RowConstraints());
    }
}
