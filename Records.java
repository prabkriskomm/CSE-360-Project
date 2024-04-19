package application;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

//team 9 is amazing
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class Records extends GridPane {

    private TextField patientNameField;
    private TextField heightField;
    private TextField weightField;
    private TextField temperatureField;
    private TextField bloodPressureField;
    private TextField bloodOxygenField;
    private TextField allergiesField;
    private TextField familyHistoryField;
    private TextField immunizationsField;
    private TextArea prescriptionsArea;
    private TextArea insuranceArea;

    public Records() {
        setupUI();
    }

    private void setupUI() {
        this.setAlignment(Pos.TOP_LEFT);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(20));

        Label titleLabel = new Label("Medical History");
        titleLabel.setFont(new Font("Arial", 24));
        this.add(titleLabel, 0, 0, 4, 1);

        Label patientNameLabel = new Label("Patient Name:");
        this.add(patientNameLabel, 0, 1);
        patientNameField = new TextField();
        this.add(patientNameField, 1, 1);
        
        


        Label heightLabel = new Label("Height:");
        this.add(heightLabel, 0, 2);
        heightField = new TextField();
        this.add(heightField, 1, 2);

        Label weightLabel = new Label("Weight:");
        this.add(weightLabel, 0, 3);
        weightField = new TextField();
        this.add(weightField, 1, 3);

        Label temperatureLabel = new Label("Temperature:");
        this.add(temperatureLabel, 0, 4);
        temperatureField = new TextField();
        this.add(temperatureField, 1, 4);

        Label bloodPressureLabel = new Label("Blood Pressure:");
        this.add(bloodPressureLabel, 0, 5);
        bloodPressureField = new TextField();
        this.add(bloodPressureField, 1, 5);

        Label bloodOxygenLabel = new Label("Blood Oxygen:");
        this.add(bloodOxygenLabel, 0, 6);
        bloodOxygenField = new TextField();
        this.add(bloodOxygenField, 1, 6);

        Label allergiesLabel = new Label("Allergies:");
        this.add(allergiesLabel, 0, 7);
        allergiesField = new TextField();
        this.add(allergiesField, 1, 7);

        Label familyHistoryLabel = new Label("Family History:");
        this.add(familyHistoryLabel, 0, 8);
        familyHistoryField = new TextField();
        this.add(familyHistoryField, 1, 8);

        Label immunizationsLabel = new Label("Immunizations:");
        this.add(immunizationsLabel, 0, 9);
        immunizationsField = new TextField();
        this.add(immunizationsField, 1, 9);

        Label prescriptionsLabel = new Label("Prescriptions:");
        this.add(prescriptionsLabel, 2, 2);
        prescriptionsArea = new TextArea();
        prescriptionsArea.setPrefRowCount(5);
        prescriptionsArea.setWrapText(true);
        this.add(prescriptionsArea, 3, 1, 1, 3);

        Label insuranceLabel = new Label("Insurance Information:");
        this.add(insuranceLabel, 2, 5);
        insuranceArea = new TextArea();
        insuranceArea.setPrefRowCount(5);
        insuranceArea.setWrapText(true);
        this.add(insuranceArea, 3, 4, 1, 3);
        
        Button search = new Button("Search");
        this.add(search, 2, 1);
        search.setOnAction(event->{
        	//heightField.setText((loadPatientInfo(patientNameField.getText(), "height")));
        	loadPatientInfo(patientNameField.getText(), "Height");
        	loadPatientInfo(patientNameField.getText(), "Weight");
        });
    }
    void loadPatientInfo(String name, String label) {
        File file = new File("Patient_Data.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                boolean foundInfo = false;
                while ((line = reader.readLine()) != null) {
                    // Check if the line contains the specific patient information
                    if (line.startsWith(name +", " + label + ":")) {
                        // Extract the value of the information
                        String[] parts = line.split(": ");
                        if (parts.length > 1) {
                            String value = parts[1].trim();
                            // Display the value in the appropriate field based on the label
                            switch (label) {
                                case "height:":
                                    heightField.setText(value);
                                    break;
                                case "weight:":
                                    weightField.setText(value);
                                    break;
                                case "Temperature:":
                                    temperatureField.setText(value);
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
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    // Getters for retrieving text from fields
    public static void main(String[] args) {
        // You can add code here to test the Records class independently
        // For example, create an instance of Records and display it in a stage
    }
}
