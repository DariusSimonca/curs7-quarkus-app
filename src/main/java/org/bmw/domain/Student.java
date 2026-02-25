package org.bmw.domain;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Student {
    private Long id;
    private String firstName;
    private String lastName;
    private String cnp;
    private String email;
    private UniversityInner university;

    public static Student reconstitute(Long id, String firstName, String lastName, String cnp,
                                       String email, UniversityInner university) {
        return Student.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .cnp(cnp)
                .email(email)
                .university(university)
                .build();
    }

    public static Student createStudent(String firstName, String lastName, String cnp, String email, UniversityInner university) {
        Student student = Student.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .cnp(cnp)
                .university(university)
                .build();
        student.validateBusinessRules();
        return student;
    }

    private void validateBusinessRules() {
        if (firstName == null || lastName == null || cnp == null || email == null) {
            throw new IllegalStateException("Student must have a firstname,lastname,cnp and email !");
        }
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor

    public static class UniversityInner{
        private Long id;
        private String name;
        private String location;
    }
}


