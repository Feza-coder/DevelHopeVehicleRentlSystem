import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RentalSystem {
    private List<Vehicle> vehicles = new ArrayList<>();
    private Map<Vehicle, List<RentalRecord>> rentalRecords = new HashMap<>();

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        rentalRecords.put(vehicle, new ArrayList<>());
    }

    public boolean isVehicleAvailable(Vehicle vehicle) {
        return !vehicle.isRented();
    }

    public RentalRecord rentVehicle(Vehicle vehicle, Customer customer, LocalDate rentalDate) {
        if (!isVehicleAvailable(vehicle)) {
            System.out.println("Vehicle is currently rented out.");
            return null;
        }
        vehicle.rentOut();
        RentalRecord record = new RentalRecord(vehicle, customer, rentalDate);
        rentalRecords.get(vehicle).add(0, record); // Add to the beginning for recent records
        return record;
    }

    public void returnVehicle(RentalRecord record, LocalDate returnDate) {
        record.returnVehicle(returnDate);
        record.getVehicle().returnVehicle();
    }

    public List<RentalRecord> getLast5Rentals(Vehicle vehicle) {
        List<RentalRecord> records = rentalRecords.get(vehicle);
        return records.subList(0, Math.min(5, records.size()));
    }
}
