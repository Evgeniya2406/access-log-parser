public class Line implements Measurable {
public Point start;
public Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Line() {
    }

    public Line(int startX, int startY, int endX, int endY) {
        this.start = new Point(startX,startY);
        this.end = new Point(endX,endY);
    }

    @Override
    public String toString() {
        return "Линия от " +  start + " до "
                 + end;
    }
        public Double getLength() {
            return Math.round(Math.sqrt(Math.pow((this.start.getX() - this.end.getX()), 2) + Math.pow((this.start.getY() - this.end.getY()), 2))*1000)/1000.0;

    }
    }


