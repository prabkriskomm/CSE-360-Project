package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font; 
import javafx.scene.layout.VBox;



public class StaffHomeTab extends BorderPane {

    private VBox messageDisplayArea;    //doc message system
    private TabPane tabs;

    public StaffHomeTab() {
        createTabs();
        createTopLabel();
        createWelcomeLabel();
    }

    private void createTabs() {
        tabs = new TabPane();
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        // Create the tabs
        Tab homeTab = new Tab("Home");
        Tab intakeTab = new Tab("Intake");
        Tab recordsTab = new Tab("Records");
        Tab diagnosisTab = new Tab("Diagnosis");
        Tab messagesTab = new Tab("Messages");

        
        VBox homeLayout = new VBox(10); 
        homeLayout.setAlignment(Pos.CENTER);
        homeLayout.setStyle("-fx-background-color: #D3D3D3;"); 

        Label welcomeLabel = new Label("Welcome!");
        welcomeLabel.setFont(new Font("Arial", 40));
        
        tabs.setStyle("-fx-background-color: #D3D3D3; -fx-border-color: black;");
        
        for(Tab tab : tabs.getTabs()) {
          
            tab.setStyle("-fx-background-color: #D3D3D3; -fx-border-color: black; -fx-border-width: 0 0 1 0;");
        }
        
        
        VBox.setMargin(welcomeLabel, new Insets(-10, 0, 30, 0)); 

        Label logoLabel = new Label("MEDIATE");
        logoLabel.setFont(new Font("Arial", 35));
        logoLabel.setStyle("-fx-font-weight: bold;");
        
        Label taglineLabel = new Label("Empowering Health, One Click at a Time.");
        taglineLabel.setFont(new Font("Arial", 19));
        VBox.setMargin(taglineLabel, new Insets(0, 0, 5, 0)); 

     
        homeLayout.getChildren().addAll(welcomeLabel, logoLabel, taglineLabel);
        
      
        homeTab.setContent(homeLayout);

        tabs.getTabs().addAll(homeTab, intakeTab, recordsTab, diagnosisTab, messagesTab);
        
        this.setTop(tabs);
        
         messagesTab.setContent(messageSys());
    }



    private void createTopLabel() {
        Label mediateLabel = new Label("MEDIATE");
        
        mediateLabel.setStyle("-fx-font-weight: bold;");
        
        mediateLabel.setFont(new Font("Arial", 25));
        
       
        StackPane.setAlignment(mediateLabel, Pos.TOP_RIGHT);
        StackPane.setMargin(mediateLabel, new Insets(1, 10, 0, 0));
        
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(tabs, mediateLabel);

        
        this.setCenter(stackPane);
    }
    
    private void createWelcomeLabel() {
        Label welcomeLabel = new Label("Welcome!");
        welcomeLabel.setFont(new Font("Arial", 30));

    }
    
    //Doc Message System
    	private BorderPane messageSys() {
        messageDisplayArea = new VBox(5);
        messageDisplayArea.setPadding(new Insets(10));
        messageDisplayArea.setFillWidth(true);

        ScrollPane scrollPane = new ScrollPane(messageDisplayArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setVvalue(1.0); 

        TextArea textArea = new TextArea();
        textArea.setPromptText("Type your message here");

        Button sendButton = new Button("Send");
        sendButton.setMaxWidth(Double.MAX_VALUE);
        sendButton.setOnAction(event -> {
            String message = textArea.getText();
            if (!message.isEmpty()) {
                Label messageLabel = new Label(message);
                messageLabel.setMaxWidth(Double.MAX_VALUE);
                messageLabel.setAlignment(Pos.TOP_RIGHT); 
                messageLabel.setStyle("-fx-background-color: lightgray; -fx-padding: 5;");
                messageDisplayArea.getChildren().add(messageLabel); 
                textArea.clear();

                
                scrollPane.setVvalue(scrollPane.getVmax()); 
            }
        });


        // Create End Chat button
        Button endChatButton = new Button("End Chat");
        endChatButton.setOnAction(event -> {
            File file = new File("chat_history.txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Node message : messageDisplayArea.getChildren()) {
                    if (message instanceof Label) {
                        Label messageLabel = (Label) message;
                        
                        String formattedMessage = "Doctor: " + messageLabel.getText();
                        writer.write(formattedMessage);
                        writer.newLine();
                    }
                }
                System.out.println("Chat saved to " + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }

            textArea.clear();
            messageDisplayArea.getChildren().clear(); 
        });

        TextField searchBar = new TextField();
        searchBar.setPromptText("Search...");
        searchBar.textProperty().addListener((observable, oldValue, Patient) -> {
            // have to get information from a file with all Patient info 
        	//Chat names = patient names find the chat
            System.out.println("Searching for: " + Patient);
        });
            
        VBox search = new VBox(10);
        search.getChildren().addAll(searchBar);
        
        VBox buttonBox = new VBox(10);
        buttonBox.getChildren().addAll(sendButton, endChatButton);
        VBox.setVgrow(sendButton, Priority.ALWAYS);
        VBox.setVgrow(endChatButton, Priority.ALWAYS);

        HBox textBox = new HBox(10);
        HBox.setHgrow(textArea, Priority.ALWAYS);
        textBox.getChildren().addAll(textArea, buttonBox);
        textBox.setPadding(new Insets(10));

        BorderPane messageLayout = new BorderPane();
        messageLayout.setLeft(search);
        messageLayout.setCenter(scrollPane);
        messageLayout.setBottom(textBox);

        return messageLayout;
    }
}
