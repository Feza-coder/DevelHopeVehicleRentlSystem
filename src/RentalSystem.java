import java.time.LocalDate;
import java.util.*;

public class RentalSystem {
    private final Map<String, Vehicle> vehicles = new HashMap<>();
    private final List<RentalRecord> rentalRecords = new ArrayList<>();

    public void addVehicle(Vehicle vehicle) {
        vehicles.put(vehicle.getLicensePlate(), vehicle);
    }

    public Vehicle getVehicleByLicensePlate(String licensePlate) {
        return vehicles.get(licensePlate);
    }

    public boolean isVehicleAvailable(Vehicle vehicle) {
        return !rentalRecords.stream()
                .anyMatch(record -> record.getVehicle().equals(vehicle) && record.isCurrentlyRented());
    }

    public RentalRecord rentVehicle(Vehicle vehicle, Customer customer, LocalDate rentDate) {
        if (isVehicleAvailable(vehicle)) {
            RentalRecord record = new RentalRecord(vehicle, customer, rentDate);
            rentalRecords.add(record);
            return record;
        }
        return null; // Vehicle is not available
    }

    public void returnVehicle(Vehicle vehicle, LocalDate returnDate) {
        for (RentalRecord record : rentalRecords) {
            if (record.getVehicle().equals(vehicle) && record.isCurrentlyRented()) {
                record.setReturnDate(returnDate);
                break;
            }
        }
    }

    public List<RentalRecord> getLast5Rentals(Vehicle vehicle) {
        List<RentalRecord> lastRentals = new ArrayList<>();
        for (int i = rentalRecords.size() - 1; i >= 0; i--) {
            RentalRecord record = rentalRecords.get(i);
            if (record.getVehicle().equals(vehicle)) {
                lastRentals.add(record);
                if (lastRentals.size() == 5) {
                    break; // Limit to last 5 rentals
                }
            }
        }
        return lastRentals;
    }

    // New method to return all vehicles
    public Collection<Vehicle> getVehicles() {
        return vehicles.values(); // Returns all vehicles
    }
}
