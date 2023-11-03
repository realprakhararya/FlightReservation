package Flight;

import Flight.Model;

public class A350 extends Model {
    public A350() {
        super("A350", 325, 8000, 35, 10); // Example capacity, range, and seat matrix size
    }

    @Override
    public String getDescription() {
        return "Airbus A350 - A wide-body long-haul passenger aircraft.";
    }
}
