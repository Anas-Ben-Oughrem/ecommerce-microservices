package com.ecommerce.orderservice.controllers;

import com.ecommerce.orderservice.models.Cart;
import com.ecommerce.orderservice.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Cart")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public List<Cart> getAll(){
        return cartService.getAll() ;    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cart createCart (@RequestBody Cart cart){
        return cartService.createCart(cart);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCart (@PathVariable Long id) {
        cartService.deleteCart(id);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cart updateCart (@RequestBody Cart cart, @PathVariable Long id){
        return cartService.updateCart(cart,id);
    }
}
