package com.adobe.bookstore.service.impl;

import com.adobe.bookstore.model.Book;
import com.adobe.bookstore.model.BookOrder;
import com.adobe.bookstore.model.BookStock;
import com.adobe.bookstore.repository.BookOrderRepository;
import com.adobe.bookstore.repository.BookRepository;
import com.adobe.bookstore.repository.BookStockRepository;
import com.adobe.bookstore.service.BookOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * BookOrder Service Implementation class
 * Extends BookOrderService Interface
 *
 * @author Guillermo Serraclara
 */
@Service
public class BookOrderServiceImpl implements BookOrderService {
    //Instance of the BookOrderRepository
    @Autowired
    BookOrderRepository bookOrderRepository;
    //Instance of the BookStockRepository
    @Autowired
    BookStockRepository bookStockRepository;
    //Instance of the BookRepository
    @Autowired
    BookRepository bookRepository;
    /**
     * Override Method from BookOrderService that checks current BookStock
     * taking into consideration the amount of each book ordered
     * @param  order List of BookStock that is ordered
     * @return String with the response due the book current Stock
     * */
    @Override
    public String checkStock(List<BookStock> order) {
        //Loop for each book in the order
        for (BookStock book: order) {
            //check if the book is not registered on the database
            if(!bookStockRepository.existsById(book.getId())){
                return "Book not Found";
            }else{
                //In case it exists we get the instance as stock
                BookStock stock = bookStockRepository.findById(book.getId()).get();
                // we get the stock value
                int q = stock.getQuantity();
                // check if stock value is less than the stock asked
                // or if the stock asked is zero
                // in that case we send a Lack of Book Stock message
                if (q < book.getQuantity() || book.getQuantity() == 0) return "Lacking Book Stock";
            }
        }
        return "";
    }
    /**
     * Override Method from BookOrderService that updates the current BookStock
     * taking into consideration the amount of each book ordered
     * @param  order List of BookStock that is ordered
     * */
    @Override
    public void updateQuantity(List<BookStock> order) {
        //Loop for each book in order
        for (BookStock book: order) {
            //We get the instance of the book in the BookStockRepository
            BookStock stock = bookStockRepository.findById(book.getId()).get();
            //We update the current stock with the given demand of that book
            stock.setQuantity(stock.getQuantity() - book.getQuantity());
            //We save the changes in the repository
            bookStockRepository.save(stock);
        }
    }
    /**
     * Override Method from BookOrderService that registers the new Order
     * @param order List of BookStock that is ordered
     * @return ResponseEntity with a List of Books that were ordered
     * */
    @Override
    public ResponseEntity<BookOrder> createOrder(List<BookStock> order) {
        //First we check if there is any problem with the order
        String response = checkStock(order);
        if(response.equals("")){
            //In case there is no problem we update the stock
            updateQuantity(order);
            //Create the instance of the order
            BookOrder bookOrder = new BookOrder();
            //Make a list of books
            List<Book> command = new ArrayList<>();
            //Loop for each book in the order
            for (BookStock book: order) {
                //loop for each same book ordered
                for(int i=0;i<book.getQuantity();i++){
                    //If the book is not registered we register it
                    if(!bookRepository.existsById(book.getId())) bookRepository.save(new Book(book.getId(),book.getName()));
                    //save the book in the command order
                    command.add(new Book(book.getId(),book.getName()));
                }
            }
            //save the commanded book
            bookOrder.setBooklist(command);
            //save the order in the repository
            bookOrderRepository.save(bookOrder);
            //Return the order accepted
            return new ResponseEntity(bookOrder,HttpStatus.ACCEPTED);
        }
        // If there is a problem, we send the informative message right away
        return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
    }
    /**
     * Override Method from BookOrderService that gets all the current registered orders
     * @return ResponseEntity with a List of Books that were ordered
     * */
    @Override
    public ResponseEntity<BookOrder> getOrders() {
        //Get all the current Orders that were registered
        List<BookOrder> orders = bookOrderRepository.findAll();
        //Return the ResponseEntity with the list
        return new ResponseEntity(orders,HttpStatus.ACCEPTED);
    }
}
