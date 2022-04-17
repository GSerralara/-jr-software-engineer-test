package com.adobe.bookstore.service.impl;


import com.adobe.bookstore.model.BookStock;
import com.adobe.bookstore.repository.BookStockRepository;
import com.adobe.bookstore.service.BookStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
/**
 * BookStock Service Implementation class
 * Extends BookStockService Interface
 *
 * @author Guillermo Serraclara
 */
@Service
public class BookStockServiceImpl implements BookStockService {
    //Instance of the BookStockRepository
    @Autowired
    BookStockRepository bookStockRepository;
    /**
     * Override Method from BookStockService that searches the book in the repository
     * @param  bookID String identifier of the BookStock.
     * @return ResponseEntity with the BookStock in case it exists.
     * */
    @Override
    public ResponseEntity<BookStock> getBook(String bookID){
        return bookStockRepository.findById(bookID)
                .map(bookStock -> ResponseEntity.ok(bookStock))
                .orElse(ResponseEntity.notFound().build());
    }
}
