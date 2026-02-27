package org.bmw.domaininteraction;

import org.bmw.domain.Student;

import java.util.List;

public interface Students {
    Student create(Student student);
    List<Student> findByFirstName(String firstName);
    List<Student> findAllStudents();
    void deleteStudent(String cnp);
    void assignStudentToUniversity(String cnp, String universityName);
    void unassignStudentFromUniversity(String cnp, String universityName);
    void updateStudent(String firstName, String lastName, String cnp, String email);
}
