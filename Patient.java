package application;

import java.util.ArrayList;
import java.util.List;

public class Patient {
    private String patientName;
    private List<Visit> visits;
    
    //diagnosis
    private String diagnosis;
    private String courseOfAction;
    private String prescription;
    private String date;
    
    //intake
    private int BP;
    private int HR;
    private int temp;
    private double weight;
    private double height;
    private int age;
    private String medicalHistory;

    
    //from Sign Up page 
    private String password;
    private String email;
    private String phoneNumber;
    private String insurance;
    private String patientId;
    private String firstName;
    private String lastName;


    public Patient(String patientId, String patientName) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.visits = new ArrayList<>();
    }

    // Getters and setters
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void addVisit(Visit visit) {
        visits.add(visit);
    }
}
