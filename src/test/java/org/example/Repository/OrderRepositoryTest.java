package org.example.Repository;

import org.example.Model.Order;

import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class OrderRepositoryTest {


    @Test
    void saveOrder() {
        //given

        //when
        Order order = new Order("1;2;3;4");

        //then
        assertEquals("1" + ";" + "2" + ";" + "3" + ";" + "4" + "\n", order.toString());
        assertNotEquals("1;2;3;4;\n", order.toString());
    }


    @Test
    void listOrder() throws IOException {
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


    @Test
    void findOrderById() {
        //given

        //when
        Order order = new Order("2;3;4;5");


        //then
        assertEquals(2, order.getOrderId());
        assertNotEquals(3, order.getOrderId());
    }
}