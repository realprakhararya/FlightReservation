package Flight;

import Flight.Model;

public class A380 extends Model {
    public A380() {
        super("A380", 853, 15200, 55, 9); // Example capacity, range, and seat matrix size
    }

    @Override
    public String getDescription() {
        return "Airbus A380 - The world's largest passenger airliner.";
    }
}
