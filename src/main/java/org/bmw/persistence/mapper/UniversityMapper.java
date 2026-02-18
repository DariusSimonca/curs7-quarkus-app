package org.bmw.persistence.mapper;

import org.bmw.domain.University;
import org.bmw.persistence.university.UniversityEntity;

public interface UniversityMapper {
    University toDomain(UniversityEntity entity);

    UniversityEntity toEntity(University university);
}
