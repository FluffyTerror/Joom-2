package com.FluffyTerror.Joom2.service.cart;

import com.FluffyTerror.Joom2.model.CartItem;

public interface ICartItemService {
    void addItemToCart(Long cartId,Long productId,int quantity);
    void removeItemFromCart(Long cartId,Long productId);

    CartItem getCartItem(Long cartId, Long productId);

    void updateItemQuantity(Long cartId, Long productId, int  quantity);


}
