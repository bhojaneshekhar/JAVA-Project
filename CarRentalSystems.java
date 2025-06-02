import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.io.FileWriter;
import java.io.IOException;


class Car {
    String name;
    int seats;
    int pricePerDay;
    String specifications;
    boolean isAvailable;

    Car(String name, int seats, int pricePerDay, String specifications) {
        this.name = name;
        this.seats = seats;
        this.pricePerDay = pricePerDay;
        this.specifications = specifications;
	this.isAvailable = true;
    }
}

class Booking {
    int bookingID;
    String customerName;
    String carName;
    LocalDate rentalStartDate;
    LocalDate rentalEndDate;
    int totalCost;

    Booking(int bookingID, String customerName, String carName, LocalDate rentalStartDate, LocalDate rentalEndDate, int totalCost) {
        this.bookingID = bookingID;
        this.customerName = customerName;
        this.carName = carName;
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
        this.totalCost = totalCost;
    }
}

public class CarRentalSystems {
    static Scanner scanner = new Scanner(System.in);
    static List<Car> carList = new ArrayList<>();
    static List<Booking> bookingList = new ArrayList<>();
    static final String ADMIN_PASSWORD = "admin123";

    public static void main(String[] args) {
        initializeCars();

        while (true) {
            System.out.println("\nWELCOME TO THE LANDROVER PARKING AREA");
            System.out.println("1) Rent Car\n2) Return Car\n3) Modify Booking\n4) Admin Panel\n5) Trip Cost Estimator\n6) Emergency Rental\n7) Available Cars\n8) Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                scanner.nextLine(); // clear the buffer
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            scanner.nextLine();

            switch (choice) {
                case 1 -> rentCar();
                case 2 -> returnCar();
                case 3 -> modifyBooking();
                case 4 -> adminPanel();
                case 5 -> tripEstimator();
                case 6 -> emergencyRental();
                case 7 -> availableCars();
                case 8 -> {
                    System.out.println("Thank you for visiting!");
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }


////// CHOOSE CAR  /////// 


    static void initializeCars() {
        carList.add(new Car("Land Rover Evoque", 5, 3000, "Luxury SUV, 200HP, Automatic"));
        carList.add(new Car("Land Rover Defender", 7, 4500, "Off-road SUV, 250HP, 4x4 Drive"));
        carList.add(new Car("Range Rover Sport", 5, 5000, "High-performance SUV, 350HP, Leather Interior"));
	carList.add(new Car("Hyundai Creta", 5, 3000, "Compact SUV, 150HP, Petrol/Manual"));
	carList.add(new Car("Maruti Swift Dzire", 5, 2200, "Economy Sedan, 90HP, Petrol/Manual"));
	carList.add(new Car("Tata Nexon", 5, 2700, "Compact SUV, 120HP, Diesel/Automatic"));
	carList.add(new Car("Honda City", 5, 3200, "Sedan, 119HP, Petrol/CVT"));
	carList.add(new Car("Toyota Innova Crysta", 7, 3800, "MPV, 150HP, Diesel/Manual"));
	carList.add(new Car("Kia Seltos", 5, 3300, "Compact SUV, 140HP, Petrol/Automatic"));
	carList.add(new Car("Mahindra Scorpio-N", 7, 4000, "Full-Size SUV, 172HP, Diesel/Manual"));
	carList.add(new Car("Tata Tiago", 5, 2000, "Hatchback, 85HP, Petrol/Manual"));
	carList.add(new Car("Ford EcoSport", 5, 2600, "Mini SUV, 123HP, Petrol/Automatic"));
	carList.add(new Car("Jeep Compass", 5, 4300, "Premium SUV, 170HP, Diesel/4x4"));

    }



///////RENT CAR ///////


static void rentCar() {
    
    System.out.println("\nAvailable Cars:");
    List<Car> availableCars = new ArrayList<>();
    for (Car car : carList) {
        if (car.isAvailable) {
            availableCars.add(car);
        }
    }

    if (availableCars.isEmpty()) {
        System.out.println("Sorry, no cars available at the moment.");
        return;
    }

    for (int i = 0; i < availableCars.size(); i++) {
        System.out.println((i + 1) + ") " + availableCars.get(i).name + " - " + availableCars.get(i).pricePerDay + " per day");
    }

    System.out.print("Select a car: ");
    int carChoice = scanner.nextInt() - 1;
    scanner.nextLine();

    if (carChoice < 0 || carChoice >= availableCars.size()) {
        System.out.println("Invalid selection! Try again.");
        return;
    }

    Car selectedCar = availableCars.get(carChoice);

    System.out.print("Enter Your Name: ");
    String name = scanner.nextLine();

    // Get rental start date
    System.out.print("Enter Rental Start Date (yyyy-MM-dd): ");
    String startDateInput = scanner.nextLine();
    LocalDate rentalStartDate = LocalDate.parse(startDateInput, DateTimeFormatter.ISO_LOCAL_DATE);

    // Get rental end date
    System.out.print("Enter Rental End Date (yyyy-MM-dd): ");
    String endDateInput = scanner.nextLine();
    LocalDate rentalEndDate = LocalDate.parse(endDateInput, DateTimeFormatter.ISO_LOCAL_DATE);

    // Calculate total rental days and cost
    long rentalDays = ChronoUnit.DAYS.between(rentalStartDate, rentalEndDate);
    if (rentalDays <= 0) {
        System.out.println("Invalid date range! End date must be after start date.");
        return;
    }

    int totalCost = (int) rentalDays * selectedCar.pricePerDay;

    int bookingID = new Random().nextInt(9000) + 1000;
    System.out.println("Booking Confirmed. Your Booking ID: " + bookingID);
    Booking booking = new Booking(bookingID, name, selectedCar.name, rentalStartDate, rentalEndDate, totalCost);
    bookingList.add(booking);
    saveRentalToFile(booking);
    selectedCar.isAvailable = false;

 System.out.println("Choose Payment Method: 1) UPI 2) Card 3) Cash");
int paymentChoice = scanner.nextInt();
scanner.nextLine(); 

switch (paymentChoice) {
    case 1 -> {
        System.out.print("Enter your UPI ID: ");
        String upiId = scanner.nextLine();
        if (!upiId.isEmpty() && upiId.contains("@")) {
            System.out.println("Processing UPI Payment...");
            System.out.println("UPI Payment Successful!");
        } else {
            System.out.println("Invalid UPI ID. Payment failed.");
        }
    }
    case 2 -> {
        System.out.print("Enter your Card Number: ");
        String cardNumber = scanner.nextLine();
        if (cardNumber.length() >= 12 && cardNumber.length() <= 16) {
            System.out.println("Processing Card Payment...");
            System.out.println("Card Payment Successful!");
        } else {
            System.out.println("Invalid Card Number. Payment failed.");
        }
    }
    case 3 -> {
        System.out.println("Please pay the cash amount to the rental counter.");
        System.out.println("Cash Payment Collected!");
    }
    default -> System.out.println("Invalid Payment Mode selected. Assuming Cash.");
}


}




// RETURN CAR

static void returnCar() {
    System.out.print("\nEnter Rental ID: ");
    int rentalID = scanner.nextInt();
    scanner.nextLine();

    boolean found = false;
    for (Booking b : bookingList) {
        if (b.bookingID == rentalID) {
            System.out.print("Is the car damaged? (yes/no): ");
            int damageCharge = scanner.nextLine().equalsIgnoreCase("yes") ? 2000 : 0;
            int finalCharge = b.totalCost + damageCharge;
            String damageStatus = damageCharge > 0 ? "Yes" : "No";

            ////// FILE HANDLING ///////
            saveReturnToFile(b.bookingID, b.customerName, b.carName, finalCharge, damageStatus);

            
            for (Car car : carList) {
                if (car.name.equalsIgnoreCase(b.carName)) {
                    car.isAvailable = true;
                    break;
                }
            }

            System.out.println("Final Charge:" + finalCharge);
            bookingList.remove(b);
            found = true;
            break;
        }
    }

    if (!found) {
        System.out.println("Booking ID not found!");
    }
}




///////////////////////////MODIFY BOOKING


static void modifyBooking() {
    System.out.print("\nEnter Booking ID to Modify: ");
    int bookingID = scanner.nextInt();
    scanner.nextLine();

    for (Booking b : bookingList) {
        if (b.bookingID == bookingID) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            System.out.print("Enter new rental start date (dd-MM-yyyy): ");
            String start = scanner.nextLine();
            LocalDate newStart = LocalDate.parse(start, formatter);

            System.out.print("Enter new rental end date (dd-MM-yyyy): ");
            String end = scanner.nextLine();
            LocalDate newEnd = LocalDate.parse(end, formatter);

            long days = ChronoUnit.DAYS.between(newStart, newEnd);
            if (days <= 0) {
                System.out.println("Invalid date range!");
                return;
            }

            b.rentalStartDate = newStart;
            b.rentalEndDate = newEnd;
            b.totalCost = (int) days * 4000;

            System.out.println("Booking updated!");
            return;
        }
    }

    System.out.println("Booking ID not found!");
}




/////////////////// FILE HANDLING CODE STARTS////////////////////

// save Rent Car Booking to rentals.txt

static void saveRentalToFile(Booking booking) {
    try {
        FileWriter writer = new FileWriter("rentals.txt", true); // append mode
        writer.write("Booking ID: " + booking.bookingID + "\n");
        writer.write("Customer Name: " + booking.customerName + "\n");
        writer.write("Car Name: " + booking.carName + "\n");
        writer.write("Rental Start Date: " + booking.rentalStartDate + "\n");
        writer.write("Rental End Date: " + booking.rentalEndDate + "\n");
        writer.write("Total Cost: " + booking.totalCost + "\n");
        writer.write("-------------------------------------------------\n");
        writer.close();
    } catch (Exception e) {
        System.out.println("Error saving rental data: " + e.getMessage());
    }
}

// save Return Car Data to returns.txt
static void saveReturnToFile(int rentalID, String customerName, String carName, int finalCost, String damageStatus) {
    try {
        FileWriter writer = new FileWriter("returns.txt", true); // append mode
        writer.write("Rental ID: " + rentalID + "\n");
        writer.write("Customer Name: " + customerName + "\n");
        writer.write("Car Name: " + carName + "\n");
        writer.write("Damage Status: " + damageStatus + "\n");
        writer.write("Final Charge: " + finalCost + "\n");
        writer.write("-------------------------------------------------\n");
        writer.close();
    } catch (Exception e) {
        System.out.println("Error saving return data: " + e.getMessage());
    }
}

/////////////////// FILE HANDLING CODE ENDED////////////////////


///// ADMIN PANEL



    static void adminPanel() {
        System.out.print("\nEnter Admin Password: ");
        if (!scanner.nextLine().equals(ADMIN_PASSWORD)) {
            System.out.println("Incorrect Password! Access Denied.");
            return;
        }

        while (true) {
            System.out.println("\n--- Admin Panel ---");
            System.out.println("1) View Booking History\n2) Modify Rent Prices\n3) Add New Car\n4) Remove Car\n5) Exit Admin Panel");
            System.out.print("Enter option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> viewBookingHistory();
                case 2 -> modifyCarPrices();
                case 3 -> addNewCar();
                case 4 -> removeCar();
                case 5 -> {
                    System.out.println("Exiting Admin Panel.");
                    return;
                }
                default -> System.out.println("Invalid option! Try again.");
            }
        }
    }



// BOOKING HISTORY



    static void viewBookingHistory() {
        if (bookingList.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            for (Booking b : bookingList) {
                System.out.println("ID: " + b.bookingID + " | Name: " + b.customerName + " | Car: " + b.carName + " | Start Date: " + b.rentalStartDate + " | End Date: " + b.rentalEndDate + " | Cost: " + b.totalCost);
            }
        }
    }




//////MODIFY CAR PRICES /////// 



    static void modifyCarPrices() {
        System.out.println("\nAvailable Cars:");
        for (int i = 0; i < carList.size(); i++) {
            System.out.println((i + 1) + ") " + carList.get(i).name + " - " + carList.get(i).pricePerDay + " per day");
        }

        System.out.print("Select car to modify price: ");
        int carIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (carIndex >= 0 && carIndex < carList.size()) {
            System.out.print("Enter new rental price per day: ");
            int newPrice = scanner.nextInt();
            scanner.nextLine();
            carList.get(carIndex).pricePerDay = newPrice;
            System.out.println("Price updated successfully!");
        } else {
            System.out.println("Invalid selection!");
        }
    }




////// ADD NEW CAR  /////// 
    static void addNewCar() {
        System.out.print("\nEnter Car Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Number of Seats: ");
        int seats = scanner.nextInt();
        System.out.print("Enter Price Per Day: ");
        int price = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Specifications: ");
        String specs = scanner.nextLine();

        carList.add(new Car(name, seats, price, specs));
        System.out.println("Car added successfully!");
    }



////// REMOVE CAR  /////// 


    static void removeCar() {
        System.out.println("\nAvailable Cars:");
        for (int i = 0; i < carList.size(); i++) {
            System.out.println((i + 1) + ") " + carList.get(i).name);
        }

        System.out.print("Select car to remove: ");
        int carIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (carIndex >= 0 && carIndex < carList.size()) {
            System.out.println("Car " + carList.get(carIndex).name + " removed.");
            carList.remove(carIndex);
        } else {
            System.out.println("Invalid selection!");
        }
    }




////// TRIP COST ESTIMATOR  /////// 



    static void tripEstimator() {
        System.out.print("\nEnter Distance (in km): ");
        int distance = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Estimated Cost: " + (distance * 15));
    }


////// EMERGENCY  CAR  RENTAL  /////// 


static void emergencyRental() {
    String aadharID;
    String licenseNumber;

    //  Aadhar ID ( 12 digits)
    while (true) {
        System.out.print("\nEnter Aadhar ID (12 digits): ");
        aadharID = scanner.nextLine();
        if (aadharID.matches("\\d{12}")) {
            break;
        } else {
            System.out.println("Invalid Aadhar ID! It must be exactly 12 digits.");
        }
    }

    // License Number ( 16 characters)
    while (true) {
        System.out.print("Enter License Number (16 characters): ");
        licenseNumber = scanner.nextLine();
        if (licenseNumber.length() == 16) {
            break;
        } else {
            System.out.println("Invalid License Number! It must be exactly 16 characters.");
        }
    }

    // current timestamp
    String timestamp = java.time.LocalDateTime.now().toString();

    // Save data to emergency_rentals.txt
    try {
        FileWriter writer = new FileWriter("emergency_rentals.txt", true); // append mode
        writer.write("Aadhar ID: " + aadharID + ", License No: " + licenseNumber + ", Timestamp: " + timestamp + "\n");
        writer.close();
        System.out.println("Car Assigned! Emergency Rental Confirmed and saved.");
    } catch (IOException e) {
        System.out.println("An error occurred while saving emergency rental data.");
        e.printStackTrace();
    }
}



////// AVAILABLE CAR  /////// 



    static void availableCars() {
    System.out.println("\nAvailable Cars:");
    boolean anyAvailable = false;

    for (int i = 0; i < carList.size(); i++) {
        Car car = carList.get(i);
        if (car.isAvailable) { // Only show available cars
            System.out.println((i + 1) + ") " + car.name + " - " + car.pricePerDay + " per day | Seats: " + car.seats + " | Specs: " + car.specifications);
            anyAvailable = true;
        }
    }

    if (!anyAvailable) {
        System.out.println("No cars currently available for rent.");
        return;
    }

    System.out.print("Select a car to confirm your choice: ");
    int carChoice = scanner.nextInt() - 1;
    scanner.nextLine();

    if (carChoice >= 0 && carChoice < carList.size() && carList.get(carChoice).isAvailable) {
        System.out.println("You have successfully chosen: " + carList.get(carChoice).name);
    } else {
        System.out.println("Invalid selection or car is not available! Please try again.");
    }
}

}