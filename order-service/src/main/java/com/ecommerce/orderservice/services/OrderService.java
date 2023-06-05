package com.ecommerce.orderservice.services;

import com.ecommerce.orderservice.models.Order;
import com.ecommerce.orderservice.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utilities.dtos.AppClientResponse;
import utilities.dtos.ProductResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order getOneOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public HashMap<String, String> deleteOrder(Long Id) {
        HashMap<String, String> message = new HashMap<>();
        try {
            orderRepository.deleteById(Id);
            message.put("state", "order deleted");
            return message;
        } catch (Exception e) {
            message.put("state", "order not deleted");
            return message;
        }
    }

    public Order saveOrder(Order o, Long idclient) {
        o.setClientId(idclient);
        return orderRepository.save(o);
    }

    public Order saveOrder(Order order, List<Long> ids, Long idclient) {
        List<ProductResponse> products = webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/product/all")
                .retrieve()
                .bodyToFlux(ProductResponse.class)
                .collectList()
                .block();

        for (int i = 0; i < ids.size(); i++) {
            int finalI = i;
            if (products.stream().filter(p -> p.getId() == ids.get(finalI)).findFirst().orElse(null) != null)
                order.getProductIdsList().add(ids.get(i));
        }

        AppClientResponse appClientResponse = webClientBuilder.build()
                .get()
                .uri("http://localhost:8082/client/getone/" + idclient)
                .retrieve()
                .bodyToMono(AppClientResponse.class)
                .block();

        if (appClientResponse != null) {
            order.setClientId(idclient);
            return orderRepository.save(order);
        } else {
            throw new RuntimeException("FAIL!");
        }
    }

    public Order updateOrder(Order order, Long Id) {
        Order o1 = orderRepository.findById(Id).orElse(null);
        if (o1 != null) {
            order.setId(Id);
            order.setClientId(o1.getClientId());
            return orderRepository.saveAndFlush(order);
        } else {
            throw new RuntimeException("FAIL!");
        }
    }

}
