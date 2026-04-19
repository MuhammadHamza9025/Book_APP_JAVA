package models;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlElement(name = "id")
    private int id;

    @XmlElement(name = "title")
    private String title;

    @XmlElement(name = "author")
    private String author;

    @XmlElement(name = "date")
    private String date;

    @XmlElement(name = "genres")
    private String genres;

    @XmlElement(name = "characters")
    private String characters;

    @XmlElement(name = "synopsis")
    private String synopsis;

    public Book() {
    }

    public Book(int id, String title, String author, String date, String genres, String characters, String synopsis) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.date = date;
        this.genres = genres;
        this.characters = characters;
        this.synopsis = synopsis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    @Override
    public String toString() {
        return "Book[" + id + "] " + title + " by " + author + " (" + date + ") - Genres: " + genres + " | Characters: "
                + characters + " | Synopsis: " + synopsis;
    }
}