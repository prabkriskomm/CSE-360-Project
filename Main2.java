package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class Main2 extends Application {

    private TextField ageField, weightField, heightField, temperatureField, bpmField;
    private TextArea healthConcernsArea;
    private TableView<String> allergiesTable;

    @Override
    public void start(Stage primaryStage) {
        // MenuBar and Menus
        MenuBar menuBar = new MenuBar();
        Menu scheduleMenu = new Menu("Schedule");
        Menu intakeMenu = new Menu("Intake");
        Menu recordsMenu = new Menu("Records");
        Menu diagnosisMenu = new Menu("Diagnosis");
        Menu messagesMenu = new Menu("Messages");
        menuBar.getMenus().addAll(scheduleMenu, intakeMenu, recordsMenu, diagnosisMenu, messagesMenu);

        // Vitals section with GridPane for even spacing
        GridPane vitalsGrid = new GridPane();
        vitalsGrid.setPadding(new Insets(0, 15, 15, 15));
        vitalsGrid.setVgap(10);
        vitalsGrid.setHgap(10);
        vitalsGrid.setAlignment(Pos.TOP_CENTER);

        Label vitalsTitle = new Label("Vitals");
        vitalsTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        vitalsTitle.setMaxWidth(Double.MAX_VALUE);
        vitalsTitle.setAlignment(Pos.CENTER);
        GridPane.setConstraints(vitalsTitle, 0, 0, 2, 1, HPos.CENTER, VPos.CENTER);
        vitalsGrid.getChildren().add(vitalsTitle);

        ageField = new TextField();
        weightField = new TextField();
        heightField = new TextField();
        temperatureField = new TextField();
        bpmField = new TextField();

        String[] labels = {"Age", "Weight", "Height", "Temperature", "BPM"};
        TextField[] fields = {ageField, weightField, heightField, temperatureField, bpmField};
        for (int i = 0; i < labels.length; i++) {
            Label label = new Label(labels[i]);
            label.setMinWidth(Region.USE_PREF_SIZE);
            label.setAlignment(Pos.CENTER_RIGHT);

            TextField textField = fields[i];
            textField.setPrefWidth(200); // Set a preferred width for text fields

            GridPane.setConstraints(label, 0, i + 1);
            GridPane.setConstraints(textField, 1, i + 1);

            vitalsGrid.getChildren().addAll(label, textField);
        }

        // Allergies section
        VBox allergiesBox = new VBox(10);
        allergiesBox.setPadding(new Insets(0, 15, 15, 15));
        Label allergiesTitle = new Label("Allergies");
        allergiesTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        allergiesTitle.setAlignment(Pos.CENTER);
        allergiesTitle.setMaxWidth(Double.MAX_VALUE);
        allergiesTable = new TableView<>();
        TableColumn<String, String> allergenColumn = new TableColumn<>("Allergen Name");
        TableColumn<String, String> dateColumn = new TableColumn<>("MM/YY");
        allergiesTable.getColumns().addAll(allergenColumn, dateColumn);
        allergiesBox.getChildren().addAll(allergiesTitle, allergiesTable);

        // Health Concerns section
        VBox healthConcernsBox = new VBox(10);
        healthConcernsBox.setPadding(new Insets(0, 15, 15, 15));
        Label healthConcernsTitle = new Label("Health Concerns");
        healthConcernsTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        healthConcernsTitle.setAlignment(Pos.CENTER);
        healthConcernsTitle.setMaxWidth(Double.MAX_VALUE);
        healthConcernsArea = new TextArea();
        healthConcernsBox.getChildren().addAll(healthConcernsTitle, healthConcernsArea);

        // Layout setup
        HBox contentBox = new HBox(20);
        contentBox.getChildren().addAll(vitalsGrid, allergiesBox, healthConcernsBox);
        contentBox.setAlignment(Pos.TOP_CENTER);

        // Save button
        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> saveData());
        VBox saveBox = new VBox(saveButton);
        saveBox.setAlignment(Pos.BOTTOM_CENTER);
        saveBox.setPadding(new Insets(15, 0, 15, 0));
        contentBox.getChildren().add(saveBox);

        // Root layout
        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(contentBox);

        // Scene and Stage setup
        Scene scene = new Scene(root, 1174, 665);
        primaryStage.setTitle("MEDIATE - Patient Intake Form");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void saveData() {
        try {
            PrintWriter writer = new PrintWriter(new File("patient_data.txt"));

            // Writing vitals to the file
            writer.println("Vitals:");
            writer.println("Age: " + ageField.getText());
            writer.println("Weight: " + weightField.getText());
            writer.println("Height: " + heightField.getText());
            writer.println("Temperature: " + temperatureField.getText());
            writer.println("BPM: " + bpmField.getText());

            // Writing allergies to the file (assuming allergiesTable.getItems() returns the list of allergies)
            writer.println("\nAllergies:");
            for (String allergy : allergiesTable.getItems()) {
                writer.println(allergy);
            }

            // Writing health concerns to the file
            writer.println("\nHealth Concerns:");
            writer.println(healthConcernsArea.getText());

            writer.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Data saved successfully.");
            alert.showAndWait();
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Could not save the data.");
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}





