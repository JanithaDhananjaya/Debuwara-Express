package sample.model;

import java.util.List;

public class CustomerBooking {
    private String customerName;
    private String bookingDate;
    private String route;
    private List<String> seats;

    public CustomerBooking() {
    }

    public CustomerBooking(String customerName, String bookingDate, String route, List<String> seats) {
        this.customerName = customerName;
        this.bookingDate = bookingDate;
        this.route = route;
        this.seats = seats;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerId(String customerName) {
        this.customerName = customerName;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public List<String> getSeats() {
        return seats;
    }

    public void setSeats(List<String> seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "CustomerBooking{" +
                "customerName='" + customerName + '\'' +
                ", bookingDate=" + bookingDate +
                ", route='" + route + '\'' +
                ", seats=" + seats +
                '}';
    }
}
