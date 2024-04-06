package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

//below are the new imports I added
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.util.List;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.Node;

//PatientVisitsTab created by Movinya Gunatilaka

public class PatientVisitsTab extends BorderPane {

    private VBox messageDisplayArea; 
    private List<Visit> visits = new ArrayList<>(); //list of visits of the patient


    public PatientVisitsTab() {
        createTabs();
        
    }

    private void createTabs() {
        TabPane tabs = new TabPane();
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab homeTab = new Tab("Home");
        Tab healthRecordTab = new Tab("Health Record");
        Tab messagesTab = new Tab("Messages");
        Tab visitsTab = new Tab("Visits");

        //do I still need to keep messages content here, or will that already be there when main runs?
        messagesTab.setContent(createMessagingInterface());
        
        //set content of visits tab
        visitsTab.setContent(createVisitTable());


        tabs.getTabs().addAll(homeTab, healthRecordTab, messagesTab, visitsTab);

	//when this button is hit a new tab will open to edit profile, I need to make it closeable somehow    
	Button editProfileButton = new Button("Edit Profile");
        editProfileButton.setOnAction(e -> {
            Tab editProfileTab = ChangeContactInfo.createEditProfileTab();
            tabs.getTabs().add(editProfileTab);
            tabs.getSelectionModel().select(editProfileTab);
        });
        
        //add a simple button on the home tab
        homeTab.setContent(editProfileButton);
	    
        setCenter(tabs);
    }

    private BorderPane createVisitTable() {
    	//I am struggling to put values in the table, PropertyValueFactory isn't working
    	Patient patient = new Patient("P001", "John Doe");
        patient.addVisit(new Visit("2024-04-01", "Routine checkup", "120/80", 75, 98.6, 70.5, 170.0));
        patient.addVisit(new Visit("2024-03-15", "Follow-up", "130/85", 72, 98.2, 71.0, 171.5));

        visits.addAll(patient.getVisits());

        TableView<Visit> table = new TableView<>();
        table.getItems().addAll(visits);

        TableColumn<Visit, String> dateCol = new TableColumn<>("Date");
        //summaryCol.setCellValueFactory(new PropertyValueFactory<>("date"));;

        TableColumn<Visit, String> summaryCol = new TableColumn<>("Summary");
        //summaryCol.setCellValueFactory(new PropertyValueFactory<>("summary"));

        TableColumn<Visit, String> bpCol = new TableColumn<>("Blood Pressure");
        //bpCol.setCellValueFactory(new PropertyValueFactory<>("bloodPressure"));

        TableColumn<Visit, Integer> hrCol = new TableColumn<>("Heart Rate");
        //hrCol.setCellValueFactory(new PropertyValueFactory<>("heartRate"));

        TableColumn<Visit, Double> tempCol = new TableColumn<>("Temperature");
        //tempCol.setCellValueFactory(new PropertyValueFactory<>("temperature"));

        TableColumn<Visit, Double> weightCol = new TableColumn<>("Weight");
        //weightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));

        TableColumn<Visit, Double> heightCol = new TableColumn<>("Height");
        //heightCol.setCellValueFactory(new PropertyValueFactory<>("height"));

        table.getColumns().addAll(dateCol, summaryCol, bpCol, hrCol, tempCol, weightCol, heightCol);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(table);
        
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(scrollPane);

        this.getChildren().add(borderPane);

        return borderPane;
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


        // Create End Chat button
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



        VBox buttonBox = new VBox(10);
        buttonBox.getChildren().addAll(sendButton, endChatButton);
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
