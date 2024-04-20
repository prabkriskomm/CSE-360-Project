package application;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//team 9 is amazing
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
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
        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Birthday");

        // Login button
        Button loginButton = new Button("Login");
        
        Hyperlink signUpLink = new Hyperlink("Sign up here");
        signUpLink.setOnAction(e -> {
        // Assuming you have a method showSignUpPage() that displays the sign-up page
        showSignUpPage();
    });


        // Back button
        Button backButton = new Button("<- Back");
        backButton.setOnAction(e -> {
            WelcomeAndLoginPage welcomePage = new WelcomeAndLoginPage();
            welcomePage.start(primaryStage);
        });

        // Horizontal layout for birthday field, login button, and back button
        HBox bottomLayout = new HBox(10);
        bottomLayout.getChildren().addAll(datePicker, loginButton, backButton, signUpLink);

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
        	LocalDate date = datePicker.getValue();
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        	// Convert the LocalDate to a string using the formatter
        	String dateString = date.format(formatter);
            String firstName = firstNameField.getText();
            String LName = lastNameField.getText();
            String password = passwordField.getText();
            save(firstName);
            loadPatientInfo(firstName, LName, password, dateString);

        });
    }

    void save(String firstName) {
        File file = new File("Current_Patient.txt");
        // Writing to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(firstName);
            System.out.println("Saved to Patient File.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	private void showSignUpPage() {
		// TODO Auto-generated method stub
		SignUpPage signUpPage = new SignUpPage();
	    signUpPage.start(primaryStage);
	}

    void loadPatientInfo(String firstName, String lastName, String password, String date) {
        File file = new File("Patient_Profiles.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                boolean foundInfo = false;
                while ((line = reader.readLine()) != null) {
                    // Split the line into parts using comma as a delimiter
                    String[] parts = line.split(", ");
                    // Check if the first name and last name match
                    if (parts.length >= 2 && parts[0].equals(firstName) && parts[1].equals(lastName)) {
                        // Verify the password (assuming password is the third element)
                        if (parts.length >= 4 && parts[3].equals(password)) {
                            foundInfo = true;
                            primaryStage.setTitle("Patient Home");
                            PatientHomeTab patientHomeTab = new PatientHomeTab();
                            primaryStage.setScene(new Scene(patientHomeTab, 800, 600));
                            primaryStage.show();
                            break;
                        }
                    }
                }
                // If the information was not found, display a message indicating it
                if (!foundInfo) {
                    // Handle the case when the information is not found
                    System.out.println("Patient information not found.");
                    System.out.println("Searching for: " + firstName + ", " + lastName);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
