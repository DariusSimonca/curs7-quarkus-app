package org.bmw.persistence.mapper;

import org.bmw.domain.University;
import org.bmw.persistence.university.UniversityEntity;

import java.time.LocalDateTime;

public class UniversityMapperImplementation implements UniversityMapper {
    @Override
    public University toDomain(UniversityEntity entity) {
        return University.reconstitute(
                entity.getId(),
                entity.getName(),
                entity.getLocation(),
                entity.getStudents()
        );
    }

    @Override
    public UniversityEntity toEntity(University university) {
        return new UniversityEntity(
                university.getId(),
                university.getName(),
                university.getLocation(),
                university.getStudents(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
