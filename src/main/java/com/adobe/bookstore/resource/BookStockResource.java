package com.adobe.bookstore.resource;

import com.adobe.bookstore.model.BookStock;
import com.adobe.bookstore.service.BookStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * BookStockResource class
 * Its function is to manage
 * All possible interaction with the BookStock entity from outside
 *
 * @author Guillermo Serraclara
 */
@RestController
@RequestMapping("/books_stock/")
public class BookStockResource {

    //Instance of the respective class service
    @Autowired
    private BookStockService bookStockService;

    /**
     * The function gets called through an HTTP request
     * using the following structure BASE_URL/books_stock/bookId
     * @param bookId id of the book asked through the GET request
     * @return ResponseEntity that is sent to the function caller
     */
    @GetMapping("{bookId}")
    public ResponseEntity<BookStock> getStockById(@PathVariable String bookId) {
        return bookStockService.getBook(bookId);
    }
}
