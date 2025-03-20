public class Start {
    public static void main(String[] args) {
    length(new Line(2,3,1,2));
    PolyLine polyLine = new PolyLine();
    polyLine.setCoordinates(new Point[]{new Point(2,3),new Point(1,2), new Point(4,5)});
    length(polyLine);
    }
    static void length(Measurable m){
        System.out.println(m.toString());
       // System.out.println("Длина равна %d".formatted(m.getLength()));
        System.out.println(m.getLength());
    }
}
