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

public class Main2 extends Application {

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

        String[] labels = {"Age", "Weight", "Height", "Temperature", "BPM"};
        for (int i = 0; i < labels.length; i++) {
            Label label = new Label(labels[i]);
            label.setMinWidth(Region.USE_PREF_SIZE);
            label.setAlignment(Pos.CENTER_RIGHT);

            TextField textField = new TextField();
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
        TableView<String> allergiesTable = new TableView<>();
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
        TextArea healthConcernsArea = new TextArea();
        healthConcernsBox.getChildren().addAll(healthConcernsTitle, healthConcernsArea);

        // Layout setup
        HBox contentBox = new HBox(20);
        contentBox.getChildren().addAll(vitalsGrid, allergiesBox, healthConcernsBox);
        contentBox.setAlignment(Pos.TOP_CENTER);

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

    public static void main(String[] args) {
        launch(args);
    }
}






