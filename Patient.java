package application;

import java.util.ArrayList;
import java.util.List;

public class Patient {
    private String patientId;
    private String patientName;
    private List<Visit> visits;

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
