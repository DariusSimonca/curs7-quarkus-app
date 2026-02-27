package org.bmw.persistence.university;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
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
    public University findByName(String name) {
        UniversityEntity entity = find("name", name).firstResult();
        return mapper.toDomain(entity);
    }

    @Override
    public List<University> findAllUniversities() {
        List<UniversityEntity> entities = listAll();
        return entities.stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void updateUniversity(String name, String location, String newName) {
        UniversityEntity entity = find("name", name).firstResult();

        if (entity == null) {
            throw new NotFoundException("University not found!");
        }

        if (!newName.isBlank() && !entity.getName().equals(newName)) {
            entity.setName(newName);
        }

        if (!location.isBlank() && !entity.getLocation().equals(location)) {
            entity.setLocation(location);
        }
    }

    @Override
    public void deleteUniversity(String name) {
        UniversityEntity entity = find("name", name).firstResult();

        if (entity == null) {
            throw new NotFoundException("University not found!");
        }

        if (entity.getStudents() != null) {
            entity.getStudents().forEach(e -> e.setUniversity(null));
            entity.setStudents(null);
        }

        delete(entity);
    }
}
