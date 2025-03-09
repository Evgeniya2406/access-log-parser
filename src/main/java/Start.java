public class Start {
    public static void main(String[] args) {
        Point tck1 = new Point(1,3);
        Point tck2 = new Point(5,9);
        Point tck3 = new Point(10,12);
        Point tck4 = new Point(15,19);
        Line ln1= new Line(tck1,tck2);
        Line ln2= new Line(tck3,tck4);
        Line ln3= new Line(tck2,tck3);
        System.out.println(ln3.toString());
        System.out.println("Меняем координаты начала и конца линии 3");
        tck2=new Point(5,10);
        tck3=new Point(16,19);
        ln1= new Line(tck1,tck2);
        ln2= new Line(tck3,tck4);
        ln3 = new Line(tck2,tck3);
        System.out.println(ln3.toString());
        System.out.println(ln1.getLength()+ln2.getLength()+ln3.getLength());
    }
}
