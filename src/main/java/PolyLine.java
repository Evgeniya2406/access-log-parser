import java.util.Arrays;

public class PolyLine {
    private Point[] coordinates;


    public Point[] getCoordinate() {
        return coordinates;
    }

    public void setCoordinates(Point[] coordinate) {
        this.coordinates = coordinate;
    }
    public int countLines(){
        return coordinates.length-1;
    }
    

    @Override
    public String toString() {
        return "Линия " +
                Arrays.toString(coordinates);
    }

    public Line[] getLines() {
        Line[] lines = new Line[coordinates.length-1];
        for (int i = 0; i< coordinates.length-1; i++) {
            lines[i]=new Line(coordinates[i],coordinates[i+1]);
        }
        return lines;
    }

    public Double getLength() {
        Double length=0.0;
        for (int i = 0; i< coordinates.length-1; i++) {
             length += new Line(coordinates[i], coordinates[i + 1]).getLength();
        }
        return length;
    }
}
