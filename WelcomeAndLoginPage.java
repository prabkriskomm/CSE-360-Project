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
