package org.bmw.domaininteraction;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.bmw.domain.Student;
import org.bmw.domain.University;

import java.util.List;

@ApplicationScoped
public class StudentService {
    private final Students students;

    public StudentService(Students students) {
        this.students = students;
    }


    public Long createStudent(String firstName, String lastName, String cnp, String email, Student.UniversityInner university) {
        Student student = Student.createStudent(firstName, lastName, cnp, email, university);
        Student created = students.create(student);
        return created.getId();
    }

    public List<Student> findByFirstName(String firstName) {
        return students.findByFirstName(firstName);
    }

    public List<Student> findAllStudents(){
        return students.findAllStudents();
    }

    public void deleteStudent(String cnp){
        students.deleteStudent(cnp);
    }

    public void assignStudentToUniversity(String cnp, String universityName){
        students.assignStudentToUniversity(cnp,universityName);
    }

    public void unassignStudentFromUniversity(String cnp, String universityName){
        students.unassignStudentFromUniversity(cnp,universityName);
    }

    public void updateStudent(String firstName, String lastName, String cnp, String email){
        students.updateStudent(firstName,lastName,cnp,email);
    }
}
