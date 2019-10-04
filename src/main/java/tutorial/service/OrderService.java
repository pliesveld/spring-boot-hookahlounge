package tutorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import tutorial.domain.Order;
import tutorial.repository.OrderRepository;
import tutorial.repository.ProductRepository;

@Service
@Transactional
public class OrderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders(Sort sort) {
        return new ArrayList<>(orderRepository.findAll(sort));
    }

    public List<Order> getAllOrders() {
        return getAllOrders(Sort.by(Sort.Order.desc("orderTime")));
    }

    

}
