package com.adobe.bookstore.repository;

import com.adobe.bookstore.model.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Interface that connects to database and manage all information
 * Extends JpaRepository with BookOrder as Template and a String as ID
 *
 * @author Guillermo Serraclara
 */
public interface BookOrderRepository extends JpaRepository<BookOrder, String> {
}
