package org.bmw.persistence.mapper;

import org.bmw.domain.Student;
import org.bmw.persistence.student.StudentEntity;

public interface StudentMapper {
    Student toDomain(StudentEntity entity);

    StudentEntity toEntity(Student student);
}
