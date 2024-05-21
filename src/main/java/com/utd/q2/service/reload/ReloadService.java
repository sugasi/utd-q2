package com.utd.q2.service.reload;

import com.utd.q2.data.entity.Customer;
import com.utd.q2.data.entity.Order;
import com.utd.q2.data.entity.Product;
import com.utd.q2.data.repository.CustomerRepository;
import com.utd.q2.data.repository.OrderRepository;
import com.utd.q2.data.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Service
@AllArgsConstructor
@Slf4j
public class ReloadService {
    /**
     * Not supposed to use the repositories directly here.
     *
     * We have to create a delegator layer and expose repositories to delegator layer only
     * and expose such delegators to public with intended functionalities only.
     */
    private final ProductRepository productRepository;

    private final CustomerRepository customerRepository;

    private final OrderRepository orderRepository;

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public boolean reload() {
        File file= null;    //creates a new file instance
        try {
            file = new ClassPathResource(
                    "data.csv").getFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileReader fr= null;   //reads the file
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader br=new BufferedReader(fr);
        try {
            br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        br.lines().forEach(this::persist);
        return true;
    }

    private void persist(String data) {
        String[] values = data.split(",", 15);
//        Arrays.stream(values).forEach(System.out::println);
        Customer customer = new Customer(values[2], values[12], values[13], values[14]);
        Product product = new Product(values[1], values[3], values[4], Double.parseDouble(values[8]));
        Order order = new Order(Integer.parseInt(values[0]), customer, product, LocalDate.parse(values[6], formatter), values[5], Integer.parseInt(values[7]), Double.parseDouble(values[9]), Double.parseDouble(values[10]), values[11]);
        log.info("{}{}{}", customer, product, order);
        customerRepository.save(customer);
        productRepository.save(product);
        orderRepository.save(order);
    }
}
