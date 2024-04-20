package application;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;

public class PatientVisitsTab extends javafx.scene.layout.BorderPane {
    public PatientVisitsTab() {
        createVisitColumns();
    }

    private void createVisitColumns() {
        // Create text fields for each column
        TextField dateField1 = createNonEditableTextField("");
        TextField dateField2 = createNonEditableTextField("");
        TextField dateField3 = createNonEditableTextField("");
        TextField dateField4 = createNonEditableTextField("");
        TextField dateField5 = createNonEditableTextField("");
        TextField dateField6 = createNonEditableTextField("");

        TextField bpField1 = createNonEditableTextField("");
        TextField bpField2 = createNonEditableTextField("");
        TextField bpField3 = createNonEditableTextField("");
        TextField bpField4 = createNonEditableTextField("");
        TextField bpField5 = createNonEditableTextField("");
        TextField bpField6 = createNonEditableTextField("");

        TextField hrField1 = createNonEditableTextField("");
        TextField hrField2 = createNonEditableTextField("");
        TextField hrField3 = createNonEditableTextField("");
        TextField hrField4 = createNonEditableTextField("");
        TextField hrField5 = createNonEditableTextField("");
        TextField hrField6 = createNonEditableTextField("");

        TextField tempField1 = createNonEditableTextField("");
        TextField tempField2 = createNonEditableTextField("");
        TextField tempField3 = createNonEditableTextField("");
        TextField tempField4 = createNonEditableTextField("");
        TextField tempField5 = createNonEditableTextField("");
        TextField tempField6 = createNonEditableTextField("");

        TextField weightField1 = createNonEditableTextField("");
        TextField weightField2 = createNonEditableTextField("");
        TextField weightField3 = createNonEditableTextField("");
        TextField weightField4 = createNonEditableTextField("");
        TextField weightField5 = createNonEditableTextField("");
        TextField weightField6 = createNonEditableTextField("");

        TextField heightField1 = createNonEditableTextField("");
        TextField heightField2 = createNonEditableTextField("");
        TextField heightField3 = createNonEditableTextField("");
        TextField heightField4 = createNonEditableTextField("");
        TextField heightField5 = createNonEditableTextField("");
        TextField heightField6 = createNonEditableTextField("");

        TextField summaryField1 = createNonEditableTextField("");
        TextField summaryField2 = createNonEditableTextField("");
        TextField summaryField3 = createNonEditableTextField("");
        TextField summaryField4 = createNonEditableTextField("");
        TextField summaryField5 = createNonEditableTextField("");
        TextField summaryField6 = createNonEditableTextField("");

        // Set values for each row
        setRowValues(dateField1, bpField1, hrField1, tempField1, weightField1, heightField1, summaryField1,
                dateField2, bpField2, hrField2, tempField2, weightField2, heightField2, summaryField2,
                dateField3, bpField3, hrField3, tempField3, weightField3, heightField3, summaryField3,
                dateField4, bpField4, hrField4, tempField4, weightField4, heightField4, summaryField4,
                dateField5, bpField5, hrField5, tempField5, weightField5, heightField5, summaryField5,
                dateField6, bpField6, hrField6, tempField6, weightField6, heightField6, summaryField6);

        // Arrange text fields in a grid
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER_LEFT);
        gridPane.setHgap(10); // horizontal gap between columns
        gridPane.setVgap(5); // vertical gap between rows

        // Set preferred width for each column
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(14);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(14);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(14);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(14);
        ColumnConstraints col5 = new ColumnConstraints();
        col5.setPercentWidth(14);
        ColumnConstraints col6 = new ColumnConstraints();
        col6.setPercentWidth(14);
        ColumnConstraints col7 = new ColumnConstraints();
        col7.setPercentWidth(16); // Adjusted the width of the last column

        gridPane.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6, col7);

        // Add column headers
        gridPane.addRow(0, new Label("Date"), new Label("BP"), new Label("Heart Rate"),
                new Label("Temperature"), new Label("Weight"), new Label("Height"), new Label("Summary"));

        // Add text fields to the grid
        gridPane.addRow(1, dateField1, bpField1, hrField1, tempField1, weightField1, heightField1, summaryField1);
        gridPane.addRow(2, dateField2, bpField2, hrField2, tempField2, weightField2, heightField2, summaryField2);
        gridPane.addRow(3, dateField3, bpField3, hrField3, tempField3, weightField3, heightField3, summaryField3);
        gridPane.addRow(4, dateField4, bpField4, hrField4, tempField4, weightField4, heightField4, summaryField4);
        gridPane.addRow(5, dateField5, bpField5, hrField5, tempField5, weightField5, heightField5, summaryField5);
        gridPane.addRow(6, dateField6, bpField6, hrField6, tempField6, weightField6, heightField6, summaryField6);

        // Add gridPane to a ScrollPane
        javafx.scene.control.ScrollPane scrollPane = new javafx.scene.control.ScrollPane();
        scrollPane.setContent(gridPane);

        // Set ScrollPane as the center of the BorderPane
        this.setCenter(scrollPane);
    }

    // Helper method to create non-editable text fields
    private TextField createNonEditableTextField(String text) {
        TextField textField = new TextField(text);
        textField.setEditable(false);
        textField.setPrefHeight(30); // Increase the height of text fields
        textField.setFont(Font.font(10)); // Decrease font size
        return textField;
    }

    // Helper method to set values for each row
    private void setRowValues(TextField dateField1, TextField bpField1, TextField hrField1, TextField tempField1, TextField weightField1, TextField heightField1, TextField summaryField1,
                              TextField dateField2, TextField bpField2, TextField hrField2, TextField tempField2, TextField weightField2, TextField heightField2, TextField summaryField2,
                              TextField dateField3, TextField bpField3, TextField hrField3, TextField tempField3, TextField weightField3, TextField heightField3, TextField summaryField3,
                              TextField dateField4, TextField bpField4, TextField hrField4, TextField tempField4, TextField weightField4, TextField heightField4, TextField summaryField4,
                              TextField dateField5, TextField bpField5, TextField hrField5, TextField tempField5, TextField weightField5, TextField heightField5, TextField summaryField5,
                              TextField dateField6, TextField bpField6, TextField hrField6, TextField tempField6, TextField weightField6, TextField heightField6, TextField summaryField6) {
        // Define values for each row
        String[] dateValues = {"1/2/20", "4/20/20", "2/1/21", "1/5/22", "6/7/23", "9/16/24"};
        String[] bpValues = {"120/80", "135/80", "135/80", "120/70", "115/70", "115/70"};
        String[] hrValues = {"115", "105", "105", "90", "109", "89"};
        String[] tempValues = {"93", "88", "88", "89", "98", "93"};
        String[] weightValues = {"80", "78", "78", "80", "79.5", "79"};
        String[] heightValues = {"5'6\"", "5'6\"", "5'6\"", "5'6.2\"", "5'6.3\"", "5'6.5\""};
        String[] summaryValues = {"Patient had general physical appointment",
                                    "Patient had left knee pain",
                                    "Patient had left knee pain",
                                    "Patient had general yearly physical",
                                    "Patient was sick and had a sore throat",
                                    "Patient was hallucinating"};

        // Set values for each text field in each row
        dateField1.setText(dateValues[0]);
        dateField2.setText(dateValues[1]);
        dateField3.setText(dateValues[2]);
        dateField4.setText(dateValues[3]);
        dateField5.setText(dateValues[4]);
        dateField6.setText(dateValues[5]);

        bpField1.setText(bpValues[0]);
        bpField2.setText(bpValues[1]);
        bpField3.setText(bpValues[2]);
        bpField4.setText(bpValues[3]);
        bpField5.setText(bpValues[4]);
        bpField6.setText(bpValues[5]);

        hrField1.setText(hrValues[0]);
        hrField2.setText(hrValues[1]);
        hrField3.setText(hrValues[2]);
        hrField4.setText(hrValues[3]);
        hrField5.setText(hrValues[4]);
        hrField6.setText(hrValues[5]);

        tempField1.setText(tempValues[0]);
        tempField2.setText(tempValues[1]);
        tempField3.setText(tempValues[2]);
        tempField4.setText(tempValues[3]);
        tempField5.setText(tempValues[4]);
        tempField6.setText(tempValues[5]);

        weightField1.setText(weightValues[0]);
        weightField2.setText(weightValues[1]);
        weightField3.setText(weightValues[2]);
        weightField4.setText(weightValues[3]);
        weightField5.setText(weightValues[4]);
        weightField6.setText(weightValues[5]);

        heightField1.setText(heightValues[0]);
        heightField2.setText(heightValues[1]);
        heightField3.setText(heightValues[2]);
        heightField4.setText(heightValues[3]);
        heightField5.setText(heightValues[4]);
        heightField6.setText(heightValues[5]);

        summaryField1.setText(summaryValues[0]);
        summaryField2.setText(summaryValues[1]);
        summaryField3.setText(summaryValues[2]);
        summaryField4.setText(summaryValues[3]);
        summaryField5.setText(summaryValues[4]);
        summaryField6.setText(summaryValues[5]);
    }
}
