package org.bmw.domaininteraction;

import org.bmw.domain.University;

import java.util.List;

public interface Universities {
    University create(University university);

    University findByName(String name);

    List<University> findAllUniversities();

    void updateUniversity(String name, String location,String newName);

    void deleteUniversity(String name);
}
