package org.bmw.persistence.student;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bmw.persistence.university.UniversityEntity;

import java.time.LocalDateTime;

@ApplicationScoped
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String cnp;

    @Column(unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private UniversityEntity university;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public StudentEntity(Long id, String firstName, String lastName, String cnp, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cnp = cnp;
        this.email = email;
    }
}
