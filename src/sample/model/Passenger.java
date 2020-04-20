package sample.model;

public class Passenger implements Comparable<Passenger> {
    private String firstName;
    private String surName;
    private double secondsInQueue;
    private int seatNo;

    public String getName() {
        return firstName;
    }

    public void setName(String firstName, String surName) {
        this.firstName = firstName;
        this.surName = surName;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    public double getSecondsInQueue() {
        return secondsInQueue;
    }

    public void setSecondsInQueue(int secondsInQueue) {
        this.secondsInQueue = secondsInQueue;
    }

    public void display(){}

    @Override
    public String toString() {
        return "Passenger{" +
                "firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", secondsInQueue=" + secondsInQueue +
                ", seatNo=" + seatNo +
                '}';
    }

    @Override
    public int compareTo(Passenger o) {
        return this.secondsInQueue > o.getSecondsInQueue() ? 1 : 0;
    }
}
