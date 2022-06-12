package Part3;

import java.util.Arrays;

public class JournalTest {
    public static void main(String[] args) throws InterruptedException {
        var student1 = new Student("Nick", "IP96");
        var student2 = new Student("Vlad", "IP96");
        var student3 = new Student("Jack", "IP92");
        var student4 = new Student("Vova", "IP91");
        var students = Arrays.asList(student1, student2, student3, student4);

        var journal = new Journal();

        var teacherThreads = Arrays.asList(
                new Thread(new Teacher(journal, students)),
                new Thread(new Teacher(journal, students)),
                new Thread(new Teacher(journal, students)),
                new Thread(new Teacher(journal, students))
        );

        for (Thread thread : teacherThreads) {
            thread.start();
        }

        for (Thread thread : teacherThreads) {
            thread.join();
        }

        int totalMarkForAllStudents = 0;
        for (Student student : students) {
            totalMarkForAllStudents += journal.getStudentsTotalMark(student);
        }
        System.out.println("Sum of all teachers points should be equal to: " + totalMarkForAllStudents);
    }
}
