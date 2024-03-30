package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import javafx.stage.Stage;

public class PatientMessaging extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = new BorderPane();

            // TabPane for the top area
            TabPane tabPane = new TabPane();
            Tab homeTab = new Tab("Home");
            Tab healthRecordTab = new Tab("Health Record");
            Tab messagesTab = new Tab("Messages");
            Tab visitsTab = new Tab("Visits");
            tabPane.getTabs().addAll(homeTab, healthRecordTab, messagesTab, visitsTab);
            
            // TextArea and Send Button for the bottom area
            TextArea messageArea = new TextArea();
            Button sendButton = new Button("Send");
            HBox messageBox = new HBox(messageArea, sendButton);
            HBox.setHgrow(messageArea, Priority.ALWAYS); 
            
           
            root.setTop(tabPane);
            root.setBottom(messageBox);

            Scene scene = new Scene(root, 800, 600);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Mediate - Staff Home");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
