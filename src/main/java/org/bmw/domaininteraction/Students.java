package org.bmw.domaininteraction;

import org.bmw.domain.Student;
import org.bmw.persistence.student.StudentEntity;

import java.util.List;

public interface Students {
    Student create(Student student);
    List<Student> findByFirstName(String firstName);
    List<Student> findAllStudents();
}
