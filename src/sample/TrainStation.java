package sample;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.model.CustomerBooking;
import sample.model.Passenger;
import sample.model.PassengerQueue;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class TrainStation extends Application {

    private Random random = new Random();

    private static final int passengerArraySize = 42;
    private List<CustomerBooking> customerBookingsList = new ArrayList<>();
    private List<CustomerBooking> waitingRoom = new ArrayList<>();
    private PassengerQueue passengerQueue = new PassengerQueue();

    @Override
    public void start(Stage primaryStage) throws Exception {
        printInstructions();
        readBookingDetailsFile();
        filterBookings();
    }


    public void printInstructions() {
        System.out.println("\t***Train Boarding Simulation*** ");
        System.out.println();

        System.out.println("Description:");
        System.out.println("A. Add Passenger to Train Queue");

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter the letter which is related your requirement: ");
        String input = scanner.next(); //getting user input.


        switch (input) {
            case "A":
                addPassenger();
                break;

            case "R":
                run();
                break;

            case "Q":
                break;

        }
    }

    public void run() {
        AddPassenger addPassenger = new AddPassenger();
        PassengerQueue passengerQueue = addPassenger.getPassengerQueue();
        this.passengerQueue = passengerQueue;
        List<Passenger> passengerList = passengerQueue.getPassengerList();

        List<Passenger> boardedPassengers = new ArrayList<>();

        int allTime = 0;
        while (!passengerQueue.isEmpty()) {
            int number = 0;
            for (int i = 0; i < 3; i++) {
                number += random.nextInt(6) + 1;
            }
            System.out.println("=================================");
            System.out.println(number);
            System.out.println("=================================");
            Passenger removedPassenger = passengerQueue.getPassengerList().remove(0);
            allTime += number;
            removedPassenger.setSecondsInQueue(allTime);
            boardedPassengers.add(removedPassenger);
        }

        for (Passenger p : boardedPassengers) {
            System.out.println("Individual Times for " + p.getName() + " : " + p.getSecondsInQueue());
        }

    }

    public void addPassenger() {

        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("addPassenger.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Add Passenger to Train Queue");
        stage.setScene(new Scene(root, 900, 600));
        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                Platform.setImplicitExit(false);
                stage.close();
                printInstructions();
            }
        });
    }

    public void filterBookings() {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String today = sdf.format(new Date());
        for (CustomerBooking booking : customerBookingsList) {
            if (booking.getBookingDate().equalsIgnoreCase(today)) {
                waitingRoom.add(booking);
            }
        }
    }


    public void readBookingDetailsFile() {
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


    public static void main(String[] args) {
        launch(args);
    }
}
