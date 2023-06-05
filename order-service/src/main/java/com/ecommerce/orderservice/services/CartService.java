package com.ecommerce.orderservice.services;

import com.ecommerce.orderservice.models.Cart;
import com.ecommerce.orderservice.repositories.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public List<Cart> getAll(){
        return cartRepository.findAll() ;    }

    public Cart createCart ( Cart cart){
        return cartRepository.save(cart);
    }

    public void deleteCart (Long id) {
        cartRepository.deleteById(id);
    }
    public Cart updateCart ( Cart cart,  Long id){
        Cart p1 = cartRepository.findById(id).orElse(null);
        if (p1 !=null){
            cart.setId(id);
            return cartRepository.saveAndFlush(cart);
        }
        else{
            throw new RuntimeException("fail");
        }
    }
}
