package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.layout.Priority;

public class PatientHealthRecord extends GridPane {

    public PatientHealthRecord() {
        createRegistrationFormPane();
    }

    private void createRegistrationFormPane() {
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(40));
        this.setHgap(10);
        this.setVgap(10);

        // Add UI controls
        TextField nameField = new TextField();
        nameField.setPromptText("Patient Information");

        TextField physicianField = new TextField();
        physicianField.setPromptText("Referance Dr/Primary Physician");

        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Visit Date");

        //create Hbox to hold Patient Information, primary doctor and visit date side by side
        HBox infoBox = new HBox(10); // Adjust spacing as needed
        infoBox.setAlignment(Pos.CENTER_LEFT);
        infoBox.getChildren().addAll(nameField, new Region(), physicianField, new Region(), datePicker);

        // Set HBox to evenly distribute space between nodes
        HBox.setHgrow(infoBox.getChildren().get(1), Priority.ALWAYS);
        HBox.setHgrow(infoBox.getChildren().get(3), Priority.ALWAYS);

        // TextAreas for diagnosis, course of action, and prescription
        TextArea courseOfActionArea = new TextArea();
        courseOfActionArea.setPromptText("Enter course of action...");

        TextArea diagnosisArea = new TextArea();
        diagnosisArea.setPromptText("Enter diagnosis...");

        TextArea medicalHistoryArea = new TextArea();
        medicalHistoryArea.setPromptText("Enter Medical History");

        TextArea prescriptionArea = new TextArea();
        prescriptionArea.setPromptText("Enter prescription details...");

        TextArea insuranceInformationArea = new TextArea();
        insuranceInformationArea.setPromptText("Enter Insurance Information");

        // Add controls to the grid
        this.add(infoBox, 0, 0, 2, 1); // Span two columns for full width

        // Add Diagnosis, Course of Action, and Prescription in separate rows
        this.add(diagnosisArea, 0, 1);
        this.add(courseOfActionArea, 1, 1);
        this.add(prescriptionArea, 0, 2, 2, 1); 
        this.add(medicalHistoryArea, 0, 3, 2, 1); 
        this.add(insuranceInformationArea, 0, 4, 2, 1);

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
