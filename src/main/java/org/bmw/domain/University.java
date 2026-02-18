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
}
