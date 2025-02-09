package com.FluffyTerror.Joom2.service.cart;

import com.FluffyTerror.Joom2.model.Cart;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);

    Long initializeNewCart();

    Cart getCartByUserId(Long userId);
}
