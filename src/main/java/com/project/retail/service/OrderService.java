package com.project.retail.service;

import com.project.retail.model.Order;
import com.project.retail.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.retail.kafka.KafkaProducerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

@Service
public class OrderService {

    @Autowired private OrderRepository orderRepository;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    private final ObjectMapper objectMapper = new ObjectMapper();
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

//    public Order saveOrder(Order order){
//        return  orderRepository.save(order);
//    }

    public Order saveOrder(Order order) {
        // Save the order in the database
        Order savedOrder = orderRepository.save(order);


        try {
            System.out.println("REQUEST IS HERE");
            String orderJson = objectMapper.writeValueAsString(savedOrder);

            kafkaProducerService.sendOrderToKafka(orderJson);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return savedOrder;
    }
}
