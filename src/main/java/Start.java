public class Start {
    public static void main(String[] args) {
        int[][] intPoints = {{1,5},{2,8},{5,3},{8,9}};
       // int[][] intPoints = {{0,5},{5,5},{10,5},{15,5}};
        Point[] points = new Point[intPoints.length];
        for (int i = 0; i< intPoints.length; i++) {
            points[i]=new Point(intPoints[i][0],intPoints[i][1]);
        }

        PolyLine polyLine = new PolyLine();
        polyLine.setCoordinates(points);
        System.out.println("Пункт задания 1:");
        System.out.println(polyLine);

        System.out.println("Пункт задания 2:");
        System.out.println("Длина Ломаной равна: " + polyLine.getLength());

        Line[] lines = new Line[]{new Line()};
        lines =polyLine.getLines();
        System.out.println("Пункт задания 3:");
        for (int i = 0;i< lines.length;i++) {
            System.out.println(lines[i]);
        }

        System.out.println("Пункт задания 4:");

        Double length=0.0;
        for (int i = 0; i<= lines.length-1; i++) {
            length += lines[i].getLength();
        }
        System.out.println("Длина массива Линий: " + length);

        System.out.println("Пункт задания 5:");
        if (Math.round(polyLine.getLength()*1000)==Math.round(length*1000)){
            System.out.println("Длина линий совпадает: " + Math.round(polyLine.getLength()*1000)/1000.0 +" = "+Math.round(length*1000)/1000.0);
        } else {
            System.out.println("Длина линий НЕ совпадает!");
        }

        System.out.println("Пункт задания 6:");
        points[1].setX(12);
        points[1].setY(8);
        System.out.println(polyLine);
    }
}
