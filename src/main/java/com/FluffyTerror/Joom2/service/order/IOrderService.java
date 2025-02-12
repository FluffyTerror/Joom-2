package com.FluffyTerror.Joom2.service.order;

import com.FluffyTerror.Joom2.dto.OrderDto;
import com.FluffyTerror.Joom2.model.Order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Long userId);
    OrderDto getOrder(Long orderId);

    List<OrderDto> getUserOrders(Long userId);
}
