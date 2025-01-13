package org.example.Service;

import org.example.Model.Customer;
import org.example.Model.CustomerType;
import org.example.Repository.CustomerRepository;
import org.example.exception.CustomerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    private CustomerRepository repository;
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(CustomerRepository.class);
        customerService = new CustomerService(repository);
    }

    @Test
    void testAddCustomer() {
        // Вызываем метод, который тестируем
        customerService.addCustomer("Иван", "NEW");

        // Проверяем, что метод addCustomer в репозитории был вызван ровно один раз
        verify(repository, times(1)).addCustomer(any(Customer.class));
    }

    @Test
    void testFindCustomerById() {
        // Создаем тестового покупателя
        Customer customer = new Customer(1, "Иван", CustomerType.NEW);

        // Настраиваем mock-объект, чтобы он возвращал тестового покупателя
        when(repository.loadCustomers()).thenReturn(Arrays.asList(customer));

        try {
            // Вызываем метод, который тестируем
            Customer found = customerService.findCustomerById(1);

            // Проверяем, что найденный покупатель совпадает с ожидаемым
            assertEquals(customer, found);
        } catch (CustomerNotFoundException e) {
            fail("Покупатель должен быть найден.");
        }
    }

    @Test
    void testFindCustomerByIdNotFound() {
        // Настраиваем mock-объект, чтобы он возвращал пустой список
        when(repository.loadCustomers()).thenReturn(Arrays.asList());

        // Проверяем, что метод выбрасывает исключение CustomerNotFoundException
        assertThrows(CustomerNotFoundException.class, () -> {
            customerService.findCustomerById(1);
        });
    }
}