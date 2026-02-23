package org.bmw.domaininteraction;

import org.bmw.domain.Student;

import java.util.List;

public interface Students {
    Student create(Student student);
    List<Student> findByFirstName(String firstName);
    List<Student> findAllStudents();
    void deleteStudent(String cnp);
}
