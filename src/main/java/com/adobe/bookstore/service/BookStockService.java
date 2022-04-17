package com.adobe.bookstore.service;

import com.adobe.bookstore.model.BookStock;
import org.springframework.http.ResponseEntity;
/**
 * Interface were all logic methods are declared
 * regarding BookStock Operations
 *
 * @author Guillermo Serraclara
 */
public interface BookStockService {
    /**
     * Method responsible in searching through the repository the asked book
     * @param bookID that has to be searched in the repository
     * @return ResponseEntity with the BookStock associated to the call
     */
    ResponseEntity<BookStock> getBook(String bookID);
}
