package com.adobe.bookstore.repository;

import com.adobe.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Interface that connects to database and manage all information
 * Extends JpaRepository with Book as Template and a String as ID
 *
 * @author Guillermo Serraclara
 */
public interface BookRepository extends JpaRepository<Book, String> {
}
