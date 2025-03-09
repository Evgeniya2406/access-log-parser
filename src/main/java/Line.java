public class Line {
public Point start;
public Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    public Line(int startX,int startY,int endX,int endY) {
        this.start = new Point(startX,startY);
        this.end = new Point(endX,endY);
    }

    @Override
    public String toString() {
        return "Линия от " +  start.toString() + " до "
                 + end.toString();
    }
        public Double getLength() {
            double sqrt;
            sqrt = Math.sqrt(Math.pow((this.start.x - this.end.x), 2) + Math.pow((this.start.y - this.end.y), 2));
        return sqrt;
    }
    }


