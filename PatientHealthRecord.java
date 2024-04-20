package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
	private String PatientName;
    private TextArea diagnosisArea;
    private TextArea courseOfActionArea;
    private TextArea medicalHistoryArea;
    private TextArea prescriptionArea;
    private TextArea insuranceInformationArea;
    
    public PatientHealthRecord() {
        createRegistrationFormPane();
        currentPatient();
    }

    void createRegistrationFormPane() {
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(40));
        this.setHgap(10);
        this.setVgap(10);

        // Add UI controls
        TextField physicianField = new TextField();
        physicianField.setPromptText("Referance Dr/Primary Physician");

        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Visit Date");

        HBox infoBox = new HBox(10);
        infoBox.setAlignment(Pos.CENTER_LEFT);
        infoBox.getChildren().addAll(new Region(), physicianField, new Region(), datePicker);

        HBox.setHgrow(infoBox.getChildren().get(1), Priority.ALWAYS);
        HBox.setHgrow(infoBox.getChildren().get(3), Priority.ALWAYS);

        diagnosisArea = new TextArea(); // Declare at the class level
        diagnosisArea.setPromptText("Enter diagnosis...");

        courseOfActionArea = new TextArea(); // Declare at the class level
        courseOfActionArea.setPromptText("Enter course of action...");

        medicalHistoryArea = new TextArea(); // Declare at the class level
        medicalHistoryArea.setPromptText("Enter Medical History");

        prescriptionArea = new TextArea(); // Declare at the class level
        prescriptionArea.setPromptText("Enter prescription details...");

        insuranceInformationArea = new TextArea(); // Declare at the class level
        insuranceInformationArea.setPromptText("Enter Insurance Information");

        Button savebtn = new Button("save");
        savebtn.setOnAction(event->{
        	LocalDate date = datePicker.getValue();
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        	// Convert the LocalDate to a string using the formatter
        	String dateString = date.format(formatter);
        	loadPatientInfo(PatientName, dateString, "Prescription");
        	loadPatientInfo(PatientName, dateString, "Diagnosis");
        	loadPatientInfo(PatientName, dateString, "Medical History");
        	loadPatientInfo(PatientName, dateString, "Course of Action");
        	loadPatientInfo(PatientName, dateString, "Insurance Information");
        });
        // Add components to the grid
        this.add(infoBox, 0, 0, 3, 1);
        this.add(diagnosisArea, 0, 1, 1, 2);
        this.add(courseOfActionArea, 1, 1, 1, 2);
        this.add(prescriptionArea, 0, 3, 3, 1);
        this.add(medicalHistoryArea, 0, 4, 3, 1);
        this.add(insuranceInformationArea, 0, 5, 3, 1);
        this.add(savebtn, 0, 6, 1, 1);

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

    void loadPatientInfo(String name, String date, String label) {
        File file = new File("Patient_Data.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                boolean foundInfo = false;
                while ((line = reader.readLine()) != null) {
                    // Check if the line contains the specific patient information
                    if (line.startsWith(name + ", " + date + ", " + label + ":")) {
                        // Extract the value of the information
                        String[] parts = line.split(": ");
                        if (parts.length >= 2) {
                            String value = parts[1].trim();
                            // Display the value in the appropriate field based on the label
                            switch (label) {
                                case "Diagnosis":
                                    diagnosisArea.setText(value);
                                    break;
                                case "Medical History":
                                    medicalHistoryArea.setText(value);
                                    break;
                                case "Course of Action":
                                    courseOfActionArea.setText(value);
                                    break;
                                case "Prescription":
                                    prescriptionArea.setText(value);
                                    break;
                                case "Insurance Information":
                                    insuranceInformationArea.setText(value);
                                    break;
                                // Add cases for other fields if needed
                            }
                            foundInfo = true;
                            break; // Stop searching once the information is found
                        }
                    }
                }
                // If the information was not found, display a message indicating it
                if (!foundInfo) {
                    // Handle the case when the information is not found
                    System.out.println("Patient information not found.");
                    System.out.println("searching for:" + name + ", " + date + ", " + label);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void currentPatient() {
        File file = new File("Current_Patient.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Trim the line to remove leading and trailing whitespace
                    line = line.trim();
                    PatientName = line;
                    

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } 
    }
}