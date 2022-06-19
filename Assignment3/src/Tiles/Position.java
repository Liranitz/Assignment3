package Tiles;

public class Position implements  Comparable<Position> {
    protected int xCoordinate;
    protected int yCoordinate;

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

    public double Range(
            double x1,
            double y1,
            double x2,
            double y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
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
