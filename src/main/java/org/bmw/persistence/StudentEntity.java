package org.bmw.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bmw.domain.University;

import java.time.LocalDateTime;

@ApplicationScoped
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")
public class StudentEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String cnp;
    private String email;
    private University university;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
