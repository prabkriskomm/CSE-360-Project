package application;
//team 9 is amazing
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font; 
import javafx.stage.Stage;

public class StaffHomeTab extends BorderPane {
    private Stage primaryStage;
    private TabPane tabs;

    public StaffHomeTab(Stage primaryStage) {
        this.primaryStage = primaryStage;
        createTabs();
        createTopLabel();
        createWelcomeLabel();
    }

    private void createTabs() {
        tabs = new TabPane();
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

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
        
        Label logoLabel = new Label("MEDIATE");
        logoLabel.setFont(new Font("Arial", 35));
        logoLabel.setStyle("-fx-font-weight: bold;");
        
        Label taglineLabel = new Label("Empowering Health, One Click at a Time.");
        taglineLabel.setFont(new Font("Arial", 19));
        VBox.setMargin(taglineLabel, new Insets(0, 0, 5, 0)); 

        homeLayout.getChildren().addAll(welcomeLabel, logoLabel, taglineLabel);
        homeTab.setContent(homeLayout);

        tabs.getTabs().addAll(homeTab, intakeTab, recordsTab, diagnosisTab, messagesTab);

        // Assuming these classes (Records, Diagnosis, PatientIntake, StaffMessaging) exist in your project
        recordsTab.setContent(new Records());
        diagnosisTab.setContent(new Diagnosis());
        intakeTab.setContent(new PatientIntake());
        messagesTab.setContent(new StaffMessaging());

        this.setTop(tabs);
    }

    private void createTopLabel() {
        Label mediateLabel = new Label("MEDIATE");
        mediateLabel.setFont(new Font("Arial", 25));
        mediateLabel.setStyle("-fx-font-weight: bold;");

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(event -> {
            Platform.runLater(() -> {
                try {
                    WelcomeAndLoginPage welcomePage = new WelcomeAndLoginPage();
                    welcomePage.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(tabs, logoutButton, mediateLabel);
        StackPane.setAlignment(logoutButton, Pos.TOP_RIGHT);
        StackPane.setMargin(logoutButton, new Insets(3, 130, 0, 0));
        StackPane.setAlignment(mediateLabel, Pos.TOP_RIGHT);
        StackPane.setMargin(mediateLabel, new Insets(1, 10, 0, 0));

        this.setCenter(stackPane);
    }

    private void createWelcomeLabel() {
        Label welcomeLabel = new Label("Welcome!");
        welcomeLabel.setFont(new Font("Arial", 30));
        // If needed, add welcomeLabel to your layout
    }
}
