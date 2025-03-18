import java.util.ArrayList;
import java.util.List;

public class Student {
  private   List<Integer> grades= new ArrayList<>();
   private String name;

    public Student(String name, List<Integer> grades) {
        for (int i=0; i<grades.size(); i++){
         if (grades.get(i)<2 || grades.get(i)>5) throw new IllegalArgumentException("Оценка должна быть от 2 до 5");
        }
        this.name = name;
        this.grades.addAll(grades);
    }

    public Student() {
    }

    public List<Integer> getGrades() {
        return grades;
    }

    @Override
    public String toString() {
        StringBuilder grades2 = new StringBuilder();

        for (int i=0; i<grades.size()-1; i++){
            grades2.append("оценка").append(i + 1).append("=");
            grades2.append(grades.get(i)).append(",");
        }
        grades2.append("оценка").append(grades.size()).append("=");
        grades2.append(grades.get(grades.size()-1));
        return "Студент: " +
                "Имя: " + name  +
                " [" + grades2 +
                ']';
    }

    public void addGrades(List<Integer> grades) {
        for (int i = 0; i < grades.size(); i++) {
            if (grades.get(i) < 2 || grades.get(i) > 5)
                throw new IllegalArgumentException("Оценка должна быть от 2 до 5");
        }
        this.grades.addAll(grades);
    }
}
