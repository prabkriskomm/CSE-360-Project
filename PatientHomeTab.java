package application;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PatientHomeTab extends BorderPane {

    private VBox messageDisplayArea;
    private TabPane tabs;
    private Stage primaryStage;

    public PatientHomeTab(Stage primaryStage) {
        this.primaryStage = primaryStage;
        createTabs();
        createHomeInterface();
    }

    private void createTabs() {
        tabs = new TabPane();

        Tab homeTab = new Tab("Home", createHomeInterface());
        homeTab.setClosable(false);
        Tab healthRecordTab = new Tab("Health Record", new PatientHealthRecord());
        healthRecordTab.setClosable(false);
        Tab messagesTab = new Tab("Messages", createMessagingInterface());
        messagesTab.setClosable(false);
        Tab visitsTab = new Tab("Visits", new PatientVisitsTab());
        visitsTab.setClosable(false);

        tabs.getTabs().addAll(homeTab, healthRecordTab, messagesTab, visitsTab);
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> {
            Platform.runLater(() -> {
                WelcomeAndLoginPage welcomePage = new WelcomeAndLoginPage();
                welcomePage.start(primaryStage);
            });
        });

        Label mediateLabel = new Label("MEDIATE");
        mediateLabel.setFont(new Font("Arial", 25));
        mediateLabel.setStyle("-fx-font-weight: bold;");

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(tabs, logoutButton, mediateLabel);
        StackPane.setAlignment(logoutButton, Pos.TOP_RIGHT);
        StackPane.setAlignment(mediateLabel, Pos.TOP_RIGHT);
        StackPane.setMargin(logoutButton, new Insets(3, 130, 0, 0));
        StackPane.setMargin(mediateLabel, new Insets(1, 10, 0, 0));

        this.setCenter(stackPane);
    }

    private BorderPane createHomeInterface() {
        Button initialsButton = new Button("Edit Profile");
        //initialsButton.setStyle("-fx-font-weight: bold;");

        initialsButton.setOnAction(e -> {
            Tab editProfileTab = ChangeContactInfo.createEditProfileTab();
            tabs.getTabs().add(editProfileTab);
            tabs.getSelectionModel().select(editProfileTab);
        });

        BorderPane topLayout = new BorderPane();
        topLayout.setRight(initialsButton);

        Label welcomeLabel = new Label("Welcome, John!"); //John needs to be changed to Patient.getFirstName()
        welcomeLabel.setFont(new Font("Arial", 40));
        //welcomeLabel.setStyle("-fx-font-weight: bold;");
        
        
        Label logoLabel = new Label("MEDIATE");
        logoLabel.setFont(new Font("Arial", 35));
        logoLabel.setStyle("-fx-font-weight: bold;");
        
        Label taglineLabel = new Label("Empowering Health, One Click at a Time.");
        taglineLabel.setFont(new Font("Arial", 19));
        VBox.setMargin(taglineLabel, new Insets(0, 0, 5, 0)); 

        VBox sloganBox = new VBox(welcomeLabel, logoLabel, taglineLabel);
        sloganBox.setAlignment(Pos.CENTER);
        sloganBox.setSpacing(10);

        BorderPane homeLayout = new BorderPane();
        homeLayout.setTop(topLayout);
        homeLayout.setCenter(sloganBox);

        return homeLayout;
    }

    private BorderPane createMessagingInterface() {
        messageDisplayArea = new VBox(5);
        messageDisplayArea.setPadding(new Insets(10));
        messageDisplayArea.setFillWidth(true);

        ScrollPane scrollPane = new ScrollPane(messageDisplayArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setVvalue(1.0);

        TextArea messageArea = new TextArea();
        messageArea.setPromptText("Type your message here");

        Button sendButton = new Button("Send");
        sendButton.setMaxWidth(Double.MAX_VALUE);
        sendButton.setOnAction(event -> {
            String message = messageArea.getText();
            if (!message.isEmpty()) {
                Label messageLabel = new Label(message);
                messageLabel.setMaxWidth(Double.MAX_VALUE);
                messageLabel.setAlignment(Pos.TOP_RIGHT);
                messageLabel.setStyle("-fx-background-color: lightgray; -fx-padding: 5;");
                messageDisplayArea.getChildren().add(messageLabel);
                messageArea.clear();
                scrollPane.setVvalue(scrollPane.getVmax());
            }
        });

        Button endChatButton = new Button("End Chat");
        endChatButton.setOnAction(event -> {
            File file = new File("chat_history.txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Node messageNode : messageDisplayArea.getChildren()) {
                    if (messageNode instanceof Label) {
                        Label messageLabel = (Label) messageNode;
                        String formattedMessage = "Patient: " + messageLabel.getText();
                        writer.write(formattedMessage);
                        writer.newLine();
                    }
                }
                System.out.println("Chat saved to " + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            messageArea.clear();
            messageDisplayArea.getChildren().clear();
        });

        VBox buttonBox = new VBox(10, sendButton, endChatButton);
        VBox.setVgrow(sendButton, Priority.ALWAYS);
        VBox.setVgrow(endChatButton, Priority.ALWAYS);

        HBox messageBox = new HBox(10);
        HBox.setHgrow(messageArea, Priority.ALWAYS);
        messageBox.getChildren().addAll(messageArea, buttonBox);
        messageBox.setPadding(new Insets(10));

        BorderPane messageLayout = new BorderPane();
        messageLayout.setCenter(scrollPane);
        messageLayout.setBottom(messageBox);

        return messageLayout;
    }
}
