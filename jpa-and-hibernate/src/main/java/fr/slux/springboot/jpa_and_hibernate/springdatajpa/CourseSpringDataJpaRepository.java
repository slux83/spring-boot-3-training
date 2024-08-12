package fr.slux.springboot.jpa_and_hibernate.springdatajpa;

import fr.slux.springboot.jpa_and_hibernate.course.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseSpringDataJpaRepository extends JpaRepository<CourseEntity, Long> {
    // naming convention with "Author" one of the table fields
    // crazy indeed!
    List<CourseEntity> findByAuthor(String author);
}
