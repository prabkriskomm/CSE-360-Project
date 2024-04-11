package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Visit {
    private String firstName;
    private String lastName;
    private String birthday;
    private String date;
    private String diagnosis;
    private String courseOfAction;
    private String prescription;
    private int BP;
    private int HR;
    private double temp;
    private double height;
    private double weight;

    // Constructor with firstName, lastName, and birthday parameters
    public Visit(String firstName, String lastName, String birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public String getPrescription() {
        loadVisitData(); // Load visit data before returning the prescription
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
        saveVisitData(); // Save visit data after setting prescription
    }

    // Getter and Setter methods for date
    public String getDate() {
        loadVisitData(); // Load visit data before returning the date
        return date;
    }

    public void setDate(String date) {
        this.date = date;
        saveVisitData(); // Save visit data after setting date
    }

    // Getter and Setter methods for diagnosis
    public String getDiagnosis() {
        loadVisitData(); // Load visit data before returning the diagnosis
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
        saveVisitData(); // Save visit data after setting diagnosis
    }

    // Getter and Setter methods for courseOfAction
    public String getCourseOfAction() {
        loadVisitData(); // Load visit data before returning the courseOfAction
        return courseOfAction;
    }

    public void setCourseOfAction(String courseOfAction) {
        this.courseOfAction = courseOfAction;
        saveVisitData(); // Save visit data after setting courseOfAction
    }

    // Getter and Setter methods for BP
    public int getBP() {
        loadVisitData(); // Load visit data before returning BP
        return BP;
    }

    public void setBP(int BP) {
        this.BP = BP;
        saveVisitData(); // Save visit data after setting BP
    }

    // Getter and Setter methods for HR
    public int getHR() {
        loadVisitData(); // Load visit data before returning HR
        return HR;
    }

    public void setHR(int HR) {
        this.HR = HR;
        saveVisitData(); // Save visit data after setting HR
    }

    // Getter and Setter methods for temp
    public double getTemp() {
        loadVisitData(); // Load visit data before returning temp
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
        saveVisitData(); // Save visit data after setting temp
    }

    // Getter and Setter methods for height
    public double getHeight() {
        loadVisitData(); // Load visit data before returning height
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
        saveVisitData(); // Save visit data after setting height
    }

    // Getter and Setter methods for weight
    public double getWeight() {
        loadVisitData(); // Load visit data before returning weight
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
        saveVisitData(); // Save visit data after setting weight
    }

    private void saveVisitData() {
        File file = new File("visit.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            if (!file.exists()) {
                file.createNewFile(); // Create a new file if it doesn't exist
            }

            StringBuilder sb = new StringBuilder();
            sb.append(firstName).append(",")
              .append(lastName).append(",")
              .append(birthday).append(",")
              .append(date).append(",")
              .append(height).append(",")
              .append(weight).append(",")
              .append(BP).append(",")
              .append(HR).append(",")
              .append(temp).append(",")
              .append(prescription).append(",")
              .append(diagnosis).append(",")
              .append(courseOfAction);
            bw.write(sb.toString());
            bw.newLine();
            System.out.println(sb);
        } catch (IOException e) {
            System.err.println("Error saving visit data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void loadVisitData() {
        try (BufferedReader br = new BufferedReader(new FileReader("visit.txt"))) {
            //String header = br.readLine(); // Read and discard the header
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                this.firstName = values[0];
                this.lastName = values[1];
                this.birthday = values[2];
                this.date = values[3];
                this.height = Double.parseDouble(values[4]);
                this.weight = Double.parseDouble(values[5]);
                this.BP = Integer.parseInt(values[6]);
                this.HR = Integer.parseInt(values[7]);
                this.temp = Double.parseDouble(values[8]);
                this.prescription = values[9];
                this.diagnosis = values[10];
                this.courseOfAction = values[11];
            }
        } catch (IOException e) {
            System.err.println("Error loading visit data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
