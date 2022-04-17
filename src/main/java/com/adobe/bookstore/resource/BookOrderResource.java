package com.adobe.bookstore.resource;

import com.adobe.bookstore.model.BookOrder;
import com.adobe.bookstore.model.BookStock;
import com.adobe.bookstore.service.BookOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * BookOrderResource class
 * Its function is to manage
 * All possible interaction with the BookOrder entity from outside
 *
 * @author Guillermo Serraclara
 */
@RestController
@RequestMapping("/orders/")
public class BookOrderResource {

    //Instance of the respective class service
    @Autowired
    private BookOrderService bookOrderService;

    /**
     * The function gets called through an HTTP POST request
     * using the following structure BASE_URL/orders/saveOrder
     * Body must be given in JSON Format
     * @param bookOrder list of books asked to create an order given through the request body
     * @return ResponseEntity that is sent to the function caller
     */
    @RequestMapping(value="/saveOrder",method= RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookOrder> saveOrder(@RequestBody List<BookStock> bookOrder){
        return bookOrderService.createOrder(bookOrder);
    }

    /**
     * The function gets called through an HTTP GET request
     * using the following structure BASE_URL/orders/
     * @return ResponseEntity that is sent to the function caller
     */
    @GetMapping("")
    public ResponseEntity<BookOrder> getOrders() {
        return bookOrderService.getOrders();
    }
}
