package com.adobe.bookstore.service;

import com.adobe.bookstore.model.BookOrder;
import com.adobe.bookstore.model.BookStock;
import org.springframework.http.ResponseEntity;

import java.util.List;
/**
 * Interface were all logic methods are declared
 * regarding the BookOrder operations
 *
 * @author Guillermo Serraclara
 */
public interface BookOrderService {
    /**
     * Method responsible in checking if there is enough stock to upfront the upcoming demand
     * @param order which stock has to be checked in the repository
     * @return String with the Stock state of the ordered items
     */
    public String checkStock(List<BookStock> order);
    /**
     * Method responsible in updating the current stock taking in consideration the demanded products
     * @param order that list the books stock to be updated in the repository
     */
    public void updateQuantity(List<BookStock> order);
    /**
     * Method responsible in crating the new BookOrder
     * @param order that contains the list to be registered in the repository
     * @return ResponseEntity with the BookOrder associated to the call
     */
    public ResponseEntity<BookOrder> createOrder(List<BookStock> order);
    /**
     * Method responsible in returning all the current BookOrders
     * @return ResponseEntity with the current BookOrders
     */
    public ResponseEntity<BookOrder> getOrders();
}
