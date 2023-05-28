// Superclass: Vehicle
abstract class Vehicle {
    protected String brand;
    protected int year;
    protected double price;
    protected boolean rented;

    public Vehicle(String brand, int year, double price) {
        this.brand = brand;
        this.year = year;
        this.price = price;
        this.rented = false;
    }

    public abstract double calculateRentalCost(int days);

    public abstract void displayInfo();

    public boolean isRented() {
        return rented;
    }

    public void rent() {
        rented = true;
    }

    public void returnVehicle() {
        rented = false;
    }
}

// Interface: Rental
interface Rental {
    double calculateRentalCost(int days);
    void displayInfo();
}

// Subclass: Car
class Car extends Vehicle implements Rental {
    private int seatingCapacity;
    private boolean hasAC;

    public Car(String brand, int year, double price, int seatingCapacity, boolean hasAC) {
        super(brand, year, price);
        this.seatingCapacity = seatingCapacity;
        this.hasAC = hasAC;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public boolean hasAC() {
        return hasAC;
    }

    @Override
    public double calculateRentalCost(int days) {
        return price * days;
    }

    @Override
    public void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Year: " + year);
        System.out.println("Price per day: $" + price);
        System.out.println("Seating capacity: " + seatingCapacity);
        System.out.println("AC: " + (hasAC ? "Yes" : "No"));
    }

    public void addNavigationSystem() {
        // Code to add navigation system
    }

    public void removeNavigationSystem() {
        // Code to remove navigation system
    }
}

// Subclass: Motorcycle
class Motorcycle extends Vehicle implements Rental {
    private int engineDisplacement;

    public Motorcycle(String brand, int year, double price, int engineDisplacement) {
        super(brand, year, price);
        this.engineDisplacement = engineDisplacement;
    }

    public int getEngineDisplacement() {
        return engineDisplacement;
    }

    @Override
    public double calculateRentalCost(int days) {
        return price * days * 0.8; // 20% discount for motorcycles
    }

    @Override
    public void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Year: " + year);
        System.out.println("Price per day: $" + price);
        System.out.println("Engine displacement: " + engineDisplacement + "cc");
    }

    public void addHelmet() {
        // Code to add a helmet
    }

    public void removeHelmet() {
        // Code to remove a helmet
    }
}

// Interface: Transaction
interface Transaction {
    double calculateTotalCost(double cost);
    void processPayment();
}

// Class: CarRentalSystem
class CarRentalSystem implements Transaction {
    @Override
    public double calculateTotalCost(double cost) {
        double tax = 0.1 * cost; // 10% tax on total cost
        return cost + tax;
    }

    @Override
    public void processPayment() {
        // Code to process payment
    }

    public void bookVehicle(Vehicle vehicle, int days) {
        double cost = vehicle.calculateRentalCost(days);
        double totalCost = calculateTotalCost(cost);

        if (!vehicle.isRented()) {
            vehicle.rent();
            System.out.println("Vehicle booked successfully.");
            System.out.println("Total cost: $" + totalCost);
            processPayment();
        } else {
            System.out.println("Vehicle is already rented.");
        }
    }

    public void returnVehicle(Vehicle vehicle) {
        if (vehicle.isRented()) {
            vehicle.returnVehicle();
            System.out.println("Vehicle returned successfully.");
        } else {
            System.out.println("Vehicle is not rented.");
        }
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        Car car = new Car("Toyota", 2020, 50.0, 5, true);
        Motorcycle motorcycle = new Motorcycle("Honda", 2021, 30.0, 250);

        CarRentalSystem rentalSystem = new CarRentalSystem();
        rentalSystem.bookVehicle(car, 3);
        rentalSystem.bookVehicle(motorcycle, 5);

        car.displayInfo();
        motorcycle.displayInfo();

        rentalSystem.returnVehicle(car);
        rentalSystem.returnVehicle(motorcycle);
    }
}
