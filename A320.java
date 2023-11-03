package Flight;

import Flight.Model;

public class A320 extends Model {
    public A320() {
        super("A320", 180, 5000, 27, 6); // Example capacity, range, and seat matrix size
    }

    @Override
    public String getDescription() {
        return "Airbus A320 - A short- to medium-range commercial passenger jet airliner.";
    }
}
