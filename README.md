# FlightReservation

A Java-based flight reservation application featuring a simple GUI using JavaFX. Users can view flight details, select aircraft types (A320, A350, A380), enter passenger info, and manage reservations.

---

## 📁 Project Layout

| File / Package | Description |
|----------------|-------------|
| `JavaFXFlightApp.java` | Main GUI application entry point |
| `Model.java` | Contains core classes like `FlightDetails`, `Passenger`, etc. |
| `A320.java`, `A350.java`, `A380.java` | Aircraft-specific logic/models |
| `PassengerInfo` | Handles input / validation for passenger data |
| `PassengerLinkedList.java` | Custom data structure to manage passenger records |

---

## 🚀 Requirements

- Java 8 or newer  
- JavaFX library  

---

## 🔧 How to Run

1. Clone the repo

 ```
 git clone https://github.com/realprakhararya/FlightReservation.git
 ```
2. Compile:

```
javac *.java
```

3. Run the main application:

```
java JavaFXFlightApp
```

⚙️ Features
-----------

*   Browse available flights by model type
*   Input passenger details, with basic validation
*   Uses linked-list data structure for passenger management
    

📝 Status & Future Work
-----------------------

*   Currently working version with core flight & passenger features
*   TODOs:
    *   Improve GUI styling and usability
    *   Add data persistence (e.g. save reservations to file/db)
    *   Handle multiple flights concurrently
    *   Edge case handling and input error feedback
        

👤 Author
---------

Prakhar Arya — [GitHub profile](https://github.com/realprakhararya)

