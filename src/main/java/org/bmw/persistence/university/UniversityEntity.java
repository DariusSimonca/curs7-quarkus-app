package org.bmw.persistence.university;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bmw.domain.Student;
import org.bmw.persistence.student.StudentEntity;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "university")
public class UniversityEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;

    @OneToMany
    private List<StudentEntity> students;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public UniversityEntity(Long id) {
        this.id = id;
    }
}
