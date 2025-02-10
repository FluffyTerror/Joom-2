package com.FluffyTerror.Joom2.service.order;

import com.FluffyTerror.Joom2.model.Order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Long userId);
    Order getOrder(Long orderId);

    List<Order> getUserOrders(Long userId);
}
