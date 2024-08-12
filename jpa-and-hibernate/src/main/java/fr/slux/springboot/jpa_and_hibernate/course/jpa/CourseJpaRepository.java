package fr.slux.springboot.jpa_and_hibernate.course.jpa;

import fr.slux.springboot.jpa_and_hibernate.course.CourseEntity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CourseJpaRepository {

    private EntityManager entityManager;

    public CourseJpaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void insert(CourseEntity course) {
        this.entityManager.merge(course);
    }

    public CourseEntity findById(long id) {
        return this.entityManager.find(CourseEntity.class, id);
    }

    public void deleteById(long id) {
        CourseEntity c = this.entityManager.find(CourseEntity.class, id);
        entityManager.remove(c);
    }


}
