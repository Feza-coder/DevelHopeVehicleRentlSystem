import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        RentalSystem rentalSystem = new RentalSystem();

        // Create some vehicles
        Vehicle car = new Car("ABC123", "Red", 50.0);
        Vehicle truck = new Truck("DEF456", "Blue", 80.0);
        Vehicle motorcycle = new Motorcycle("GHI789", "Black", 30.0);

        // Add vehicles to the system
        rentalSystem.addVehicle(car);
        rentalSystem.addVehicle(truck);
        rentalSystem.addVehicle(motorcycle);

        // Create a customer
        Customer customer = new Customer("John Doe", "123 Main St", 30);

        // Rent out a vehicle
        RentalRecord record = rentalSystem.rentVehicle(car, customer, LocalDate.now());

        // Return the vehicle after 3 days
        if (record != null) {
            rentalSystem.returnVehicle(record, LocalDate.now().plusDays(3));
            System.out.println("Total price: " + record.calculateTotalPrice());
        }

        // Get the last 5 rentals for a vehicle
        java.util.List<RentalRecord> last5Rentals = rentalSystem.getLast5Rentals(car);
        System.out.println("Last 5 rentals for the car:");
        for (RentalRecord r : last5Rentals) {
            System.out.println("Rented by: " + r.getVehicle().getLicensePlate());
        }
    }
}
