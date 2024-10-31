import java.util.ArrayList;
import java.util.List;

abstract class Vehicle {
    private String licensePlate;
    private String color;
    private double pricePerDay;
    private boolean isRented;

    public Vehicle(String licensePlate, String color, double pricePerDay) {
        this.licensePlate = licensePlate;
        this.color = color;
        this.pricePerDay = pricePerDay;
        this.isRented = false;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getColor() {
        return color;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public boolean isRented() {
        return isRented;
    }

    public void rentOut() {
        this.isRented = true;
    }

    public void returnVehicle() {
        this.isRented = false;
    }

    public double calculateRentalPrice(int days) {
        return days * pricePerDay;
    }
}
