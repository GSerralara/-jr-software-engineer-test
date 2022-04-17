package com.adobe.bookstore.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
/**
 * Entity BookStock class
 *
 */
@Entity
@Table(name = "book_stock")
@JsonSerialize
public class BookStock {
    /**
     * Columns of the table book_stock
     */
    // ID field of the table, it cannot be null
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    // Name field of the table, it cannot be null
    @Column(name = "name", nullable = false)
    private String name;

    // Quantity field of the table, it cannot be null
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    /**
     * Constructor by default of the class.
     * */
    public BookStock() {
    }

    /**
     * Constructor of the class.
     * @param id it is the book's universal unique identifier
     * @param name it represents the name of the book
     * @param quantity it represents the stock of the book
     * */
    public BookStock(String id, String name, Integer quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
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

    /**
     * Name setter method
     * @return Integer that represents the stock of the book
     * */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Quantity setter method
     * @param quantity it represents the stock of the book
     * */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
