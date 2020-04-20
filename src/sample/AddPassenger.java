package sample;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import sample.model.CustomerBooking;
import sample.model.Passenger;
import sample.model.PassengerQueue;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class AddPassenger {

    @FXML
    private AnchorPane container;

    @FXML
    private FlowPane flowContainer;

    @FXML
    private Button btnNext;

    @FXML
    private AnchorPane nexrContainer;

    @FXML
    private FlowPane gridContainer;


    public AddPassenger() {
        readBookingDetailsFile();
        filterBookings();
    }

    private Random random = new Random();

    private static List<CustomerBooking> customerBookingsList = new ArrayList<>();
    private static List<CustomerBooking> todayCustomerBookings = new ArrayList<>();
    private List<CustomerBooking> boredBookings = new ArrayList<>();
    private static final PassengerQueue passengerQueue = new PassengerQueue();

    @FXML
    void getThis(ActionEvent event) {
        takePassengers();
    }

    public static void readBookingDetailsFile() {
        try {
            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get("booking.json"));

            // convert JSON array to list of users
            List<CustomerBooking> users = new Gson().fromJson(reader, new TypeToken<List<CustomerBooking>>() {
            }.getType());
            if (users != null) {
                customerBookingsList = users;
            }

            // close reader
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void filterBookings() {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String today = sdf.format(new Date());
        for (CustomerBooking booking : customerBookingsList) {
            if (booking.getBookingDate().equalsIgnoreCase(today)) {
                todayCustomerBookings.add(booking);
            }
        }
    }

    public int getPassengersRandomly() {
        int generatedRandom = random.nextInt(6) + 1;
        return generatedRandom;
    }


    public void takePassengers() {
        int randomNumber = getPassengersRandomly();
        System.out.println("random number : " + randomNumber);
        for (int i = 1; i <= randomNumber; i++) {
            Passenger passenger = new Passenger();
            passenger.setName(todayCustomerBookings.get(i).getCustomerName(), "");
            passenger.setSeatNo(Integer.parseInt(todayCustomerBookings.get(i).getSeats().get(0)));
            passengerQueue.add(passenger);
            Button lbl = new Button(todayCustomerBookings.get(i).getCustomerName() + " : " + todayCustomerBookings.get(i).getSeats());
            lbl.setId(Integer.toString(i));
            flowContainer.getChildren().add(lbl);
        }
    }

    public void pressNext() {
        container.setVisible(false);
        nexrContainer.setVisible(true);
        loadSeats();
    }

    public PassengerQueue getPassengerQueue() {
        return passengerQueue;
    }


    public void loadSeats() {
        boolean exists = false;
        for (int k = 0; k < 42; k++) {
            exists = false;
            for (int i = 0; i < passengerQueue.getPassengerList().size(); i++) {
                if (passengerQueue.getPassengerList().get(i).getSeatNo() == (k + 1)) {
                    Button lbl = new Button(passengerQueue.getPassengerList().get(i).getName() + " : " + passengerQueue.getPassengerList().get(i).getSeatNo());
                    lbl.setId(Integer.toString(k + 1));
                    lbl.setStyle("-fx-background-color: blue;");
                    lbl.setTextFill(Color.WHITE);
                    gridContainer.getChildren().add(lbl);
                    exists = true;
                    break;
                }
            }
            if (exists == false) {
                Button lbl = new Button("None " + (k + 1));
                lbl.setId(Integer.toString(k + 1));
                gridContainer.getChildren().add(lbl);
            }
        }
    }

}
