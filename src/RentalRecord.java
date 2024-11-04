import java.time.LocalDate;

public class RentalRecord {
    private Vehicle vehicle;
    private Customer customer;
    private LocalDate rentDate;
    private LocalDate returnDate;

    public RentalRecord(Vehicle vehicle, Customer customer, LocalDate rentDate) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.rentDate = rentDate;
        this.returnDate = null; // Not yet returned
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
    public Customer getCustomer() {
        return customer;
    }
    public LocalDate getRentDate() {
        return rentDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }


    public boolean isCurrentlyRented() {
        return returnDate == null;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public double calculateTotalPrice() {
        if (returnDate != null) {
            long daysRented = java.time.Duration.between(rentDate.atStartOfDay(), returnDate.atStartOfDay()).toDays();
            return daysRented * vehicle.getPricePerDay();
        }
        return 0.0; // Not returned yet
    }
}
