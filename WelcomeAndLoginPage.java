package application;
//team 9 is amazing
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WelcomeAndLoginPage extends Application {

	private Stage primaryStage;
    private LoginController loginController;
    private StaffHomeTab staffHomeTab;

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Welcome to MEDIATE");

        // Initialize the StaffHomeTab
        staffHomeTab = new StaffHomeTab();

        // Initialize the LoginController with StaffHomeTab instance
        loginController = new LoginController(primaryStage, staffHomeTab);

        // Header
        Text headerText = new Text("MEDIATE");
        headerText.setFont(Font.font(30));

        // Subheader
        Text subHeaderText = new Text("Empowering health, One click at a time");

        // Buttons
        Button employeeLoginButton = new Button("Employee Login");
        Button patientLoginButton = new Button("Patient Login");

        // Employee login button action
        employeeLoginButton.setOnAction(e -> loginController.showEmployeeLoginPage());

        // Patient login button action
        patientLoginButton.setOnAction(e -> loginController.showPatientLoginPage());

        // Layout for welcome page
        HBox buttonLayout = new HBox(20);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.getChildren().addAll(employeeLoginButton, patientLoginButton);

        VBox welcomeLayout = new VBox(20);
        welcomeLayout.setAlignment(Pos.CENTER);
        welcomeLayout.setPadding(new Insets(50));
        welcomeLayout.getChildren().addAll(headerText, subHeaderText, buttonLayout);

        // Scene for welcome page
        Scene welcomeScene = new Scene(welcomeLayout, 400, 300);

        primaryStage.setScene(welcomeScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

class LoginController {

    private Stage primaryStage;
    private StaffHomeTab staffHomeTab;

    public LoginController(Stage primaryStage, StaffHomeTab staffHomeTab) {
        this.primaryStage = primaryStage;
        this.staffHomeTab = staffHomeTab;
    }

    public void showEmployeeLoginPage() {
        primaryStage.setTitle("Employee Login");

        // Header
        Text headerText = new Text("Employee Login");
        headerText.setFont(Font.font(15));

        // Input fields
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");

        // Horizontal layout for first name and last name
        HBox nameLayout = new HBox(10);
        nameLayout.getChildren().addAll(firstNameField, lastNameField);

        // Password field
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        // Birthday field
        TextField birthdayField = new TextField();
        birthdayField.setPromptText("Birthday");

        // Login button
        Button loginButton = new Button("Login");

        // Back button
        Button backButton = new Button("<- Back");
        backButton.setOnAction(e -> {
            WelcomeAndLoginPage welcomePage = new WelcomeAndLoginPage();
            welcomePage.start(primaryStage);
        });

        // Horizontal layout for birthday field, login button, and back button
        HBox bottomLayout = new HBox(10);
        bottomLayout.getChildren().addAll(birthdayField, loginButton, backButton);

        // Welcome Back text
        Text welcomeBackText = new Text("Welcome back");
        welcomeBackText.setFont(Font.font(25));

        // Layout for login page
        VBox loginLayout = new VBox(20);
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.setPadding(new Insets(50));

        loginLayout.getChildren().addAll(welcomeBackText, headerText, nameLayout, passwordField, bottomLayout);

        // Scene for login page
        Scene loginScene = new Scene(loginLayout, 400, 300);

        primaryStage.setScene(loginScene);
        primaryStage.show();
        
        loginButton.setOnAction(e -> {
            primaryStage.setTitle("Mediate - Staff Home");
            primaryStage.setScene(new Scene(staffHomeTab, 800, 600));
            primaryStage.show();
        });
    }

    public void showPatientLoginPage() {
        primaryStage.setTitle("Patient Login");

        // Header
        Text headerText = new Text("Patient Login");
        headerText.setFont(Font.font(15));

        // Input fields
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");

        // Horizontal layout for first name and last name
        HBox nameLayout = new HBox(10);
        nameLayout.getChildren().addAll(firstNameField, lastNameField);

        // Password field
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        // Birthday field
        TextField birthdayField = new TextField();
        birthdayField.setPromptText("Birthday");

        // Login button
        Button loginButton = new Button("Login");

        // Back button
        Button backButton = new Button("<- Back");
        backButton.setOnAction(e -> {
            WelcomeAndLoginPage welcomePage = new WelcomeAndLoginPage();
            welcomePage.start(primaryStage);
        });

        // Horizontal layout for birthday field, login button, and back button
        HBox bottomLayout = new HBox(10);
        bottomLayout.getChildren().addAll(birthdayField, loginButton, backButton);

        // Welcome Back text
        Text welcomeBackText = new Text("Welcome back");
        welcomeBackText.setFont(Font.font(25));

        // Layout for login page
        VBox loginLayout = new VBox(20);
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.setPadding(new Insets(50));

        loginLayout.getChildren().addAll(welcomeBackText, headerText, nameLayout, passwordField, bottomLayout);

        // Scene for login page
        Scene loginScene = new Scene(loginLayout, 400, 300);

        primaryStage.setScene(loginScene);
        primaryStage.show();

        loginButton.setOnAction(e -> {
            primaryStage.setTitle("Patient Home");
            PatientHomeTab patientHomeTab = new PatientHomeTab(); // Create an instance of PatientHomeTab
            primaryStage.setScene(new Scene(patientHomeTab, 800, 600)); // Set the scene to the patient home tab
            primaryStage.show();
        });
    }

}