package com.adobe.bookstore.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Entity Book class
 *
 * @author Guillermo Serraclara
 */
@Entity
@Table(name = "book")
@JsonSerialize
public class Book {

    /**
     * Columns of the table book
     */

    // ID field of the table, it cannot be null
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    // Name field of the table, it cannot be null
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Constructor by default of the class.
     * */
    public Book() {
    }

    /**
     * Constructor of the class.
     * @param id it is the book's universal unique identifier
     * @param name it represents the name of the book
     * */
    public Book(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Getters and setters of the attributes
     */

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
     * Name getter method
     * @return a String with the name of the book
     * */
    public String getName() {
        return name;
    }

    /**
     * Name setter method
     * @param name it represents the name of the book
     * */
    public void setName(String name) {
        this.name = name;
    }
}
