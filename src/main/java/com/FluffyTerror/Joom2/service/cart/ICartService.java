package com.FluffyTerror.Joom2.service.cart;

import com.FluffyTerror.Joom2.model.Cart;
import com.FluffyTerror.Joom2.model.User;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);

    Cart initializeNewCart(User user);

    Cart getCartByUserId(Long userId);
}
