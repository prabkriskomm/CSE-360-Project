package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Root layout
        BorderPane root = new BorderPane();

        // MenuBar and Menus
        MenuBar menuBar = new MenuBar();
        Menu homeMenu = new Menu("Home");
        Menu recordMenu = new Menu("Health Record");
        Menu messageMenu = new Menu("Messages");
        Menu visitMenu = new Menu("Visits");
        menuBar.getMenus().addAll(homeMenu, recordMenu, messageMenu, visitMenu);

        // Button for patient initials
        Button initialsButton = new Button("JS");
        initialsButton.setStyle("-fx-font-weight: bold;");

        // Top layout with MenuBar and Button for patient initials
        BorderPane topLayout = new BorderPane();
        topLayout.setLeft(menuBar);
        topLayout.setRight(initialsButton);

        // Welcome Message
        Label welcomeLabel = new Label("Welcome, John!");
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Slogan
        Label sloganLabel = new Label("MEDIATE\nEmpowering Health, One Click at a Time.");
        sloganLabel.setStyle("-fx-font-size: 16px;");

        // VBox for center alignment
        VBox centerBox = new VBox(welcomeLabel, sloganLabel);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setSpacing(10);

        // Set components to BorderPane
        root.setTop(topLayout);
        root.setCenter(centerBox);

        // Scene and Stage setup
        Scene scene = new Scene(root, 936, 530);
        primaryStage.setTitle("MEDIATE - Patient Home");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
