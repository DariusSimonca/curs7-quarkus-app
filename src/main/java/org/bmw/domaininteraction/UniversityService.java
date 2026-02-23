package org.bmw.domaininteraction;

import jakarta.enterprise.context.ApplicationScoped;
import org.bmw.domain.Student;
import org.bmw.domain.University;

import java.util.List;

@ApplicationScoped
public class UniversityService {
    private final Universities universities;

    public UniversityService(Universities universities) {
        this.universities = universities;
    }

    public Long create(String name, String location, List<Student> students){
        University university = University.createUniversity(name,location,students);
        University created = universities.create(university);
        return created.getId();
    }

    public List<University> findAllUniversities(){
        return universities.findAllUniversities();
    }

}
