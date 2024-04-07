package application;

public class Visit {
    private String date;
    private String summary;
    private String bloodPressure;
    private int heartRate;
    private double temperature;
    private double weight;
    private double height;

    public Visit(String date, String summary, String bloodPressure, int heartRate, double temperature, double weight, double height) {
        this.date = date;
        this.summary = summary;
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
        this.temperature = temperature;
        this.weight = weight;
        this.height = height;
    }

    // Getters
    public String getDate() { return date; }
    public String getSummary() { return summary; }
    public String getBloodPressure() { return bloodPressure; }
    public int getHeartRate() { return heartRate; }
    public double getTemperature() { return temperature; }
    public double getWeight() { return weight; }
    public double getHeight() { return height; }
    
    // Setters
    public void setDate(String date) { this.date = date; }
    public void setSummary(String summary) { this.summary = summary; }
    public void setBloodPressure(String bloodPressure) { this.bloodPressure = bloodPressure; }
    public void setHeartRate(int heartRate) { this.heartRate = heartRate; }
    public void setTemperature(double temperature) { this.temperature = temperature; }
    public void setWeight(double weight) { this.weight = weight; }
    public void setHeight(double height) { this.height = height; }
}
