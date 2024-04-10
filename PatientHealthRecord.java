package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;

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

        HBox infoBox = new HBox(10);
        infoBox.setAlignment(Pos.CENTER_LEFT);
        infoBox.getChildren().addAll(nameField, new Region(), physicianField, new Region(), datePicker);

        HBox.setHgrow(infoBox.getChildren().get(1), Priority.ALWAYS);
        HBox.setHgrow(infoBox.getChildren().get(3), Priority.ALWAYS);

        TextArea diagnosisArea = new TextArea();
        diagnosisArea.setPromptText("Enter diagnosis...");

        TextArea courseOfActionArea = new TextArea();
        courseOfActionArea.setPromptText("Enter course of action...");

        TextArea medicalHistoryArea = new TextArea();
        medicalHistoryArea.setPromptText("Enter Medical History");

        TextArea prescriptionArea = new TextArea();
        prescriptionArea.setPromptText("Enter prescription details...");

        TextArea insuranceInformationArea = new TextArea();
        insuranceInformationArea.setPromptText("Enter Insurance Information");

        // Add components to the grid
        this.add(infoBox, 0, 0, 3, 1);
        this.add(diagnosisArea, 0, 1, 1, 2);
        this.add(courseOfActionArea, 1, 1, 1, 2);
        this.add(prescriptionArea, 0, 3, 3, 1);
        this.add(medicalHistoryArea, 0, 4, 3, 1);
        this.add(insuranceInformationArea, 0, 5, 3, 1);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.ALWAYS);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setHgrow(Priority.ALWAYS);
        this.getColumnConstraints().addAll(col1, col2, col3);

        RowConstraints row1 = new RowConstraints();
        row1.setVgrow(Priority.ALWAYS);
        this.getRowConstraints().addAll(row1, new RowConstraints(), new RowConstraints(), new RowConstraints(), new RowConstraints(), new RowConstraints());
    }
}