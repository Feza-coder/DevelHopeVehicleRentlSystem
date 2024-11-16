import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        RentalSystem rentalSystem = new RentalSystem();
        Scanner scanner = new Scanner(System.in);

        // Create and add vehicles to the system
        rentalSystem.addVehicle(new Car("BMW123", "Red", 50.0));
        rentalSystem.addVehicle(new Truck("TRK456", "Blue", 80.0));
        rentalSystem.addVehicle(new Motorcycle("MOTO789", "Black", 30.0));

        // Display the menu for the user

        while (true) {
            System.out.println("\nVehicle Rental System\n===========================");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Rent Vehicle");
            System.out.println("3. Return Vehicle");
            System.out.println("4. View Last 5 Rentals for a Vehicle");
            System.out.println("5. Remove Vehicle");
            System.out.println("6. View All Vehicles");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addVehicle(rentalSystem, scanner);
                case 2 -> rentVehicle(rentalSystem, scanner);
                case 3 -> returnVehicle(rentalSystem, scanner);
                case 4 -> viewLast5Rentals(rentalSystem, scanner);
                case 5  -> removeVehicle(rentalSystem, scanner);
                case 6 -> viewAllVehicles(rentalSystem, scanner);
                case 7-> {
                    System.out.println("Exiting the system. Thank you!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to add a vehicle to the rental system
    private static void addVehicle(RentalSystem rentalSystem, Scanner scanner) {
        System.out.print("Enter vehicle type (Car, Truck, Motorcycle): ");
        String type = scanner.nextLine();
        System.out.print("Enter license plate: ");
        String licensePlate = scanner.nextLine();
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
        System.out.print("Enter price per day: ");
        double pricePerDay = scanner.nextDouble();
        scanner.nextLine(); // Consume newline


        Vehicle vehicle;
        switch (type.toLowerCase()) {
            case "car" -> vehicle = new Car(licensePlate, color, pricePerDay);
            case "truck" -> vehicle = new Truck(licensePlate, color, pricePerDay);
            case "motorcycle" -> vehicle = new Motorcycle(licensePlate, color, pricePerDay);
            default -> {
                System.out.println("Invalid vehicle type.");
                return;
            }
        }
        rentalSystem.addVehicle(vehicle);
        System.out.println(type + " added successfully.");
    }

    // Method to rent a vehicle
    private static void rentVehicle(RentalSystem rentalSystem, Scanner scanner) {
        System.out.print("Enter license plate of the vehicle to rent: ");
        String licensePlate = scanner.nextLine();
        Vehicle vehicle = rentalSystem.getVehicleByLicensePlate(licensePlate);

        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        if (!rentalSystem.isVehicleAvailable(vehicle)) {
            System.out.println("Vehicle is currently rented out.");
            return;
        }

        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer address: ");
        String address = scanner.nextLine();
        System.out.print("Enter customer age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Customer customer = new Customer(name, address, age);
        RentalRecord record = rentalSystem.rentVehicle(vehicle, customer, LocalDate.now());
        if (record != null) {
            System.out.println("Vehicle rented successfully to " + customer.getName() + ".");
        }
    }

    // Method to return a vehicle
    private static void returnVehicle(RentalSystem rentalSystem, Scanner scanner) {
        System.out.print("Enter license plate of the vehicle to return: ");
        String licensePlate = scanner.nextLine();
        Vehicle vehicle = rentalSystem.getVehicleByLicensePlate(licensePlate);

        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        List<RentalRecord> records = rentalSystem.getLast5Rentals(vehicle);
        if (records.isEmpty() || !records.get(0).isCurrentlyRented()) {
            System.out.println("This vehicle is not currently rented.");
            return;
        }

        rentalSystem.returnVehicle(vehicle, LocalDate.now());
        System.out.println("Vehicle returned successfully.");
    }


    private static void removeVehicle(RentalSystem rentalSystem, Scanner scanner) {
        System.out.print("Enter license plate of the vehicle to remove: ");
        String licensePlate = scanner.nextLine();
        Vehicle vehicle = rentalSystem.getVehicleByLicensePlate(licensePlate);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }
        rentalSystem.removeVehicle(vehicle);
        System.out.println("Thank you in case you want to add another one you can.");
    }

    private static void viewAllVehicles(RentalSystem rentalSystem, Scanner scanner) {
        List<Vehicle> vehicles = rentalSystem.getAllVehicles();
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }
    // Method to display the last 5 rentals for a specific vehicle
    private static void viewLast5Rentals(RentalSystem rentalSystem, Scanner scanner) {
        System.out.print("Enter license plate of the vehicle: ");
        String licensePlate = scanner.nextLine();
        Vehicle vehicle = rentalSystem.getVehicleByLicensePlate(licensePlate);

        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        List<RentalRecord> lastRentals = rentalSystem.getLast5Rentals(vehicle);
        if (lastRentals.isEmpty()) {
            System.out.println("No rental records found for this vehicle.");
            return;
        }

        System.out.println("Last 5 rentals for vehicle " + licensePlate + ":");
        for (RentalRecord record : lastRentals) {
            System.out.println("Rented by: " + record.getCustomer().getName() + " on " + record.getRentDate() +
                    (record.isCurrentlyRented() ? " (Currently rented)" : " (Returned on " + record.getReturnDate() + ")"));
        }
    }
}
