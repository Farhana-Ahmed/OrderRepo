package com.project.retail.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "orders-topic", groupId = "order-consumer-group")
    public void listen(String orderMessage) {
        // Process the order (e.g., update inventory, send notification)
        System.out.println("Received order: " + orderMessage);
    }
}
