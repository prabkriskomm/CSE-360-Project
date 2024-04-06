package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
 @Override
 public void start(Stage primaryStage) {
     try {
    	 
    	 
         //StaffHomeTab staffHomeTab = new StaffHomeTab();
         //Scene scene = new Scene(staffHomeTab, 800, 600);
         
         PatientHomeTab patientHomeTab = new PatientHomeTab();
         Scene scene = new Scene(patientHomeTab, 800, 600);
	 Scene scene2 = new Scene(patientVisitsTab, 800, 600); //Movi added this line

         
         scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
         primaryStage.setScene(scene2);
         primaryStage.setTitle("Mediate");
         primaryStage.show();
         
     } catch (Exception e) {
         e.printStackTrace();
     }
 }

 public static void main(String[] args) {
     launch(args);
 }
}
