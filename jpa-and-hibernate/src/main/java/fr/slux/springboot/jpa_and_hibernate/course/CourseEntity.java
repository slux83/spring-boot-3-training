package fr.slux.springboot.jpa_and_hibernate.course;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// Can't use Java records with JPA!!!
@Entity
public class CourseEntity {
    @Id
    private long id;

    // Name matches the column, so not mandatory
    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    public CourseEntity(long id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public CourseEntity() {
    }

    @Override
    public String toString() {
        return "CourseEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
