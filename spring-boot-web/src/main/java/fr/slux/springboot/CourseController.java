package fr.slux.springboot;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CourseController {

    @RequestMapping("/courses")
    public List<Course> retrieveAllCourses() {
        return Arrays.asList(
                new Course(1, "Learn AWS", "28min"),
                new Course(2, "Learn DevOps", "28min"),
                new Course(3, "Learn CI/CD", "slux")
        );
    }

    @RequestMapping("/ping")
    public Boolean ping() {
        return true;
    }
}
