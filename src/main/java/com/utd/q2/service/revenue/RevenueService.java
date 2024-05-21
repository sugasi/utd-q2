package com.utd.q2.service.revenue;

import com.utd.q2.data.repository.OrderRepository;
import com.utd.q2.data.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@AllArgsConstructor
public class RevenueService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    public double totalRevenue(LocalDate from, LocalDate to) {
        return orderRepository.findAllByOrderDateBetween(from, to)
                .parallelStream()
                .mapToDouble(order -> {
                    double mp = order.getQuantity() * order.getProduct().getPrice();
                    double sp = mp - order.getDiscount() * mp / 100.0;
                    return sp;
                })
                .sum();
    }

    public double totalRevenueByProduct(LocalDate from, LocalDate to, String productId) {
        if (productRepository.existsById(productId)) {
            return orderRepository.findAllByOrderDateBetweenAndProduct(from, to, productRepository.findById(productId).get())
                    .parallelStream()
                    .mapToDouble(order -> {
                        double mp = order.getQuantity() * order.getProduct().getPrice();
                        double sp = mp - order.getDiscount() * mp / 100.0;
                        return sp;
                    })
                    .sum();
        } else {
            return 0;
        }
    }
}
