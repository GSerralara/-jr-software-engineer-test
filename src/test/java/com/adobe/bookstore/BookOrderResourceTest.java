package com.adobe.bookstore;

import com.adobe.bookstore.model.BookStock;
import com.adobe.bookstore.resource.BookOrderResource;
import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookOrderResourceTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BookOrderResource bookOrderResource;

    @Test
    @Order(1)
    public void contextLoads() {
        Assertions.assertThat(bookOrderResource).isNotNull();
    }

    @Test
    @Order(2)
    public void checkEmptyOrderList(){
        String urlQuery = "http://localhost:"+ port +"/orders/";
        ResponseEntity<String> responseEntity = restTemplate
                .exchange(urlQuery, HttpMethod.GET,null,
                        new ParameterizedTypeReference<String>() {
        });
        String responseEntityBody = responseEntity.getBody();
        assertThat(responseEntityBody).isEqualTo("[]");
    }

    @Test
    @Order(3)
    public void checkWhenProductDoesNotExists(){
        String urlQuery = "http://localhost:"+ port +"/orders/saveOrder";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        List<BookStock> order = new ArrayList<>();

        order.add(new BookStock("22d580fc-d02e-4f70-9980-f9693c18f6e0",
                "dolore aliqua sint ipsum laboris",2));
        order.add(new BookStock("12312412-23124124123","Random",3));

        HttpEntity<List<BookStock>> request = new HttpEntity<>(order,headers);

        ResponseEntity<String> responseEntity = restTemplate
                .exchange(urlQuery, HttpMethod.POST,request,
                        new ParameterizedTypeReference<String>() {
                        });
        String responseEntityBody = responseEntity.getBody();
        assertThat(responseEntityBody).isEqualTo("Book not Found");
    }


    @Test
    @Order(4)
    public void checkWhenProductStockIsEmptyOrOverDemand(){
        String urlQuery = "http://localhost:"+ port +"/orders/saveOrder";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        List<BookStock> order = new ArrayList<>();

        order.add(new BookStock("22d580fc-d02e-4f70-9980-f9693c18f6e0",
                "dolore aliqua sint ipsum laboris",7));
        order.add(new BookStock("302bbd26-2d64-40f1-9f53-dd3a6e858e05", "do esse amet dolor Lorem", 3));

        HttpEntity<List<BookStock>> request = new HttpEntity<>(order,headers);

        ResponseEntity<String> responseEntity = restTemplate
                .exchange(urlQuery, HttpMethod.POST,request,
                        new ParameterizedTypeReference<String>() {
                        });
        String responseEntityBody = responseEntity.getBody();
        assertThat(responseEntityBody).isEqualTo("Lacking Book Stock");
    }

    @Test
    @Order(5)
    public void makeAnOrder(){
        String urlQuery = "http://localhost:"+ port +"/orders/saveOrder";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        List<BookStock> order = new ArrayList<>();

        order.add(new BookStock("22d580fc-d02e-4f70-9980-f9693c18f6e0",
                "dolore aliqua sint ipsum laboris",2));

        HttpEntity<List<BookStock>> request = new HttpEntity<>(order,headers);

        ResponseEntity<String> responseEntity = restTemplate
                .exchange(urlQuery, HttpMethod.POST,request,
                        new ParameterizedTypeReference<String>() {
                        });
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.ACCEPTED);
    }

    @Test
    @Order(6)
    public void checkStockUpdate() throws JSONException {
        String urlQuery = "http://localhost:"+ port +"/orders/saveOrder";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        List<BookStock> order = new ArrayList<>();

        order.add(new BookStock("8d80c009-b3be-4d9d-95ba-cec1e7a2d52b",
                "Lorem sint adipisicing consectetur anim", 1));

        HttpEntity<List<BookStock>> request = new HttpEntity<>(order,headers);

        ResponseEntity<String> responseEntity = restTemplate
                .exchange(urlQuery, HttpMethod.POST,request,
                        new ParameterizedTypeReference<String>() {
                        });
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.ACCEPTED);
        var result = restTemplate.getForObject(
                "http://localhost:" + port + "/books_stock/8d80c009-b3be-4d9d-95ba-cec1e7a2d52b",
                BookStock.class);

        assertThat(result.getQuantity()).isEqualTo(0);
    }

    @Test
    @Order(7)
    public void checkOrderList(){
        String urlQuery = "http://localhost:"+ port +"/orders/";
        ResponseEntity<String> responseEntity = restTemplate
                .exchange(urlQuery, HttpMethod.GET,null,
                        new ParameterizedTypeReference<String>() {
                        });
        String responseEntityBody = responseEntity.getBody();
        assertThat(responseEntityBody).isNotEqualTo("[]");
    }
}
