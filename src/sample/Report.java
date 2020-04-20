package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.model.Passenger;
import sample.model.TableModel;


import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class Report implements Initializable {

    @FXML
    private TableColumn<TableModel, String> col_name;

    @FXML
    private TableColumn<TableModel, Double> col_avg_time;

    @FXML
    private TableView<TableModel> tbl_passenger;

    @FXML
    private TextField txt_max_waiting_time;

    @FXML
    private TextField txt_min_waiting_time;

    private static List<Passenger> passengerList = new ArrayList<>();

    ObservableList<TableModel> tableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Passenger p : passengerList) {
            tableList.add(new TableModel(p.getName(), (double) (p.getSecondsInQueue()/passengerList.size())));
        }
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_avg_time.setCellValueFactory(new PropertyValueFactory<>("time"));

        tbl_passenger.setItems(tableList);

        Collections.sort(passengerList);

        txt_max_waiting_time.setText(String.valueOf(passengerList.get(passengerList.size()-1).getSecondsInQueue()));
        txt_min_waiting_time.setText(String.valueOf(passengerList.get(0).getSecondsInQueue()));

    }

    public void get(List<Passenger> list) {
        this.passengerList = list;
    }
}
