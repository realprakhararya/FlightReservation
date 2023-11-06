import Flight.A320;
import Flight.A350;
import Flight.A380;
import Flight.FlightDetails;
import Flight.Model;
import PassengerInfo.Passenger;
import PassengerInfo.PassengerLinkedList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class JavaFXFlightAppWithPassenger extends Application {
    private FlightDetails selectedFlight;
    private PassengerLinkedList passengerList;

    private double seatWidth = 40; // Width of each seat circle
    private double seatHeight = 40; // Height of each seat circle

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create flight objects
        Model a320 = new A320();
        FlightDetails flightA320 = new FlightDetails(a320, "New York", "Los Angeles", "09:00 AM", "2023-11-10");

        Model a350 = new A350();
        FlightDetails flightA350 = new FlightDetails(a350, "London", "Singapore", "10:30 AM", "2023-11-12");

        Model a380 = new A380();
        FlightDetails flightA380 = new FlightDetails(a380, "Dubai", "Sydney", "11:45 AM", "2023-11-15");

        // Initialize the selected flight
        selectedFlight = flightA320; // Default to A320
        passengerList = new PassengerLinkedList();

        // Create a ComboBox to select flights
        ComboBox<String> flightComboBox = new ComboBox<>();
        flightComboBox.getItems().addAll("A320 - New York to Los Angeles", "A350 - London to Singapore", "A380 - Dubai to Sydney");

        // Create a GridPane to display seat availability
        GridPane seatGrid = new GridPane();
        seatGrid.setHgap(5);
        seatGrid.setVgap(5);

        // Event handler for seat selection
        seatGrid.setOnMouseClicked(event -> {
            // Calculate the row and column based on the mouse click position in the seatGrid
            int row = (int) (event.getY() / seatHeight);
            int column = (int) (event.getX() / seatWidth);

            // Display selected seat details (you can customize this part)
            System.out.println("Selected Seat: Row " + (row + 1) + ", Column " + (column + 1));
        });

        // Create passenger details form
        VBox passengerDetailsForm = new VBox(10);
        passengerDetailsForm.setMaxWidth(200);
        passengerDetailsForm.setStyle("-fx-background-color: #f5f5f5;");
        passengerDetailsForm.setPadding(new Insets(10));

        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");
        TextField ageField = new TextField();
        ageField.setPromptText("Age");
        TextField genderField = new TextField();
        genderField.setPromptText("Gender");

        Button addPassengerButton = new Button("Add Passenger");
        addPassengerButton.setOnAction(event -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String gender = genderField.getText();
            int row = 0; // Default seat row (modify as needed)
            int column = 0; // Default seat column (modify as needed)
            char seatType = 'A'; // Default seat type (modify as needed)

            Passenger passenger = new Passenger(firstName, lastName, age, gender, row, column, seatType);
            passengerList.addPassenger(passenger);

            // Update seat grid after adding passenger
            updateSeatGrid(seatGrid);
        });

        passengerDetailsForm.getChildren().addAll(firstNameField, lastNameField, ageField, genderField, addPassengerButton);

        // Event handler for flight selection
        flightComboBox.setOnAction(event -> {
            String selectedFlightText = flightComboBox.getValue();
            if (selectedFlightText != null) {
                if (selectedFlightText.startsWith("A320")) {
                    selectedFlight = flightA320;
                } else if (selectedFlightText.startsWith("A350")) {
                    selectedFlight = flightA350;
                } else if (selectedFlightText.startsWith("A380")) {
                    selectedFlight = flightA380;
                }

                // Update seat grid and clear passenger details form
                updateSeatGrid(seatGrid);
                clearPassengerDetailsForm(firstNameField, lastNameField, ageField, genderField);
            }
        });

        // Create the root layout
        BorderPane root = new BorderPane();
        root.setTop(flightComboBox);
        root.setLeft(seatGrid);
        root.setRight(passengerDetailsForm);

        // Create the scene and set it on the stage
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Flight Details and Passenger Booking");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateSeatGrid(GridPane seatGrid) {
        seatGrid.getChildren().clear();

        int rows = selectedFlight.getAircraft().getSeatMatrix().length;
        int columns = selectedFlight.getAircraft().getSeatMatrix()[0].length;

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Circle seat = new Circle(seatWidth / 2);
                seat.setStroke(Color.BLACK);

                int seatStatus = selectedFlight.getAircraft().getSeat(row, column);

                if (seatStatus == 1) {
                    seat.setFill(Color.RED); // Booked seat
                } else {
                    seat.setFill(Color.WHITE); // Available seat
                }

                seatGrid.add(seat, column, row);
            }
        }
    }

    private void clearPassengerDetailsForm(TextField firstNameField, TextField lastNameField, TextField ageField, TextField genderField) {
        firstNameField.clear();
        lastNameField.clear();
        ageField.clear();
        genderField.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}