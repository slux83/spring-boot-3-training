package fr.slux.springboot.jpa_and_hibernate;

import fr.slux.springboot.jpa_and_hibernate.course.Course;
import fr.slux.springboot.jpa_and_hibernate.course.CourseEntity;
import fr.slux.springboot.jpa_and_hibernate.course.jdbc.CourseJdbcRepository;
import fr.slux.springboot.jpa_and_hibernate.course.jpa.CourseJpaRepository;
import fr.slux.springboot.jpa_and_hibernate.springdatajpa.CourseSpringDataJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Executed at startup of the app
 */
@Component
public class CourseJdbcCliRunner implements CommandLineRunner {
    private CourseJdbcRepository courseJdbcRepository;
    private CourseJpaRepository courseJpaRepository;

    @Autowired
    private CourseSpringDataJpaRepository springDataJpaRepository;

    public CourseJdbcCliRunner(CourseJdbcRepository courseJdbcRepository, CourseJpaRepository courseJpaRepository) {
        this.courseJdbcRepository = courseJdbcRepository;
        this.courseJpaRepository = courseJpaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        testJdbcRepository();
        testJpaRepository();
        testSpringDataJpaRepository();
    }

    private void testJdbcRepository() {
        this.courseJdbcRepository.insert(new Course(1, "Learn SP", "udemy"));
        this.courseJdbcRepository.insert(new Course(2, "Learn AWS", "udemy"));
        this.courseJdbcRepository.insert(new Course(3, "Learn CI/CD", "udemy"));

        this.courseJdbcRepository.deleteById(2L);

        Course c = this.courseJdbcRepository.findById(3L);
        System.out.println(c);
    }

    private void testJpaRepository() {
        this.courseJpaRepository.insert(new CourseEntity(5, "Learn SP", "udemy"));
        this.courseJpaRepository.insert(new CourseEntity(6, "Learn AWS", "udemy"));
        this.courseJpaRepository.insert(new CourseEntity(7, "Learn CI/CD", "udemy"));

        this.courseJpaRepository.deleteById(6L);

        CourseEntity c = this.courseJpaRepository.findById(7L);
        System.out.println(c);
    }

    private void testSpringDataJpaRepository() {
        this.springDataJpaRepository.save(new CourseEntity(10, "Learn SP", "udemy SJPA"));
        this.springDataJpaRepository.save(new CourseEntity(11, "Learn AWS", "udemy SJPA"));
        this.springDataJpaRepository.save(new CourseEntity(12, "Learn CI/CD", "udemy SJPA"));

        this.springDataJpaRepository.deleteById(11L);

        Optional<CourseEntity> c = this.springDataJpaRepository.findById(12L);
        System.out.println(c);

        List<CourseEntity> allCourses = this.springDataJpaRepository.findAll();

        System.out.println(allCourses);
        System.out.println(this.springDataJpaRepository.count());

        System.out.println(this.springDataJpaRepository.findByAuthor("udemy SJPA"));
        System.out.println(this.springDataJpaRepository.findByAuthor(""));
    }

}
