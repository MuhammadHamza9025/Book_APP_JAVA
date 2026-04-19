package models;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "books")
public class BookList {
    private List<Book> books;

    public BookList() {
        this.books = new ArrayList<>();
    }

    public BookList(List<Book> books) {
        this.books = books;
    }

    @XmlElement(name = "book")
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}