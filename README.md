🧾 Car Rental System (Console-Based in Java)
📖 Overview
The Car Rental System is a console-based Java application that provides a simple and effective solution for renting cars using a user-friendly terminal interface. It is built entirely in core Java and leverages file handling to simulate real-world operations like renting, returning, and tracking vehicles.

The system supports two types of users:

General users – can rent or return cars.

Admin users – can view rental history, car listings, and will later be able to add, remove, or modify cars and prices.

It is ideal for educational purposes, mini-projects, or hackathon submissions. It emphasizes fundamental object-oriented principles and file operations in Java.

🏁 Key Functionalities
1. Rent a Car
Allows users to choose from a list of available cars.

Takes input like the user's name and car choice.

Confirms the booking and displays relevant information.

Updates the internal data structures (in-memory).

2. Return a Car
Lets users return a previously rented car.

Takes car ID and user name to verify ownership.

Calculates return time and marks the car as available again.

3. Admin Panel
Protected by a password (admin123).

Admins can:

View complete rental history (rental_records.txt)

See current car database

In future: Modify bookings, add/remove cars, update pricing

4. View Rental History
Admins can view a complete record of who rented what and when.

History is saved in a text file to simulate persistence.

Format:

yaml
Copy
Edit
[Timestamp] Name: John | Car: Hyundai i20 | Car ID: 1001 | Action: RENTED
5. Car Database
Cars are stored in an array of objects, each with:

Car ID

Car Name

Car Type

Rent Per Day

Availability status

Future plan: Make car database dynamic and persistent with a cars.txt file.

6. Future Features (Planned)
modifyBooking() – allows users/admins to change an existing booking.

emergencyRental() – for instant bookings without formal inputs.

Add/Remove cars using admin panel.

Real-time file updates to manage car inventory.

🛠️ Technologies Used
Language: Java (Core)

JDK: 8 or later

IDE: Any (Eclipse, IntelliJ IDEA, VS Code)

Persistence: File handling (TXT files)

📋 Sample Data
Car List:

text
Copy
Edit
ID     Name           Type       Rent/Day   Availability
1001   Hyundai i20    Hatchback   ₹4000      Yes
1002   Honda City     Sedan       ₹6000      Yes
1003   Toyota Fortuner SUV        ₹8000      Yes
1004   Mahindra XUV   SUV         ₹7000      Yes
1005   Tata Nexon     Compact     ₹5000      Yes
🧪 Testing the Application
Try these test cases in the console:

Rent Flow

Choose 1 to rent.

Enter name: Alice

Choose ID: 1002

Confirm details → Booking message

Return Flow

Choose 2 to return.

Enter name: Alice

Car ID: 1002

Return confirmation shown

Admin Login

Choose 3

Enter password: admin123

View options like Rental History and Car Database

📂 File Structure
bash
Copy
Edit
CarRentalSystem/
├── RentalSystem.java         # Main class file
├── rental_records.txt        # File storing rental logs
├── cars.txt (planned)        # Will store car info dynamically
├── README.md                 # This documentation
🔐 Security
Admin Password Protection

The admin panel is protected using a basic password (admin123).

Prevents unauthorized access to rental records and car database.

In a real-world system, this would be encrypted and stored securely.

📦 How to Compile and Run
Compile the program

bash
Copy
Edit
javac RentalSystem.java
Run the program

bash
Copy
Edit
java RentalSystem
📘 Code Structure Overview
The project is based on Object-Oriented Programming (OOP) principles.

Classes and Methods:
Main Class: RentalSystem

Data Structures:

String[] carNames

String[] carTypes

int[] carIDs

int[] carPrices

boolean[] carAvailability

Methods:

main()

displayMenu()

rentCar()

returnCar()

adminPanel()

displayCarList()

viewRentalHistory()

recordRentalHistory()

emergencyRental() (to be implemented)

modifyBooking() (to be implemented)

📍 Current Limitations
Car details are not persisted across restarts.

No real-time updates to a car database file.

Admin credentials are hardcoded.

Booking modification is not yet implemented.

🎯 Future Roadmap
Feature	Status
Dynamic Car Inventory	🚧 Planned
Booking Modification	🚧 Planned
Emergency Rentals	🚧 Planned
Car Removal & Addition	🚧 Planned
File I/O for Car Database	🚧 Planned
GUI-based Interface	❌ Not started

✅ Why This Project Matters
Reinforces OOP concepts (arrays, objects, methods)

Demonstrates file handling in real applications

Builds logic for user roles (admin vs. regular)

Shows how to simulate real-world workflows in Java

Can be upgraded into a full-stack or GUI-based project

💡 Ideas for Expansion
Add a GUI using Java Swing or JavaFX

Store data using MySQL instead of text files

Host with a Spring Boot backend + React frontend

Add email confirmation or OTP for admin access

Integrate car photos, pricing calculators, or invoice generation

🤝 Contributions
Contributions, feedback, or feature ideas are welcome.
To contribute:

Fork the repo

Create a new branch

Submit a pull request
