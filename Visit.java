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

    // Getters and setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}

