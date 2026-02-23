package org.bmw.persistence.university;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bmw.domain.University;
import org.bmw.domaininteraction.Universities;
import org.bmw.persistence.mapper.UniversityMapper;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class UniversityRepository implements PanacheRepository<UniversityEntity>, Universities {

    private final UniversityMapper mapper;

    public UniversityRepository(UniversityMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public University create(University university) {
        UniversityEntity entity = mapper.toEntity(university);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setModifiedAt(LocalDateTime.now());
        persist(entity);

        return mapper.toDomain(entity);
    }

    @Override
    public List<University> findByName(String name) {
        return List.of();
    }

    @Override
    public List<University> findAllUniversities() {
        List<UniversityEntity> entities = listAll();
        return entities.stream()
                .map(mapper::toDomain)
                .toList();
    }
}
