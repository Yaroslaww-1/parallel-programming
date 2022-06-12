package Part3;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Journal {
    private final Map<Student, List<Mark>> studentsMarks;

    public Journal() {
        studentsMarks = new ConcurrentHashMap<>();
    }

    public void setMark(Student student, Mark mark) {
        synchronized (studentsMarks.computeIfAbsent(student, k -> Collections.synchronizedList(new ArrayList<>()))) {
            studentsMarks.get(student).add(mark);
        }
    }

    public int getStudentsTotalMark(Student student) {
        synchronized(studentsMarks) {
            return studentsMarks.getOrDefault(student, new ArrayList<>())
                    .stream()
                    .reduce(0, (total, mark) -> total + mark.getValue(), Integer::sum);
        }
    }
}
