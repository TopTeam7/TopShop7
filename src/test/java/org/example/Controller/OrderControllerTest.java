package org.example.Controller;

import org.example.Model.Order;
import org.example.Repository.OrderRepository;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderControllerTest {

    OrderRepository orderRepository = new OrderRepository();


    @Test
    void showOrders() throws IOException {
        //given
        Order order = new Order("2;3;4;5");
        Path path = Path.of("src/test/testresources/orders.txt");
        //when

        Files.write(path, order.toString().getBytes());
        List<String> expect = new ArrayList<>();
        expect.add("2;3;4;5");

        //then
        assertEquals(expect, Files.readAllLines(path));
    }


}