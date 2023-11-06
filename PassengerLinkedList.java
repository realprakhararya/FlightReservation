package PassengerInfo;

import java.util.LinkedList;

public class PassengerLinkedList {
    private LinkedList<Passenger> passengers = new LinkedList<>();

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    public void modifyPassenger(int index, Passenger newPassenger) {
        if (index >= 0 && index < passengers.size()) {
            passengers.set(index, newPassenger);
        }
    }

    public void removePassenger(int index) {
        if (index >= 0 && index < passengers.size()) {
            passengers.remove(index);
        }
    }

    public void displayPassengers() {
        for (int i = 0; i < passengers.size(); i++) {
            Passenger passenger = passengers.get(i);
            System.out.println("Passenger #" + (i + 1));
            System.out.println("Name: " + passenger.getFirstName() + " " + passenger.getLastName());
            System.out.println("Age: " + passenger.getAge());
            System.out.println("Gender: " + passenger.getGender());
            System.out.println("Seat: Row " + passenger.getSeat()[0] + ", Column " + passenger.getSeat()[1] + " (" + passenger.getSeatType() + ")");
            System.out.println();
        }
    }

    public int getPassengerCount() {
        return passengers.size();
    }

    public int findPassenger(int row, int column) {
        for (int i = 0; i < passengers.size(); i++) {
            Passenger passenger = passengers.get(i);
            int[] seat = passenger.getSeat();
            if (seat[0] == row && seat[1] == column) {
                return i; // Passenger found at this index
            }
        }
        return -1; // Passenger not found
    }

    public Passenger getPassenger(int index) {
        if (index >= 0 && index < passengers.size()) {
            return passengers.get(index);
        }
        return null; // Passenger not found
    }

    public void setPassengerDetails(int index, String firstName, String lastName, int age, String gender) {
        if (index >= 0 && index < passengers.size()) {
            Passenger passenger = passengers.get(index);
            passenger.setFirstName(firstName);
            passenger.setLastName(lastName);
            passenger.setAge(age);
            passenger.setGender(gender);
        }
    }
}