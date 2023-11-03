package Flight;

import Flight.Model;

public class FlightDetails {
    private Model aircraft;
    private String departureDestination;
    private String arrivalDestination;
    private String departureTime;
    private String departureDate;

    public FlightDetails(Model aircraft, String departureDestination, String arrivalDestination, String departureTime, String departureDate) {
        this.aircraft = aircraft;
        this.departureDestination = departureDestination;
        this.arrivalDestination = arrivalDestination;
        this.departureTime = departureTime;
        this.departureDate = departureDate;
    }

    public Model getAircraft() {
        return aircraft;
    }

    public String getDepartureDestination() {
        return departureDestination;
    }

    public String getArrivalDestination() {
        return arrivalDestination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getModel() {
        return aircraft.getModel();
    }

    public String getDescription() {
        return aircraft.getDescription();
    }
}