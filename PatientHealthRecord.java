package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;

public class PatientHealthRecord extends GridPane {
    private Patient patient;

    public PatientHealthRecord(Patient patient) {
        this.patient = patient;
        createRegistrationFormPane();
    }

    private void createRegistrationFormPane() {
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(40));
        this.setHgap(10);
        this.setVgap(15);

        // Create headers
        Label patientInfoHeader = new Label("Patient Info:");
        Label physicianHeader = new Label("Primary Physician:");
        Label visitDateHeader = new Label("Visit Date");

        // Add UI controls
        TextField nameField = new TextField();
        nameField.setPromptText("Enter Patient Information");

        TextField physicianField = new TextField();
        physicianField.setPromptText("Enter Reference Dr/Primary Physician");

        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Select Visit Date");

        // Create VBox for labels and fields
        VBox patientInfoBox = new VBox(10, patientInfoHeader, nameField);
        VBox physicianInfoBox = new VBox(10, physicianHeader, physicianField);
        VBox visitDateBox = new VBox(10, visitDateHeader, datePicker);

        // Combine all VBox into a single HBox for the top row
        HBox topRowBox = new HBox(10, patientInfoBox, physicianInfoBox, visitDateBox);
        topRowBox.setAlignment(Pos.CENTER);
        HBox.setHgrow(patientInfoBox, Priority.ALWAYS);
        HBox.setHgrow(physicianInfoBox, Priority.ALWAYS);
        HBox.setHgrow(visitDateBox, Priority.ALWAYS);

        // Add components to the grid
        this.add(topRowBox, 0, 0, 3, 1);

        Label diagnosisHeader = new Label("Diagnosis");
        TextArea diagnosisArea = new TextArea();
        diagnosisArea.setPromptText("Enter diagnosis...");

        Label courseOfActionHeader = new Label("Course of Action");
        TextArea courseOfActionArea = new TextArea();
        courseOfActionArea.setPromptText("Enter course of action...");

        Label prescriptionHeader = new Label("Prescription Details");
        TextArea prescriptionArea = new TextArea();
        prescriptionArea.setPromptText("Enter prescription details...");

        Label medicalHistoryHeader = new Label("Medical History");
        TextArea medicalHistoryArea = new TextArea();
        medicalHistoryArea.setPromptText("Enter Medical History");

        Label insuranceInfoHeader = new Label("Insurance Information");
        TextArea insuranceInformationArea = new TextArea();
        insuranceInformationArea.setPromptText("Enter Insurance Information");

        // Add UI controls for the rest of the form
        this.add(diagnosisHeader, 0, 1);
        this.add(diagnosisArea, 0, 2);
        this.add(courseOfActionHeader, 1, 1);
        this.add(courseOfActionArea, 1, 2);
        this.add(prescriptionHeader, 0, 3);
        this.add(prescriptionArea, 0, 4, 3, 1);
        this.add(medicalHistoryHeader, 0, 5);
        this.add(medicalHistoryArea, 0, 6, 3, 1);
        this.add(insuranceInfoHeader, 0, 7);
        this.add(insuranceInformationArea, 0, 8, 3, 1);

        // Define column and row constraints
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.ALWAYS);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setHgrow(Priority.ALWAYS);
        this.getColumnConstraints().addAll(col1, col2, col3);

        RowConstraints row1 = new RowConstraints();
        row1.setVgrow(Priority.ALWAYS);
        this.getRowConstraints().addAll(row1, new RowConstraints(), new RowConstraints(), new RowConstraints(), new RowConstraints(), new RowConstraints(), new RowConstraints(), new RowConstraints());
    }
}
