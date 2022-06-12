package Part3;

import java.util.List;
import java.util.Random;

public class Teacher implements Runnable {
    private final Journal journal;
    private final List<Student> students;

    public Teacher(Journal journal, List<Student> students) {
        this.journal = journal;
        this.students = students;
    }

    @Override
    public void run() {
        Random random = new Random();
        int totalWeeks = 4 * 4;
        int totalMarkForAllStudents = 0;
        for (int week = 0; week < totalWeeks; week++) {
            for (Student student : students) {
                var mark = new Mark(random.nextInt(1, 100));
                totalMarkForAllStudents += mark.getValue();
                journal.setMark(student, mark);
            }
        }
        System.out.println("Teacher totally set " + totalMarkForAllStudents + " points");
    }
}
