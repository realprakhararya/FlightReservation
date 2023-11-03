import Flight.A320;
import Flight.A350;
import Flight.A380;
import Flight.FlightDetails;
import Flight.Model;


public class Main {
    public static void main(String[] args) {
        // Create A320, A350, and A380 flight objects
        Model a320 = new A320();
        FlightDetails flightA320 = new FlightDetails(a320, "New York", "Los Angeles", "09:00 AM", "2023-11-10");

        Model a350 = new A350();
        FlightDetails flightA350 = new FlightDetails(a350, "London", "Singapore", "10:30 AM", "2023-11-12");

        Model a380 = new A380();
        FlightDetails flightA380 = new FlightDetails(a380, "Dubai", "Sydney", "11:45 AM", "2023-11-15");

        // Initialize passenger linked lists for each flight
        PassengerLinkedList passengerListA320 = new PassengerLinkedList();
        PassengerLinkedList passengerListA350 = new PassengerLinkedList();
        PassengerLinkedList passengerListA380 = new PassengerLinkedList();

        // Display flight details
        System.out.println("Flight A320:");
        System.out.println("Model: " + flightA320.getModel());
        System.out.println("Description: " + flightA320.getDescription());
        System.out.println("Departure Destination: " + flightA320.getDepartureDestination());
        System.out.println("Arrival Destination: " + flightA320.getArrivalDestination());
        System.out.println("Departure Time: " + flightA320.getDepartureTime());
        System.out.println("Departure Date: " + flightA320.getDepartureDate());
        System.out.println();

        System.out.println("Flight A350:");
        System.out.println("Model: " + flightA350.getModel());
        System.out.println("Description: " + flightA350.getDescription());
        System. out.println("Departure Destination: " + flightA350.getDepartureDestination());
        System.out.println("Arrival Destination: " + flightA350.getArrivalDestination());
        System.out.println("Departure Time: " + flightA350.getDepartureTime());
        System.out.println("Departure Date: " + flightA350.getDepartureDate());
        System.out.println();

        System.out.println("Flight A380:");
        System.out.println("Model: " + flightA380.getModel());
        System.out.println("Description: " + flightA380.getDescription());
        System.out.println("Departure Destination: " + flightA380.getDepartureDestination());
        System.out.println("Arrival Destination: " + flightA380.getArrivalDestination());
        System.out.println("Departure Time: " + flightA380.getDepartureTime());
        System.out.println("Departure Date: " + flightA380.getDepartureDate());
        System.out.println();

        // Add passengers to the lists (example passengers)
        passengerListA320.addPassenger(new Passenger("John", "Doe", 35, "Male", 10, 5, 'A'));
        passengerListA350.addPassenger(new Passenger("Alice", "Smith", 28, "Female", 15, 3, 'C'));
        passengerListA380.addPassenger(new Passenger("Bob", "Johnson", 42, "Male", 20, 7, 'D'));

        // Display passengers for each flight
        System.out.println("Passengers for Flight A320:");
        passengerListA320.displayPassengers();

        System.out.println("Passengers for Flight A350:");
        passengerListA350.displayPassengers();

        System.out.println("Passengers for Flight A380:");
        passengerListA380.displayPassengers();
    }
}
