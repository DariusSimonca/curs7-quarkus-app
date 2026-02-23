package org.bmw.domain;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class University {
    private Long id;
    private String name;
    private String location;
    private List<Student> students;

    public static University reconstitute(Long id, String name, String location, List<Student> students) {
        return University.builder()
                .id(id)
                .name(name)
                .location(location)
                .students(students)
                .build();
    }

    public static University createUniversity(String name, String location, List<Student> students){
        University university = University.builder()
                .name(name)
                .location(location)
                .students(students)
                .build();
        university.validateBusinessRules();
        return university;
    }

    private void validateBusinessRules(){
        if (name == null || location == null) {
            throw new IllegalStateException("University must have a name and a location !");
        }
    }
}
