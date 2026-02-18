package org.bmw.persistence.mapper;

import org.bmw.domain.Student;
import org.bmw.persistence.student.StudentEntity;

import java.time.LocalDateTime;

public class StudentMapperImplementation implements StudentMapper {
    @Override
    public Student toDomain(StudentEntity entity) {
        return Student.reconstitute(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getCnp(),
                entity.getEmail(),
                entity.getUniversity()
        );
    }

    @Override
    public StudentEntity toEntity(Student student) {
        return new StudentEntity(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getCnp(),
                student.getEmail(),
                student.getUniversity(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
