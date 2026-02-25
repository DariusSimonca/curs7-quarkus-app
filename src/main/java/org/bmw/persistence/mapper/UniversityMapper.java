package org.bmw.persistence.mapper;

import org.bmw.domain.Student;
import org.bmw.domain.University;
import org.bmw.persistence.university.UniversityEntity;

public interface UniversityMapper {
    University toDomain(UniversityEntity entity);

    UniversityEntity toEntity(University university);

    Student.UniversityInner toInner(UniversityEntity university);
}
