package org.bmw.persistence.student;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import org.bmw.domain.Student;
import org.bmw.domaininteraction.Students;
import org.bmw.persistence.mapper.StudentMapper;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class StudentRepository implements PanacheRepository<StudentEntity>, Students {

    private final StudentMapper mapper;

    public StudentRepository(StudentMapper mapper) {
        this.mapper = mapper;
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
    public List<Student> findAllStudents(){
        List<StudentEntity> entities = listAll();
        return entities.stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteStudent(String cnp) {
       StudentEntity entity = find("cnp",cnp).firstResult();

       if(entity == null){
           throw new NotFoundException("Student not found !");
       }

       delete(entity);
    }
}
