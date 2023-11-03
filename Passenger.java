public class Passenger {
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private boolean isMealBooked;
    private int[] seat = new int[2];
    private char seatType;

    public Passenger(String firstName, String lastName, int age, String gender, int row, int column, char type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        seat[0] = row;
        seat[1] = column;
        seatType = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public boolean isMealBooked() {
        return isMealBooked;
    }

    public void bookMeal() {
        isMealBooked = true;
    }

    public void cancelMeal() {
        isMealBooked = false;
    }

    public int[] getSeat() {
        return seat;
    }

    public char getSeatType() {
        return seatType;
    }
}
