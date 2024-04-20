package application;
//team 9 is amazing
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class PatientIntake extends BorderPane {
	private Patient patient;
    public PatientIntake() {
    	this.patient = patient;
        // MenuBar and Menus
        MenuBar menuBar = new MenuBar();
//        Menu scheduleMenu = new Menu("Schedule");
//        Menu intakeMenu = new Menu("Intake");
//        Menu recordsMenu = new Menu("Records");
//        Menu diagnosisMenu = new Menu("Diagnosis");
//        Menu messagesMenu = new Menu("Messages");
//        menuBar.getMenus().addAll(scheduleMenu, intakeMenu, recordsMenu, diagnosisMenu, messagesMenu);

        // Vitals section
     // Vitals section with GridPane for even spacing
        GridPane vitalsGrid = new GridPane();
        vitalsGrid.setPadding(new Insets(0, 15, 15, 15));
        vitalsGrid.setVgap(10);
        vitalsGrid.setHgap(10);
        vitalsGrid.setAlignment(Pos.TOP_CENTER);
        Label vitalsTitle = new Label("Vitals");
        vitalsTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        //vitalsTitle.setAlignment(Pos.CENTER);
        vitalsTitle.setMaxWidth(Double.MAX_VALUE);
        vitalsTitle.setMaxWidth(Double.MAX_VALUE);
        vitalsTitle.setAlignment(Pos.CENTER);
        GridPane.setConstraints(vitalsTitle, 0, 0, 2, 1, HPos.CENTER, VPos.CENTER);
        vitalsGrid.getChildren().add(vitalsTitle);
        String[] labels = {"Age", "Weight", "Height", "Temperature", "BPM"}; //change BPM to be called "Heart Rate (bpm)", add a DatePicker, make a node for each of these, get rid of for loop
	    //add a save button, when you hit the save button it clears the fields, patient.setAge(age), patientField.clear();
        // Age
        Label ageLabel = new Label("Age");
        ageLabel.setMinWidth(Region.USE_PREF_SIZE);
        ageLabel.setAlignment(Pos.CENTER_RIGHT);
        TextField ageField = new TextField();
        ageField.setPrefWidth(200);
        GridPane.setConstraints(ageLabel, 0, 3);
        GridPane.setConstraints(ageField, 1, 3);
        vitalsGrid.getChildren().addAll(ageLabel, ageField);

        //name
        Label nameLabel = new Label("Patient Name");
        ageLabel.setMinWidth(Region.USE_PREF_SIZE);
        ageLabel.setAlignment(Pos.CENTER_RIGHT);
        TextField nameField = new TextField();
        ageField.setPrefWidth(200);
        GridPane.setConstraints(nameLabel, 0, 2);
        GridPane.setConstraints(nameField, 1, 2);
        vitalsGrid.getChildren().addAll(nameLabel, nameField);
        // Weight
        Label weightLabel = new Label("Weight");
        weightLabel.setMinWidth(Region.USE_PREF_SIZE);
        weightLabel.setAlignment(Pos.CENTER_RIGHT);
        TextField weightField = new TextField();
        weightField.setPrefWidth(200);
        GridPane.setConstraints(weightLabel, 0, 4);
        GridPane.setConstraints(weightField, 1, 4);
        vitalsGrid.getChildren().addAll(weightLabel, weightField);

        // Height
        Label heightLabel = new Label("Height");
        heightLabel.setMinWidth(Region.USE_PREF_SIZE);
        heightLabel.setAlignment(Pos.CENTER_RIGHT);
        TextField heightField = new TextField();
        heightField.setPrefWidth(200);
        GridPane.setConstraints(heightLabel, 0, 5);
        GridPane.setConstraints(heightField, 1, 5);
        vitalsGrid.getChildren().addAll(heightLabel, heightField);
        
     // Temperature
        Label tempLabel = new Label("Temperature");
        tempLabel.setMinWidth(Region.USE_PREF_SIZE);
        tempLabel.setAlignment(Pos.CENTER_RIGHT);
        TextField tempField = new TextField();
        tempField.setPrefWidth(200);
        GridPane.setConstraints(tempLabel, 0, 6);
        GridPane.setConstraints(tempField, 1, 6);
        vitalsGrid.getChildren().addAll(tempLabel, tempField);

        // BPM
        Label bpmLabel = new Label("Heart Rate (bpm)");
        bpmLabel.setMinWidth(Region.USE_PREF_SIZE);
        bpmLabel.setAlignment(Pos.CENTER_RIGHT);
        TextField bpmField = new TextField();
        bpmField.setPrefWidth(200);
        GridPane.setConstraints(bpmLabel, 0, 7);
        GridPane.setConstraints(bpmField, 1, 7);
        vitalsGrid.getChildren().addAll(bpmLabel, bpmField);

       // Date Picker
        Label dateLabel = new Label("Date");
        dateLabel.setMinWidth(Region.USE_PREF_SIZE);
        dateLabel.setAlignment(Pos.CENTER_RIGHT);
        DatePicker datePicker = new DatePicker();
        datePicker.setPrefWidth(200);
        GridPane.setConstraints(dateLabel, 0, 1);
        GridPane.setConstraints(datePicker, 1, 1);
        vitalsGrid.getChildren().addAll(dateLabel, datePicker);

        // Allergies section
        VBox allergiesBox = new VBox(10);
        //allergiesBox.setPadding(new Insets(15));
        allergiesBox.setPadding(new Insets(0, 15, 15, 15));
        Label allergiesTitle = new Label("Allergies");
        allergiesTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        allergiesTitle.setAlignment(Pos.CENTER);
        allergiesTitle.setMaxWidth(Double.MAX_VALUE);
        TableView<String> allergiesTable = new TableView<>();
        TableColumn<String, String> allergenColumn = new TableColumn<>("Allergen Name");
        TableColumn<String, String> dateColumn = new TableColumn<>("MM/YY");
        allergiesTable.getColumns().addAll(allergenColumn, dateColumn);
        allergiesBox.getChildren().addAll(allergiesTitle, allergiesTable);

        // Health Concerns section
        VBox healthConcernsBox = new VBox(10);
        //healthConcernsBox.setPadding(new Insets(15));
        healthConcernsBox.setPadding(new Insets(0, 15, 15, 15));
        Label healthConcernsTitle = new Label("Health Concerns");
        healthConcernsTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        healthConcernsTitle.setAlignment(Pos.CENTER);
        healthConcernsTitle.setMaxWidth(Double.MAX_VALUE);
        TextArea healthConcernsArea = new TextArea();
        healthConcernsBox.getChildren().addAll(healthConcernsTitle, healthConcernsArea);
        
        
        Button savebtn = new Button("save");
        // Layout setup
        HBox contentBox = new HBox(20);
        contentBox.getChildren().addAll(vitalsGrid, allergiesBox, healthConcernsBox, savebtn);
        contentBox.setAlignment(Pos.TOP_CENTER);
        
        
        // Root layout
        this.setTop(menuBar);
        this.setCenter(contentBox);
        savebtn.setOnAction(event->{
        	LocalDate date = datePicker.getValue();
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        	// Convert the LocalDate to a string using the formatter
        	String dateString = date.format(formatter);
        	
        	save(nameField.getText(),"Date", dateString);
        	save(nameField.getText(), "Age", ageField.getText());
        	save(nameField.getText(), "Heart Rate", bpmField.getText());
        	save(nameField.getText(), "Temperature", tempField.getText());
        	save(nameField.getText(), "Height", heightField.getText());
        	save(nameField.getText(), "Weight", weightField.getText());
        	//save(nameField.getText(), "Health Concerns", weightField.getText());
        	
        });
    }

    void save(String name, String Label, String message) {
        File file = new File("Patient_Data.txt");
        //writing to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            //formatting the message for the file 
            String formattedMessage = name + ", " + Label + ": " + message;
            writer.write(formattedMessage);
            writer.newLine();
            System.out.println("Saved to Patient File.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
