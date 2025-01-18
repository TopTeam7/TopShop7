package org.example.Service;

import org.example.Controller.CustomerController;
import org.example.Model.Customer;
import org.example.Model.CustomerType;
import org.example.Repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {
    private CustomerService customerService;
    private Path pathForTest;
    CustomerRepository customerRepository;
    @BeforeEach
    void setUp()throws IOException {
        String customerPath = "src/test/java/customers.txt";
        String customerPathId = "src/test/java/customers_id.txt";
        customerRepository = new CustomerRepository(customerPath, customerPathId);
        customerService = new CustomerService(customerRepository);
    }

    @AfterEach
    void tearDown()throws IOException {
        Files.deleteIfExists(Path.of("src/test/java/customers.txt"));
        Files.deleteIfExists(Path.of("src/test/java/customers_id.txt"));
    }

    @Test
    void addCustomer() {
        //given
        String type = CustomerType.НОВЫЙ.toString();
        //when
        customerService.addCustomer("Иван" , type);
        //then
        List<Customer> customers = customerRepository.loadCustomers();
        assertEquals(1, customers.size());
        assertEquals("Иван" , customers.get(0).getName());
    }

    @Test
    void getAllCustomers() {
        //given
        customerService.addCustomer("Александр" , CustomerType.ПОСТОЯННЫЙ.toString());
        customerService.addCustomer("Иван" , CustomerType.VIP.toString());
        //when
        List<Customer> customers = customerService.getAllCustomers();
        //then
        assertEquals(2, customers.size());
        assertEquals("Александр" , customers.get(0).getName());
        assertEquals(CustomerType.VIP.toString(), customers.get(1).getType().toString());
    }

    @Test
    void findCustomerById() {
        //given
        customerService.addCustomer("Иван" , CustomerType.VIP.toString());
        //when
        Customer foundCustomer = customerService.findCustomerById(1);
        //then
        assertNotNull(foundCustomer);
        assertEquals("Иван" , foundCustomer.getName());
    }
}