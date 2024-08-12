package fr.slux.springboot.jpa_and_hibernate.course.jdbc;

import fr.slux.springboot.jpa_and_hibernate.course.Course;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseJdbcRepository {

    private static String INSERT_QUERY =
            """
                            INSERT INTO course (id, name, author)
                            VALUES (?, ?, ?);
                    """;

    private static String DELETE_QUERY =
            """
                    DELETE FROM course 
                    WHERE id = ?
                    """;

    public static String SELECT_BY_ID_QUERY =
            """
                    SELECT * FROM course
                    WHERE id = ?
                    """;

    private JdbcTemplate jdbcTemplate;

    public CourseJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(Course course) {
        this.jdbcTemplate.update(INSERT_QUERY,
                course.id(), course.name(), course.author());
    }

    public void deleteById(long id) {
        this.jdbcTemplate.update(DELETE_QUERY, id);
    }

    public Course findById(long id) {
        return this.jdbcTemplate.queryForObject(SELECT_BY_ID_QUERY,
                new DataClassRowMapper<>(Course.class), id);
    }


}
