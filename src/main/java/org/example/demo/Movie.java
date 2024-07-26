package org.example.demo;

public class Movie {
    private String title;
    private String year;
    private String genre;
    private String director;
    private String imdbRating;
    private String rottenTomatoesRating;

    // Getter und Setter
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public String getImdbRating() {
        return imdbRating;
    }
    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }
    public String getRottenTomatoesRating() {
        return rottenTomatoesRating;
    }
    public void setRottenTomatoesRating(String rottenTomatoesRating) {
        this.rottenTomatoesRating = rottenTomatoesRating;
    }
}
