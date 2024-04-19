package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class PatientVisitsTab extends BorderPane {
    private List<Visit> visits = new ArrayList<>();

    public PatientVisitsTab() {
        createVisitTable();
    }

    private void createVisitTable() {
        TableView<Visit> table = new TableView<>();
        table.getItems().addAll(visits);

        TableColumn<Visit, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Visit, String> summaryCol = new TableColumn<>("Summary");
        summaryCol.setCellValueFactory(new PropertyValueFactory<>("summary"));

        TableColumn<Visit, String> bpCol = new TableColumn<>("Blood Pressure");
        bpCol.setCellValueFactory(new PropertyValueFactory<>("bloodPressure"));

        TableColumn<Visit, Integer> hrCol = new TableColumn<>("Heart Rate");
        hrCol.setCellValueFactory(new PropertyValueFactory<>("heartRate"));

        TableColumn<Visit, Double> tempCol = new TableColumn<>("Temperature");
        tempCol.setCellValueFactory(new PropertyValueFactory<>("temperature"));

        TableColumn<Visit, Double> weightCol = new TableColumn<>("Weight");
        weightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));

        TableColumn<Visit, Double> heightCol = new TableColumn<>("Height");
        heightCol.setCellValueFactory(new PropertyValueFactory<>("height"));

        table.getColumns().addAll(Arrays.asList(dateCol, summaryCol, bpCol, hrCol, tempCol, weightCol, heightCol));

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(table);

        this.setCenter(scrollPane);
    }
}
