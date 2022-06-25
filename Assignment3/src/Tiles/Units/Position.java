package Tiles.Units;

public class Position implements  Comparable<Position> {
    private int xCoordinate;
    private int yCoordinate;

    public Position(int xCoordinate, int yCoordinate){
        this.yCoordinate = yCoordinate;
        this.xCoordinate = xCoordinate;
    }

    public double Range(Position other){
        return Math.sqrt((other.yCoordinate - this.yCoordinate) *
                (other.yCoordinate - this.yCoordinate) + (other.xCoordinate - this.xCoordinate) *
                (other.xCoordinate - this.xCoordinate));
    }

    public Integer getXCoordinate() {
        return xCoordinate;
    }

    public Integer getYCoordinate() {
        return yCoordinate;
    }


    @Override
    public int compareTo(Position other) {
        if(xCoordinate> other.getXCoordinate())
            return 1;
        else if(xCoordinate == other.getXCoordinate() && yCoordinate> other.getYCoordinate())
            return 1;
        else if(xCoordinate == other.getXCoordinate() && yCoordinate == other.getYCoordinate())
            return 0;
        else
            return -1;

    }
}
