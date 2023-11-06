package Flight; 

public abstract class Model{
    private String model;
    private int capacity;
    private int range;
    private int[][] seatMatrix;

    public Model(String model, int capacity, int range, int rows, int cols) {
        this.model = model;
        this.capacity = capacity;
        this.range = range;
        this.seatMatrix = new int[rows][cols];
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                seatMatrix[i][j]=0;
            }
        }
    }

    public String getModel() {
        return model;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRange() {
        return range;
    }

    public int[][] getSeatMatrix() {
        return seatMatrix;
    }

    public void setSeat(int row, int col, int status) {
        seatMatrix[row][col] = status;
    }


    public void clearSeat(int row, int col) {
        seatMatrix[row][col] = 0;
    }

    public int getSeat(int row, int col) {
        return seatMatrix[row][col];
    }

    public abstract String getDescription();
}