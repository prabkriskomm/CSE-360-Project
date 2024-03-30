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
}
