package sample.model;

import java.util.ArrayList;
import java.util.List;

public class PassengerQueue {
    private List<Passenger> queueArray;
    private int first;
    private int last;
    private int maxStayInQueue;
    private int maxLength;

    public PassengerQueue() {
        queueArray = new ArrayList<>();
    }

    public void add(Passenger nextPassenger) {
        this.queueArray.add(nextPassenger);
    }

    public List<Passenger> getPassengerList() {
        return this.queueArray;
    }

    public Passenger remove(int index) {
        return queueArray.remove(index);
    }

    public boolean isEmpty() {
        return queueArray.size() > 0 ? false : true;
    }

    public boolean isFull() {
        return queueArray.size() == 42 ? true : false;
    }

    public int getLength() {
        return queueArray.size();
    }

    public int getMaxStay() {
        return maxLength;
    }

    public void display() {

    }
}
