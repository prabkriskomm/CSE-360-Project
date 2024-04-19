package application;
//team 9 is amazing
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

        // Create a VBox layout for the Home tab content
        VBox homeLayout = new VBox(10); // Adjust the spacing as needed
        homeLayout.setAlignment(Pos.CENTER);
        homeLayout.setStyle("-fx-background-color: #D3D3D3;"); // This sets the background to light gray

        Label welcomeLabel = new Label("Welcome!");
        welcomeLabel.setFont(new Font("Arial", 40));
        
        tabs.setStyle("-fx-background-color: #D3D3D3; -fx-border-color: black;");
        
        for(Tab tab : tabs.getTabs()) {
            // This will apply to all tabs, you might need to adjust this if you want different styles for each tab
            tab.setStyle("-fx-background-color: #D3D3D3; -fx-border-color: black; -fx-border-width: 0 0 1 0;");
        }
        
        // Add some spacing above the welcome label if needed
        VBox.setMargin(welcomeLabel, new Insets(-10, 0, 30, 0)); // Reduces space above the welcome label

        Label logoLabel = new Label("MEDIATE");
        logoLabel.setFont(new Font("Arial", 35));
        logoLabel.setStyle("-fx-font-weight: bold;");
        
        Label taglineLabel = new Label("Empowering Health, One Click at a Time.");
        taglineLabel.setFont(new Font("Arial", 19));
        VBox.setMargin(taglineLabel, new Insets(0, 0, 5, 0)); // Adjust the bottom margin to move the slogan up

        // Add the labels to the VBox
        homeLayout.getChildren().addAll(welcomeLabel, logoLabel, taglineLabel);
        
        // Set the VBox as the content of the Home tab
        homeTab.setContent(homeLayout);

        tabs.getTabs().addAll(homeTab, intakeTab, recordsTab, diagnosisTab, messagesTab);
        
        this.setTop(tabs);
        
     // Create and set the Records page in the Records tab
        Records recordsPage = new Records();
        recordsTab.setContent(recordsPage);
        
        Diagnosis diagnosisPage = new Diagnosis();
        diagnosisTab.setContent(diagnosisPage);
        
        PatientIntake intakePage = new PatientIntake();
        intakeTab.setContent(intakePage);

        StaffMessaging StaffMessagePage = new StaffMessaging();
        messagesTab.setContent(StaffMessagePage);

        
    }



    private void createTopLabel() {
        Label mediateLabel = new Label("MEDIATE");
        
        mediateLabel.setStyle("-fx-font-weight: bold;");
        
        mediateLabel.setFont(new Font("Arial", 25));
        
        // Align the label to the top right
        StackPane.setAlignment(mediateLabel, Pos.TOP_RIGHT);
        // Margin can be adjusted to control the spacing from the top and right edges
        StackPane.setMargin(mediateLabel, new Insets(1, 10, 0, 0));
        
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(tabs, mediateLabel);

        // Set the StackPane containing the TabPane and the label to the center
        this.setCenter(stackPane);
    }
    
    private void createWelcomeLabel() {
        Label welcomeLabel = new Label("Welcome!");
        welcomeLabel.setFont(new Font("Arial", 30));

    }
}
