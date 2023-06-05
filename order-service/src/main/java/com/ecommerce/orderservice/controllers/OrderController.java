package com.ecommerce.orderservice.controllers;

import com.ecommerce.orderservice.models.Order;
import com.ecommerce.orderservice.repositories.OrderRepository;
import com.ecommerce.orderservice.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/Order")
@CrossOrigin("*")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/save")
    public Order createOrder(@RequestBody Order o) {
        return orderService.createOrder(o);
    }

    @GetMapping("/getone/{id}")
    public Order getOneOrder(@PathVariable Long id) {
        return orderService.getOneOrder(id);
    }


    @DeleteMapping("/delete/{Id}")
    public HashMap<String, String> deleteOrder(@PathVariable Long Id) {
        return orderService.deleteOrder(Id);
    }

    @PostMapping("/save/{idclient}")
    public Order saveOrder(@RequestBody Order o, @PathVariable Long idclient) {
        return orderService.saveOrder(o, idclient);
    }

    @PostMapping("/save1/{idclient}")
    public Order saveOrder(@RequestBody Order order, @RequestParam List<Long> ids, @PathVariable Long idclient) {
        return orderService.saveOrder(order, ids, idclient);
    }

    @PutMapping("/update/{Id}")
    public Order updateOrder(@RequestBody Order order, @PathVariable Long Id) {
        return orderService.updateOrder(order, Id);
    }
}