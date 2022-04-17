package com.adobe.bookstore.repository;

import com.adobe.bookstore.model.BookStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * Interface that connects to database and manage all information
 * Extends JpaRepository with BookStock as Template and a String as ID
 *
 * @author Guillermo Serraclara
 */
@Repository
public interface BookStockRepository extends JpaRepository<BookStock, String> {
    /**
     * Method that retrieves a BookStock entity by its id.
     * @param bookId that has to be searched in the repository
     * @return Optional BookStock with the BookStock associated to the call
     */
    public Optional<BookStock> findById(String bookId);
}
