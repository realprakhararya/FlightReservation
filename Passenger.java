package PassengerInfo;

public class Passenger {
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private int[] seat = new int[2];


    //Datatypes that can be considered for prject upgradation
    //private boolean isMealBooked;
    //private char seatType;

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

    public int[] getSeat() {
        return seat;
    }

    public char getSeatType() {
        return seatType;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

