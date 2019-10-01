package com.stackroute.userservice.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Movie {
    @Id
    private Integer id;
    private String title;
    private String overview;
    private String original_language;
//    private List<Integer> genre_ids;

    public Movie() {
    }
    public Movie(Integer id, String title, String overview, String original_language) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.original_language = original_language;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }


    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", original_language='" + original_language + '\'' +
                '}';
    }
}
