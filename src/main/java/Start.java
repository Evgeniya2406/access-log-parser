import java.util.ArrayList;
import java.util.List;

public class Start {
    public static void main(String[] args) {
        List<Integer> grades= new ArrayList<>();
        grades.add(2);
        grades.add(3);
        grades.add(4);
        grades.add(5);

        String name = "Sherbakov";
        Student std = new Student(name, grades);
        System.out.println(std);

        List<Integer> newGrades= new ArrayList<>();
        newGrades.add(2);
        newGrades.add(4);
        newGrades.add(3);
        newGrades.add(5);
        std.addGrades(newGrades);
        System.out.println(std);
        System.out.println(std.getGrades());
    }
}
