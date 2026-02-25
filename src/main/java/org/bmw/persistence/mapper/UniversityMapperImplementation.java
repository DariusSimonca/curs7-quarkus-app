package org.bmw.persistence.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.bmw.domain.Student;
import org.bmw.domain.University;
import org.bmw.persistence.university.UniversityEntity;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class UniversityMapperImplementation implements UniversityMapper {

    private final StudentMapper studentMapper;

    public UniversityMapperImplementation(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public University toDomain(UniversityEntity entity) {
        if (entity == null) {
            return null;
        }

        return University.reconstitute(
                entity.getId(),
                entity.getName(),
                entity.getLocation(),
                entity.getStudents().stream()
                        .map(studentMapper::toInner)
                        .toList()
        );
    }

    @Override
    public UniversityEntity toEntity(University university) {
        if (university == null) {
            return null;
        }
        return new UniversityEntity(
                university.getId(),
                university.getName(),
                university.getLocation(),
                university.getStudents() != null
                        ? university.getStudents().stream().map(studentMapper::fromInnerToEntity).toList()
                        : List.of(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    @Override
    public Student.UniversityInner toInner(UniversityEntity university) {
        if (university == null) {
            return null;
        }
        return new Student.UniversityInner(university.getId(), university.getName(), university.getLocation());
    }
}
