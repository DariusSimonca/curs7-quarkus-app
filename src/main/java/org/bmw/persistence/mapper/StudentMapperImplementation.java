package org.bmw.persistence.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.bmw.domain.Student;
import org.bmw.domain.University;
import org.bmw.persistence.student.StudentEntity;
import org.bmw.persistence.university.UniversityEntity;

import java.time.LocalDateTime;

@ApplicationScoped
public class StudentMapperImplementation implements StudentMapper {

    private final UniversityMapper universityMapper;

    public StudentMapperImplementation(UniversityMapper universityMapper) {
        this.universityMapper = universityMapper;
    }

    @Override
    public Student toDomain(StudentEntity entity) {
        return Student.reconstitute(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getCnp(),
                entity.getEmail(),
                universityMapper.toInner(entity.getUniversity())
        );
    }

    @Override
    public StudentEntity toEntity(Student student) {

        UniversityEntity universityEntity = null;

        if (student.getUniversity() != null) {
            if (student.getUniversity().getId() != null) {
                universityEntity = new UniversityEntity(
                        student.getUniversity().getId()
                );
            }
            else {
                throw new IllegalArgumentException("University must already exist before assigning it to a student.");
            }
        }

        return new StudentEntity(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getCnp(),
                student.getEmail(),
                universityEntity,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    @Override
    public University.StudentInner toInner(StudentEntity student) {
        return new University.StudentInner(student.getId(), student.getFirstName(),student.getLastName(),student.getCnp(),student.getEmail());
    }

    @Override
    public StudentEntity fromInnerToEntity(University.StudentInner student) {
        return new StudentEntity(student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getCnp(),
                student.getEmail());
    }


}
