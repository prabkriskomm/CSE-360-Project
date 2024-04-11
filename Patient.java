package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Patient {

    private List<Visit> visits;
    private int age;
    private String medicalHistory;
    private String password;
    private String email;
    private String phoneNumber;
    private String insurance;
    private String firstName;
    private String lastName;
    private String birthday;

    // Constructor to initialize visits list
    public Patient() {
        this.visits = new ArrayList<>();
    }
    
    public void addVisit(Visit visit) {
    	visits.add(visit);
    }

    // Load patient data from patient.txt
    public void loadData() {
        try (BufferedReader br = new BufferedReader(new FileReader("patient.txt"))) {
            //String header = br.readLine(); // Read and discard the header
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                this.firstName = values[0];
                this.lastName = values[1];
                this.birthday = values[2];
                this.age = Integer.parseInt(values[3]);
                this.medicalHistory = values[4];
                this.password = values[5];
                this.email = values[6];
                this.phoneNumber = values[7];
                this.insurance = values[8];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save patient data to patient.txt
    public void saveData(){
    	
	    File file = new File("patient.txt");
	    

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
    	    if (!file.exists()) {
                file.createNewFile(); // Create a new file if it doesn't exist
            }
    	    
            StringBuilder sb = new StringBuilder();
            sb.append(this.firstName).append(",")
              .append(this.lastName).append(",")
              .append(this.birthday).append(",")
              .append(this.age).append(",")
              .append(this.medicalHistory).append(",")
              .append(this.password).append(",")
              .append(this.email).append(",")
              .append(this.phoneNumber).append(",")
              .append(this.insurance);
            bw.write(sb.toString());
            bw.newLine();
            System.out.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getter and Setter methods
    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public int getAge() {
        loadData();
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        saveData();
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
        saveData();
    }

    public String getBirthday() {
        loadData();
        return birthday;
    }

    public String getMedicalHistory() {
        loadData();
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
        saveData();
    }

    public String getPassword() {
        loadData();
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        saveData();
    }

    public String getEmail() {
        loadData();
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        saveData();
    }

    public String getPhoneNumber() {
        loadData();
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        saveData();
    }

    public String getInsurance() {
        loadData();
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
        saveData();
    }

    public String getFirstName() {
        loadData();
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        saveData();
    }

    public String getLastName() {
        loadData();
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        saveData();
    }
}
