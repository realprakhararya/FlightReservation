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
    private int selectedRow = -1;
    private int selectedColumn = -1;

    private TextField firstNameField;
    private TextField lastNameField;
    private TextField ageField;
    private TextField genderField;
    private GridPane seatGrid;
    private Label passengerDetailsLabel;

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
        seatGrid = new GridPane();
        seatGrid.setHgap(5);
        seatGrid.setVgap(5);

        // Event handler for seat selection
        seatGrid.setOnMouseClicked(event -> {
            // Calculate the row and column based on the mouse click position in the seatGrid
            int row = (int) (event.getY() / seatHeight);
            int column = (int) (event.getX() / seatWidth);

            if (row == selectedRow && column == selectedColumn) {
                // If the clicked seat is already selected, deselect it.
                selectedRow = -1;
                selectedColumn = -1;
            } else {
                // Otherwise, select the new seat.
                selectedRow = row;
                selectedColumn = column;
            }

            // Update the seat grid to reflect the selection.
            updateSeatGrid();

            // Display passenger details if a passenger is associated with the selected seat.
            if (selectedRow >= 0 && selectedColumn >= 0) {
                int passengerIndex = passengerList.findPassenger(selectedRow, selectedColumn);
                if (passengerIndex >= 0) {
                    Passenger passenger = passengerList.getPassenger(passengerIndex);
                    displayPassengerDetails(passenger);
                } else {
                    // No passenger associated with the selected seat, clear the passenger details label.
                    passengerDetailsLabel.setText("");
                }
            } else {
                // No seat selected, clear the passenger details label.
                passengerDetailsLabel.setText("");
            }
        });

        // Create passenger details form
        VBox passengerDetailsForm = new VBox(10);
        passengerDetailsForm.setMaxWidth(200);
        passengerDetailsForm.setStyle("-fx-background-color: #f5f5f5;");
        passengerDetailsForm.setPadding(new Insets(10));

        firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");
        ageField = new TextField();
        ageField.setPromptText("Age");
        genderField = new TextField();
        genderField.setPromptText("Gender");

        Button addPassengerButton = new Button("Add Passenger");
        Button updatePassengerButton = new Button("Update Passenger");
        Button deletePassengerButton = new Button("Delete Passenger");
        Button flightDetailsButton = new Button("Flight Details");
        Button displayAllButton = new Button("Display All Passengers");

        addPassengerButton.setOnAction(event -> addPassenger());
        updatePassengerButton.setOnAction(event -> updatePassenger());
        deletePassengerButton.setOnAction(event -> deletePassenger());
        flightDetailsButton.setOnAction(event -> displayFlightDetails());
        displayAllButton.setOnAction(event -> displayAllPassengers());

        passengerDetailsLabel = new Label();
        passengerDetailsForm.getChildren().addAll(
            firstNameField, lastNameField, ageField, genderField, addPassengerButton, updatePassengerButton, deletePassengerButton, flightDetailsButton, displayAllButton, passengerDetailsLabel
        );

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
                updateSeatGrid();
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

    private void updateSeatGrid() {
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
                } else if (row == selectedRow && column == selectedColumn) {
                    seat.setFill(Color.GREEN); // Highlight the selected seat
                } else {
                    seat.setFill(Color.WHITE); // Available seat
                }

                seatGrid.add(seat, column, row);
            }
        }
    }

    private void clearPassengerDetailsForm(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }

    private void displayPassengerDetails(Passenger passenger) {
        passengerDetailsLabel.setText("Passenger Details:\n" +
                "Name: " + passenger.getFirstName() + " " + passenger.getLastName() + "\n" +
                "Age: " + passenger.getAge() + "\n" +
                "Gender: " + passenger.getGender());
    }

    private void displayFlightDetails() {
        // Display flight details in the terminal
        System.out.println();
        System.out.println("Flight Details for " + selectedFlight.getDepartureDestination() + " to " + selectedFlight.getArrivalDestination());
        System.out.println("Date: " + selectedFlight.getDepartureDate());
        System.out.println("Model: " + selectedFlight.getModel());
        System.out.println("Description: " + selectedFlight.getDescription());
        System.out.println();
        System.out.println("*************************************************************");
        System.out.println("*************************************************************");
    }

    private void displayAllPassengers() {
        // Display details of all passengers in the terminal
        System.out.println();
        System.out.println("Passenger List:");
        for (int i = 0; i < passengerList.getPassengerCount(); i++) {
            Passenger passenger = passengerList.getPassenger(i);
            System.out.println("Passenger #" + (i + 1));
            System.out.println("Name: " + passenger.getFirstName() + " " + passenger.getLastName());
            System.out.println("Age: " + passenger.getAge());
            System.out.println("Gender: " + passenger.getGender());
            System.out.println("Seat: Row " + passenger.getSeat()[0] + ", Column " + passenger.getSeat()[1] + " (" + passenger.getSeatType() + ")");
            System.out.println("*************************************************************");
        }
        System.out.println();
        System.out.println("*************************************************************");
    }

    private void addPassenger() {
        // Input validation: Check if required fields are not empty and age is a valid integer.
        if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || ageField.getText().isEmpty()) {
            showAlert("Please fill in all the passenger details.");
            return;
        }
        
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        int age;
        try {
            age = Integer.parseInt(ageField.getText());
            if (age < 18) {
                showAlert("Age must be 18 or older.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Please enter a valid age as a number.");
            return;
        }
        String gender = genderField.getText();

        if (selectedRow >= 0 && selectedColumn >= 0) {
            Passenger passenger = new Passenger(firstName, lastName, age, gender, selectedRow, selectedColumn, 'A');
            passengerList.addPassenger(passenger);

            // Mark the seat as booked
            selectedFlight.getAircraft().setSeat(selectedRow, selectedColumn, 1);

            // Update seat grid after adding passenger
            updateSeatGrid();

            // Clear selected seat and form
            selectedRow = -1;
            selectedColumn = -1;
            clearPassengerDetailsForm(firstNameField, lastNameField, ageField, genderField);
        }
    }

    private void updatePassenger() {
        if (selectedRow >= 0 && selectedColumn >= 0) {
            // Find the passenger at the selected seat
            int passengerIndex = passengerList.findPassenger(selectedRow, selectedColumn);

            if (passengerIndex >= 0) {
                // Update passenger details
                Passenger passenger = passengerList.getPassenger(passengerIndex);
                passenger.setFirstName(firstNameField.getText());
                passenger.setLastName(lastNameField.getText());
                int age;
                try {
                    age = Integer.parseInt(ageField.getText());
                    if (age < 18) {
                        showAlert("Age must be 18 or older.");
                        return;
                    }
                } catch (NumberFormatException e) {
                    showAlert("Please enter a valid age as a number.");
                    return;
                }
                passenger.setAge(age);
                passenger.setGender(genderField.getText());

                // Update the seat grid with the modified passenger data
                updateSeatGrid();

                // Clear selected seat and form
                selectedRow = -1;
                selectedColumn = -1;
                clearPassengerDetailsForm(firstNameField, lastNameField, ageField, genderField);
            }
        }
    }

    private void deletePassenger() {
        if (selectedRow >= 0 && selectedColumn >= 0) {
            // Find the passenger at the selected seat
            int passengerIndex = passengerList.findPassenger(selectedRow, selectedColumn);

            if (passengerIndex >= 0) {
                // Remove the passenger from the list
                passengerList.removePassenger(passengerIndex);

                // Mark the seat as available
                selectedFlight.getAircraft().setSeat(selectedRow, selectedColumn, 0);

                // Update the seat grid after deleting the passenger
                updateSeatGrid();

                // Clear selected seat and form
                selectedRow = -1;
                selectedColumn = -1;
                clearPassengerDetailsForm(firstNameField, lastNameField, ageField, genderField);
            }
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
