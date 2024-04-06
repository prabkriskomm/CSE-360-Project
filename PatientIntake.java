package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PatientIntake extends BorderPane {
    public PatientIntake() {
        // MenuBar and Menus
        MenuBar menuBar = new MenuBar();
        Menu scheduleMenu = new Menu("Schedule");
        Menu intakeMenu = new Menu("Intake");
        Menu recordsMenu = new Menu("Records");
        Menu diagnosisMenu = new Menu("Diagnosis");
        Menu messagesMenu = new Menu("Messages");
        menuBar.getMenus().addAll(scheduleMenu, intakeMenu, recordsMenu, diagnosisMenu, messagesMenu);

        // Vitals section
        VBox vitalsBox = new VBox(10);
        vitalsBox.setPadding(new Insets(15));
        Label vitalsTitle = new Label("Vitals");
        vitalsTitle.setAlignment(Pos.CENTER);
        vitalsTitle.setMaxWidth(Double.MAX_VALUE);
        vitalsBox.getChildren().add(vitalsTitle);
        String[] labels = {"Age", "Weight", "Height", "Temperature", "BPM"};
        for (String label : labels) {
            HBox row = new HBox(10);
            row.setAlignment(Pos.CENTER_LEFT);
            row.getChildren().addAll(new Label(label), new TextField());
            vitalsBox.getChildren().add(row);
        }

        // Allergies section
        VBox allergiesBox = new VBox(10);
        allergiesBox.setPadding(new Insets(15));
        Label allergiesTitle = new Label("Allergies");
        allergiesTitle.setAlignment(Pos.CENTER);
        allergiesTitle.setMaxWidth(Double.MAX_VALUE);
        TableView<String> allergiesTable = new TableView<>();
        TableColumn<String, String> allergenColumn = new TableColumn<>("Allergen Name");
        TableColumn<String, String> dateColumn = new TableColumn<>("MM/YY");
        allergiesTable.getColumns().addAll(allergenColumn, dateColumn);
        allergiesBox.getChildren().addAll(allergiesTitle, allergiesTable);

        // Health Concerns section
        VBox healthConcernsBox = new VBox(10);
        healthConcernsBox.setPadding(new Insets(15));
        Label healthConcernsTitle = new Label("Health Concerns");
        healthConcernsTitle.setAlignment(Pos.CENTER);
        healthConcernsTitle.setMaxWidth(Double.MAX_VALUE);
        TextArea healthConcernsArea = new TextArea();
        healthConcernsBox.getChildren().addAll(healthConcernsTitle, healthConcernsArea);

        // Layout setup
        HBox contentBox = new HBox(20);
        contentBox.getChildren().addAll(vitalsBox, allergiesBox, healthConcernsBox);
        contentBox.setAlignment(Pos.TOP_CENTER);

        // Root layout
        this.setTop(menuBar);
        this.setCenter(contentBox);
    }
}
