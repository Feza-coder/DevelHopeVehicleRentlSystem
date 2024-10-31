import java.time.LocalDate;

public class RentalRecord {
    private Vehicle vehicle;
    private Customer customer;
    private LocalDate rentalDate;
    private LocalDate returnDate;

    public RentalRecord(Vehicle vehicle, Customer customer, LocalDate rentalDate) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.rentalDate = rentalDate;
        this.returnDate = null;
    }

    public void returnVehicle(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public double calculateTotalPrice() {
        if (returnDate == null) return 0;
        int days = (int) (returnDate.toEpochDay() - rentalDate.toEpochDay());
        return vehicle.calculateRentalPrice(days);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
