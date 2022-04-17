package com.adobe.bookstore.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/**
 * Entity BookOrder class
 *
 * @author Guillermo Serraclara
 */
@Entity
@Table(name = "book_order")
@JsonSerialize
public class BookOrder {

    /**
     * Columns of the table book_order
     */

    // ID field of the table, it cannot be null
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    // A list of the books in the order
    // The relation is N-M because a book can be in more than 1 order
    @ManyToMany
    public List<Book> booklist = new ArrayList<>();

    /**
     * Constructor by default of the class.
     * it Generates automatically its UUID
     * */
    public BookOrder() {
        this.id = UUID.randomUUID().toString();
    }

    /**
     * ID getter method
     * @return A String that contains the book's universal unique identifier
     * */
    public String getId() {
        return id;
    }

    /**
     * ID setter method
     * @param id it is the book's universal unique identifier
     * */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * BookList getter method
     * @return A List of Books that contains the books of the order
     * */
    public List getBooklist() {
        return booklist;
    }

    /**
     * BookList setter method
     * @param booklist that contains the books of the order
     * */
    public void setBooklist(List<Book> booklist) {
        this.booklist = booklist;
    }

}
