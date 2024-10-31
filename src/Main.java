import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        RentalSystem rentalSystem = new RentalSystem();

        // Create some vehicles
        Vehicle car = new Car("BMW", "Red", 50.0);
        Vehicle Car1 = new Car("Toyota", "Blue", 40.0);
        Vehicle truck1 = new Truck("sinotruck", "Blue", 80.0);
        Vehicle truck = new Truck("Truck1", "Blue", 80.0);
        Vehicle motorcycle = new Motorcycle("Bajaaj", "Black", 30.0);

        // Add vehicles to the system
        rentalSystem.addVehicle(car);
        rentalSystem.addVehicle(truck);
        rentalSystem.addVehicle(motorcycle);

        // Create a customer
        Customer customer = new Customer("Melissa", "123 Kampala Street", 30);
        System.out.println("Customer created: " + customer.getName());

//        Customer customer1 = new Customer("Suzan", "423 Kampala Road", 34);
//        System.out.println("Customer created: " + customer1.getName());

        // Rent out a vehicle
        RentalRecord record = rentalSystem.rentVehicle(car, customer, LocalDate.now());

        // Return the vehicle after 3 days
        if (record != null) {
            rentalSystem.returnVehicle(record, LocalDate.now().plusDays(3));
            System.out.println("Total price: " + record.calculateTotalPrice());
            System.out.println("Vehicle returned.");
        }

        // Get the last 5 rentals for a vehicle
        java.util.List<RentalRecord> last5Rentals = rentalSystem.getLast5Rentals(car);
        System.out.println("Last 5 rentals for the car:");
        for (RentalRecord r : last5Rentals) {
            System.out.println("Plate number " + r.getVehicle().getLicensePlate() +" was rented by "+customer.getName());
        }
    }
}
