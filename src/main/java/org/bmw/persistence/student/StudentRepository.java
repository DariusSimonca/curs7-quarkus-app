package org.bmw.persistence.student;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import org.bmw.domain.Student;
import org.bmw.domaininteraction.Students;
import org.bmw.persistence.mapper.StudentMapper;
import org.bmw.persistence.university.UniversityEntity;
import org.bmw.persistence.university.UniversityRepository;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class StudentRepository implements PanacheRepository<StudentEntity>, Students {

    private final StudentMapper mapper;
    private final UniversityRepository universityRepository;

    public StudentRepository(StudentMapper mapper, UniversityRepository universityRepository) {
        this.mapper = mapper;
        this.universityRepository = universityRepository;
    }

    @Override
    public Student create(Student student) {
        StudentEntity entity = mapper.toEntity(student);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setModifiedAt(LocalDateTime.now());
        persist(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public List<Student> findByFirstName(String firstName) {
        List<StudentEntity> entities = list("firstName", firstName);
        return entities.stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Student> findAllStudents() {
        List<StudentEntity> entities = listAll();
        return entities.stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteStudent(String cnp) {
        StudentEntity entity = find("cnp", cnp).firstResult();

        if (entity == null) {
            throw new NotFoundException("Student not found !");
        }


        if (entity.getUniversity() != null){
            UniversityEntity universityEntity = universityRepository.findById(entity.getUniversity().getId());
            universityEntity.deleteStudent(entity);
            entity.setUniversity(null);
        }

        delete(entity);
    }

    @Override
    public void assignStudentToUniversity(String cnp, String universityName) {
        StudentEntity entity = find("cnp", cnp).firstResult();

        if (entity == null) {
            throw new NotFoundException("Student not found !");
        }

        UniversityEntity universityEntity = universityRepository.find("name", universityName).firstResult();

        if (universityEntity == null) {
            throw new NotFoundException("University not found !");
        }

        entity.setUniversity(universityEntity);
        universityEntity.addStudent(entity);
    }

    @Override
    public void unassignStudentFromUniversity(String cnp, String universityName) {
        StudentEntity entity = find("cnp", cnp).firstResult();

        if (entity == null) {
            throw new NotFoundException("Student not found !");
        }

        UniversityEntity universityEntity = universityRepository.find("name", universityName).firstResult();

        if (universityEntity == null) {
            throw new NotFoundException("University not found !");
        }

        entity.setUniversity(null);
        universityEntity.deleteStudent(entity);
    }

    @Override
    public void updateStudent(String firstName, String lastName, String cnp, String email) {
        StudentEntity entity = find("cnp", cnp).firstResult();

        if (entity == null) {
            throw new NotFoundException("Student not found !");
        }

        if(!entity.getFirstName().equals(firstName) && !firstName.isBlank()){
            entity.setFirstName(firstName);
        }

        if(!entity.getLastName().equals(lastName) && !lastName.isBlank()){
            entity.setLastName(lastName);
        }

        if(!entity.getEmail().equals(email) && !email.isBlank()){
            entity.setEmail(email);
        }
    }
}
