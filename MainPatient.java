package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class MainPatient extends Application {
 @Override
 public void start(Stage primaryStage) {
     try {
    	 
         PatientHomeTab PatientHomeTab = new PatientHomeTab();
         Scene scene = new Scene(PatientHomeTab, 800, 600);
         
         
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